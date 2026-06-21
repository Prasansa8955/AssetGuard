package com.tourism.assetguard.ui.screens.asset

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    assetViewModel: AssetViewModel,
    onNavigateBack: () -> Unit
) {
    val dark = isSystemInDarkTheme()
    val sites by assetViewModel.sites.collectAsState()
    val assets by assetViewModel.assets.collectAsState()
    val scope = rememberCoroutineScope()

    val initialPos = LatLng(6.9271, 79.8612) // Colombo Center
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPos, 11f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fleet Geolocation Shield", color = if (dark) SlateTextPrimary else LightTextPrimary, fontWeight = FontWeight.ExtraBold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = TealPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = if (dark) SlateBackground else LightBackground)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(if (dark) SlateBackground else LightBackground).padding(innerPadding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = true),
                properties = MapProperties(
                    isMyLocationEnabled = true, 
                    mapType = if(dark) MapType.HYBRID else MapType.NORMAL
                )
            ) {
                // Show Site Markers
                sites.forEach { site ->
                    val assetsAtSite = assets.count { it.currentSite == site.name }
                    Marker(
                        state = MarkerState(position = LatLng(site.latitude, site.longitude)),
                        title = site.name,
                        snippet = "$assetsAtSite critical units deployed.",
                        onClick = {
                            scope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(LatLng(site.latitude, site.longitude), 15f)
                                )
                            }
                            false
                        }
                    )
                }

                // Show Individual Asset Markers (for assets with GPS data)
                assets.filter { it.lastLatitude != 0.0 && it.lastLongitude != 0.0 }.forEach { asset ->
                    Marker(
                        state = MarkerState(position = LatLng(asset.lastLatitude, asset.lastLongitude)),
                        title = asset.name,
                        snippet = "Status: ${asset.status} | Condition: ${asset.condition}",
                        alpha = 0.7f,
                        onClick = {
                            scope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(LatLng(asset.lastLatitude, asset.lastLongitude), 17f)
                                )
                            }
                            false
                        }
                    )
                }
            }

            // ADVANCED OVERLAY: Operational Status
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(if (dark) SlateSurface.copy(alpha = 0.96f) else Color.White.copy(alpha = 0.96f))
                    .border(1.dp, TealPrimary.copy(alpha = 0.4f), RoundedCornerShape(28.dp))
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("GLOBAL DEPLOYMENT", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = TealPrimary, letterSpacing = 1.5.sp)
                            Text("${assets.size} Assets Tracker Active", fontSize = 19.sp, fontWeight = FontWeight.Black, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        }
                        IconButton(
                            onClick = { 
                                scope.launch {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(initialPos, 11f)
                                    )
                                }
                            }, 
                            modifier = Modifier.size(52.dp).background(TealPrimary, CircleShape)
                        ) {
                            Icon(Icons.Filled.MyLocation, null, tint = Color.White)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        StatusIndicator("SITES", sites.size.toString(), SuccessGreen)
                        StatusIndicator("MAINTENANCE", assets.count { it.status == "Maintenance" }.toString(), WarningAmber)
                        StatusIndicator("LOST", assets.count { it.status == "Lost" }.toString(), DangerRed)
                    }
                }
            }
        }
    }
}

@Composable
fun StatusIndicator(label: String, value: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(6.dp))
        Text("$label: $value", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if(isSystemInDarkTheme()) SlateTextSecondary else LightTextSecondary)
    }
}

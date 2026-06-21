package com.tourism.assetguard.ui.screens.asset

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourism.assetguard.data.local.Asset
import com.tourism.assetguard.data.local.Site
import com.tourism.assetguard.data.local.User
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.util.BarcodeScannerView
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditScreen(
    assetViewModel: AssetViewModel,
    user: User?,
    onNavigateBack: () -> Unit
) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val assets by assetViewModel.assets.collectAsState()
    val sitesList by assetViewModel.sites.collectAsState()

    var scannedAsset by remember { mutableStateOf<Asset?>(null) }
    var auditSiteName by remember { mutableStateOf("Warehouse HQ") }
    var physicalCondition by remember { mutableStateOf("Good") }
    var barcodeInputQuery by remember { mutableStateOf("") }
    
    var forceLocationMatch by remember { mutableStateOf(false) }

    var showCameraScanner by remember { mutableStateOf(false) }
    var expandedSiteMenu by remember { mutableStateOf(false) }
    var expandedConditionMenu by remember { mutableStateOf(false) }

    var liveLatitude by remember { mutableStateOf(0.0) }
    var liveLongitude by remember { mutableStateOf(0.0) }
    var liveTimestamp by remember { mutableStateOf(0L) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(6.9271, 79.8612), 14f)
    }

    LaunchedEffect(scannedAsset) {
        if (scannedAsset != null) {
            liveTimestamp = System.currentTimeMillis()
            val currentLoc = com.tourism.assetguard.util.LocationHelper.getCurrentLocation(context)
            if (currentLoc != null) {
                liveLatitude = currentLoc.latitude
                liveLongitude = currentLoc.longitude
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(LatLng(liveLatitude, liveLongitude), 16f)
                )
            }
            auditSiteName = scannedAsset?.currentSite ?: "Warehouse HQ"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Physical Asset Audit", color = if (dark) SlateTextPrimary else LightTextPrimary, fontWeight = FontWeight.Bold) },
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
            if (showCameraScanner) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BarcodeScannerView(
                        onBarcodeDetected = { scanned ->
                            scope.launch {
                                val found = assetViewModel.resolveAssetByBarcode(scanned)
                                if (found != null) {
                                    scannedAsset = found
                                    showCameraScanner = false
                                } else {
                                    Toast.makeText(context, "Entry mismatch", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    )
                    IconButton(onClick = { showCameraScanner = false }, modifier = Modifier.padding(16.dp).align(Alignment.TopEnd).clip(CircleShape).background(Color.Black.copy(0.6f))) {
                        Icon(Icons.Filled.Close, null, tint = Color.White)
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp, vertical = 10.dp)) {
                    if (scannedAsset == null) {
                        Text("Operational Verification", fontWeight = FontWeight.Black, fontSize = 24.sp, color = if(dark) SlateTextPrimary else LightTextPrimary)
                        Text("Validate units via optical scan or ledger override.", fontSize = 13.sp, color = if(dark) SlateTextSecondary else LightTextSecondary)
                        Spacer(modifier = Modifier.height(20.dp))

                        GlassyCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(modifier = Modifier.size(90.dp).clip(CircleShape).background(TealPrimary.copy(0.1f)).border(2.dp, TealPrimary, CircleShape), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Filled.QrCodeScanner, null, tint = TealPrimary, modifier = Modifier.size(44.dp))
                                }
                                Spacer(modifier = Modifier.height(24.dp))
                                LushPrimaryButton(text = "Initialize Optical Scan", onClick = { showCameraScanner = true })
                                
                                Spacer(modifier = Modifier.height(30.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    TourismTextField(value = barcodeInputQuery, onValueChange = { barcodeInputQuery = it }, label = "Enter S/N or ID", leadingIcon = Icons.Filled.Pin, modifier = Modifier.weight(1f))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                val found = assetViewModel.resolveAssetByBarcode(barcodeInputQuery)
                                                if (found != null) scannedAsset = found
                                                else Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        modifier = Modifier.size(54.dp).clip(RoundedCornerShape(12.dp)).background(TealPrimary)
                                    ) { Icon(Icons.Filled.Search, null, tint = Color.White) }
                                }
                            }
                        }
                    } else {
                        val target = scannedAsset!!
                        Text(text = "Auditing: ${target.name}", fontWeight = FontWeight.Black, fontSize = 22.sp, color = TealPrimary)
                        Spacer(modifier = Modifier.height(16.dp))

                        GlassyCard(modifier = Modifier.fillMaxWidth().height(240.dp)) {
                            GoogleMap(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState,
                                properties = MapProperties(isMyLocationEnabled = true)
                            ) {
                                if (liveLatitude != 0.0) {
                                    Marker(state = MarkerState(position = LatLng(liveLatitude, liveLongitude)), title = "Your Position")
                                }
                                val site = sitesList.find { it.name == auditSiteName }
                                site?.let {
                                    Circle(
                                        center = LatLng(it.latitude, it.longitude),
                                        radius = it.radiusMeters,
                                        fillColor = (if(forceLocationMatch) SuccessGreen else DangerRed).copy(alpha = 0.2f),
                                        strokeColor = if(forceLocationMatch) SuccessGreen else DangerRed,
                                        strokeWidth = 3f
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        GlassyCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    Icon(Icons.Filled.GpsFixed, null, tint = if(forceLocationMatch) SuccessGreen else WarningAmber)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text("Geospatial Validation", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if(dark) SlateTextPrimary else LightTextPrimary)
                                        Text(if(forceLocationMatch) "Hardware Override Active" else "Cross-referencing site data...", fontSize = 11.sp, color = if(dark) SlateTextSecondary else LightTextSecondary)
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedTextField(
                                        value = auditSiteName, onValueChange = {}, readOnly = true,
                                        label = { Text("Designated Site") }, modifier = Modifier.fillMaxWidth(),
                                        leadingIcon = { Icon(Icons.Filled.Place, null, tint = TealPrimary) },
                                        trailingIcon = { IconButton(onClick = { expandedSiteMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } }
                                    )
                                    DropdownMenu(expanded = expandedSiteMenu, onDismissRequest = { expandedSiteMenu = false }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                        sitesList.forEach { site -> 
                                            DropdownMenuItem(text = { Text(site.name) }, onClick = { auditSiteName = site.name; expandedSiteMenu = false }) 
                                        }
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedTextField(
                                        value = physicalCondition, onValueChange = {}, readOnly = true,
                                        label = { Text("Physical Condition") }, modifier = Modifier.fillMaxWidth(),
                                        leadingIcon = { Icon(Icons.Filled.HealthAndSafety, null, tint = TealPrimary) },
                                        trailingIcon = { IconButton(onClick = { expandedConditionMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } }
                                    )
                                    DropdownMenu(expanded = expandedConditionMenu, onDismissRequest = { expandedConditionMenu = false }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                        listOf("Excellent", "Good", "Fair", "Poor", "Broken Down").forEach { cond -> 
                                            DropdownMenuItem(text = { Text(cond) }, onClick = { physicalCondition = cond; expandedConditionMenu = false }) 
                                        }
                                    }
                                }
                                
                                Spacer(modifier = Modifier.height(20.dp))
                                
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { forceLocationMatch = !forceLocationMatch }) {
                                    Checkbox(checked = forceLocationMatch, onCheckedChange = { forceLocationMatch = it }, colors = CheckboxDefaults.colors(checkedColor = TealPrimary))
                                    Text("Force Authenticate (Location Override)", fontSize = 12.sp, color = if(dark) SlateTextPrimary else LightTextPrimary)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        if (assetViewModel.isLoading) {
                            CircularProgressIndicator(color = TealPrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                        } else {
                            LushPrimaryButton(text = "Validate & Lock Entry", onClick = {
                                val site = sitesList.find { it.name == auditSiteName } ?: Site("DEPOT", auditSiteName, "", liveLatitude, liveLongitude, 500.0)
                                val finalLat = if(forceLocationMatch) site.latitude else liveLatitude
                                val finalLng = if(forceLocationMatch) site.longitude else liveLongitude
                                
                                assetViewModel.performLocationVerifiedAudit(target, site, user?.name ?: "Auditor", physicalCondition, "Physical verification completed.", finalLat, finalLng, liveTimestamp) {
                                    Toast.makeText(context, "Audit Record Secured!", Toast.LENGTH_SHORT).show()
                                    scannedAsset = null
                                }
                            })
                            Spacer(modifier = Modifier.height(10.dp))
                            TextButton(onClick = { scannedAsset = null }, modifier = Modifier.fillMaxWidth()) {
                                Text("Discard Audit", color = CoralSecondary)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

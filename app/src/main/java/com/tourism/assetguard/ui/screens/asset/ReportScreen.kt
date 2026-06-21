package com.tourism.assetguard.ui.screens.asset

import androidx.compose.animation.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import java.util.Locale

// Vico Charts 2.0 Alpha API
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    assetViewModel: AssetViewModel,
    onNavigateBack: () -> Unit
) {
    val dark = isSystemInDarkTheme()

    val assets by assetViewModel.assets.collectAsState()
    val audits by assetViewModel.combinedAudits.collectAsState()
    
    val totalValuation = assetViewModel.getTotalValue()
    val totalMaintCost = assetViewModel.getMaintenanceTotalCost()
    val valuationBySite = assetViewModel.getValuationBySite()
    
    val healthScore = if (assets.isNotEmpty()) (assets.count { it.condition == "Excellent" || it.condition == "Good" }.toDouble() / assets.size * 100).toInt() else 0

    // Auditor Leaderboard Data (Cooperative Insight)
    val auditorStats = audits.groupBy { it.auditorName }.mapValues { it.value.size }.toList().sortedByDescending { it.second }.take(3)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text("Enterprise Analytics Hub", color = if (dark) SlateTextPrimary else LightTextPrimary, fontWeight = FontWeight.Black, fontSize = 20.sp)
                        Text("Real-time Intelligence & Team Performance", color = TealPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                },
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
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp, vertical = 10.dp)) {
                
                // --- FINANCIAL SUMMARY CARDS ---
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricCard("FLEET EQUITY", String.format(Locale.US, "$%,.0f", totalValuation), TealPrimary, Icons.Filled.AccountBalance, dark, Modifier.weight(1f))
                    MetricCard("HEALTH INDEX", "$healthScore%", SuccessGreen, Icons.Filled.Verified, dark, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --- TEAM PERFORMANCE (NEW COOPERATIVE FEATURE) ---
                Text("Field Auditor Leaderboard", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                GlassyCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        if (auditorStats.isEmpty()) {
                            Text("No audit records found.", fontSize = 13.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                        } else {
                            auditorStats.forEach { (name, count) ->
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                                    Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(TealPrimary), contentAlignment = Alignment.Center) {
                                        Text(name.take(1), color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(name, fontWeight = FontWeight.SemiBold, color = if(dark) SlateTextPrimary else LightTextPrimary, modifier = Modifier.weight(1f))
                                    Text("$count Audits", color = TealPrimary, fontWeight = FontWeight.Black)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --- GLOBAL FLEET MAP ---
                Text("Global Asset Tracking Matrix", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                
                GlassyCard(modifier = Modifier.fillMaxWidth().height(250.dp)) {
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(LatLng(6.9271, 79.8612), 10f)
                    }
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(mapType = MapType.HYBRID, isMyLocationEnabled = true)
                    ) {
                        assets.filter { it.lastLatitude != 0.0 }.forEach { asset ->
                            Marker(
                                state = rememberMarkerState(position = LatLng(asset.lastLatitude, asset.lastLongitude)),
                                title = asset.name,
                                snippet = "Condition: ${asset.condition}"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --- VALUATION GROWTH TREND ---
                Text("Asset Valuation Projections", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                
                GlassyCard(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.padding(16.dp).height(200.dp)) {
                        val model = CartesianChartModel(LineCartesianLayerModel.build { series(10, 15, 12, 24, 20, 35, 30, 45) })
                        CartesianChartHost(
                            chart = rememberCartesianChart(rememberLineCartesianLayer()),
                            model = model,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // --- SITE ALLOCATION ---
                Text("Geographical Capital Allocation", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                
                GlassyCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        valuationBySite.entries.forEach { entry ->
                            val percentage = if (totalValuation > 0) (entry.value / totalValuation).toFloat() else 0f
                            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(entry.key, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                                    Text(String.format(Locale.US, "$%,.0f", entry.value), fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TealPrimary)
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                LinearProgressIndicator(
                                    progress = { percentage },
                                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                                    color = TealPrimary,
                                    trackColor = if(dark) SlateSurface else Color.Black.copy(0.05f)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun MetricCard(title: String, value: String, accentColor: Color, icon: ImageVector, dark: Boolean, modifier: Modifier = Modifier) {
    GlassyCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(accentColor.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = accentColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(title, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = if (dark) SlateTextSecondary else LightTextSecondary, letterSpacing = 1.2.sp)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Black, color = if (dark) SlateTextPrimary else LightTextPrimary)
        }
    }
}

package com.tourism.assetguard.ui.screens.profile

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tourism.assetguard.data.local.Asset
import com.tourism.assetguard.data.local.User
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.viewmodel.AssetViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Vico Charts 2.0 Alpha API
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    assetViewModel: AssetViewModel,
    user: User?,
    onNavigateToDetails: (Asset) -> Unit,
    onNavigateToAddAsset: () -> Unit
) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current

    val assets by assetViewModel.assets.collectAsState()
    val activityLogs by assetViewModel.activityLogs.collectAsState()
    val maintenanceRequests by assetViewModel.maintenanceRequests.collectAsState()
    val usersList by assetViewModel.users.collectAsState()
    val isLoading = assetViewModel.isLoading

    var searchQuery by remember { mutableStateOf("") }
    var activeTab by remember { mutableStateOf("Directory") }
    var showFilterSheet by remember { mutableStateOf(false) }
    
    // Advanced Filter State
    var selectedCategory by remember { mutableStateOf("All") }

    val isManager = user?.role == "Manager"
    val totalValuation = assetViewModel.getTotalValue()
    
    val filteredAssets = remember(assets, searchQuery, selectedCategory) {
        assets.filter { asset ->
            (searchQuery.isBlank() || asset.name.contains(searchQuery, ignoreCase = true) || asset.serialNumber.contains(searchQuery, ignoreCase = true)) &&
            (selectedCategory == "All" || asset.category == selectedCategory)
        }
    }

    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        floatingActionButton = {
            if (isManager && activeTab == "Directory") {
                FloatingActionButton(
                    onClick = onNavigateToAddAsset, 
                    containerColor = TealPrimary, 
                    contentColor = Color.White, 
                    shape = RoundedCornerShape(16.dp),
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Filled.Add, "Register Asset")
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(if (dark) SlateBackground else LightBackground).padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize()) {
                
                // --- PREMIUM HEADER WITH MINI CHART ---
                HeaderWithInsights(user, totalValuation, dark)

                // --- TAB NAVIGATION (Animated) ---
                TabsNavigation(activeTab, dark) { activeTab = it }

                Spacer(modifier = Modifier.height(16.dp))

                // --- DYNAMIC CONTENT AREA ---
                AnimatedContent(
                    targetState = activeTab,
                    transitionSpec = {
                        (fadeIn(animationSpec = tween(300, delayMillis = 100)) + slideInHorizontally(initialOffsetX = { it / 2 }))
                            .togetherWith(fadeOut(animationSpec = tween(200)))
                    },
                    label = "MainContentTransition"
                ) { targetTab ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        when (targetTab) {
                            "Directory" -> {
                                SearchAndFilterBar(
                                    query = searchQuery,
                                    onQueryChange = { searchQuery = it },
                                    onFilterClick = { showFilterSheet = true },
                                    dark = dark
                                )
                                
                                if (isLoading && assets.isEmpty()) {
                                    ShimmerListLoader(dark)
                                } else {
                                    AssetList(filteredAssets, dark, onNavigateToDetails)
                                }
                            }
                            "Maintenance" -> MaintenanceDashboard(maintenanceRequests, dark)
                            "Security" -> SecurityIntelligence(activityLogs, dark)
                            "Employees" -> EmployeeManagement(usersList, dark)
                            "Batch Ingest" -> BatchImporter(assetViewModel, context) { activeTab = "Directory" }
                        }
                    }
                }
            }
        }

        // --- FILTER BOTTOM SHEET ---
        if (showFilterSheet) {
            ModalBottomSheet(
                onDismissRequest = { showFilterSheet = false },
                sheetState = sheetState,
                containerColor = if (dark) SlateSurface else Color.White,
                tonalElevation = 8.dp
            ) {
                FilterOptions(selectedCategory, dark) { 
                    selectedCategory = it
                    showFilterSheet = false 
                }
            }
        }
    }
}

@Composable
fun HeaderWithInsights(user: User?, valuation: Double, dark: Boolean) {
    GlassyCard(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        backgroundColor = if (dark) SlateSurface else Color.White
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "CORPORATE SHIELD", fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, color = TealPrimary, letterSpacing = 2.sp)
                Text(text = "Hello, ${user?.name?.split(" ")?.firstOrNull() ?: "User"}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "PORTFOLIO VALUATION", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = if (dark) SlateTextSecondary else LightTextSecondary)
                Text(text = String.format(Locale.US, "$%,.0f", valuation), fontSize = 24.sp, fontWeight = FontWeight.Black, color = if (dark) SlateTextPrimary else LightTextPrimary)
            }
            
            // Interactive Insight Chart
            Box(modifier = Modifier.size(110.dp, 70.dp)) {
                val model = CartesianChartModel(LineCartesianLayerModel.build { series(5, 12, 8, 16, 11, 20) })
                CartesianChartHost(
                    chart = rememberCartesianChart(rememberLineCartesianLayer()),
                    model = model,
                )
            }
        }
    }
}

@Composable
fun TabsNavigation(activeTab: String, dark: Boolean, onTabSelected: (String) -> Unit) {
    val tabs = listOf("Directory", "Maintenance", "Security", "Employees", "Batch Ingest")
    LazyRow(
        modifier = Modifier.fillMaxWidth(), 
        contentPadding = PaddingValues(horizontal = 20.dp), 
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tabs) { tab ->
            val isSelected = activeTab == tab
            val bgColor = if (isSelected) TealPrimary else if (dark) SlateSurface else LightCard
            val textColor = if (isSelected) Color.White else if (dark) SlateTextPrimary else LightTextPrimary
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(bgColor)
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(tab, color = textColor, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SearchAndFilterBar(query: String, onQueryChange: (String) -> Unit, onFilterClick: () -> Unit, dark: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TourismTextField(
            value = query, 
            onValueChange = onQueryChange, 
            label = "Search by Unit ID or Name...", 
            leadingIcon = Icons.Filled.Search,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        IconButton(
            onClick = onFilterClick,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(14.dp)).background(if(dark) SlateSurface else LightCard).border(1.dp, TealPrimary.copy(0.2f), RoundedCornerShape(14.dp))
        ) {
            Icon(Icons.Filled.Tune, null, tint = TealPrimary)
        }
    }
}

@Composable
fun AssetList(assets: List<Asset>, dark: Boolean, onDetails: (Asset) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp), 
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 10.dp, bottom = 100.dp)
    ) {
        items(assets, key = { it.id }) { asset ->
            AssetProfessionalCard(asset, dark) { onDetails(asset) }
        }
    }
}

@Composable
fun FilterOptions(current: String, dark: Boolean, onSelect: (String) -> Unit) {
    Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
        Text("Classification Intelligence", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = TealPrimary)
        Text("Select asset class to filter views", fontSize = 12.sp, color = if(dark) SlateTextSecondary else LightTextSecondary)
        Spacer(modifier = Modifier.height(24.dp))
        
        val categories = listOf("All", "Machinery", "Tools", "Vehicles", "Equipment", "Electronics")
        categories.forEach { cat ->
            Surface(
                onClick = { onSelect(cat) },
                color = if (current == cat) TealPrimary.copy(0.1f) else Color.Transparent,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = current == cat, onClick = { onSelect(cat) }, colors = RadioButtonDefaults.colors(selectedColor = TealPrimary))
                    Text(cat, modifier = Modifier.padding(start = 12.dp), fontWeight = if(current == cat) FontWeight.Bold else FontWeight.Normal, color = if(dark) SlateTextPrimary else LightTextPrimary)
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ShimmerListLoader(dark: Boolean) {
    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        repeat(6) {
            val transition = rememberInfiniteTransition(label = "shimmer")
            val translateAnim by transition.animateFloat(
                initialValue = 0f, targetValue = 1000f,
                animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing), RepeatMode.Restart),
                label = "shimmerPos"
            )
            val brush = Brush.linearGradient(
                colors = if(dark) listOf(Color(0xFF1B202A), Color(0xFF2C3442), Color(0xFF1B202A)) else listOf(Color(0xFFEDF1F5), Color(0xFFF8FAFC), Color(0xFFEDF1F5)),
                start = Offset.Zero, end = Offset(x = translateAnim, y = translateAnim)
            )
            Box(modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(18.dp)).background(brush))
        }
    }
}

@Composable
fun MaintenanceDashboard(requests: List<com.tourism.assetguard.data.local.MaintenanceRequest>, dark: Boolean) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(requests) { req ->
            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(req.assetName, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        StatusBadge(req.status)
                    }
                    Text(req.description, fontSize = 13.sp, color = if (dark) SlateTextSecondary else LightTextSecondary, modifier = Modifier.padding(vertical = 8.dp))
                    
                    // --- NEW FEATURE: BROKEN ASSET PHOTOS ---
                    if (req.damagePhotoUrls.isNotEmpty()) {
                        Text("DAMAGE INSPECTION PHOTOS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TealPrimary, letterSpacing = 1.sp)
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(req.damagePhotoUrls) { photoUrl ->
                                Box(
                                    modifier = Modifier
                                        .size(120.dp, 90.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(if (dark) Color.Black.copy(0.2f) else LightCard)
                                        .border(1.dp, TealPrimary.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                                ) {
                                    AsyncImage(
                                        model = photoUrl,
                                        contentDescription = "Damage Photo",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = if(dark) Color.White.copy(0.05f) else Color.Black.copy(0.05f))
                    Row(modifier = Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Build, null, tint = TealPrimary, modifier = Modifier.size(14.dp))
                        Text(" Estimated Cost: ${String.format(Locale.US, "$%.2f", req.repairCost)}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TealPrimary)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Reporter: ${req.reporterName}", fontSize = 10.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
fun SecurityIntelligence(logs: List<com.tourism.assetguard.data.local.ActivityLog>, dark: Boolean) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(logs.reversed()) { log ->
            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(if(log.action.contains("Add")) SuccessGreen else TealPrimary))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(log.userName, fontWeight = FontWeight.Bold, color = TealPrimary, fontSize = 14.sp)
                        Text("${log.action}: ${log.details}", fontSize = 12.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        Text(SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(log.timestamp)), fontSize = 10.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeManagement(users: List<User>, dark: Boolean) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(users) { emp ->
            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(44.dp).clip(CircleShape).background(TealDark), contentAlignment = Alignment.Center) {
                        Text(emp.name.take(1).uppercase(), color = Color.White, fontWeight = FontWeight.Black, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(emp.name, fontWeight = FontWeight.Bold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        Text(emp.role, fontSize = 11.sp, color = TealPrimary, fontWeight = FontWeight.SemiBold)
                    }
                    Icon(Icons.Default.VerifiedUser, null, tint = if(emp.isEnabled) SuccessGreen else CoralSecondary)
                }
            }
        }
    }
}

@Composable
fun BatchImporter(vm: AssetViewModel, context: android.content.Context, onDone: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Mass Data Ingestion", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = if(isSystemInDarkTheme()) SlateTextPrimary else LightTextPrimary)
        Text("Paste comma-separated asset data for bulk sync.", fontSize = 12.sp, color = if(isSystemInDarkTheme()) SlateTextSecondary else LightTextSecondary)
        Spacer(modifier = Modifier.height(16.dp))
        var csvData by remember { mutableStateOf("") }
        OutlinedTextField(
            value = csvData, 
            onValueChange = { csvData = it }, 
            label = { Text("CSV Dataset") }, 
            modifier = Modifier.fillMaxWidth().height(220.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TealPrimary)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LushPrimaryButton(text = "Authorize Batch Sync", onClick = { 
            vm.simulateBulkCSVImport(csvData) { 
                Toast.makeText(context, "Cloud Ledger Updated!", Toast.LENGTH_SHORT).show()
                onDone() 
            } 
        })
    }
}

@Composable
fun StatusBadge(status: String) {
    val color = when (status) {
        "Completed", "Available" -> SuccessGreen
        "In Progress", "Maintenance" -> WarningAmber
        "Scheduled", "Assigned" -> TealPrimary
        else -> DangerRed
    }
    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(color.copy(alpha = 0.12f)).border(1.2.dp, color, RoundedCornerShape(8.dp)).padding(horizontal = 10.dp, vertical = 4.dp)) {
        Text(status, color = color, fontSize = 10.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun AssetProfessionalCard(asset: Asset, dark: Boolean, onClick: () -> Unit) {
    val statusColor = when (asset.status) {
        "Available" -> SuccessGreen
        "Assigned" -> TealPrimary
        "Maintenance" -> WarningAmber
        else -> DangerRed
    }
    GlassyCard(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(16.dp)).background(if (dark) SlateSurface else LightCard).border(1.dp, if(dark) Color.White.copy(0.08f) else Color.Black.copy(0.08f), RoundedCornerShape(16.dp)), contentAlignment = Alignment.Center) {
                if (asset.imageUrls.isNotEmpty() && asset.imageUrls.first().startsWith("http")) {
                    AsyncImage(model = asset.imageUrls.first(), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                    Icon(Icons.Filled.Construction, null, tint = TealPrimary, modifier = Modifier.size(30.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(asset.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = if (dark) SlateTextPrimary else LightTextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("SN: ${asset.serialNumber} | QTY: ${asset.quantity}", fontSize = 11.sp, color = if (dark) SlateTextSecondary else LightTextSecondary, fontWeight = FontWeight.SemiBold)
                
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 6.dp)) {
                    Icon(Icons.Filled.Place, null, tint = TealPrimary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = asset.currentSite, fontSize = 11.sp, color = TealPrimary, fontWeight = FontWeight.Black)
                }
            }
            StatusBadge(asset.status)
        }
    }
}

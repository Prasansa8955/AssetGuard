package com.tourism.assetguard.ui.screens.asset

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tourism.assetguard.data.local.Asset
import com.tourism.assetguard.data.local.User
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.util.AssetQrCode
import com.tourism.assetguard.viewmodel.AssetViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetDetailScreen(
    assetViewModel: AssetViewModel,
    user: User?,
    onNavigateBack: () -> Unit,
    onNavigateToAssign: (Asset) -> Unit,
    onNavigateToEdit: (Asset) -> Unit
) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val asset = assetViewModel.selectedAsset ?: return
    val activityLogs by assetViewModel.activityLogs.collectAsState()

    var showMaintenanceSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val assetHistory = remember(activityLogs, asset.id) {
        activityLogs.filter { it.assetId == asset.id }.sortedByDescending { it.timestamp }
    }

    val isManager = user?.role == "Manager"

    val statusColor = when (asset.status) {
        "Available" -> SuccessGreen
        "Assigned" -> TealPrimary
        "Maintenance" -> WarningAmber
        else -> DangerRed
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asset Intelligence", color = if (dark) SlateTextPrimary else LightTextPrimary, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = TealPrimary)
                    }
                },
                actions = {
                    if (isManager) {
                        IconButton(onClick = { onNavigateToEdit(asset) }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit", tint = TealPrimary)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = if (dark) SlateBackground else LightBackground)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().background(if (dark) SlateBackground else LightBackground).padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp, vertical = 10.dp)) {
                // Header Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (dark) SlateSurface else LightCard)
                        .border(1.dp, if(dark) Color.White.copy(0.1f) else Color.Black.copy(0.05f), RoundedCornerShape(24.dp)), 
                    contentAlignment = Alignment.Center
                ) {
                    if (asset.imageUrls.isNotEmpty()) {
                        AsyncImage(model = asset.imageUrls.first(), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    } else {
                        Icon(imageVector = Icons.Filled.Category, contentDescription = null, tint = TealPrimary, modifier = Modifier.size(80.dp))
                    }
                    
                    Box(modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).clip(RoundedCornerShape(8.dp)).background(statusColor.copy(alpha = 0.9f)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                        Text(text = asset.status.uppercase(), color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Black)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = asset.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Text(text = "Unit ID: ${asset.barcode} • Serial: ${asset.serialNumber}", fontSize = 13.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)

                Spacer(modifier = Modifier.height(24.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    GlassyCard(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("STOCK LEVEL", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TealPrimary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = asset.quantity.toString(), fontSize = 24.sp, fontWeight = FontWeight.Black, color = if (dark) SlateTextPrimary else LightTextPrimary)
                                if (isManager) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row {
                                        IconButton(onClick = { if (asset.quantity > 0) assetViewModel.updateAsset(asset.copy(quantity = asset.quantity - 1)) {} }, modifier = Modifier.size(28.dp)) {
                                            Icon(Icons.Filled.RemoveCircle, null, tint = CoralSecondary)
                                        }
                                        IconButton(onClick = { assetViewModel.updateAsset(asset.copy(quantity = asset.quantity + 1)) {} }, modifier = Modifier.size(28.dp)) {
                                            Icon(Icons.Filled.AddCircle, null, tint = SuccessGreen)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    GlassyCard(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("CONDITION", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                            Text(text = asset.condition, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // REPORT DAMAGE BUTTON (New Complex Feature)
                if (asset.status != "Maintenance") {
                    LushPrimaryButton(
                        text = "Report Hardware Malfunction",
                        onClick = { showMaintenanceSheet = true },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                Text("Lifecycle & Operating Protocol", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                GlassyCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        DetailItem(Icons.Filled.Verified, "Warranty Protection", asset.warrantyExpiration ?: "Not Registered", CoralSecondary, dark)
                        DetailItem(Icons.Filled.AutoGraph, "Predicted Service Date", asset.nextMaintenanceDate ?: "Data Insufficient", TealPrimary, dark)
                        DetailItem(Icons.Filled.History, "Last Field Audit", if(asset.lastAuditedAt != null) SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(asset.lastAuditedAt!!)) else "Never Audited", SuccessGreen, dark)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Fleet Deployment", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                GlassyCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        DetailItem(Icons.Filled.Business, "Operational Base", asset.currentSite, TealPrimary, dark)
                        if (asset.assignedCompany?.isNotEmpty() == true) DetailItem(Icons.Filled.CorporateFare, "Holding Entity", asset.assignedCompany!!, CoralSecondary, dark)
                        if (asset.status == "Assigned") DetailItem(Icons.Filled.Person, "Current Custodian", asset.assignedTo ?: "Unassigned", TealPrimary, dark)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text("Immutable Audit History", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                Spacer(modifier = Modifier.height(16.dp))

                if (assetHistory.isEmpty()) {
                    Text("No historical event logs found.", fontSize = 13.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                } else {
                    assetHistory.forEachIndexed { index, log ->
                        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(36.dp)) {
                                Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(TealPrimary).border(2.dp, Color.White, CircleShape))
                                if (index < assetHistory.size - 1) {
                                    Box(modifier = Modifier.width(2.dp).height(50.dp).background(if (dark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.1f)))
                                }
                            }
                            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = log.action, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                                    Text(text = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).format(Date(log.timestamp)), fontSize = 11.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                                }
                                Text(text = log.details, fontSize = 13.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                                Text(text = "BY: ${log.userName.uppercase()}", fontSize = 10.sp, fontWeight = FontWeight.Black, color = TealPrimary, modifier = Modifier.padding(top = 4.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        // --- MAINTENANCE REPORTING BOTTOM SHEET ---
        if (showMaintenanceSheet) {
            ModalBottomSheet(
                onDismissRequest = { showMaintenanceSheet = false },
                sheetState = sheetState,
                containerColor = if (dark) SlateSurface else Color.White
            ) {
                MaintenanceReportContent(
                    asset = asset,
                    onReport = { desc, photos ->
                        assetViewModel.scheduleMaintenance(
                            asset = asset,
                            description = desc,
                            reporter = user?.name ?: "System",
                            breakdownType = "Hardware Malfunction",
                            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                            cost = 0.0,
                            photoUrls = photos,
                            onSuccess = {
                                Toast.makeText(context, "Malfunction Logged & Dispatched!", Toast.LENGTH_SHORT).show()
                                showMaintenanceSheet = false
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MaintenanceReportContent(asset: Asset, onReport: (String, List<String>) -> Unit) {
    val context = LocalContext.current
    val dark = isSystemInDarkTheme()
    var description by remember { mutableStateOf("") }
    val capturedPhotos = remember { mutableStateListOf<String>() }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val file = java.io.File(context.filesDir, "damage_${System.currentTimeMillis()}.jpg")
            try {
                val out = java.io.FileOutputStream(file)
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 90, out)
                out.flush(); out.close()
                capturedPhotos.add(android.net.Uri.fromFile(file).toString())
            } catch (e: Exception) { /* Log Error */ }
        }
    }

    Column(modifier = Modifier.padding(24.dp).fillMaxWidth().verticalScroll(rememberScrollState())) {
        Text("Report Unit Malfunction", fontWeight = FontWeight.Black, fontSize = 20.sp, color = TealPrimary)
        Text("Incident: ${asset.name}", fontSize = 12.sp, color = if(dark) SlateTextSecondary else LightTextSecondary)
        Spacer(modifier = Modifier.height(24.dp))

        TourismTextField(value = description, onValueChange = { description = it }, label = "Describe Malfunction / Breakdown", leadingIcon = Icons.Filled.Warning)
        
        Spacer(modifier = Modifier.height(20.dp))
        Text("DAMAGE EVIDENCE PHOTOS (MIN 1, MAX 4)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TealPrimary)
        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            capturedPhotos.forEach { photo ->
                Box(modifier = Modifier.size(70.dp).clip(RoundedCornerShape(12.dp)).background(SlateSurface)) {
                    AsyncImage(model = photo, contentDescription = null, contentScale = ContentScale.Crop)
                    IconButton(onClick = { capturedPhotos.remove(photo) }, modifier = Modifier.align(Alignment.TopEnd).size(24.dp).background(Color.Black.copy(0.4f), CircleShape)) {
                        Icon(Icons.Filled.Close, null, tint = Color.White, modifier = Modifier.size(14.dp))
                    }
                }
            }
            if (capturedPhotos.size < 4) {
                Box(
                    modifier = Modifier.size(70.dp).clip(RoundedCornerShape(12.dp)).background(if(dark) SlateBackground else LightCard).clickable { cameraLauncher.launch(null) }.border(1.dp, TealPrimary.copy(0.3f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.AddAPhoto, null, tint = TealPrimary)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        LushPrimaryButton(
            text = "Finalize & Submit Report",
            onClick = {
                if (description.isBlank() || capturedPhotos.isEmpty()) {
                    Toast.makeText(context, "Description and photos required", Toast.LENGTH_SHORT).show()
                } else {
                    onReport(description, capturedPhotos.toList())
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun DetailItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String, iconColor: Color, dark: Boolean) {
    Row(modifier = Modifier.padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(iconColor.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (dark) SlateTextSecondary else LightTextSecondary, letterSpacing = 0.8.sp)
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if (dark) SlateTextPrimary else LightTextPrimary)
        }
    }
}

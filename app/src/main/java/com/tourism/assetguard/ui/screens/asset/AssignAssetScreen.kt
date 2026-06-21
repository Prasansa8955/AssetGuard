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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourism.assetguard.data.local.Asset
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.util.BarcodeScannerView
import com.tourism.assetguard.viewmodel.AssetViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignAssetScreen(
    assetViewModel: AssetViewModel,
    preSelectedAsset: Asset?,
    onNavigateBack: () -> Unit
) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var resolvedAsset by remember { mutableStateOf<Asset?>(preSelectedAsset) }
    var searchBarcodeQuery by remember { mutableStateOf("") }
    var employeeName by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("BuildCo SL") }
    var projectSite by remember { mutableStateOf("Colombo Port City") }

    var showCameraScanner by remember { mutableStateOf(false) }
    var expandedEmployeeMenu by remember { mutableStateOf(false) }
    var expandedCompanyMenu by remember { mutableStateOf(false) }
    var expandedSiteMenu by remember { mutableStateOf(false) }

    val employees = listOf("Marcus Brody", "John Doe", "Jane Smith", "Sarah Auditor")
    val companies = listOf("BuildCo SL", "Refinery Partners", "Logistics Express", "Tourism Assets Ltd")
    val sites = listOf("Colombo Port City", "Hambantota Refinery", "Warehouse HQ", "Kandy Central")

    var cameraPermissionGranted by remember {
        mutableStateOf(
            context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraPermissionGranted = isGranted
        if (isGranted) showCameraScanner = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Assign to Company / Staff", color = if (dark) SlateTextPrimary else LightTextPrimary, fontWeight = FontWeight.Bold) },
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
                                    resolvedAsset = found
                                    showCameraScanner = false
                                }
                            }
                        }
                    )
                    IconButton(
                        onClick = { showCameraScanner = false },
                        modifier = Modifier.padding(16.dp).align(Alignment.TopEnd).clip(CircleShape).background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Icon(Icons.Filled.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 20.dp, vertical = 10.dp)) {
                    if (resolvedAsset == null) {
                        Text(text = "Step 1: Identify Asset", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                        Spacer(modifier = Modifier.height(16.dp))
                        GlassyCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                LushPrimaryButton(text = "Scan QR Code", onClick = { if (cameraPermissionGranted) showCameraScanner = true else permissionLauncher.launch(Manifest.permission.CAMERA) })
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    TourismTextField(value = searchBarcodeQuery, onValueChange = { searchBarcodeQuery = it }, label = "Enter Barcode", leadingIcon = Icons.Filled.Keyboard, modifier = Modifier.weight(1f))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                val found = assetViewModel.resolveAssetByBarcode(searchBarcodeQuery)
                                                if (found != null) resolvedAsset = found
                                                else Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        modifier = Modifier.size(54.dp).clip(RoundedCornerShape(12.dp)).background(TealPrimary)
                                    ) { Icon(Icons.Filled.Search, null, tint = Color.White) }
                                }
                            }
                        }
                    } else {
                        val currentAsset = resolvedAsset!!
                        Text(text = "Assigning: ${currentAsset.name}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TealPrimary)
                        Spacer(modifier = Modifier.height(16.dp))

                        GlassyCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // 1. Company Selection (User Request)
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedTextField(
                                        value = companyName, onValueChange = {}, readOnly = true,
                                        label = { Text("Assigning Company") }, modifier = Modifier.fillMaxWidth(),
                                        leadingIcon = { Icon(Icons.Filled.Business, null, tint = TealPrimary) },
                                        trailingIcon = { IconButton(onClick = { expandedCompanyMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } }
                                    )
                                    DropdownMenu(expanded = expandedCompanyMenu, onDismissRequest = { expandedCompanyMenu = false }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                        companies.forEach { comp -> DropdownMenuItem(text = { Text(comp) }, onClick = { companyName = comp; expandedCompanyMenu = false }) }
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))

                                // 2. Employee Selection
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedTextField(
                                        value = employeeName, onValueChange = {}, readOnly = true,
                                        label = { Text("Assigning Employee") }, modifier = Modifier.fillMaxWidth(),
                                        leadingIcon = { Icon(Icons.Filled.Person, null, tint = TealPrimary) },
                                        trailingIcon = { IconButton(onClick = { expandedEmployeeMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } }
                                    )
                                    DropdownMenu(expanded = expandedEmployeeMenu, onDismissRequest = { expandedEmployeeMenu = false }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                        employees.forEach { emp -> DropdownMenuItem(text = { Text(emp) }, onClick = { employeeName = emp; expandedEmployeeMenu = false }) }
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))

                                // 3. Site Selection
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedTextField(
                                        value = projectSite, onValueChange = {}, readOnly = true,
                                        label = { Text("Operational Site") }, modifier = Modifier.fillMaxWidth(),
                                        leadingIcon = { Icon(Icons.Filled.Place, null, tint = TealPrimary) },
                                        trailingIcon = { IconButton(onClick = { expandedSiteMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } }
                                    )
                                    DropdownMenu(expanded = expandedSiteMenu, onDismissRequest = { expandedSiteMenu = false }, modifier = Modifier.fillMaxWidth(0.8f)) {
                                        sites.forEach { site -> DropdownMenuItem(text = { Text(site) }, onClick = { projectSite = site; expandedSiteMenu = false }) }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        if (assetViewModel.isLoading) {
                            CircularProgressIndicator(color = TealPrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                        } else {
                            LushPrimaryButton(
                                text = "Finalize Assignment",
                                onClick = {
                                    if (employeeName.isBlank()) {
                                        Toast.makeText(context, "Select an employee", Toast.LENGTH_SHORT).show()
                                        return@LushPrimaryButton
                                    }
                                    assetViewModel.checkoutAsset(
                                        asset = currentAsset,
                                        employeeId = "EMP_ID", // Simple placeholder
                                        employeeName = employeeName,
                                        companyName = companyName,
                                        siteId = "SITE_ID",
                                        siteName = projectSite,
                                        onSuccess = onNavigateBack
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedButton(onClick = { resolvedAsset = null }, modifier = Modifier.fillMaxWidth().height(48.dp), shape = RoundedCornerShape(12.dp)) {
                                Text("Cancel", color = CoralSecondary)
                            }
                        }
                    }
                }
            }
        }
    }
}

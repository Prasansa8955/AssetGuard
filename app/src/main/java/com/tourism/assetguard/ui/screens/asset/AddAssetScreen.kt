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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.tourism.assetguard.data.local.Asset
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.util.BarcodeScannerView
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssetScreen(
    assetViewModel: AssetViewModel,
    existingAsset: Asset? = null,
    onNavigateBack: () -> Unit
) {
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isEditMode = existingAsset != null

    var name by remember { mutableStateOf(existingAsset?.name ?: "") }
    var description by remember { mutableStateOf(existingAsset?.description ?: "") }
    var category by remember { mutableStateOf(existingAsset?.category ?: "Tools") }
    var serialNumber by remember { mutableStateOf(existingAsset?.serialNumber ?: "") }
    var barcode by remember { mutableStateOf(existingAsset?.barcode ?: "") }
    var purchaseDate by remember { mutableStateOf(existingAsset?.purchaseDate ?: "") }
    var purchasePriceStr by remember { mutableStateOf(existingAsset?.purchasePrice?.toString() ?: "") }
    var depreciationRateStr by remember { mutableStateOf(existingAsset?.depreciationRate?.toString() ?: "10.0") }
    var currentSite by remember { mutableStateOf(existingAsset?.currentSite ?: "Warehouse HQ") }
    var locationDetails by remember { mutableStateOf(existingAsset?.locationDetails ?: "") }
    var condition by remember { mutableStateOf(existingAsset?.condition ?: "Good") }
    var quantityStr by remember { mutableStateOf(existingAsset?.quantity?.toString() ?: "1") }
    var attachedImageUri by remember { mutableStateOf<String?>(existingAsset?.imageUrls?.firstOrNull()) }
    
    // Geolocation States
    var latitude by remember { mutableStateOf(existingAsset?.lastLatitude ?: 0.0) }
    var longitude by remember { mutableStateOf(existingAsset?.lastLongitude ?: 0.0) }

    var expandedCategoryMenu by remember { mutableStateOf(false) }
    var expandedConditionMenu by remember { mutableStateOf(false) }
    var showBarcodeScanner by remember { mutableStateOf(false) }
    var showMapPicker by remember { mutableStateOf(false) }
    
    var showDeleteDialog by remember { mutableStateOf(false) }

    var cameraPermissionGranted by remember {
        mutableStateOf(
            context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraPermissionGranted = isGranted
        if (isGranted) {
            showBarcodeScanner = true
        } else {
            Toast.makeText(context, "Camera permission required for QR scanning", Toast.LENGTH_SHORT).show()
        }
    }

    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val file = java.io.File(context.filesDir, "asset_${System.currentTimeMillis()}.jpg")
            try {
                val out = java.io.FileOutputStream(file)
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 95, out)
                out.flush()
                out.close()
                attachedImageUri = android.net.Uri.fromFile(file).toString()
            } catch (e: Exception) {
                Toast.makeText(context, "Capture error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        if (isEditMode) "Modify Asset Profile" else "Register New Asset", 
                        color = if (dark) SlateTextPrimary else LightTextPrimary,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = TealPrimary)
                    }
                },
                actions = {
                    if (isEditMode) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = DangerRed)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = if (dark) SlateBackground else LightBackground)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (dark) SlateBackground else LightBackground)
                .padding(innerPadding)
        ) {
            if (showBarcodeScanner) {
                Box(modifier = Modifier.fillMaxSize()) {
                    BarcodeScannerView(
                        onBarcodeDetected = { scannedText ->
                            barcode = scannedText
                            showBarcodeScanner = false
                        }
                    )
                    IconButton(
                        onClick = { showBarcodeScanner = false },
                        modifier = Modifier.padding(16.dp).align(Alignment.TopEnd).clip(CircleShape).background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Icon(Icons.Filled.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    // Image attachment panel
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (dark) SlateSurface else LightCard)
                            .border(1.dp, if(dark) Color.White.copy(0.1f) else Color.Black.copy(0.05f), RoundedCornerShape(20.dp))
                            .clickable { photoLauncher.launch(null) },
                        contentAlignment = Alignment.Center
                    ) {
                        if (attachedImageUri != null) {
                            AsyncImage(
                                model = attachedImageUri,
                                contentDescription = "Asset Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.2f)))
                            IconButton(
                                onClick = { attachedImageUri = null },
                                modifier = Modifier.align(Alignment.TopEnd).padding(12.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.4f))
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = null, tint = Color.White)
                            }
                        } else {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(imageVector = Icons.Filled.AddAPhoto, contentDescription = null, tint = TealPrimary, modifier = Modifier.size(54.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Capture Unit Photo", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Basic Identification", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                    Spacer(modifier = Modifier.height(12.dp))

                    GlassyCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            TourismTextField(value = name, onValueChange = { name = it }, label = "Asset Name", leadingIcon = Icons.Filled.Label)
                            Spacer(modifier = Modifier.height(16.dp))
                            TourismTextField(value = description, onValueChange = { description = it }, label = "Unit Description", leadingIcon = Icons.Filled.Description)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            // Category Dropdown
                            Box(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = category,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Asset Classification") },
                                    modifier = Modifier.fillMaxWidth(),
                                    leadingIcon = { Icon(Icons.Filled.Category, null, tint = TealPrimary) },
                                    trailingIcon = { IconButton(onClick = { expandedCategoryMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } },
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TealPrimary, unfocusedBorderColor = if(dark) Color.White.copy(0.1f) else Color.Black.copy(0.1f))
                                )
                                DropdownMenu(expanded = expandedCategoryMenu, onDismissRequest = { expandedCategoryMenu = false }) {
                                    listOf("Tools", "Electronics", "Equipment", "Vehicles", "Machinery").forEach { cat ->
                                        DropdownMenuItem(text = { Text(cat) }, onClick = { category = cat; expandedCategoryMenu = false })
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Technical & Inventory Details", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                    Spacer(modifier = Modifier.height(12.dp))

                    GlassyCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                             // Condition Dropdown
                            Box(modifier = Modifier.fillMaxWidth()) {
                                OutlinedTextField(
                                    value = condition,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Current Condition Status") },
                                    modifier = Modifier.fillMaxWidth(),
                                    leadingIcon = { Icon(Icons.Filled.HealthAndSafety, null, tint = TealPrimary) },
                                    trailingIcon = { IconButton(onClick = { expandedConditionMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } },
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = TealPrimary)
                                )
                                DropdownMenu(expanded = expandedConditionMenu, onDismissRequest = { expandedConditionMenu = false }) {
                                    listOf("Excellent", "Good", "Fair", "Poor", "Broken Down").forEach { cond ->
                                        DropdownMenuItem(text = { Text(cond) }, onClick = { condition = cond; expandedConditionMenu = false })
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                TourismTextField(
                                    value = quantityStr, 
                                    onValueChange = { quantityStr = it }, 
                                    label = "Qty", 
                                    leadingIcon = Icons.Filled.Inventory, 
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.weight(1f)
                                )
                                TourismTextField(
                                    value = serialNumber, 
                                    onValueChange = { serialNumber = it }, 
                                    label = "Serial S/N", 
                                    leadingIcon = Icons.Filled.Fingerprint,
                                    modifier = Modifier.weight(1.5f)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            // Barcode
                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                TourismTextField(value = barcode, onValueChange = { barcode = it }, label = "Barcode ID", leadingIcon = Icons.Filled.QrCode, modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(10.dp))
                                IconButton(
                                    onClick = { if (cameraPermissionGranted) showBarcodeScanner = true else permissionLauncher.launch(Manifest.permission.CAMERA) },
                                    modifier = Modifier.size(54.dp).clip(RoundedCornerShape(12.dp)).background(TealPrimary)
                                ) {
                                    Icon(Icons.Filled.QrCodeScanner, contentDescription = "Scan", tint = Color.White)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Acquisition & Location", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = if (dark) SlateTextPrimary else LightTextPrimary)
                    Spacer(modifier = Modifier.height(12.dp))

                    GlassyCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                TourismTextField(value = purchasePriceStr, onValueChange = { purchasePriceStr = it }, label = "Cost ($)", leadingIcon = Icons.Filled.AttachMoney, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.weight(1f))
                                TourismTextField(value = purchaseDate, onValueChange = { purchaseDate = it }, label = "Date", leadingIcon = Icons.Filled.DateRange, modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            TourismTextField(value = currentSite, onValueChange = { currentSite = it }, label = "Operational Site", leadingIcon = Icons.Filled.Business)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            // Location Field with Map Picker Trigger
                            TourismTextField(
                                value = locationDetails, 
                                onValueChange = { locationDetails = it }, 
                                label = "Internal GPS/Details", 
                                leadingIcon = Icons.Filled.LocationOn,
                                trailingIcon = {
                                    IconButton(onClick = { showMapPicker = true }) {
                                        Icon(imageVector = Icons.Filled.Map, contentDescription = "Auto Location", tint = TealPrimary)
                                    }
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    if (assetViewModel.isLoading) {
                        CircularProgressIndicator(color = TealPrimary, modifier = Modifier.align(Alignment.CenterHorizontally))
                    } else {
                        LushPrimaryButton(
                            text = if (isEditMode) "Update Asset Record" else "Authorize Registration",
                            onClick = {
                                if (name.isBlank() || serialNumber.isBlank()) {
                                    Toast.makeText(context, "Identity fields required", Toast.LENGTH_SHORT).show()
                                    return@LushPrimaryButton
                                }
                                val price = purchasePriceStr.toDoubleOrNull() ?: 0.0
                                val depRate = depreciationRateStr.toDoubleOrNull() ?: 10.0
                                val qty = quantityStr.toIntOrNull() ?: 1
                                val img = attachedImageUri ?: ""
                                
                                if (isEditMode && existingAsset != null) {
                                    val updated = existingAsset.copy(
                                        name = name, description = description, category = category,
                                        serialNumber = serialNumber, barcode = barcode,
                                        purchaseDate = purchaseDate, purchasePrice = price,
                                        depreciationRate = depRate, currentSite = currentSite,
                                        locationDetails = locationDetails, condition = condition,
                                        quantity = qty, imageUrls = if(img.isNotEmpty()) listOf(img) else existingAsset.imageUrls,
                                        lastLatitude = latitude, lastLongitude = longitude
                                    )
                                    assetViewModel.updateAsset(updated) {
                                        Toast.makeText(context, "Asset profile synchronized!", Toast.LENGTH_SHORT).show()
                                        onNavigateBack()
                                    }
                                } else {
                                    assetViewModel.addAsset(
                                        name = name, description = description, category = category,
                                        serialNumber = serialNumber, barcode = barcode,
                                        purchaseDate = purchaseDate, purchasePrice = price,
                                        depreciationRate = depRate, currentSite = currentSite,
                                        locationDetails = locationDetails, condition = condition,
                                        quantity = qty, imageUrls = if(img.isNotEmpty()) listOf(img) else emptyList(),
                                        latitude = latitude, longitude = longitude,
                                        onSuccess = onNavigateBack
                                    )
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }

        // ==========================================
        // GEOLOCATION PICKER DIALOG (Full Screen)
        // ==========================================
        if (showMapPicker) {
            Dialog(
                onDismissRequest = { showMapPicker = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                    val initialPos = if (latitude != 0.0) LatLng(latitude, longitude) else LatLng(6.9271, 79.8612)
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(initialPos, 16f)
                    }
                    val markerState = rememberMarkerState(position = initialPos)

                    // Auto-direct to current location if empty
                    LaunchedEffect(Unit) {
                        if (latitude == 0.0) {
                            val currentLoc = com.tourism.assetguard.util.LocationHelper.getCurrentLocation(context)
                            if (currentLoc != null) {
                                val latLng = LatLng(currentLoc.latitude, currentLoc.longitude)
                                markerState.position = latLng
                                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 16f)
                            }
                        }
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { markerState.position = it },
                        uiSettings = MapUiSettings(myLocationButtonEnabled = true, zoomControlsEnabled = false),
                        properties = MapProperties(isMyLocationEnabled = true)
                    ) {
                        Marker(state = markerState, title = "New Asset Location")
                    }

                    // Header UI
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter).clip(RoundedCornerShape(12.dp)).background(Color.Black.copy(alpha = 0.7f)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Fleet Geolocation Pick", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Tap map to position unit precisely", color = Color.White.copy(0.7f), fontSize = 11.sp)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { showMapPicker = false }) { Icon(Icons.Default.Close, null, tint = Color.White) }
                    }

                    // Footer UI (Confirm)
                    Button(
                        onClick = {
                            latitude = markerState.position.latitude
                            longitude = markerState.position.longitude
                            locationDetails = String.format(Locale.US, "%.6f, %.6f", latitude, longitude)
                            showMapPicker = false
                        },
                        modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp).fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                    ) {
                        Text("Confirm Asset Location", fontWeight = FontWeight.Black)
                    }
                }
            }
        }
        
        // Delete Confirmation Dialog
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Permanently Scrapped?") },
                text = { Text("Are you sure you want to remove this unit from the corporate registry? This action is non-reversible.") },
                confirmButton = {
                    Button(
                        onClick = {
                            existingAsset?.let {
                                assetViewModel.deleteAsset(it.id) {
                                    showDeleteDialog = false
                                    onNavigateBack()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                    ) { Text("Scrap Unit", color = Color.White) }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}

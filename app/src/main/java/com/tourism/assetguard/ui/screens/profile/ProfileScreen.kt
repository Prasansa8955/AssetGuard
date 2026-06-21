package com.tourism.assetguard.ui.screens.profile

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.tourism.assetguard.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    assetViewModel: AssetViewModel,
    onDarkModeToggle: (Boolean) -> Unit,
    isDarkMode: Boolean,
    onLogout: () -> Unit
) {
    val user = authViewModel.currentUser
    val dark = isSystemInDarkTheme()
    val context = LocalContext.current

    var cameraPermissionGranted by remember {
        mutableStateOf(
            context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraPermissionGranted = isGranted
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val file = java.io.File(context.filesDir, "profile_${user?.userId ?: "temp"}.jpg")
            try {
                val out = java.io.FileOutputStream(file)
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 95, out)
                out.flush()
                out.close()
                authViewModel.uploadProfileImage(android.net.Uri.fromFile(file)) { _ -> }
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (dark) SlateBackground else LightBackground)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                        .background(TealPrimary)
                        .clickable {
                            if (cameraPermissionGranted) cameraLauncher.launch(null)
                            else permissionLauncher.launch(Manifest.permission.CAMERA)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (user?.profileImage?.isNotEmpty() == true) {
                        AsyncImage(model = user.profileImage, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Filled.CameraAlt, null, tint = Color.White)
                        }
                    } else {
                        Icon(Icons.Filled.Person, null, tint = Color.White, modifier = Modifier.size(50.dp))
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = user?.name ?: "User", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                    Text(text = user?.email ?: "staff@company.com", fontSize = 14.sp, color = if (dark) SlateTextSecondary else LightTextSecondary)
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(TealDark).padding(horizontal = 8.dp, vertical = 2.dp)) {
                        Text(text = user?.role ?: "Staff", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("App Settings & Configuration", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = if (dark) SlateTextPrimary else LightTextPrimary)
            Spacer(modifier = Modifier.height(12.dp))

            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // 1. Dark Theme Toggle (Requested)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.DarkMode, null, tint = TealPrimary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("Dark Theme Mode", fontWeight = FontWeight.SemiBold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                                Text(if (isDarkMode) "Currently Enabled" else "Currently Disabled", fontSize = 11.sp, color = if (isDarkMode) SuccessGreen else CoralSecondary)
                            }
                        }
                        Switch(checked = isDarkMode, onCheckedChange = onDarkModeToggle, colors = SwitchDefaults.colors(checkedThumbColor = TealPrimary))
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = if (dark) Color.White.copy(alpha = 0.05f) else Color.Black.copy(alpha = 0.05f))

                    // 2. Audit GPS Location Logs Toggle (Requested)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.GpsFixed, null, tint = CoralSecondary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("Audit GPS Location Logs", fontWeight = FontWeight.SemiBold, color = if (dark) SlateTextPrimary else LightTextPrimary)
                                Text(if (assetViewModel.isGpsLoggingEnabled) "Logging Enabled" else "Logs Disabled", fontSize = 11.sp, color = if (assetViewModel.isGpsLoggingEnabled) SuccessGreen else CoralSecondary)
                            }
                        }
                        Switch(
                            checked = assetViewModel.isGpsLoggingEnabled, 
                            onCheckedChange = { assetViewModel.isGpsLoggingEnabled = it }, 
                            colors = SwitchDefaults.colors(checkedThumbColor = SuccessGreen)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            LushPrimaryButton(text = "Log Out Portal", onClick = onLogout)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

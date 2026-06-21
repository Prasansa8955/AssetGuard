package com.tourism.assetguard.ui.screens.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpCenter
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourism.assetguard.ui.components.GlassyCard
import com.tourism.assetguard.ui.components.LushPrimaryButton
import com.tourism.assetguard.ui.components.TourismTextField
import com.tourism.assetguard.ui.theme.*
import com.tourism.assetguard.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    var animate by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        animate = true
        delay(2000)
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(SlateBackground),
        contentAlignment = Alignment.Center
    ) {
        MeshGradientBackground()

        AnimatedVisibility(
            visible = animate,
            enter = fadeIn(animationSpec = tween(1000)) + scaleIn(initialScale = 0.8f),
            exit = fadeOut()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Shield,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(110.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "ASSET GUARD",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 3.sp
                )
                Text(
                    text = "Advanced Corporate Fleet Protection",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgot: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        MeshGradientBackground()

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Filled.Security, null, tint = Color.White, modifier = Modifier.size(72.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Secure Access Portal", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black, color = Color.White)
            Spacer(modifier = Modifier.height(40.dp))

            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    TourismTextField(value = email, onValueChange = { email = it }, label = "Email Address", leadingIcon = Icons.Filled.Email)
                    Spacer(modifier = Modifier.height(16.dp))
                    TourismTextField(value = password, onValueChange = { password = it }, label = "Security Key", leadingIcon = Icons.Filled.Lock, isPassword = true)
                    
                    Text(
                        text = "Forgot Access Key?",
                        color = TealPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.End).padding(vertical = 14.dp).clickable { onNavigateToForgot() }
                    )

                    if (authViewModel.isLoading) {
                        CircularProgressIndicator(color = TealPrimary, modifier = Modifier.align(Alignment.CenterHorizontally).padding(12.dp))
                    } else {
                        LushPrimaryButton(text = "Authorize & Login", onClick = { authViewModel.login(email, password, onLoginSuccess) })
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            // BIOMETRIC UI
            Icon(
                imageVector = Icons.Filled.Fingerprint,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.4f),
                modifier = Modifier.size(60.dp).clickable { /* Biometric */ }
            )
            Text("Biometric Authentication", color = Color.White.copy(alpha = 0.4f), fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp))

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Enroll New Fleet Personnel",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.clickable { onNavigateToRegister() },
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Manager") }
    var expandedRoleMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        MeshGradientBackground()

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text("Create Security Profile", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black, color = Color.White)
            Spacer(modifier = Modifier.height(32.dp))

            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    TourismTextField(value = name, onValueChange = { name = it }, label = "Full Identity", leadingIcon = Icons.Filled.Person)
                    Spacer(modifier = Modifier.height(16.dp))
                    TourismTextField(value = email, onValueChange = { email = it }, label = "Corporate Email", leadingIcon = Icons.Filled.AlternateEmail)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = role, onValueChange = {}, readOnly = true, label = { Text("Operational Role") },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = { Icon(Icons.Filled.Badge, null, tint = TealPrimary) },
                            trailingIcon = { IconButton(onClick = { expandedRoleMenu = true }) { Icon(Icons.Filled.ArrowDropDown, null) } },
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                        )
                        DropdownMenu(expanded = expandedRoleMenu, onDismissRequest = { expandedRoleMenu = false }) {
                            DropdownMenuItem(text = { Text("Manager") }, onClick = { role = "Manager"; expandedRoleMenu = false })
                            DropdownMenuItem(text = { Text("Auditor") }, onClick = { role = "Auditor"; expandedRoleMenu = false })
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    TourismTextField(value = password, onValueChange = { password = it }, label = "Secure Key", leadingIcon = Icons.Filled.VpnKey, isPassword = true)
                    
                    if (password.isNotEmpty()) {
                        PasswordStrengthIndicator(password)
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    LushPrimaryButton(text = "Register Identity", onClick = { authViewModel.register(name, email, "", role, password, onRegisterSuccess) })
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Existing Personnel? Access Login", color = Color.White.copy(alpha = 0.8f), modifier = Modifier.clickable { onNavigateToLogin() })
        }
    }
}

@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.length > 8 && password.any { it.isDigit() } -> 1f
        password.length > 5 -> 0.6f
        else -> 0.3f
    }
    val color = when {
        strength > 0.8f -> SuccessGreen
        strength > 0.5f -> WarningAmber
        else -> DangerRed
    }
    LinearProgressIndicator(
        progress = { strength },
        modifier = Modifier.fillMaxWidth().height(4.dp).padding(top = 8.dp).clip(CircleShape),
        color = color,
        trackColor = Color.White.copy(alpha = 0.1f)
    )
}

@Composable
fun MeshGradientBackground() {
    val transition = rememberInfiniteTransition(label = "mesh")
    val shift by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing), RepeatMode.Reverse),
        label = "shift"
    )

    Box(modifier = Modifier.fillMaxSize().background(SlateBackground)) {
        Canvas(modifier = Modifier.fillMaxSize().blur(60.dp)) {
            drawCircle(
                color = TealPrimary.copy(alpha = 0.3f),
                radius = size.width / 1.5f,
                center = Offset(size.width * shift, size.height * (1 - shift))
            )
            drawCircle(
                color = CoralSecondary.copy(alpha = 0.2f),
                radius = size.width / 2f,
                center = Offset(size.width * (1 - shift), size.height * shift)
            )
        }
    }
}

@Composable
fun ForgotPasswordScreen(onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var sent by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        MeshGradientBackground()

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (sent) Icons.Filled.MarkEmailRead else Icons.AutoMirrored.Filled.HelpCenter,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (sent) "Check Your Inbox" else "Key Recovery",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))
            GlassyCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(24.dp)) {
                    if (sent) {
                        Text(
                            text = "A secure recovery link has been dispatched to $email. Please follow the instructions to reset your security key.",
                            color = Color.White.copy(alpha = 0.9f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        LushPrimaryButton(text = "Back to Login", onClick = onNavigateBack)
                    } else {
                        Text(
                            text = "Provide your corporate email to dispatch a secure recovery link to your registered identity.",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        TourismTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Verified Email",
                            leadingIcon = Icons.Filled.Email
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        LushPrimaryButton(
                            text = "Dispatch Link",
                            onClick = { if (email.isNotEmpty()) sent = true }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        TextButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
                            Text("Return to Portal", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

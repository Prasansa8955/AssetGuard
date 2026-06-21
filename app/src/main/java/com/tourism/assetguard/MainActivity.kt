package com.tourism.assetguard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tourism.assetguard.ui.screens.asset.*
import com.tourism.assetguard.ui.screens.auth.*
import com.tourism.assetguard.ui.screens.profile.HomeScreen
import com.tourism.assetguard.ui.screens.profile.ProfileScreen
import com.tourism.assetguard.ui.theme.SmartTourismTheme
import com.tourism.assetguard.ui.theme.TealPrimary
import com.tourism.assetguard.viewmodel.AssetViewModel
import com.tourism.assetguard.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var customDarkMode by remember { mutableStateOf<Boolean?>(null) }
            val systemDark = isSystemInDarkTheme()
            val isDark = customDarkMode ?: systemDark

            SmartTourismTheme(darkTheme = isDark) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AssetGuardAppOrchestrator(
                        isDarkMode = isDark,
                        onDarkModeToggle = { customDarkMode = it }
                    )
                }
            }
        }
    }
}

sealed class NavigationItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String) {
    object Directory : NavigationItem("directory", Icons.Filled.Inventory, "Inventory")
    object Map : NavigationItem("map_tracking", Icons.Filled.Map, "Map")
    object Audit : NavigationItem("audit_mode", Icons.AutoMirrored.Filled.FactCheck, "Audit")
    object Reports : NavigationItem("reports", Icons.Filled.BarChart, "Analytics")
    object Profile : NavigationItem("profile", Icons.Filled.AccountCircle, "Profile")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetGuardAppOrchestrator(
    isDarkMode: Boolean,
    onDarkModeToggle: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val authViewModel: AuthViewModel = viewModel()
    val assetViewModel: AssetViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentUser = authViewModel.currentUser
    val isManager = currentUser?.role == "Manager"

    // PERMISSIONS
    var locationPermissionGranted by remember {
        mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }
    var cameraPermissionGranted by remember {
        mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
    }

    val requestPermissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        locationPermissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: locationPermissionGranted
        cameraPermissionGranted = permissions[Manifest.permission.CAMERA] ?: cameraPermissionGranted
    }

    LaunchedEffect(Unit) {
        if (!locationPermissionGranted || !cameraPermissionGranted) {
            requestPermissionsLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA))
        }
    }

    val mainScreens = listOf(
        NavigationItem.Directory.route,
        NavigationItem.Map.route,
        NavigationItem.Audit.route,
        NavigationItem.Reports.route,
        NavigationItem.Profile.route
    )

    val showBars = currentRoute in mainScreens

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = showBars,
        drawerContent = {
            if (showBars) {
                PremiumDrawerContent(
                    user = currentUser,
                    onNavigate = { route ->
                        navController.navigate(route)
                        scope.launch { drawerState.close() }
                    },
                    onLogout = {
                        authViewModel.logout {
                            navController.navigate("login") { popUpTo(NavigationItem.Directory.route) { inclusive = true } }
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showBars) {
                    PremiumTopBar(
                        onOpenDrawer = { scope.launch { drawerState.open() } },
                        onOpenNotifications = { /* Open Notification Sheet Logic */ }
                    )
                }
            },
            bottomBar = {
                if (showBars) {
                    NavigationBar(
                        modifier = Modifier.height(72.dp).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 12.dp
                    ) {
                        val navItems = if (isManager) {
                            listOf(NavigationItem.Directory, NavigationItem.Map, NavigationItem.Audit, NavigationItem.Reports, NavigationItem.Profile)
                        } else {
                            listOf(NavigationItem.Directory, NavigationItem.Map, NavigationItem.Audit, NavigationItem.Profile)
                        }

                        navItems.forEach { item ->
                            NavigationBarItem(
                                icon = { Icon(item.icon, null) },
                                label = { Text(item.label, fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                                selected = currentRoute == item.route,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    selectedTextColor = TealPrimary,
                                    indicatorColor = TealPrimary
                                ),
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = "splash", modifier = Modifier.padding(innerPadding)) {
                composable("splash") { SplashScreen { navController.navigate("login") { popUpTo("splash") { inclusive = true } } } }
                composable("login") { LoginScreen(authViewModel, onLoginSuccess = { navController.navigate(NavigationItem.Directory.route) { popUpTo("login") { inclusive = true } } }, onNavigateToRegister = { navController.navigate("register") }, onNavigateToForgot = { navController.navigate("forgot") }) }
                composable("register") { RegisterScreen(authViewModel, onRegisterSuccess = { navController.navigate("login") { popUpTo("register") { inclusive = true } } }, onNavigateToLogin = { navController.navigate("login") }) }
                composable("forgot") { ForgotPasswordScreen { navController.navigate("login") } }
                
                composable(NavigationItem.Directory.route) { 
                    HomeScreen(
                        assetViewModel = assetViewModel, 
                        user = currentUser, 
                        onNavigateToDetails = { asset -> 
                            assetViewModel.selectAsset(asset)
                            navController.navigate("details") 
                        }, 
                        onNavigateToAddAsset = { navController.navigate("add_asset") }
                    ) 
                }
                
                composable("details") { 
                    AssetDetailScreen(
                        assetViewModel = assetViewModel, 
                        user = currentUser, 
                        onNavigateBack = { navController.navigateUp() }, 
                        onNavigateToAssign = { navController.navigate("assign_workflow") }, 
                        onNavigateToEdit = { navController.navigate("edit_asset") }
                    ) 
                }
                
                composable("add_asset") { AddAssetScreen(assetViewModel, null, onNavigateBack = { navController.navigateUp() }) }
                composable("edit_asset") { AddAssetScreen(assetViewModel, assetViewModel.selectedAsset, onNavigateBack = { navController.navigateUp() }) }
                
                composable("assign_workflow") { AssignAssetScreen(assetViewModel, assetViewModel.selectedAsset, onNavigateBack = { assetViewModel.selectAsset(null); navController.navigateUp() }) }
                composable(NavigationItem.Map.route) { MapScreen(assetViewModel, onNavigateBack = { navController.navigateUp() }) }
                composable(NavigationItem.Audit.route) { AuditScreen(assetViewModel, currentUser, onNavigateBack = { navController.navigateUp() }) }
                composable(NavigationItem.Reports.route) { ReportScreen(assetViewModel, onNavigateBack = { navController.navigateUp() }) }
                composable(NavigationItem.Profile.route) { ProfileScreen(authViewModel, assetViewModel, onDarkModeToggle, isDarkMode, onLogout = { authViewModel.logout { navController.navigate("login") { popUpTo(NavigationItem.Directory.route) { inclusive = true } } } }) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumTopBar(onOpenDrawer: () -> Unit, onOpenNotifications: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { 
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Shield, null, tint = TealPrimary, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("ASSET GUARD", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
            }
        },
        navigationIcon = {
            IconButton(onClick = onOpenDrawer) {
                Icon(Icons.Filled.Menu, null, tint = TealPrimary)
            }
        },
        actions = {
            IconButton(onClick = onOpenNotifications) {
                BadgedBox(badge = { Badge { Text("3") } }) {
                    Icon(Icons.Filled.NotificationsNone, null, tint = TealPrimary)
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
    )
}

@Composable
fun PremiumDrawerContent(user: com.tourism.assetguard.data.model.User?, onNavigate: (String) -> Unit, onLogout: () -> Unit) {
    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        drawerShape = RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            // DRAWER HEADER
            Box(modifier = Modifier.size(80.dp).clip(CircleShape).background(TealPrimary), contentAlignment = Alignment.Center) {
                Text(user?.name?.take(1)?.uppercase() ?: "U", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(user?.name ?: "Corporate User", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(user?.role ?: "Operational Access", fontSize = 12.sp, color = TealPrimary, fontWeight = FontWeight.Bold)
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp), color = TealPrimary.copy(alpha = 0.1f))
            
            // MENU ITEMS
            DrawerMenuItem(Icons.Filled.Dashboard, "Executive Deck", "reports", onNavigate)
            DrawerMenuItem(Icons.Filled.Settings, "System Configuration", "profile", onNavigate)
            DrawerMenuItem(Icons.Filled.SupportAgent, "Global Support", "profile", onNavigate)
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4B5C)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, null)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Terminate Session", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DrawerMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, route: String, onNavigate: (String) -> Unit) {
    Surface(
        onClick = { onNavigate(route) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = TealPrimary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(label, fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }
    }
}

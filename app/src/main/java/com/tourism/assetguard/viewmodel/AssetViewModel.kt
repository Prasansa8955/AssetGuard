package com.tourism.assetguard.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.assetguard.data.local.*
import com.tourism.assetguard.data.model.getCurrentValue
import com.tourism.assetguard.data.repository.AssetRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.*

class AssetViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AssetRepository(application.applicationContext)
    
    val assets: StateFlow<List<Asset>> = repository.assets
    val audits: StateFlow<List<AuditLog>> = repository.audits
    val assignments: StateFlow<List<Assignment>> = repository.assignments
    val maintenanceRequests: StateFlow<List<MaintenanceRequest>> = repository.maintenanceRequests
    val assetRequests: StateFlow<List<AssetRequest>> = repository.assetRequests
    val sites: StateFlow<List<Site>> = repository.sites
    val notifications: StateFlow<List<Notification>> = repository.notifications
    val activityLogs: StateFlow<List<ActivityLog>> = repository.activityLogs
    val reports: StateFlow<List<Report>> = repository.reports
    val users: StateFlow<List<User>> = repository.users

    val isSyncing: StateFlow<Boolean> = com.tourism.assetguard.data.remote.FirebaseSyncManager.isSyncing
    val syncLogs: List<String> = com.tourism.assetguard.data.remote.FirebaseSyncManager.syncLogs

    private val _combinedAudits = kotlinx.coroutines.flow.MutableStateFlow<List<AuditLog>>(emptyList())
    val combinedAudits: StateFlow<List<AuditLog>> = _combinedAudits.asStateFlow()

    var selectedAsset by mutableStateOf<Asset?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isGpsLoggingEnabled by mutableStateOf(true)

    init {
        viewModelScope.launch {
            repository.loadLocalData()
            refreshCombinedAudits()
            if (assets.value.size < 10) {
                seedData()
            }
        }
    }

    private suspend fun seedData() {
        val mockSites = listOf(
            Site("SITE_001", "Colombo Port City", "Port City Terminal", 6.9319, 79.8412, 1000.0),
            Site("SITE_002", "Hambantota Refinery", "Refinery Industrial Zone", 6.1244, 81.1211, 2000.0),
            Site("SITE_003", "Warehouse HQ", "Baseline Rd, Colombo", 6.9167, 79.8733, 500.0),
            Site("SITE_004", "Kandy Smart City", "Central Business Dist", 7.2906, 80.6337, 800.0)
        )
        mockSites.forEach { repository.addSite(it) }

        val mockUsers = listOf(
            User("manager_default", "John Manager", "manager@assetguard.com", "+94771234561", "", "Manager", true, listOf("All")),
            User("EMP_001", "Marcus Brody", "marcus@assetguard.com", "+94771234562", "", "Employee", true, listOf("Read"))
        )
        mockUsers.forEach { repository.addOrUpdateUser(it) }

        val mockAssets = listOf(
            // MACHINERY (1-7)
            Asset("AS_001", "Caterpillar 320 Excavator", "Heavy-duty excavation machinery for ground preparation.", "Machinery", "CAT320-X99", "QR-CAT-001", listOf("https://images.unsplash.com/photo-1579758629938-03607ccdbaba?q=80&w=400"), "2023-01-01", 150000.0, 5.0, "Available", "Excellent", "Colombo Port City", quantity = 1, lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_002", "Komatsu PC210 Excavator", "Hydraulic excavator with high precision controls.", "Machinery", "KOM-PC210-22", "QR-KOM-002", listOf("https://images.unsplash.com/photo-1581094288338-2314dddb7ecb?q=80&w=400"), "2023-08-12", 185000.0, 4.5, "Available", "Good", "Colombo Port City", lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_003", "JCB 3CX Backhoe Loader", "Versatile backhoe for loading and digging tasks.", "Machinery", "JCB-3CX-X8", "QR-JCB-003", listOf("https://images.unsplash.com/photo-1578575437130-527eed3abbec?q=80&w=400"), "2023-10-05", 85000.0, 6.0, "Available", "Excellent", "Kandy Smart City", lastLatitude = 7.2906, lastLongitude = 80.6337),
            Asset("AS_004", "Liebherr Tower Crane", "High-rise construction crane with 50m reach.", "Machinery", "LIE-TC-550-A", "QR-LIE-004", listOf("https://images.unsplash.com/photo-1512351735230-a07ebdf5b5e1?q=80&w=400"), "2023-05-20", 450000.0, 3.5, "Available", "Good", "Colombo Port City", lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_005", "Bobcat S450 Skid Steer", "Compact loader for narrow space site work.", "Machinery", "BOB-S450-9", "QR-BOB-005", listOf("https://images.unsplash.com/photo-1581092921461-7d6560562fd9?q=80&w=400"), "2023-09-10", 35000.0, 10.0, "Available", "Good", "Hambantota Refinery", lastLatitude = 6.1244, lastLongitude = 81.1211),
            Asset("AS_006", "Genie Z-45 Boom Lift", "Articulating boom lift for high altitude access.", "Machinery", "GEN-Z45-B1", "QR-GEN-006", listOf("https://images.unsplash.com/photo-1581094288338-2314dddb7ecb?q=80&w=400"), "2023-11-15", 42000.0, 7.5, "Available", "Good", "Kandy Smart City", lastLatitude = 7.2906, lastLongitude = 80.6337),
            Asset("AS_007", "Atlas Copco Drill Rig", "Surface drill rig for mining and construction.", "Machinery", "ATL-FLEX-D3-K", "QR-ATL-007", listOf("https://images.unsplash.com/photo-1541625602330-2277a4c4b28d?q=80&w=400"), "2023-07-30", 320000.0, 4.0, "Available", "Good", "Hambantota Refinery", lastLatitude = 6.1244, lastLongitude = 81.1211),

            // TOOLS (8-14)
            Asset("AS_008", "DeWalt Drill DCH273", "SDS Plus Hammer Drill for masonry.", "Tools", "DW-DCH273-01", "QR-DW-008", listOf("https://images.unsplash.com/photo-1504148455328-c376907d081c?q=80&w=400"), "2023-05-15", 350.0, 10.0, "Assigned", "Good", "Warehouse HQ", quantity = 5, assignedTo = "Marcus Brody", assignedToId = "EMP_001", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_009", "Hilti Breaker TE 3000", "High-performance concrete demolition breaker.", "Tools", "HIL-TE3000-XX", "QR-HIL-009", listOf("https://images.unsplash.com/photo-1589939705384-5185137a7f0f?q=80&w=400"), "2024-01-20", 2800.0, 12.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_010", "Makita Circular Saw", "Precision cutting tool for wood and metal.", "Tools", "MAK-CS-500-Z", "QR-MAK-010", listOf("https://images.unsplash.com/photo-1544724569-5f546fd6f2b5?q=80&w=400"), "2024-03-22", 220.0, 20.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_011", "Stihl MS 261 Chainsaw", "Professional gas chainsaw for clearing.", "Tools", "STL-MS261-P", "QR-STL-011", listOf("https://images.unsplash.com/photo-1590856029826-c7a73142bbf1?q=80&w=400"), "2024-01-15", 650.0, 15.0, "Available", "Fair", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_012", "Husqvarna K770 Saw", "Power cutter for asphalt and concrete.", "Tools", "HUS-K770-Q", "QR-HUS-012", listOf("https://images.unsplash.com/photo-1544724569-5f546fd6f2b5?q=80&w=400"), "2024-02-28", 1100.0, 18.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_013", "Snap-on Heavy Duty Set", "Mechanics tool set for site maintenance.", "Tools", "SNP-SET-500-A", "QR-SNP-013", listOf("https://images.unsplash.com/photo-1581147036324-c17da42e26c2?q=80&w=400"), "2024-02-15", 2200.0, 5.0, "Available", "Excellent", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_014", "Bosch Laser Level", "Self-leveling 360-degree cross line laser.", "Tools", "BSH-GLL-3-80", "QR-BSH-014", listOf("https://images.unsplash.com/photo-1581092160562-40aa08e78837?q=80&w=400"), "2024-02-10", 180.0, 5.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),

            // VEHICLES (15-19)
            Asset("AS_015", "Toyota Hilux Pickup", "Site utility and personnel transport vehicle.", "Vehicles", "TOY-HLX-2024", "QR-TOY-015", listOf("https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?q=80&w=400"), "2024-01-10", 45000.0, 12.0, "Available", "Good", "Hambantota Refinery", quantity = 2, lastLatitude = 6.1244, lastLongitude = 81.1211),
            Asset("AS_016", "Ford F-150 Lightning", "All-electric site truck with exportable power.", "Vehicles", "FRD-LTG-2024", "QR-FRD-016", listOf("https://images.unsplash.com/photo-1621932953986-15fcf084da0f?q=80&w=400"), "2024-05-01", 65000.0, 8.0, "Available", "Excellent", "Hambantota Refinery", lastLatitude = 6.1244, lastLongitude = 81.1211),
            Asset("AS_017", "Volvo FMX Dump Truck", "Heavy logistics truck for site material moving.", "Vehicles", "VOL-FM-2024-X", "QR-VOL-017", listOf("https://images.unsplash.com/photo-1586864387917-f5383edc183b?q=80&w=400"), "2024-01-15", 125000.0, 9.0, "Available", "Good", "Colombo Port City", lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_018", "Mercedes Sprinter Van", "Personnel logistics and tool transport van.", "Vehicles", "MER-SPR-24-L", "QR-MER-018", listOf("https://images.unsplash.com/photo-1581092160562-40aa08e78837?q=80&w=400"), "2024-04-01", 52000.0, 15.0, "Available", "Good", "Colombo Port City", lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_019", "Toyota Forklift 5-ton", "Industrial forklift for warehouse loading.", "Vehicles", "TOY-FL-10-XT", "QR-TOY-019", listOf("https://images.unsplash.com/photo-1581092160562-40aa08e78837?q=80&w=400"), "2023-11-20", 12000.0, 10.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),

            // IT & TECH (20-24)
            Asset("AS_020", "MacBook Pro M3 Max", "Lead developer and management workstation.", "IT", "MBP-M3-992-K", "QR-APL-020", listOf("https://images.unsplash.com/photo-1517336714468-12b58211074e?q=80&w=400"), "2024-04-10", 2500.0, 15.0, "Assigned", "Excellent", "Warehouse HQ", quantity = 10, assignedTo = "John Manager", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_021", "Cisco Nexus Switch", "Enterprise-grade data center core switch.", "IT", "CSC-NX-9300-X", "QR-CSC-021", listOf("https://images.unsplash.com/photo-1558494949-ef010cbdcc51?q=80&w=400"), "2023-09-15", 12000.0, 10.0, "Available", "Excellent", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_022", "Dell EMC PowerEdge", "Server for site data and local cloud services.", "IT", "DEL-R740-A1", "QR-DEL-022", listOf("https://images.unsplash.com/photo-1558494949-ef010cbdcc51?q=80&w=400"), "2023-11-01", 14000.0, 12.0, "Available", "Good", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_023", "Canon EOS R5 Kit", "High-res camera for site documentation.", "IT", "CAN-R5-99-Z", "QR-CAN-023", listOf("https://images.unsplash.com/photo-1516035069371-29a1b244cc32?q=80&w=400"), "2023-12-12", 3800.0, 10.0, "Available", "Excellent", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_024", "DJI Matrice 300 Drone", "Industrial drone for aerial site mapping.", "IT", "DJI-M300-RTK", "QR-DJI-024", listOf("https://images.unsplash.com/photo-1508614589041-895b88991e3e?q=80&w=400"), "2024-02-05", 15000.0, 20.0, "Available", "Good", "Kandy Smart City", lastLatitude = 7.2906, lastLongitude = 80.6337),

            // POWER & INDUSTRIAL (25-30)
            Asset("AS_025", "Bosch Generator 15kW", "Heavy-duty emergency backup power unit.", "Power", "BSH-GEN-5K-L", "QR-BSH-025", listOf("https://images.unsplash.com/photo-1620714223084-8fcacc6dfd8d?q=80&w=400"), "2023-03-01", 3200.0, 8.0, "Maintenance", "Fair", "Warehouse HQ", quantity = 3, lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_026", "Solar Panel Array 10kW", "Portable solar grid for remote site power.", "Power", "SLR-10KW-P-24", "QR-SLR-026", listOf("https://images.unsplash.com/photo-1508514177221-188b1cf16e9d?q=80&w=400"), "2024-03-10", 8500.0, 5.0, "Available", "Excellent", "Colombo Port City", lastLatitude = 6.9319, lastLongitude = 79.8412),
            Asset("AS_027", "Tesla Powerwall 2", "Site energy storage and load management.", "Power", "TSL-PW-02-B", "QR-TSL-027", listOf("https://images.unsplash.com/photo-1548333341-9c6c4987f7f4?q=80&w=400"), "2024-03-30", 9500.0, 5.0, "Available", "Excellent", "Warehouse HQ", lastLatitude = 6.9167, lastLongitude = 79.8733),
            Asset("AS_028", "Honda Trash Pump", "High-capacity pump for site dewatering.", "Industrial", "HON-WB30-XT", "QR-HON-028", listOf("https://images.unsplash.com/photo-1581094794329-c8112a89af12?q=80&w=400"), "2023-06-20", 850.0, 12.0, "Available", "Fair", "Hambantota Refinery", lastLatitude = 6.1244, lastLongitude = 81.1211),
            Asset("AS_029", "Sullair Air Compressor", "Industrial compressor for pneumatic tools.", "Industrial", "SUL-AIR-185-K", "QR-SUL-029", listOf("https://images.unsplash.com/photo-1590959651373-a3db0f38a961?q=80&w=400"), "2023-12-01", 18000.0, 7.0, "Available", "Good", "Hambantota Refinery", lastLatitude = 6.1244, lastLongitude = 81.1211),
            Asset("AS_030", "Trimble R12i GNSS", "Survey-grade GPS for site mapping.", "Measurement", "TRM-R12I-99", "QR-TRM-030", listOf("https://images.unsplash.com/photo-1581092580497-e0d23cbdf1dc?q=80&w=400"), "2024-04-05", 25000.0, 10.0, "Available", "Excellent", "Kandy Smart City", lastLatitude = 7.2906, lastLongitude = 80.6337)
        )
        mockAssets.forEach { repository.addAsset(it) }
        
        repository.logMaintenance(MaintenanceRequest("MNT_001", "AS_025", "Bosch Generator 15kW", "Sarah Auditor", "Engine oil change and filter swap.", "Routine", "2024-05-10", "2024-05-12", 150.0, "Completed", "Completed", emptyList(), "Sam Silva", "Filter Kit"))

        logActivity("System", "Enterprise Seed", "Asset Guard database populated with 30 high-fidelity enterprise units.")
    }

    fun refreshCombinedAudits() {
        viewModelScope.launch {
            _combinedAudits.value = repository.getCombinedAudits()
        }
    }

    fun forceManualSync() {
        viewModelScope.launch {
            isLoading = true
            repository.scheduleOfflineSync()
            kotlinx.coroutines.delay(1200)
            repository.loadLocalData()
            refreshCombinedAudits()
            isLoading = false
        }
    }

    fun selectAsset(asset: Asset?) {
        selectedAsset = asset
    }

    fun addAsset(
        name: String, description: String, category: String, serialNumber: String,
        barcode: String, purchaseDate: String, purchasePrice: Double,
        depreciationRate: Double, currentSite: String, locationDetails: String = "",
        condition: String = "Good", quantity: Int = 1, imageUrls: List<String>,
        latitude: Double = 0.0, longitude: Double = 0.0,
        warrantyExpiration: String? = null, manualUrl: String? = null,
        onSuccess: () -> Unit
    ) {
        isLoading = true
        val newAsset = Asset(
            id = "ASSET_${System.currentTimeMillis()}",
            name = name.trim(), description = description.trim(), category = category,
            serialNumber = serialNumber.trim(), barcode = barcode.ifBlank { "QR_${System.currentTimeMillis()}" },
            purchaseDate = purchaseDate.ifBlank { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) },
            purchasePrice = purchasePrice, depreciationRate = depreciationRate,
            currentSite = currentSite, locationDetails = locationDetails,
            condition = condition, quantity = quantity, imageUrls = imageUrls, status = "Available",
            lastLatitude = latitude, lastLongitude = longitude,
            warrantyExpiration = warrantyExpiration, manualUrl = manualUrl,
            nextMaintenanceDate = calculatePredictiveMaintenance(condition, purchaseDate)
        )
        viewModelScope.launch {
            repository.addAsset(newAsset)
            logActivity("Manager", "Add Asset", "Registered: ${newAsset.name}", newAsset.id)
            isLoading = false
            onSuccess()
        }
    }

    private fun calculatePredictiveMaintenance(condition: String, purchaseDate: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val cal = Calendar.getInstance()
        try { cal.time = sdf.parse(purchaseDate) ?: Date() } catch (e: Exception) { cal.time = Date() }
        
        val monthsToAdd = when(condition) {
            "Excellent" -> 12
            "Good" -> 6
            "Fair" -> 3
            else -> 1
        }
        cal.add(Calendar.MONTH, monthsToAdd)
        return sdf.format(cal.time)
    }

    fun updateAsset(asset: Asset, onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            val updatedAsset = asset.copy(nextMaintenanceDate = calculatePredictiveMaintenance(asset.condition, asset.purchaseDate))
            repository.updateAsset(updatedAsset)
            if (selectedAsset?.id == asset.id) selectedAsset = updatedAsset
            logActivity("Manager", "Update Asset", "Modified unit profile: ${asset.name}", asset.id)
            isLoading = false
            onSuccess()
        }
    }

    fun deleteAsset(assetId: String, onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            repository.deleteAsset(assetId)
            logActivity("Manager", "Delete Asset", "Removed unit: $assetId from corporate registry.")
            isLoading = false
            onSuccess()
        }
    }

    fun checkoutAsset(asset: Asset, employeeId: String, employeeName: String, companyName: String, siteId: String, siteName: String, onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            val assignment = Assignment(
                id = "ASS_${System.currentTimeMillis()}", assetId = asset.id, assetName = asset.name,
                employeeId = employeeId, employeeName = employeeName, companyName = companyName,
                projectId = siteId, projectName = siteName, assignedDate = System.currentTimeMillis()
            )
            repository.addAssignment(assignment)
            repository.updateAsset(asset.copy(status = "Assigned", assignedTo = employeeName, assignedToId = employeeId, assignedProject = siteName, assignedCompany = companyName, currentSite = siteName))
            logActivity(employeeName, "Checkout", "Handover unit: ${asset.name} to $employeeName ($companyName)", asset.id)
            isLoading = false
            onSuccess()
        }
    }

    fun checkInAsset(asset: Asset, onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            repository.updateAsset(asset.copy(status = "Available", assignedTo = null, assignedToId = null, assignedProject = null, assignedCompany = null))
            logActivity("Manager", "Return", "Asset ${asset.name} returned to internal fleet.", asset.id)
            isLoading = false
            onSuccess()
        }
    }

    fun isWithinSiteGeofence(site: Site, lat: Double, lng: Double): Boolean {
        val earthRadius = 6371000.0
        val dLat = Math.toRadians(lat - site.latitude)
        val dLng = Math.toRadians(lng - site.longitude)
        val a = sin(dLat / 2).pow(2.0) + cos(Math.toRadians(site.latitude)) * cos(Math.toRadians(lat)) * sin(dLng / 2).pow(2.0)
        return (earthRadius * 2 * atan2(sqrt(a), sqrt(1 - a))) <= site.radiusMeters
    }

    fun performLocationVerifiedAudit(asset: Asset, site: Site, auditorName: String, condition: String, notes: String, latitude: Double, longitude: Double, timestamp: Long, onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            val finalLat = if (isGpsLoggingEnabled) latitude else 0.0
            val finalLng = if (isGpsLoggingEnabled) longitude else 0.0
            val isVerified = if (isGpsLoggingEnabled) isWithinSiteGeofence(site, latitude, longitude) else true
            
            val auditLog = AuditLog(
                id = "AUDIT_${System.currentTimeMillis()}", assetId = asset.id, assetName = asset.name,
                auditorName = auditorName, timestamp = timestamp, latitude = finalLat, longitude = finalLng,
                siteId = site.id, siteName = site.name, isNearSite = isVerified, condition = condition, statusDescription = notes
            )
            repository.logAudit(auditLog)
            
            val updatedAsset = asset.copy(
                lastAuditedAt = timestamp, 
                currentSite = site.name, 
                condition = condition, 
                status = "Available",
                lastLatitude = finalLat,
                lastLongitude = finalLng
            )
            repository.updateAsset(updatedAsset)

            refreshCombinedAudits()
            logActivity(auditorName, "Audit", "Field verification for ${asset.name}. Location Match: $isVerified", asset.id)
            isLoading = false
            onSuccess()
        }
    }

    fun scheduleMaintenance(
        asset: Asset, 
        description: String, 
        reporter: String, 
        breakdownType: String, 
        date: String, 
        cost: Double, 
        photoUrls: List<String>, 
        status: String = "Scheduled", 
        mechanicName: String? = null, 
        partsReplaced: String? = null, 
        onSuccess: () -> Unit
    ) {
        isLoading = true
        viewModelScope.launch {
            val request = MaintenanceRequest(
                id = "MNT_${System.currentTimeMillis()}", assetId = asset.id, assetName = asset.name,
                reporterName = reporter, description = description, breakdownType = breakdownType,
                scheduledDate = date, repairCost = cost, status = status,
                damagePhotoUrls = photoUrls,
                mechanicName = mechanicName, partsReplaced = partsReplaced
            )
            repository.logMaintenance(request)
            if (status != "Completed") repository.updateAsset(asset.copy(status = "Maintenance", condition = "Broken Down"))
            else repository.updateAsset(asset.copy(status = "Available", condition = "Good"))
            
            logActivity(reporter, "Maintenance", "Logged $status maintenance with ${photoUrls.size} evidence photos for ${asset.name}", asset.id)
            isLoading = false
            onSuccess()
        }
    }

    fun logActivity(username: String, actionType: String, details: String, assetId: String? = null) {
        viewModelScope.launch {
            repository.logSecurityActivity(ActivityLog(id = "LOG_${System.currentTimeMillis()}", assetId = assetId, userId = username, userName = username, action = actionType, details = details, timestamp = System.currentTimeMillis()))
        }
    }

    suspend fun resolveAssetByBarcode(barcode: String): Asset? = repository.getAssetByBarcode(barcode)

    fun getTotalValue(): Double = assets.value.sumOf { it.purchasePrice * it.quantity.coerceAtLeast(1) }
    fun getCurrentDepreciatedValue(): Double = assets.value.sumOf { it.getCurrentValue() }
    fun getCategoryCount(): Map<String, Int> = assets.value.groupBy { it.category }.mapValues { it.value.size }
    fun getValuationBySite(): Map<String, Double> = assets.value.groupBy { it.currentSite }.mapValues { it.value.sumOf { a -> a.purchasePrice * a.quantity } }
    fun getMaintenanceTotalCost(): Double = maintenanceRequests.value.sumOf { it.repairCost }

    fun simulateBulkCSVImport(csvText: String, onSuccess: (Int) -> Unit) {
        isLoading = true
        viewModelScope.launch {
            val rows = csvText.split("\n")
            rows.forEach { row ->
                val tokens = row.split(",")
                if (tokens.size >= 5) {
                    addAsset(tokens[0].trim(), "", tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), "", tokens[4].trim().toDoubleOrNull() ?: 100.0, 10.0, "Warehouse HQ", imageUrls = emptyList(), onSuccess = {})
                }
            }
            isLoading = false
            onSuccess(rows.size)
        }
    }
}

package com.tourism.assetguard.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.*

import com.tourism.assetguard.data.model.*
import com.tourism.assetguard.data.remote.FirebaseSyncManager
import com.tourism.assetguard.util.OfflineSyncWorker
import com.tourism.assetguard.data.local.LocalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AssetRepository(private val context: Context) {
    private val db = LocalDatabase.getDatabase(context)
    
    fun scheduleOfflineSync() {
        try {
            val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val syncRequest = OneTimeWorkRequestBuilder<OfflineSyncWorker>().setConstraints(constraints).build()
            WorkManager.getInstance(context).enqueueUniqueWork("OfflineSyncTask", ExistingWorkPolicy.REPLACE, syncRequest)
        } catch (e: Exception) {
            Log.e("AssetRepository", "Sync Error: ${e.message}")
        }
    }
    
    private val userDao = db.userDao()
    private val assetDao = db.assetDao()
    private val assignmentDao = db.assignmentDao()
    private val auditDao = db.auditDao()
    private val maintenanceDao = db.maintenanceDao()
    private val requestDao = db.requestDao()
    private val siteDao = db.siteDao()
    private val notificationDao = db.notificationDao()
    private val activityLogDao = db.activityLogDao()
    private val reportDao = db.reportDao()

    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets.asStateFlow()

    private val _audits = MutableStateFlow<List<AuditLog>>(emptyList())
    val audits: StateFlow<List<AuditLog>> = _audits.asStateFlow()

    private val _assignments = MutableStateFlow<List<Assignment>>(emptyList())
    val assignments: StateFlow<List<Assignment>> = _assignments.asStateFlow()

    private val _maintenanceRequests = MutableStateFlow<List<MaintenanceRequest>>(emptyList())
    val maintenanceRequests: StateFlow<List<MaintenanceRequest>> = _maintenanceRequests.asStateFlow()

    private val _assetRequests = MutableStateFlow<List<AssetRequest>>(emptyList())
    val assetRequests: StateFlow<List<AssetRequest>> = _assetRequests.asStateFlow()

    private val _sites = MutableStateFlow<List<Site>>(emptyList())
    val sites: StateFlow<List<Site>> = _sites.asStateFlow()

    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
    val notifications: StateFlow<List<Notification>> = _notifications.asStateFlow()

    private val _activityLogs = MutableStateFlow<List<ActivityLog>>(emptyList())
    val activityLogs: StateFlow<List<ActivityLog>> = _activityLogs.asStateFlow()

    private val _reports = MutableStateFlow<List<Report>>(emptyList())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val repositoryScope = CoroutineScope(Dispatchers.IO)

    init {
        repositoryScope.launch { loadLocalData() }
    }

    suspend fun loadLocalData() {
        withContext(Dispatchers.IO) {
            _assets.value = assetDao.getAllAssets().map { it.toAsset() }
            _audits.value = auditDao.getAllAudits().map { it.toAuditLog() }
            _assignments.value = assignmentDao.getAllAssignments().map { it.toAssignment() }
            _maintenanceRequests.value = maintenanceDao.getAllRequests().map { it.toMaintenanceRequest() }
            _assetRequests.value = requestDao.getAllRequests().map { it.toAssetRequest() }
            _sites.value = siteDao.getAllSites().map { it.toSite() }
            _notifications.value = notificationDao.getAllNotifications().map { it.toNotification() }
            _activityLogs.value = activityLogDao.getAllActivityLogs().map { it.toActivityLog() }
            _reports.value = reportDao.getAllReports().map { it.toReport() }
            _users.value = userDao.getAllUsers().map { it.toUser() }
        }
    }

    suspend fun addOrUpdateUser(user: User) = withContext(Dispatchers.IO) {
        userDao.insertUser(user.toOfflineUser())
        loadLocalData()
    }

    suspend fun addAsset(asset: Asset): Boolean = withContext(Dispatchers.IO) {
        assetDao.insertAsset(asset.toOfflineAsset(isPendingSync = true))
        loadLocalData()
        scheduleOfflineSync()
        true
    }

    suspend fun updateAsset(asset: Asset): Boolean = withContext(Dispatchers.IO) {
        assetDao.updateAsset(asset.toOfflineAsset(isPendingSync = true))
        loadLocalData()
        scheduleOfflineSync()
        true
    }

    suspend fun deleteAsset(assetId: String): Boolean = withContext(Dispatchers.IO) {
        assetDao.deleteAsset(assetId)
        loadLocalData()
        true
    }

    suspend fun getAssetByBarcode(barcode: String): Asset? = withContext(Dispatchers.IO) {
        assetDao.getAssetByBarcode(barcode)?.toAsset()
    }

    suspend fun addAssignment(assignment: Assignment) = withContext(Dispatchers.IO) {
        assignmentDao.insertAssignment(assignment.toOfflineAssignment(isPendingSync = true))
        loadLocalData()
        scheduleOfflineSync()
    }

    suspend fun logAudit(auditLog: AuditLog): Boolean = withContext(Dispatchers.IO) {
        auditDao.insertAudit(auditLog.toOfflineAuditLog(isPendingSync = true))
        val asset = assetDao.getAssetById(auditLog.assetId)?.toAsset()
        if (asset != null) {
            val updated = asset.copy(lastAuditedAt = auditLog.timestamp, currentSite = auditLog.siteName, condition = auditLog.condition, status = "Available")
            assetDao.updateAsset(updated.toOfflineAsset(isPendingSync = true))
        }
        loadLocalData()
        scheduleOfflineSync()
        true
    }

    suspend fun logMaintenance(request: MaintenanceRequest) = withContext(Dispatchers.IO) {
        maintenanceDao.insertRequest(request.toOfflineMaintenanceRequest(isPendingSync = true))
        loadLocalData()
        scheduleOfflineSync()
    }

    suspend fun addSite(site: Site) = withContext(Dispatchers.IO) {
        siteDao.insertSite(site.toOfflineSite())
        loadLocalData()
    }

    suspend fun logSecurityActivity(log: ActivityLog) = withContext(Dispatchers.IO) {
        activityLogDao.insertActivityLog(log.toOfflineActivityLog(isPendingSync = true))
        loadLocalData()
        scheduleOfflineSync()
    }

    suspend fun getCombinedAudits(): List<AuditLog> = withContext(Dispatchers.IO) {
        val local = auditDao.getAllAudits().map { it.toAuditLog() }
        val remote = try { FirebaseSyncManager.fetchCloudAudits() } catch (e: Exception) { emptyList() }
        (local + remote).distinctBy { it.id }.sortedByDescending { it.timestamp }
    }
}

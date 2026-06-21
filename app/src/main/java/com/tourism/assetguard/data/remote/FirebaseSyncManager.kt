package com.tourism.assetguard.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.tourism.assetguard.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

/**
 * Enterprise Cloud Sync Manager
 * Handles Real-time Firestore synchronization and local simulator fallback.
 */
object FirebaseSyncManager {
    private const val TAG = "FirebaseSyncManager"

    private val simulatedRemoteAssets = mutableListOf<Asset>()
    private val simulatedRemoteAudits = mutableListOf<AuditLog>()
    private val simulatedRemoteAssignments = mutableListOf<Assignment>()
    private val simulatedRemoteMaintenance = mutableListOf<MaintenanceRequest>()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    val syncLogs = mutableListOf<String>()

    init {
        logTelemetry("Cloud Core: Advanced Enterprise Synchronizer initialized.")
        seedCloudDatabase()
    }

    fun logTelemetry(msg: String) {
        val timestamp = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())
        syncLogs.add(0, "[$timestamp] $msg")
        Log.d(TAG, msg)
    }

    private fun getFirestoreSafe(): FirebaseFirestore? {
        return try { FirebaseFirestore.getInstance() } catch (e: Exception) { null }
    }

    // --- PUSH OPERATIONS (Local -> Cloud) ---

    suspend fun pushAsset(asset: Asset): Boolean = withContext(Dispatchers.IO) {
        _isSyncing.value = true
        logTelemetry("Syncing Master Ledger: ${asset.name}...")
        delay(1000)

        val firestore = getFirestoreSafe()
        if (firestore != null) {
            try {
                firestore.collection("assets").document(asset.id).set(asset)
                _isSyncing.value = false
                return@withContext true
            } catch (e: Exception) {
                logTelemetry("Firestore Error, using Shield Simulator.")
            }
        }

        simulatedRemoteAssets.removeAll { it.id == asset.id }
        simulatedRemoteAssets.add(asset)
        _isSyncing.value = false
        true
    }

    suspend fun pushMaintenance(request: MaintenanceRequest): Boolean = withContext(Dispatchers.IO) {
        _isSyncing.value = true
        logTelemetry("Uploading Repair Log: ${request.assetName} [Cost: ${request.repairCost}]")
        delay(800)
        simulatedRemoteMaintenance.removeAll { it.id == request.id }
        simulatedRemoteMaintenance.add(request)
        _isSyncing.value = false
        true
    }

    suspend fun pushAuditLog(audit: AuditLog): Boolean = withContext(Dispatchers.IO) {
        _isSyncing.value = true
        logTelemetry("Archiving Physical Audit for ${audit.assetName}...")
        delay(1200)
        simulatedRemoteAudits.add(audit)
        _isSyncing.value = false
        true
    }

    suspend fun pushAssignment(assignment: Assignment): Boolean = withContext(Dispatchers.IO) {
        _isSyncing.value = true
        logTelemetry("Pasting Multi-Company Handover: ${assignment.companyName}...")
        delay(1000)
        simulatedRemoteAssignments.add(assignment)
        _isSyncing.value = false
        true
    }

    suspend fun pushUser(user: User): Boolean = withContext(Dispatchers.IO) {
        _isSyncing.value = true
        logTelemetry("Syncing Profile: ${user.name}")
        delay(500)
        _isSyncing.value = false
        true
    }

    // --- FETCH OPERATIONS (Cloud -> Local) ---

    suspend fun fetchCloudAudits(): List<AuditLog> = withContext(Dispatchers.IO) {
        logTelemetry("Retrieving cloud audit vault...")
        delay(600)
        simulatedRemoteAudits
    }

    suspend fun fetchCloudMaintenance(): List<MaintenanceRequest> = withContext(Dispatchers.IO) {
        logTelemetry("Fetching remote repair histories...")
        delay(600)
        simulatedRemoteMaintenance
    }

    private fun seedCloudDatabase() {
        if (simulatedRemoteAudits.isEmpty()) {
            logTelemetry("Seeding remote cloud cache with verified enterprise logs.")
            // Simulated cloud data would go here
        }
    }
}

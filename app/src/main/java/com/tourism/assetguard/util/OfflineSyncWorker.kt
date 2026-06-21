package com.tourism.assetguard.util

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tourism.assetguard.data.local.LocalDatabase
import com.tourism.assetguard.data.model.toAsset
import com.tourism.assetguard.data.model.toAssignment
import com.tourism.assetguard.data.model.toAuditLog
import com.tourism.assetguard.data.remote.FirebaseSyncManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfflineSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    companion object {
        private const val TAG = "OfflineSyncWorker"
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

        FirebaseSyncManager.logTelemetry(
            "Background Sync triggered by WorkManager."
        )

        Log.d(TAG, "Executing background synchronization task")

        val db = LocalDatabase.getDatabase(applicationContext)

        val assetDao = db.assetDao()
        val assignmentDao = db.assignmentDao()
        val auditDao = db.auditDao()
        val maintenanceDao = db.maintenanceDao()
        val requestDao = db.requestDao()
        val activityLogDao = db.activityLogDao()

        var syncedAny = false

        try {

            // 1. Sync Pending Assets
            val pendingAssets = assetDao.getPendingAssets()

            if (pendingAssets.isNotEmpty()) {

                FirebaseSyncManager.logTelemetry(
                    "Found ${pendingAssets.size} pending assets."
                )

                for (offlineAsset in pendingAssets) {

                    val success = FirebaseSyncManager.pushAsset(
                        offlineAsset.toAsset()
                    )

                    if (success) {
                        assetDao.updateAsset(
                            offlineAsset.copy(
                                isPendingSync = false
                            )
                        )

                        syncedAny = true
                    }
                }
            }

            // 2. Sync Pending Assignments
            val pendingAssignments =
                assignmentDao.getPendingAssignments()

            if (pendingAssignments.isNotEmpty()) {

                FirebaseSyncManager.logTelemetry(
                    "Found ${pendingAssignments.size} pending assignments."
                )

                for (offlineAssign in pendingAssignments) {

                    val success =
                        FirebaseSyncManager.pushAssignment(
                            offlineAssign.toAssignment()
                        )

                    if (success) {

                        assignmentDao.updateAssignment(
                            offlineAssign.copy(
                                isPendingSync = false
                            )
                        )

                        syncedAny = true
                    }
                }
            }

            // 3. Sync Pending Audits
            val pendingAudits =
                auditDao.getPendingAudits()

            if (pendingAudits.isNotEmpty()) {

                FirebaseSyncManager.logTelemetry(
                    "Found ${pendingAudits.size} pending audits."
                )

                for (offlineAudit in pendingAudits) {

                    val success =
                        FirebaseSyncManager.pushAuditLog(
                            offlineAudit.toAuditLog()
                        )

                    if (success) {

                        auditDao.insertAudit(
                            offlineAudit.copy(
                                isPendingSync = false
                            )
                        )

                        syncedAny = true
                    }
                }
            }

            // 4. Sync Maintenance Requests
            val pendingMaint =
                maintenanceDao.getPendingRequests()

            if (pendingMaint.isNotEmpty()) {

                for (offlineMaint in pendingMaint) {

                    maintenanceDao.insertRequest(
                        offlineMaint.copy(
                            isPendingSync = false
                        )
                    )

                    syncedAny = true
                }
            }

            // 5. Sync Asset Requests
            val pendingReqs =
                requestDao.getPendingRequests()

            if (pendingReqs.isNotEmpty()) {

                for (offlineReq in pendingReqs) {

                    requestDao.insertRequest(
                        offlineReq.copy(
                            isPendingSync = false
                        )
                    )

                    syncedAny = true
                }
            }

            // 6. Sync Activity Logs
            val pendingLogs =
                activityLogDao.getPendingActivityLogs()

            if (pendingLogs.isNotEmpty()) {

                for (offlineLog in pendingLogs) {

                    activityLogDao.insertActivityLog(
                        offlineLog.copy(
                            isPendingSync = false
                        )
                    )

                    syncedAny = true
                }
            }

            if (syncedAny) {
                FirebaseSyncManager.logTelemetry(
                    "Synchronization completed successfully."
                )
            } else {
                FirebaseSyncManager.logTelemetry(
                    "No pending offline records found."
                )
            }

            Result.success()

        } catch (e: Exception) {

            FirebaseSyncManager.logTelemetry(
                "Background Sync Failed: ${e.message}"
            )

            Log.e(
                TAG,
                "Synchronization failed",
                e
            )

            Result.retry()
        }
    }
}
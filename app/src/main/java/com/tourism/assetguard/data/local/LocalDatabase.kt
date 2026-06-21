package com.tourism.assetguard.data.local

import android.content.Context
import androidx.room.*
import com.tourism.assetguard.data.model.*

@Dao
interface UserDao {
    @Query("SELECT * FROM offline_users")
    suspend fun getAllUsers(): List<OfflineUser>

    @Query("SELECT * FROM offline_users WHERE userId = :userId LIMIT 1")
    suspend fun getUserById(userId: String): OfflineUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: OfflineUser)

    @Update
    suspend fun updateUser(user: OfflineUser)

    @Query("DELETE FROM offline_users WHERE userId = :userId")
    suspend fun deleteUser(userId: String)

    @Query("DELETE FROM offline_users")
    suspend fun clearUsers()
}

@Dao
interface AssetDao {
    @Query("SELECT * FROM offline_assets")
    suspend fun getAllAssets(): List<OfflineAsset>

    @Query("SELECT * FROM offline_assets WHERE id = :assetId LIMIT 1")
    suspend fun getAssetById(assetId: String): OfflineAsset?

    @Query("SELECT * FROM offline_assets WHERE barcode = :barcode LIMIT 1")
    suspend fun getAssetByBarcode(barcode: String): OfflineAsset?

    @Query("SELECT * FROM offline_assets WHERE isPendingSync = 1")
    suspend fun getPendingAssets(): List<OfflineAsset>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssets(assets: List<OfflineAsset>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: OfflineAsset)

    @Update
    suspend fun updateAsset(asset: OfflineAsset)

    @Query("DELETE FROM offline_assets WHERE id = :assetId")
    suspend fun deleteAsset(assetId: String)

    @Query("DELETE FROM offline_assets")
    suspend fun clearAssets()
}

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM offline_assignments ORDER BY assignedDate DESC")
    suspend fun getAllAssignments(): List<OfflineAssignment>

    @Query("SELECT * FROM offline_assignments WHERE employeeId = :empId")
    suspend fun getAssignmentsForEmployee(empId: String): List<OfflineAssignment>

    @Query("SELECT * FROM offline_assignments WHERE isPendingSync = 1")
    suspend fun getPendingAssignments(): List<OfflineAssignment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: OfflineAssignment)

    @Update
    suspend fun updateAssignment(assignment: OfflineAssignment)

    @Query("DELETE FROM offline_assignments")
    suspend fun clearAssignments()
}

@Dao
interface AuditDao {
    @Query("SELECT * FROM offline_audit_logs ORDER BY timestamp DESC")
    suspend fun getAllAudits(): List<OfflineAuditLog>

    @Query("SELECT * FROM offline_audit_logs WHERE isPendingSync = 1")
    suspend fun getPendingAudits(): List<OfflineAuditLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudit(audit: OfflineAuditLog)

    @Query("DELETE FROM offline_audit_logs")
    suspend fun clearAudits()
}

@Dao
interface MaintenanceDao {
    @Query("SELECT * FROM offline_maintenance_requests ORDER BY scheduledDate DESC")
    suspend fun getAllRequests(): List<OfflineMaintenanceRequest>

    @Query("SELECT * FROM offline_maintenance_requests WHERE isPendingSync = 1")
    suspend fun getPendingRequests(): List<OfflineMaintenanceRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: OfflineMaintenanceRequest)

    @Update
    suspend fun updateRequest(request: OfflineMaintenanceRequest)

    @Query("DELETE FROM offline_maintenance_requests")
    suspend fun clearRequests()
}

@Dao
interface RequestDao {
    @Query("SELECT * FROM offline_asset_requests ORDER BY id DESC")
    suspend fun getAllRequests(): List<OfflineAssetRequest>

    @Query("SELECT * FROM offline_asset_requests WHERE requesterId = :reqId")
    suspend fun getRequestsForEmployee(reqId: String): List<OfflineAssetRequest>

    @Query("SELECT * FROM offline_asset_requests WHERE isPendingSync = 1")
    suspend fun getPendingRequests(): List<OfflineAssetRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: OfflineAssetRequest)

    @Update
    suspend fun updateRequest(request: OfflineAssetRequest)

    @Query("DELETE FROM offline_asset_requests")
    suspend fun clearRequests()
}

@Dao
interface SiteDao {
    @Query("SELECT * FROM offline_sites")
    suspend fun getAllSites(): List<OfflineSite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSite(site: OfflineSite)

    @Query("DELETE FROM offline_sites WHERE id = :siteId")
    suspend fun deleteSite(siteId: String)

    @Query("DELETE FROM offline_sites")
    suspend fun clearSites()
}

@Dao
interface NotificationDao {
    @Query("SELECT * FROM offline_notifications ORDER BY timestamp DESC")
    suspend fun getAllNotifications(): List<OfflineNotification>

    @Query("SELECT * FROM offline_notifications WHERE targetUserId = :userId ORDER BY timestamp DESC")
    suspend fun getNotificationsForUser(userId: String): List<OfflineNotification>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: OfflineNotification)

    @Query("DELETE FROM offline_notifications")
    suspend fun clearNotifications()
}

@Dao
interface ActivityLogDao {
    @Query("SELECT * FROM offline_activity_logs ORDER BY timestamp DESC")
    suspend fun getAllActivityLogs(): List<OfflineActivityLog>

    @Query("SELECT * FROM offline_activity_logs WHERE isPendingSync = 1")
    suspend fun getPendingActivityLogs(): List<OfflineActivityLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivityLog(log: OfflineActivityLog)

    @Query("DELETE FROM offline_activity_logs")
    suspend fun clearActivityLogs()
}

@Dao
interface ReportDao {
    @Query("SELECT * FROM offline_reports ORDER BY timestamp DESC")
    suspend fun getAllReports(): List<OfflineReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: OfflineReport)

    @Query("DELETE FROM offline_reports")
    suspend fun clearReports()
}

@Database(
    entities = [
        OfflineUser::class,
        OfflineAsset::class,
        OfflineAssignment::class,
        OfflineAuditLog::class,
        OfflineMaintenanceRequest::class,
        OfflineAssetRequest::class,
        OfflineSite::class,
        OfflineNotification::class,
        OfflineActivityLog::class,
        OfflineReport::class
    ],
    version = 7,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun assetDao(): AssetDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun auditDao(): AuditDao
    abstract fun maintenanceDao(): MaintenanceDao
    abstract fun requestDao(): RequestDao
    abstract fun siteDao(): SiteDao
    abstract fun notificationDao(): NotificationDao
    abstract fun activityLogDao(): ActivityLogDao
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "asset_guard_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

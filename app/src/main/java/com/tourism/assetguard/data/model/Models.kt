package com.tourism.assetguard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "offline_users")
data class OfflineUser(
    @PrimaryKey val userId: String,
    val name: String,
    val email: String,
    val phone: String,
    val profileImage: String,
    val role: String,
    val isEnabled: Boolean,
    val permissionsJson: String
)

fun OfflineUser.toUser(): User {
    val perms = try { Json.Default.decodeFromString<List<String>>(permissionsJson) } catch (e: Exception) { emptyList() }
    return User(userId, name, email, phone, profileImage, role, isEnabled, perms)
}

fun User.toOfflineUser(): OfflineUser {
    return OfflineUser(userId, name, email, phone, profileImage, role, isEnabled, Json.Default.encodeToString(permissions))
}


// 2. Offline Asset Entity

@Entity(tableName = "offline_assets")
data class OfflineAsset(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: String,
    val serialNumber: String,
    val barcode: String,
    val imageUrlsJson: String,
    val purchaseDate: String,
    val purchasePrice: Double,
    val depreciationRate: Double,
    val status: String,
    val condition: String,
    val currentSite: String,
    val locationDetails: String,
    val quantity: Int,
    val assignedTo: String?,
    val assignedToId: String?,
    val assignedProject: String?,
    val assignedCompany: String?,
    val lastAuditedAt: Long?,
    val lastLatitude: Double = 0.0,
    val lastLongitude: Double = 0.0,
    val warrantyExpiration: String?,
    val manualUrl: String?,
    val nextMaintenanceDate: String?,
    val isPendingSync: Boolean = false
)

fun OfflineAsset.toAsset(): Asset {
    val imgs = try { Json.Default.decodeFromString<List<String>>(imageUrlsJson) } catch (e: Exception) { emptyList() }
    return Asset(
        id = id, name = name, description = description, category = category,
        serialNumber = serialNumber, barcode = barcode, imageUrls = imgs,
        purchaseDate = purchaseDate, purchasePrice = purchasePrice, depreciationRate = depreciationRate,
        status = status, condition = condition, currentSite = currentSite, 
        locationDetails = locationDetails, quantity = quantity,
        assignedTo = assignedTo, assignedToId = assignedToId,
        assignedProject = assignedProject, assignedCompany = assignedCompany,
        lastAuditedAt = lastAuditedAt,
        lastLatitude = lastLatitude, lastLongitude = lastLongitude,
        warrantyExpiration = warrantyExpiration, manualUrl = manualUrl,
        nextMaintenanceDate = nextMaintenanceDate
    )
}

fun Asset.toOfflineAsset(isPendingSync: Boolean = false): OfflineAsset {
    return OfflineAsset(
        id = id, name = name, description = description, category = category,
        serialNumber = serialNumber, barcode = barcode, imageUrlsJson = Json.Default.encodeToString(imageUrls),
        purchaseDate = purchaseDate, purchasePrice = purchasePrice, depreciationRate = depreciationRate,
        status = status, condition = condition, currentSite = currentSite,
        locationDetails = locationDetails, quantity = quantity,
        assignedTo = assignedTo, assignedToId = assignedToId,
        assignedProject = assignedProject, assignedCompany = assignedCompany,
        lastAuditedAt = lastAuditedAt,
        lastLatitude = lastLatitude, lastLongitude = lastLongitude,
        warrantyExpiration = warrantyExpiration, manualUrl = manualUrl,
        nextMaintenanceDate = nextMaintenanceDate,
        isPendingSync = isPendingSync
    )
}


// 3. Offline Assignment Entity

@Entity(tableName = "offline_assignments")
data class OfflineAssignment(
    @PrimaryKey val id: String,
    val assetId: String,
    val assetName: String,
    val employeeId: String,
    val employeeName: String,
    val companyName: String,
    val projectId: String,
    val projectName: String,
    val assignedDate: Long,
    val returnedDate: Long?,
    val status: String,
    val isPendingSync: Boolean = false
)

fun OfflineAssignment.toAssignment(): Assignment {
    return Assignment(id, assetId, assetName, employeeId, employeeName, companyName, projectId, projectName, assignedDate, returnedDate, status)
}

fun Assignment.toOfflineAssignment(isPendingSync: Boolean = false): OfflineAssignment {
    return OfflineAssignment(id, assetId, assetName, employeeId, employeeName, companyName, projectId, projectName, assignedDate, returnedDate, status, isPendingSync)
}


// 4. Offline Audit Log Entity

@Entity(tableName = "offline_audit_logs")
data class OfflineAuditLog(
    @PrimaryKey val id: String,
    val assetId: String,
    val assetName: String,
    val auditorName: String,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double,
    val siteId: String,
    val siteName: String,
    val isNearSite: Boolean,
    val condition: String,
    val statusDescription: String,
    val isPendingSync: Boolean = false
)

fun OfflineAuditLog.toAuditLog(): AuditLog {
    return AuditLog(id, assetId, assetName, auditorName, timestamp, latitude, longitude, siteId, siteName, isNearSite, condition, statusDescription)
}

fun AuditLog.toOfflineAuditLog(isPendingSync: Boolean = false): OfflineAuditLog {
    return OfflineAuditLog(id,
        assetId, assetName, auditorName, timestamp, latitude, longitude, siteId, siteName, isNearSite, condition, statusDescription, isPendingSync)
}


// 5. Offline Maintenance Request Entity

@Entity(tableName = "offline_maintenance_requests")
data class OfflineMaintenanceRequest(
    @PrimaryKey val id: String,
    val assetId: String,
    val assetName: String,
    val reporterName: String,
    val description: String,
    val breakdownType: String,
    val scheduledDate: String,
    val completionDate: String?,
    val repairCost: Double,
    val notes: String,
    val status: String,
    val damagePhotoUrlsJson: String,
    val mechanicName: String?,
    val partsReplaced: String?,
    val isPendingSync: Boolean = false
)

fun OfflineMaintenanceRequest.toMaintenanceRequest(): MaintenanceRequest {
    val photos = try { Json.Default.decodeFromString<List<String>>(damagePhotoUrlsJson) } catch (e: Exception) { emptyList() }
    return MaintenanceRequest(id, assetId, assetName, reporterName, description, breakdownType, scheduledDate, completionDate, repairCost, notes, status, photos, mechanicName, partsReplaced)
}

fun MaintenanceRequest.toOfflineMaintenanceRequest(isPendingSync: Boolean = false): OfflineMaintenanceRequest {
    return OfflineMaintenanceRequest(id, assetId, assetName, reporterName, description, breakdownType, scheduledDate, completionDate, repairCost, notes, status, Json.Default.encodeToString(damagePhotoUrls), mechanicName, partsReplaced, isPendingSync)
}


// 6. Offline Asset Request Entity

@Entity(tableName = "offline_asset_requests")
data class OfflineAssetRequest(
    @PrimaryKey val id: String,
    val requesterId: String,
    val requesterName: String,
    val assetId: String,
    val assetName: String,
    val requestType: String,
    val destinationSite: String,
    val status: String,
    val isPendingSync: Boolean = false
)

fun OfflineAssetRequest.toAssetRequest(): AssetRequest {
    return AssetRequest(id, requesterId, requesterName, assetId, assetName, requestType, destinationSite, status)
}

fun AssetRequest.toOfflineAssetRequest(isPendingSync: Boolean = false): OfflineAssetRequest {
    return OfflineAssetRequest(id, requesterId, requesterName, assetId, assetName, requestType, destinationSite, status, isPendingSync)
}


// 7. Offline Site Entity

@Entity(tableName = "offline_sites")
data class OfflineSite(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val radiusMeters: Double
)

fun OfflineSite.toSite(): Site {
    return Site(id, name, address, latitude, longitude, radiusMeters)
}

fun Site.toOfflineSite(): OfflineSite {
    return OfflineSite(id, name, address, latitude, longitude, radiusMeters)
}


// 8. Offline Notification Entity

@Entity(tableName = "offline_notifications")
data class OfflineNotification(
    @PrimaryKey val id: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val targetUserId: String,
    val isRead: Boolean
)

fun OfflineNotification.toNotification(): Notification {
    return Notification(id, title, message, timestamp, targetUserId, isRead)
}

fun Notification.toOfflineNotification(): OfflineNotification {
    return OfflineNotification(id, title, message, timestamp, targetUserId, isRead)
}


// 9. Offline Activity Log Entity

@Entity(tableName = "offline_activity_logs")
data class OfflineActivityLog(
    @PrimaryKey val id: String,
    val assetId: String?,
    val userId: String,
    val userName: String,
    val action: String,
    val details: String,
    val timestamp: Long,
    val ipAddress: String,
    val isPendingSync: Boolean = false
)

fun OfflineActivityLog.toActivityLog(): ActivityLog {
    return ActivityLog(id, assetId, userId, userName, action, details, timestamp, ipAddress)
}

fun ActivityLog.toOfflineActivityLog(isPendingSync: Boolean = false): OfflineActivityLog {
    return OfflineActivityLog(id, assetId, userId, userName, action, details, timestamp, ipAddress, isPendingSync)
}


// 10. Offline Report Entity

@Entity(tableName = "offline_reports")
data class OfflineReport(
    @PrimaryKey val id: String,
    val reportType: String,
    val title: String,
    val timestamp: Long,
    val dataJson: String
)

fun OfflineReport.toReport(): Report {
    return Report(id, reportType, title, timestamp, dataJson)
}

fun Report.toOfflineReport(): OfflineReport {
    return OfflineReport(id, reportType, title, timestamp, dataJson)
}

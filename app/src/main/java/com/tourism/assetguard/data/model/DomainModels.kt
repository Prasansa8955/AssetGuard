package com.tourism.assetguard.data.model

import kotlinx.serialization.Serializable
import kotlin.math.pow


// USER

@Serializable
data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val profileImage: String = "",
    val role: String = "",
    val isEnabled: Boolean = true,
    val permissions: List<String> = emptyList()
)


// ASSET

@Serializable
data class Asset(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val serialNumber: String = "",
    val barcode: String = "",
    val imageUrls: List<String> = emptyList(),
    val purchaseDate: String = "",
    val purchasePrice: Double = 0.0,
    val depreciationRate: Double = 0.0,
    val status: String = "", 
    val condition: String = "Good", 
    val currentSite: String = "",
    val locationDetails: String = "",
    val quantity: Int = 1,
    val assignedTo: String? = null,
    val assignedToId: String? = null,
    val assignedProject: String? = null,
    val assignedCompany: String? = null,
    val lastAuditedAt: Long? = null,
    val lastLatitude: Double = 0.0,
    val lastLongitude: Double = 0.0,
    val warrantyExpiration: String? = null,
    val manualUrl: String? = null,
    val nextMaintenanceDate: String? = null
)


// EXTENSION FUNCTION

fun Asset.getCurrentValue(): Double {
    val yearsOld = 2.0
    return (purchasePrice * (1.0 - (depreciationRate / 100.0)).pow(yearsOld))
        .coerceAtLeast(0.0)
}


// ASSIGNMENT

@Serializable
data class Assignment(
    val id: String = "",
    val assetId: String = "",
    val assetName: String = "",
    val employeeId: String = "",
    val employeeName: String = "",
    val companyName: String = "", 
    val projectId: String = "",
    val projectName: String = "",
    val assignedDate: Long = 0L,
    val returnedDate: Long? = null,
    val status: String = ""
)


// AUDIT LOG

@Serializable
data class AuditLog(
    val id: String = "",
    val assetId: String = "",
    val assetName: String = "",
    val auditorName: String = "",
    val timestamp: Long = 0L,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val siteId: String = "",
    val siteName: String = "",
    val isNearSite: Boolean = false,
    val condition: String = "",
    val statusDescription: String = ""
)


// MAINTENANCE

@Serializable
data class MaintenanceRequest(
    val id: String = "",
    val assetId: String = "",
    val assetName: String = "",
    val reporterName: String = "",
    val description: String = "",
    val breakdownType: String = "Routine",
    val scheduledDate: String = "",
    val completionDate: String? = null,
    val repairCost: Double = 0.0,
    val notes: String = "",
    val status: String = "Pending", 
    val damagePhotoUrls: List<String> = emptyList(), // Upgraded to support multiple photos
    val mechanicName: String? = null,
    val partsReplaced: String? = null
)


// ASSET REQUEST

@Serializable
data class AssetRequest(
    val id: String = "",
    val requesterId: String = "",
    val requesterName: String = "",
    val assetId: String = "",
    val assetName: String = "",
    val requestType: String = "",
    val destinationSite: String = "",
    val status: String = ""
)


// SITE

@Serializable
data class Site(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val radiusMeters: Double = 0.0
)


// NOTIFICATION

@Serializable
data class Notification(
    val id: String = "",
    val title: String = "",
    val message: String = "",
    val timestamp: Long = 0L,
    val targetUserId: String = "",
    val isRead: Boolean = false
)


// ACTIVITY LOG

@Serializable
data class ActivityLog(
    val id: String = "",
    val assetId: String? = null,
    val userId: String = "",
    val userName: String = "",
    val action: String = "",
    val details: String = "",
    val timestamp: Long = 0L,
    val ipAddress: String = ""
)


// REPORT

@Serializable
data class Report(
    val id: String = "",
    val reportType: String = "",
    val title: String = "",
    val timestamp: Long = 0L,
    val dataJson: String = ""
)

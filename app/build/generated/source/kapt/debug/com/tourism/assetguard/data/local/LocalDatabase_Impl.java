package com.tourism.assetguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class LocalDatabase_Impl extends LocalDatabase {
  private volatile UserDao _userDao;

  private volatile AssetDao _assetDao;

  private volatile AssignmentDao _assignmentDao;

  private volatile AuditDao _auditDao;

  private volatile MaintenanceDao _maintenanceDao;

  private volatile RequestDao _requestDao;

  private volatile SiteDao _siteDao;

  private volatile NotificationDao _notificationDao;

  private volatile ActivityLogDao _activityLogDao;

  private volatile ReportDao _reportDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(7, "1fc37245e124ad12ad245dfeee7fdb19", "c2cb0ccccc75133257829377f4664bd2") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_users` (`userId` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `profileImage` TEXT NOT NULL, `role` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `permissionsJson` TEXT NOT NULL, PRIMARY KEY(`userId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_assets` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `category` TEXT NOT NULL, `serialNumber` TEXT NOT NULL, `barcode` TEXT NOT NULL, `imageUrlsJson` TEXT NOT NULL, `purchaseDate` TEXT NOT NULL, `purchasePrice` REAL NOT NULL, `depreciationRate` REAL NOT NULL, `status` TEXT NOT NULL, `condition` TEXT NOT NULL, `currentSite` TEXT NOT NULL, `locationDetails` TEXT NOT NULL, `quantity` INTEGER NOT NULL, `assignedTo` TEXT, `assignedToId` TEXT, `assignedProject` TEXT, `assignedCompany` TEXT, `lastAuditedAt` INTEGER, `lastLatitude` REAL NOT NULL, `lastLongitude` REAL NOT NULL, `warrantyExpiration` TEXT, `manualUrl` TEXT, `nextMaintenanceDate` TEXT, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_assignments` (`id` TEXT NOT NULL, `assetId` TEXT NOT NULL, `assetName` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `employeeName` TEXT NOT NULL, `companyName` TEXT NOT NULL, `projectId` TEXT NOT NULL, `projectName` TEXT NOT NULL, `assignedDate` INTEGER NOT NULL, `returnedDate` INTEGER, `status` TEXT NOT NULL, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_audit_logs` (`id` TEXT NOT NULL, `assetId` TEXT NOT NULL, `assetName` TEXT NOT NULL, `auditorName` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `siteId` TEXT NOT NULL, `siteName` TEXT NOT NULL, `isNearSite` INTEGER NOT NULL, `condition` TEXT NOT NULL, `statusDescription` TEXT NOT NULL, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_maintenance_requests` (`id` TEXT NOT NULL, `assetId` TEXT NOT NULL, `assetName` TEXT NOT NULL, `reporterName` TEXT NOT NULL, `description` TEXT NOT NULL, `breakdownType` TEXT NOT NULL, `scheduledDate` TEXT NOT NULL, `completionDate` TEXT, `repairCost` REAL NOT NULL, `notes` TEXT NOT NULL, `status` TEXT NOT NULL, `damagePhotoUrlsJson` TEXT NOT NULL, `mechanicName` TEXT, `partsReplaced` TEXT, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_asset_requests` (`id` TEXT NOT NULL, `requesterId` TEXT NOT NULL, `requesterName` TEXT NOT NULL, `assetId` TEXT NOT NULL, `assetName` TEXT NOT NULL, `requestType` TEXT NOT NULL, `destinationSite` TEXT NOT NULL, `status` TEXT NOT NULL, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_sites` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `radiusMeters` REAL NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_notifications` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `targetUserId` TEXT NOT NULL, `isRead` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_activity_logs` (`id` TEXT NOT NULL, `assetId` TEXT, `userId` TEXT NOT NULL, `userName` TEXT NOT NULL, `action` TEXT NOT NULL, `details` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `ipAddress` TEXT NOT NULL, `isPendingSync` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `offline_reports` (`id` TEXT NOT NULL, `reportType` TEXT NOT NULL, `title` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `dataJson` TEXT NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1fc37245e124ad12ad245dfeee7fdb19')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_users`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_assets`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_assignments`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_audit_logs`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_maintenance_requests`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_asset_requests`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_sites`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_notifications`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_activity_logs`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `offline_reports`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsOfflineUsers = new HashMap<String, TableInfo.Column>(8);
        _columnsOfflineUsers.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("profileImage", new TableInfo.Column("profileImage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineUsers.put("permissionsJson", new TableInfo.Column("permissionsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineUsers = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineUsers = new TableInfo("offline_users", _columnsOfflineUsers, _foreignKeysOfflineUsers, _indicesOfflineUsers);
        final TableInfo _existingOfflineUsers = TableInfo.read(connection, "offline_users");
        if (!_infoOfflineUsers.equals(_existingOfflineUsers)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_users(com.tourism.assetguard.data.model.OfflineUser).\n"
                  + " Expected:\n" + _infoOfflineUsers + "\n"
                  + " Found:\n" + _existingOfflineUsers);
        }
        final Map<String, TableInfo.Column> _columnsOfflineAssets = new HashMap<String, TableInfo.Column>(26);
        _columnsOfflineAssets.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("serialNumber", new TableInfo.Column("serialNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("barcode", new TableInfo.Column("barcode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("imageUrlsJson", new TableInfo.Column("imageUrlsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("purchaseDate", new TableInfo.Column("purchaseDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("purchasePrice", new TableInfo.Column("purchasePrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("depreciationRate", new TableInfo.Column("depreciationRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("condition", new TableInfo.Column("condition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("currentSite", new TableInfo.Column("currentSite", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("locationDetails", new TableInfo.Column("locationDetails", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("assignedTo", new TableInfo.Column("assignedTo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("assignedToId", new TableInfo.Column("assignedToId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("assignedProject", new TableInfo.Column("assignedProject", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("assignedCompany", new TableInfo.Column("assignedCompany", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("lastAuditedAt", new TableInfo.Column("lastAuditedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("lastLatitude", new TableInfo.Column("lastLatitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("lastLongitude", new TableInfo.Column("lastLongitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("warrantyExpiration", new TableInfo.Column("warrantyExpiration", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("manualUrl", new TableInfo.Column("manualUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("nextMaintenanceDate", new TableInfo.Column("nextMaintenanceDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssets.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineAssets = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineAssets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineAssets = new TableInfo("offline_assets", _columnsOfflineAssets, _foreignKeysOfflineAssets, _indicesOfflineAssets);
        final TableInfo _existingOfflineAssets = TableInfo.read(connection, "offline_assets");
        if (!_infoOfflineAssets.equals(_existingOfflineAssets)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_assets(com.tourism.assetguard.data.model.OfflineAsset).\n"
                  + " Expected:\n" + _infoOfflineAssets + "\n"
                  + " Found:\n" + _existingOfflineAssets);
        }
        final Map<String, TableInfo.Column> _columnsOfflineAssignments = new HashMap<String, TableInfo.Column>(12);
        _columnsOfflineAssignments.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("assetId", new TableInfo.Column("assetId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("assetName", new TableInfo.Column("assetName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("employeeId", new TableInfo.Column("employeeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("employeeName", new TableInfo.Column("employeeName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("companyName", new TableInfo.Column("companyName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("projectId", new TableInfo.Column("projectId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("projectName", new TableInfo.Column("projectName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("assignedDate", new TableInfo.Column("assignedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("returnedDate", new TableInfo.Column("returnedDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssignments.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineAssignments = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineAssignments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineAssignments = new TableInfo("offline_assignments", _columnsOfflineAssignments, _foreignKeysOfflineAssignments, _indicesOfflineAssignments);
        final TableInfo _existingOfflineAssignments = TableInfo.read(connection, "offline_assignments");
        if (!_infoOfflineAssignments.equals(_existingOfflineAssignments)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_assignments(com.tourism.assetguard.data.model.OfflineAssignment).\n"
                  + " Expected:\n" + _infoOfflineAssignments + "\n"
                  + " Found:\n" + _existingOfflineAssignments);
        }
        final Map<String, TableInfo.Column> _columnsOfflineAuditLogs = new HashMap<String, TableInfo.Column>(13);
        _columnsOfflineAuditLogs.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("assetId", new TableInfo.Column("assetId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("assetName", new TableInfo.Column("assetName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("auditorName", new TableInfo.Column("auditorName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("siteId", new TableInfo.Column("siteId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("siteName", new TableInfo.Column("siteName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("isNearSite", new TableInfo.Column("isNearSite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("condition", new TableInfo.Column("condition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("statusDescription", new TableInfo.Column("statusDescription", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAuditLogs.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineAuditLogs = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineAuditLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineAuditLogs = new TableInfo("offline_audit_logs", _columnsOfflineAuditLogs, _foreignKeysOfflineAuditLogs, _indicesOfflineAuditLogs);
        final TableInfo _existingOfflineAuditLogs = TableInfo.read(connection, "offline_audit_logs");
        if (!_infoOfflineAuditLogs.equals(_existingOfflineAuditLogs)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_audit_logs(com.tourism.assetguard.data.model.OfflineAuditLog).\n"
                  + " Expected:\n" + _infoOfflineAuditLogs + "\n"
                  + " Found:\n" + _existingOfflineAuditLogs);
        }
        final Map<String, TableInfo.Column> _columnsOfflineMaintenanceRequests = new HashMap<String, TableInfo.Column>(15);
        _columnsOfflineMaintenanceRequests.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("assetId", new TableInfo.Column("assetId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("assetName", new TableInfo.Column("assetName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("reporterName", new TableInfo.Column("reporterName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("breakdownType", new TableInfo.Column("breakdownType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("scheduledDate", new TableInfo.Column("scheduledDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("completionDate", new TableInfo.Column("completionDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("repairCost", new TableInfo.Column("repairCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("damagePhotoUrlsJson", new TableInfo.Column("damagePhotoUrlsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("mechanicName", new TableInfo.Column("mechanicName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("partsReplaced", new TableInfo.Column("partsReplaced", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineMaintenanceRequests.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineMaintenanceRequests = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineMaintenanceRequests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineMaintenanceRequests = new TableInfo("offline_maintenance_requests", _columnsOfflineMaintenanceRequests, _foreignKeysOfflineMaintenanceRequests, _indicesOfflineMaintenanceRequests);
        final TableInfo _existingOfflineMaintenanceRequests = TableInfo.read(connection, "offline_maintenance_requests");
        if (!_infoOfflineMaintenanceRequests.equals(_existingOfflineMaintenanceRequests)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_maintenance_requests(com.tourism.assetguard.data.model.OfflineMaintenanceRequest).\n"
                  + " Expected:\n" + _infoOfflineMaintenanceRequests + "\n"
                  + " Found:\n" + _existingOfflineMaintenanceRequests);
        }
        final Map<String, TableInfo.Column> _columnsOfflineAssetRequests = new HashMap<String, TableInfo.Column>(9);
        _columnsOfflineAssetRequests.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("requesterId", new TableInfo.Column("requesterId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("requesterName", new TableInfo.Column("requesterName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("assetId", new TableInfo.Column("assetId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("assetName", new TableInfo.Column("assetName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("requestType", new TableInfo.Column("requestType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("destinationSite", new TableInfo.Column("destinationSite", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineAssetRequests.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineAssetRequests = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineAssetRequests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineAssetRequests = new TableInfo("offline_asset_requests", _columnsOfflineAssetRequests, _foreignKeysOfflineAssetRequests, _indicesOfflineAssetRequests);
        final TableInfo _existingOfflineAssetRequests = TableInfo.read(connection, "offline_asset_requests");
        if (!_infoOfflineAssetRequests.equals(_existingOfflineAssetRequests)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_asset_requests(com.tourism.assetguard.data.model.OfflineAssetRequest).\n"
                  + " Expected:\n" + _infoOfflineAssetRequests + "\n"
                  + " Found:\n" + _existingOfflineAssetRequests);
        }
        final Map<String, TableInfo.Column> _columnsOfflineSites = new HashMap<String, TableInfo.Column>(6);
        _columnsOfflineSites.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineSites.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineSites.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineSites.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineSites.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineSites.put("radiusMeters", new TableInfo.Column("radiusMeters", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineSites = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineSites = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineSites = new TableInfo("offline_sites", _columnsOfflineSites, _foreignKeysOfflineSites, _indicesOfflineSites);
        final TableInfo _existingOfflineSites = TableInfo.read(connection, "offline_sites");
        if (!_infoOfflineSites.equals(_existingOfflineSites)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_sites(com.tourism.assetguard.data.model.OfflineSite).\n"
                  + " Expected:\n" + _infoOfflineSites + "\n"
                  + " Found:\n" + _existingOfflineSites);
        }
        final Map<String, TableInfo.Column> _columnsOfflineNotifications = new HashMap<String, TableInfo.Column>(6);
        _columnsOfflineNotifications.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineNotifications.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineNotifications.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineNotifications.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineNotifications.put("targetUserId", new TableInfo.Column("targetUserId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineNotifications.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineNotifications = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineNotifications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineNotifications = new TableInfo("offline_notifications", _columnsOfflineNotifications, _foreignKeysOfflineNotifications, _indicesOfflineNotifications);
        final TableInfo _existingOfflineNotifications = TableInfo.read(connection, "offline_notifications");
        if (!_infoOfflineNotifications.equals(_existingOfflineNotifications)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_notifications(com.tourism.assetguard.data.model.OfflineNotification).\n"
                  + " Expected:\n" + _infoOfflineNotifications + "\n"
                  + " Found:\n" + _existingOfflineNotifications);
        }
        final Map<String, TableInfo.Column> _columnsOfflineActivityLogs = new HashMap<String, TableInfo.Column>(9);
        _columnsOfflineActivityLogs.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("assetId", new TableInfo.Column("assetId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("userName", new TableInfo.Column("userName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("details", new TableInfo.Column("details", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("ipAddress", new TableInfo.Column("ipAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineActivityLogs.put("isPendingSync", new TableInfo.Column("isPendingSync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineActivityLogs = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineActivityLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineActivityLogs = new TableInfo("offline_activity_logs", _columnsOfflineActivityLogs, _foreignKeysOfflineActivityLogs, _indicesOfflineActivityLogs);
        final TableInfo _existingOfflineActivityLogs = TableInfo.read(connection, "offline_activity_logs");
        if (!_infoOfflineActivityLogs.equals(_existingOfflineActivityLogs)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_activity_logs(com.tourism.assetguard.data.model.OfflineActivityLog).\n"
                  + " Expected:\n" + _infoOfflineActivityLogs + "\n"
                  + " Found:\n" + _existingOfflineActivityLogs);
        }
        final Map<String, TableInfo.Column> _columnsOfflineReports = new HashMap<String, TableInfo.Column>(5);
        _columnsOfflineReports.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineReports.put("reportType", new TableInfo.Column("reportType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineReports.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineReports.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineReports.put("dataJson", new TableInfo.Column("dataJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysOfflineReports = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesOfflineReports = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineReports = new TableInfo("offline_reports", _columnsOfflineReports, _foreignKeysOfflineReports, _indicesOfflineReports);
        final TableInfo _existingOfflineReports = TableInfo.read(connection, "offline_reports");
        if (!_infoOfflineReports.equals(_existingOfflineReports)) {
          return new RoomOpenDelegate.ValidationResult(false, "offline_reports(com.tourism.assetguard.data.model.OfflineReport).\n"
                  + " Expected:\n" + _infoOfflineReports + "\n"
                  + " Found:\n" + _existingOfflineReports);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "offline_users", "offline_assets", "offline_assignments", "offline_audit_logs", "offline_maintenance_requests", "offline_asset_requests", "offline_sites", "offline_notifications", "offline_activity_logs", "offline_reports");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "offline_users", "offline_assets", "offline_assignments", "offline_audit_logs", "offline_maintenance_requests", "offline_asset_requests", "offline_sites", "offline_notifications", "offline_activity_logs", "offline_reports");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AssetDao.class, AssetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AssignmentDao.class, AssignmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AuditDao.class, AuditDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MaintenanceDao.class, MaintenanceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RequestDao.class, RequestDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SiteDao.class, SiteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotificationDao.class, NotificationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActivityLogDao.class, ActivityLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReportDao.class, ReportDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public AssetDao assetDao() {
    if (_assetDao != null) {
      return _assetDao;
    } else {
      synchronized(this) {
        if(_assetDao == null) {
          _assetDao = new AssetDao_Impl(this);
        }
        return _assetDao;
      }
    }
  }

  @Override
  public AssignmentDao assignmentDao() {
    if (_assignmentDao != null) {
      return _assignmentDao;
    } else {
      synchronized(this) {
        if(_assignmentDao == null) {
          _assignmentDao = new AssignmentDao_Impl(this);
        }
        return _assignmentDao;
      }
    }
  }

  @Override
  public AuditDao auditDao() {
    if (_auditDao != null) {
      return _auditDao;
    } else {
      synchronized(this) {
        if(_auditDao == null) {
          _auditDao = new AuditDao_Impl(this);
        }
        return _auditDao;
      }
    }
  }

  @Override
  public MaintenanceDao maintenanceDao() {
    if (_maintenanceDao != null) {
      return _maintenanceDao;
    } else {
      synchronized(this) {
        if(_maintenanceDao == null) {
          _maintenanceDao = new MaintenanceDao_Impl(this);
        }
        return _maintenanceDao;
      }
    }
  }

  @Override
  public RequestDao requestDao() {
    if (_requestDao != null) {
      return _requestDao;
    } else {
      synchronized(this) {
        if(_requestDao == null) {
          _requestDao = new RequestDao_Impl(this);
        }
        return _requestDao;
      }
    }
  }

  @Override
  public SiteDao siteDao() {
    if (_siteDao != null) {
      return _siteDao;
    } else {
      synchronized(this) {
        if(_siteDao == null) {
          _siteDao = new SiteDao_Impl(this);
        }
        return _siteDao;
      }
    }
  }

  @Override
  public NotificationDao notificationDao() {
    if (_notificationDao != null) {
      return _notificationDao;
    } else {
      synchronized(this) {
        if(_notificationDao == null) {
          _notificationDao = new NotificationDao_Impl(this);
        }
        return _notificationDao;
      }
    }
  }

  @Override
  public ActivityLogDao activityLogDao() {
    if (_activityLogDao != null) {
      return _activityLogDao;
    } else {
      synchronized(this) {
        if(_activityLogDao == null) {
          _activityLogDao = new ActivityLogDao_Impl(this);
        }
        return _activityLogDao;
      }
    }
  }

  @Override
  public ReportDao reportDao() {
    if (_reportDao != null) {
      return _reportDao;
    } else {
      synchronized(this) {
        if(_reportDao == null) {
          _reportDao = new ReportDao_Impl(this);
        }
        return _reportDao;
      }
    }
  }
}

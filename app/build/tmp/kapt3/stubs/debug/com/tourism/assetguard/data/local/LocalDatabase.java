package com.tourism.assetguard.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&\u00a8\u0006\u0018"}, d2 = {"Lcom/tourism/assetguard/data/local/LocalDatabase;", "Landroidx/room/RoomDatabase;", "()V", "activityLogDao", "Lcom/tourism/assetguard/data/local/ActivityLogDao;", "assetDao", "Lcom/tourism/assetguard/data/local/AssetDao;", "assignmentDao", "Lcom/tourism/assetguard/data/local/AssignmentDao;", "auditDao", "Lcom/tourism/assetguard/data/local/AuditDao;", "maintenanceDao", "Lcom/tourism/assetguard/data/local/MaintenanceDao;", "notificationDao", "Lcom/tourism/assetguard/data/local/NotificationDao;", "reportDao", "Lcom/tourism/assetguard/data/local/ReportDao;", "requestDao", "Lcom/tourism/assetguard/data/local/RequestDao;", "siteDao", "Lcom/tourism/assetguard/data/local/SiteDao;", "userDao", "Lcom/tourism/assetguard/data/local/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.tourism.assetguard.data.model.OfflineUser.class, com.tourism.assetguard.data.model.OfflineAsset.class, com.tourism.assetguard.data.model.OfflineAssignment.class, com.tourism.assetguard.data.model.OfflineAuditLog.class, com.tourism.assetguard.data.model.OfflineMaintenanceRequest.class, com.tourism.assetguard.data.model.OfflineAssetRequest.class, com.tourism.assetguard.data.model.OfflineSite.class, com.tourism.assetguard.data.model.OfflineNotification.class, com.tourism.assetguard.data.model.OfflineActivityLog.class, com.tourism.assetguard.data.model.OfflineReport.class}, version = 7, exportSchema = false)
public abstract class LocalDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.tourism.assetguard.data.local.LocalDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.tourism.assetguard.data.local.LocalDatabase.Companion Companion = null;
    
    public LocalDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.AssetDao assetDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.AssignmentDao assignmentDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.AuditDao auditDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.MaintenanceDao maintenanceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.RequestDao requestDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.SiteDao siteDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.NotificationDao notificationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.ActivityLogDao activityLogDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tourism.assetguard.data.local.ReportDao reportDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/tourism/assetguard/data/local/LocalDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/tourism/assetguard/data/local/LocalDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.tourism.assetguard.data.local.LocalDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}
package com.tourism.assetguard.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ce\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010LJ\u0016\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010PJ\u0016\u0010Q\u001a\u00020N2\u0006\u0010R\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010SJ\u0016\u0010T\u001a\u00020N2\u0006\u0010U\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010VJ\u0016\u0010W\u001a\u00020J2\u0006\u0010X\u001a\u00020YH\u0086@\u00a2\u0006\u0002\u0010ZJ\u0018\u0010[\u001a\u0004\u0018\u00010\f2\u0006\u0010\\\u001a\u00020YH\u0086@\u00a2\u0006\u0002\u0010ZJ\u0014\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007H\u0086@\u00a2\u0006\u0002\u0010^J\u000e\u0010_\u001a\u00020NH\u0086@\u00a2\u0006\u0002\u0010^J\u0016\u0010`\u001a\u00020J2\u0006\u0010a\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010bJ\u0016\u0010c\u001a\u00020N2\u0006\u0010d\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010eJ\u0016\u0010f\u001a\u00020N2\u0006\u0010g\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010hJ\u0006\u0010i\u001a\u00020NJ\u0016\u0010j\u001a\u00020J2\u0006\u0010K\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010LR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u000e\u0010\'\u001a\u00020(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u000e\u0010+\u001a\u00020,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010 R\u000e\u00105\u001a\u000206X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u000e\u00109\u001a\u00020:X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010 R\u000e\u0010=\u001a\u00020>X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010 R\u000e\u0010E\u001a\u00020FX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00070\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010 \u00a8\u0006k"}, d2 = {"Lcom/tourism/assetguard/data/repository/AssetRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_activityLogs", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/tourism/assetguard/data/model/ActivityLog;", "_assetRequests", "Lcom/tourism/assetguard/data/model/AssetRequest;", "_assets", "Lcom/tourism/assetguard/data/model/Asset;", "_assignments", "Lcom/tourism/assetguard/data/model/Assignment;", "_audits", "Lcom/tourism/assetguard/data/model/AuditLog;", "_maintenanceRequests", "Lcom/tourism/assetguard/data/model/MaintenanceRequest;", "_notifications", "Lcom/tourism/assetguard/data/model/Notification;", "_reports", "Lcom/tourism/assetguard/data/model/Report;", "_sites", "Lcom/tourism/assetguard/data/model/Site;", "_users", "Lcom/tourism/assetguard/data/model/User;", "activityLogDao", "Lcom/tourism/assetguard/data/local/ActivityLogDao;", "activityLogs", "Lkotlinx/coroutines/flow/StateFlow;", "getActivityLogs", "()Lkotlinx/coroutines/flow/StateFlow;", "assetDao", "Lcom/tourism/assetguard/data/local/AssetDao;", "assetRequests", "getAssetRequests", "assets", "getAssets", "assignmentDao", "Lcom/tourism/assetguard/data/local/AssignmentDao;", "assignments", "getAssignments", "auditDao", "Lcom/tourism/assetguard/data/local/AuditDao;", "audits", "getAudits", "db", "Lcom/tourism/assetguard/data/local/LocalDatabase;", "maintenanceDao", "Lcom/tourism/assetguard/data/local/MaintenanceDao;", "maintenanceRequests", "getMaintenanceRequests", "notificationDao", "Lcom/tourism/assetguard/data/local/NotificationDao;", "notifications", "getNotifications", "reportDao", "Lcom/tourism/assetguard/data/local/ReportDao;", "reports", "getReports", "repositoryScope", "Lkotlinx/coroutines/CoroutineScope;", "requestDao", "Lcom/tourism/assetguard/data/local/RequestDao;", "siteDao", "Lcom/tourism/assetguard/data/local/SiteDao;", "sites", "getSites", "userDao", "Lcom/tourism/assetguard/data/local/UserDao;", "users", "getUsers", "addAsset", "", "asset", "(Lcom/tourism/assetguard/data/model/Asset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addAssignment", "", "assignment", "(Lcom/tourism/assetguard/data/model/Assignment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addOrUpdateUser", "user", "(Lcom/tourism/assetguard/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addSite", "site", "(Lcom/tourism/assetguard/data/model/Site;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAsset", "assetId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssetByBarcode", "barcode", "getCombinedAudits", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadLocalData", "logAudit", "auditLog", "(Lcom/tourism/assetguard/data/model/AuditLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logMaintenance", "request", "(Lcom/tourism/assetguard/data/model/MaintenanceRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logSecurityActivity", "log", "(Lcom/tourism/assetguard/data/model/ActivityLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleOfflineSync", "updateAsset", "app_debug"})
public final class AssetRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.LocalDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.AssetDao assetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.AssignmentDao assignmentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.AuditDao auditDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.MaintenanceDao maintenanceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.RequestDao requestDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.SiteDao siteDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.NotificationDao notificationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.ActivityLogDao activityLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tourism.assetguard.data.local.ReportDao reportDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.Asset>> _assets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Asset>> assets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.AuditLog>> _audits = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.AuditLog>> audits = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.Assignment>> _assignments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Assignment>> assignments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.MaintenanceRequest>> _maintenanceRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.MaintenanceRequest>> maintenanceRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.AssetRequest>> _assetRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.AssetRequest>> assetRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.Site>> _sites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Site>> sites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.Notification>> _notifications = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Notification>> notifications = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.ActivityLog>> _activityLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.ActivityLog>> activityLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.Report>> _reports = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Report>> reports = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tourism.assetguard.data.model.User>> _users = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.User>> users = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope repositoryScope = null;
    
    public AssetRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void scheduleOfflineSync() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Asset>> getAssets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.AuditLog>> getAudits() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Assignment>> getAssignments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.MaintenanceRequest>> getMaintenanceRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.AssetRequest>> getAssetRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Site>> getSites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Notification>> getNotifications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.ActivityLog>> getActivityLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.Report>> getReports() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tourism.assetguard.data.model.User>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadLocalData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addOrUpdateUser(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAsset(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.Asset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAsset(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.Asset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAsset(@org.jetbrains.annotations.NotNull()
    java.lang.String assetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAssetByBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tourism.assetguard.data.model.Asset> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAssignment(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.Assignment assignment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logAudit(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.AuditLog auditLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logMaintenance(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.MaintenanceRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSite(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.Site site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logSecurityActivity(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.ActivityLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCombinedAudits(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.AuditLog>> $completion) {
        return null;
    }
}
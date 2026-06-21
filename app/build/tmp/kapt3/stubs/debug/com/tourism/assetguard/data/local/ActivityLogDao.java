package com.tourism.assetguard.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/tourism/assetguard/data/local/ActivityLogDao;", "", "clearActivityLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActivityLogs", "", "Lcom/tourism/assetguard/data/model/OfflineActivityLog;", "getPendingActivityLogs", "insertActivityLog", "log", "(Lcom/tourism/assetguard/data/model/OfflineActivityLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ActivityLogDao {
    
    @androidx.room.Query(value = "SELECT * FROM offline_activity_logs ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllActivityLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineActivityLog>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_activity_logs WHERE isPendingSync = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingActivityLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineActivityLog>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertActivityLog(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineActivityLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM offline_activity_logs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearActivityLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
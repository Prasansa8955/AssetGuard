package com.tourism.assetguard.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/tourism/assetguard/data/local/RequestDao;", "", "clearRequests", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRequests", "", "Lcom/tourism/assetguard/data/model/OfflineAssetRequest;", "getPendingRequests", "getRequestsForEmployee", "reqId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRequest", "request", "(Lcom/tourism/assetguard/data/model/OfflineAssetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRequest", "app_debug"})
@androidx.room.Dao()
public abstract interface RequestDao {
    
    @androidx.room.Query(value = "SELECT * FROM offline_asset_requests ORDER BY id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllRequests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssetRequest>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_asset_requests WHERE requesterId = :reqId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRequestsForEmployee(@org.jetbrains.annotations.NotNull()
    java.lang.String reqId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssetRequest>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_asset_requests WHERE isPendingSync = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingRequests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAssetRequest>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRequest(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAssetRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRequest(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAssetRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM offline_asset_requests")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearRequests(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
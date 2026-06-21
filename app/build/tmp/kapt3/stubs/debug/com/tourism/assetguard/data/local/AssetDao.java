package com.tourism.assetguard.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/tourism/assetguard/data/local/AssetDao;", "", "clearAssets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAsset", "assetId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAssets", "", "Lcom/tourism/assetguard/data/model/OfflineAsset;", "getAssetByBarcode", "barcode", "getAssetById", "getPendingAssets", "insertAsset", "asset", "(Lcom/tourism/assetguard/data/model/OfflineAsset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAssets", "assets", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAsset", "app_debug"})
@androidx.room.Dao()
public abstract interface AssetDao {
    
    @androidx.room.Query(value = "SELECT * FROM offline_assets")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAssets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAsset>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_assets WHERE id = :assetId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAssetById(@org.jetbrains.annotations.NotNull()
    java.lang.String assetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tourism.assetguard.data.model.OfflineAsset> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_assets WHERE barcode = :barcode LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAssetByBarcode(@org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tourism.assetguard.data.model.OfflineAsset> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM offline_assets WHERE isPendingSync = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPendingAssets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tourism.assetguard.data.model.OfflineAsset>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAssets(@org.jetbrains.annotations.NotNull()
    java.util.List<com.tourism.assetguard.data.model.OfflineAsset> assets, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAsset(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAsset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAsset(@org.jetbrains.annotations.NotNull()
    com.tourism.assetguard.data.model.OfflineAsset asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM offline_assets WHERE id = :assetId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAsset(@org.jetbrains.annotations.NotNull()
    java.lang.String assetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM offline_assets")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAssets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
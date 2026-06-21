package com.tourism.assetguard.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\u0012\u0010\"\u001a\u0004\u0018\u00010\t2\u0006\u0010#\u001a\u00020\u0011H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020\u0011H\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\t2\u0006\u0010%\u001a\u00020\u0011H\u0002J$\u0010\'\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u00112\f\u0010)\u001a\b\u0012\u0004\u0012\u00020!0*J\u0014\u0010+\u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020!0*J<\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u00112\f\u0010)\u001a\b\u0012\u0004\u0012\u00020!0*J\u001a\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020\t2\b\u0010(\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u00102\u001a\u00020!H\u0002J$\u00103\u001a\u00020!2\u0006\u0010-\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00112\f\u00105\u001a\b\u0012\u0004\u0012\u00020!0*J\"\u00106\u001a\u00020!2\u0006\u00107\u001a\u0002082\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020!09R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR/\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\b\u001a\u0004\u0018\u00010\u00118F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u00188F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\n \u0007*\u0004\u0018\u00010\u001f0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/tourism/assetguard/viewmodel/AuthViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "<set-?>", "Lcom/tourism/assetguard/data/model/User;", "currentUser", "getCurrentUser", "()Lcom/tourism/assetguard/data/model/User;", "setCurrentUser", "(Lcom/tourism/assetguard/data/model/User;)V", "currentUser$delegate", "Landroidx/compose/runtime/MutableState;", "", "errorMessage", "getErrorMessage", "()Ljava/lang/String;", "setErrorMessage", "(Ljava/lang/String;)V", "errorMessage$delegate", "", "isLoading", "()Z", "setLoading", "(Z)V", "isLoading$delegate", "sharedPrefs", "Landroid/content/SharedPreferences;", "clearError", "", "findUserByEmailInCache", "email", "getCachedPassword", "userId", "getCachedUser", "login", "password", "onSuccess", "Lkotlin/Function0;", "logout", "register", "name", "phone", "role", "saveUserToCache", "user", "seedDefaultAccounts", "updateUserProfile", "profileImage", "onComplete", "uploadProfileImage", "uri", "Landroid/net/Uri;", "Lkotlin/Function1;", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.AndroidViewModel {
    private final android.content.Context context = null;
    private final android.content.SharedPreferences sharedPrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState currentUser$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState errorMessage$delegate = null;
    
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.tourism.assetguard.data.model.User getCurrentUser() {
        return null;
    }
    
    private final void setCurrentUser(com.tourism.assetguard.data.model.User p0) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    private final void setLoading(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    private final void setErrorMessage(java.lang.String p0) {
    }
    
    private final void seedDefaultAccounts() {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void logout(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void clearError() {
    }
    
    public final void uploadProfileImage(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSuccess) {
    }
    
    public final void updateUserProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String profileImage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    private final void saveUserToCache(com.tourism.assetguard.data.model.User user, java.lang.String password) {
    }
    
    private final com.tourism.assetguard.data.model.User getCachedUser(java.lang.String userId) {
        return null;
    }
    
    private final java.lang.String getCachedPassword(java.lang.String userId) {
        return null;
    }
    
    private final com.tourism.assetguard.data.model.User findUserByEmailInCache(java.lang.String email) {
        return null;
    }
}
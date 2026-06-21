package com.tourism.assetguard.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tourism.assetguard.data.model.User
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val sharedPrefs = context.getSharedPreferences("asset_guard_users", Context.MODE_PRIVATE)

    var currentUser by mutableStateOf<User?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        // Seed default accounts for instant testing
        seedDefaultAccounts()
        
        // Auto sign-in or offline session restore
        viewModelScope.launch {
            val lastUserId = sharedPrefs.getString("logged_in_user_id", null)
            if (lastUserId != null) {
                currentUser = getCachedUser(lastUserId)
            }
        }
    }

    private fun seedDefaultAccounts() {
        if (getCachedUser("manager_default") == null) {
            val manager = User("manager_default", "John Manager", "manager@assetguard.com", "+123456789", "", "Manager", true, emptyList())
            saveUserToCache(manager, "manager123")
        }
        if (getCachedUser("auditor_default") == null) {
            val auditor = User("auditor_default", "Sarah Auditor", "auditor@assetguard.com", "+987654321", "", "Auditor", true, emptyList())
            saveUserToCache(auditor, "auditor123")
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()

        if (trimmedEmail.isBlank() || trimmedPassword.isBlank()) {
            errorMessage = "Email and password cannot be empty"
            return
        }

        isLoading = true
        errorMessage = null

        val cachedUser = findUserByEmailInCache(trimmedEmail)
        val cachedPassword = getCachedPassword(cachedUser?.userId ?: "")
        
        if (cachedUser != null && cachedPassword == trimmedPassword) {
            currentUser = cachedUser
            sharedPrefs.edit().putString("logged_in_user_id", cachedUser.userId).apply()
            isLoading = false
            onSuccess()
        } else {
            isLoading = false
            errorMessage = "Invalid credentials. Offline bypass did not match."
        }
    }

    fun register(name: String, email: String, phone: String, role: String, password: String, onSuccess: () -> Unit) {
        val trimmedName = name.trim()
        val trimmedEmail = email.trim()
        val trimmedPhone = phone.trim()
        val trimmedPassword = password.trim()

        if (trimmedName.isBlank() || trimmedEmail.isBlank() || trimmedPhone.isBlank() || trimmedPassword.isBlank()) {
            errorMessage = "All fields are required"
            return
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            val uid = "local_${System.currentTimeMillis()}"
            val newUser = User(uid, trimmedName, trimmedEmail, trimmedPhone, "", role, true, emptyList())
            saveUserToCache(newUser, trimmedPassword)
            
            // Push registration data directly to Firebase Firestore
            com.tourism.assetguard.data.remote.FirebaseSyncManager.pushUser(newUser)
            
            // Do not auto-login upon registration, so user can manually login on the login page
            // currentUser = newUser
            // sharedPrefs.edit().putString("logged_in_user_id", uid).apply()
            isLoading = false
            onSuccess()
        }
    }

    fun logout(onSuccess: () -> Unit) {
        currentUser = null
        sharedPrefs.edit().remove("logged_in_user_id").apply()
        onSuccess()
    }

    fun clearError() {
        errorMessage = null
    }

    fun uploadProfileImage(uri: android.net.Uri, onSuccess: (String) -> Unit) {
        val user = currentUser ?: return
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            val updatedUser = user.copy(profileImage = uri.toString())
            saveUserToCache(updatedUser, null)
            currentUser = updatedUser
            isLoading = false
            onSuccess(uri.toString())
        }
    }

    fun updateUserProfile(name: String, profileImage: String, onComplete: () -> Unit) {
        val user = currentUser ?: return
        val updatedUser = user.copy(name = name, profileImage = profileImage)
        viewModelScope.launch {
            saveUserToCache(updatedUser, null)
            currentUser = updatedUser
            onComplete()
        }
    }

    // --- SharedPreferences User Serialization Cache Helpers ---

    private fun saveUserToCache(user: User, password: String?) {
        val userJson = Json.encodeToString(user)
        sharedPrefs.edit().apply {
            putString("user_data_${user.userId}", userJson)
            if (password != null) {
                putString("user_pass_${user.userId}", password)
            }
            // Add email mapping for fast logins
            putString("user_email_${user.email.lowercase().trim()}", user.userId)
        }.apply()
    }

    private fun getCachedUser(userId: String): User? {
        val json = sharedPrefs.getString("user_data_$userId", null) ?: return null
        return runCatching { Json.decodeFromString<User>(json) }.getOrNull()
    }

    private fun getCachedPassword(userId: String): String? {
        return sharedPrefs.getString("user_pass_$userId", null)
    }

    private fun findUserByEmailInCache(email: String): User? {
        val userId = sharedPrefs.getString("user_email_${email.lowercase().trim()}", null) ?: return null
        return getCachedUser(userId)
    }
}

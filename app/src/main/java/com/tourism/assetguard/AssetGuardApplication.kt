package com.tourism.assetguard

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class AssetGuard : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
    }

    private fun initializeFirebase() {
        try {
            // Check if already auto-initialized by standard content providers
            val apps = FirebaseApp.getApps(this)
            if (apps.isEmpty()) {
                val options = FirebaseOptions.Builder()
                    .setApplicationId("1:1026310381062:android:52a65798b1184767c0ed87")
                    .setApiKey("AIzaSyDh_lkOAUuBMnvgaQdqtHouRuCE8m54on8")
                    .setProjectId("assetguard-app")
                    .build()
                FirebaseApp.initializeApp(this, options)
                Log.d("AssetGuardApp", "Firebase manually soft-initialized with compiler bypass options.")
            } else {
                Log.d("AssetGuardApp", "Firebase auto-initialized successfully via resource configurations.")
            }
        } catch (e: Exception) {
            Log.e("AssetGuardApp", "Firebase soft-initialization fallback: ${e.message}")
        }
    }
}

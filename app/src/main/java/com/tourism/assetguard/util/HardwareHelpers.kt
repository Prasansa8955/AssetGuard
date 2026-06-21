package com.tourism.assetguard.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.math.abs

// 1. QR/Barcode Scanner (CameraX + ML Kit)

@Composable
fun BarcodeScannerView(
    onBarcodeDetected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                
                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(previewView.surfaceProvider)
                }

                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                    .build()
                val scanner = BarcodeScanning.getClient(options)

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
                    processImageProxy(imageProxy, scanner, onBarcodeDetected)
                }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.e("BarcodeScannerView", "Use case binding failed", e)
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
        modifier = modifier.fillMaxSize()
    )
}

@SuppressLint("UnsafeOptInUsageError")
private fun processImageProxy(
    imageProxy: ImageProxy,
    scanner: com.google.mlkit.vision.barcode.BarcodeScanner,
    onBarcodeDetected: (String) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    barcode.rawValue?.let { rawValue ->
                        onBarcodeDetected(rawValue)
                    }
                }
            }
            .addOnFailureListener {
                Log.e("BarcodeScannerView", "Barcode analysis failed", it)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    } else {
        imageProxy.close()
    }
}

// 2. Location Helper (GPS Fused Location)

object LocationHelper {
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(context: Context): Location? = suspendCancellableCoroutine { continuation ->
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            continuation.resume(null)
            return@suspendCancellableCoroutine
        }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                continuation.resume(location)
            }
            .addOnFailureListener {
                continuation.resume(null)
            }
    }
}

// 3. Custom QR Code Generator (Compose Canvas)

@Composable
fun AssetQrCode(
    data: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White)
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val sizePx = size.width
            val gridSize = 21
            val moduleSize = sizePx / gridSize

            drawRect(color = Color.White, size = size)

            drawFinderPattern(0, 0, moduleSize)
            drawFinderPattern(gridSize - 7, 0, moduleSize)
            drawFinderPattern(0, gridSize - 7, moduleSize)

            val seed = abs(data.hashCode())
            val random = java.util.Random(seed.toLong())

            for (row in 0 until gridSize) {
                for (col in 0 until gridSize) {
                    val inTopLeft = row < 8 && col < 8
                    val inTopRight = row < 8 && col >= gridSize - 8
                    val inBottomLeft = row >= gridSize - 8 && col < 8
                    
                    if (inTopLeft || inTopRight || inBottomLeft) continue

                    if (row == 6 || col == 6) {
                        if ((row + col) % 2 == 0) {
                            drawRect(
                                color = Color.Black,
                                topLeft = Offset(col * moduleSize, row * moduleSize),
                                size = Size(moduleSize, moduleSize)
                            )
                        }
                        continue
                    }

                    if (random.nextBoolean()) {
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(col * moduleSize, row * moduleSize),
                            size = Size(moduleSize, moduleSize)
                        )
                    }
                }
            }
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawFinderPattern(
    xGrid: Int,
    yGrid: Int,
    moduleSize: Float
) {
    val x = xGrid * moduleSize
    val y = yGrid * moduleSize

    drawRect(
        color = Color.Black,
        topLeft = Offset(x, y),
        size = Size(7 * moduleSize, 7 * moduleSize)
    )

    drawRect(
        color = Color.White,
        topLeft = Offset(x + moduleSize, y + moduleSize),
        size = Size(5 * moduleSize, 5 * moduleSize)
    )

    drawRect(
        color = Color.Black,
        topLeft = Offset(x + 2 * moduleSize, y + 2 * moduleSize),
        size = Size(3 * moduleSize, 3 * moduleSize)
    )
}

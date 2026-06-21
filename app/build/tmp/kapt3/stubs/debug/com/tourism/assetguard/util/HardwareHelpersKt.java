package com.tourism.assetguard.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a&\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a,\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a$\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u00a8\u0006\u0015"}, d2 = {"AssetQrCode", "", "data", "", "modifier", "Landroidx/compose/ui/Modifier;", "BarcodeScannerView", "onBarcodeDetected", "Lkotlin/Function1;", "processImageProxy", "imageProxy", "Landroidx/camera/core/ImageProxy;", "scanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "drawFinderPattern", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "xGrid", "", "yGrid", "moduleSize", "", "app_debug"})
public final class HardwareHelpersKt {
    
    @androidx.compose.runtime.Composable()
    public static final void BarcodeScannerView(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBarcodeDetected, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @android.annotation.SuppressLint(value = {"UnsafeOptInUsageError"})
    private static final void processImageProxy(androidx.camera.core.ImageProxy imageProxy, com.google.mlkit.vision.barcode.BarcodeScanner scanner, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBarcodeDetected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AssetQrCode(@org.jetbrains.annotations.NotNull()
    java.lang.String data, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final void drawFinderPattern(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawFinderPattern, int xGrid, int yGrid, float moduleSize) {
    }
}
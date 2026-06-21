[void][System.Reflection.Assembly]::LoadWithPartialName("System.Drawing")
$srcImage = [System.Drawing.Image]::FromFile("C:\games\ayesha\istockphoto-2157290923-612x612.jpg")

$sizes = @{
    "mipmap-mdpi" = 48
    "mipmap-hdpi" = 72
    "mipmap-xhdpi" = 96
    "mipmap-xxhdpi" = 144
    "mipmap-xxxhdpi" = 192
}

foreach ($folder in $sizes.Keys) {
    $size = $sizes[$folder]
    $destDir = "C:\games\ayesha\app\src\main\res\$folder"
    if (!(Test-Path $destDir)) {
        New-Item -ItemType Directory -Path $destDir | Out-Null
    }
    
    # Calculate scaled logo size (75% of canvas) and center offset padding
    $logoSize = [Math]::Round($size * 0.75)
    $offset = [Math]::Round(($size - $logoSize) / 2)

    # ------------------ 1. Standard Rounded Square Launcher Icon ------------------
    $bmp = New-Object System.Drawing.Bitmap($size, $size)
    $g = [System.Drawing.Graphics]::FromImage($bmp)
    
    $g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
    $g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
    
    # Clip background into rounded square
    $path = New-Object System.Drawing.Drawing2D.GraphicsPath
    $r = $size * 0.16
    $path.AddArc(0, 0, $r, $r, 180, 90)
    $path.AddArc($size - $r, 0, $r, $r, 270, 90)
    $path.AddArc($size - $r, $size - $r, $r, $r, 0, 90)
    $path.AddArc(0, $size - $r, $r, $r, 90, 90)
    $path.CloseAllFigures()
    $g.SetClip($path)
    
    # Fill background with Solid White
    $whiteBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::White)
    $g.FillPath($whiteBrush, $path)
    
    # Draw scaled-down logo in the center
    $g.DrawImage($srcImage, $offset, $offset, $logoSize, $logoSize)
    
    $whiteBrush.Dispose()
    $g.Dispose()
    $bmp.Save("$destDir\ic_launcher.png", [System.Drawing.Imaging.ImageFormat]::Png)
    $bmp.Dispose()

    # ------------------ 2. Round Ellipse Launcher Icon ------------------
    $bmpRound = New-Object System.Drawing.Bitmap($size, $size)
    $gRound = [System.Drawing.Graphics]::FromImage($bmpRound)
    
    $gRound.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
    $gRound.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic
    
    # Clip into a perfect circular ellipse
    $circlePath = New-Object System.Drawing.Drawing2D.GraphicsPath
    $circlePath.AddEllipse(0, 0, $size, $size)
    $gRound.SetClip($circlePath)
    
    # Fill background with Solid White
    $whiteBrushRound = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::White)
    $gRound.FillEllipse($whiteBrushRound, 0, 0, $size, $size)
    
    # Draw scaled-down logo in the center of the circular canvas
    $gRound.DrawImage($srcImage, $offset, $offset, $logoSize, $logoSize)
    
    $whiteBrushRound.Dispose()
    $gRound.Dispose()
    $bmpRound.Save("$destDir\ic_launcher_round.png", [System.Drawing.Imaging.ImageFormat]::Png)
    $bmpRound.Dispose()

    Write-Host "Generated centered padded icons for $folder ($size x $size)"
}
$srcImage.Dispose()

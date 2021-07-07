package posidon.android.conveniencelib

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

inline fun Drawable.toBitmap(duplicateIfBitmapDrawable: Boolean = false): Bitmap {
    if (duplicateIfBitmapDrawable && this is BitmapDrawable && bitmap != null) {
        return bitmap
    }
    val bitmap: Bitmap = if (intrinsicWidth <= 0 || intrinsicHeight <= 0) Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    else Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    try { draw(canvas) }
    catch (e: Exception) {}
    return bitmap
}

inline fun Drawable.toBitmap(width: Int, height: Int, duplicateIfBitmapDrawable: Boolean = false): Bitmap {
    if (duplicateIfBitmapDrawable && this is BitmapDrawable && bitmap != null) {
        return bitmap
    }
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    try { draw(canvas) }
    catch (e: Exception) { e.printStackTrace() }
    return bitmap
}

inline fun Drawable.clone() = constantState?.newDrawable()?.mutate()
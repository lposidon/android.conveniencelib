package io.posidon.android.conveniencelib

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresPermission
import java.io.BufferedReader
import java.io.InputStreamReader

inline val Context.isAirplaneModeOn get() =
    android.provider.Settings.System.getInt(contentResolver, android.provider.Settings.Global.AIRPLANE_MODE_ON, 0) != 0

inline fun Context.pullStatusbar() {
    try {
        @SuppressLint("WrongConstant")
        val sbs = getSystemService("statusbar")
        Class.forName("android.app.StatusBarManager").getMethod("expandNotificationsPanel")(sbs)
    } catch (e: Exception) { e.printStackTrace() }
}

inline fun Context.getStatusBarHeight(): Int {
    val id = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (id > 0) resources.getDimensionPixelSize(id) else 0
}

@RequiresPermission(android.Manifest.permission.VIBRATE)
inline fun Context.vibrate(duration: Int) {
    if (duration != 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
            VibrationEffect.createOneShot(duration.toLong(), VibrationEffect.DEFAULT_AMPLITUDE),
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        ) else (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(duration.toLong())
    }
}

inline fun <T> Context.loadRaw(id: Int, fn: (BufferedReader) -> T) =
    resources.openRawResource(id).use { stream ->
        val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        fn(reader)
    }

inline fun PackageManager.isInstalled(packageName: String): Boolean {
    try { getPackageInfo(packageName, 0) }
    catch (e: Exception) { return false }
    return true
}
package io.posidon.android.conveniencelib.units

import android.util.DisplayMetrics

@JvmInline
@Suppress("OVERRIDE_BY_INLINE")
value class Sp(
    val value: Float
) : ScreenUnit {
    override inline fun toPixels(displayMetrics: DisplayMetrics): Int = toFloatPixels(displayMetrics).toInt()
    override inline fun toFloatPixels(displayMetrics: DisplayMetrics): Float = displayMetrics.scaledDensity * value
}

inline val Float.sp get() = Sp(this)
inline val Number.sp get() = toFloat().sp

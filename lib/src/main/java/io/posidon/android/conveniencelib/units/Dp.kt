package io.posidon.android.conveniencelib.units

import android.util.DisplayMetrics

@JvmInline
@Suppress("OVERRIDE_BY_INLINE")
value class Dp(
    val value: Float
) : ScreenUnit {
    override inline fun toPixels(displayMetrics: DisplayMetrics): Int = toFloatPixels(displayMetrics).toInt()
    override inline fun toFloatPixels(displayMetrics: DisplayMetrics): Float = displayMetrics.density * value
}

inline val Float.dp get() = Dp(this)
inline val Number.dp get() = toFloat().dp

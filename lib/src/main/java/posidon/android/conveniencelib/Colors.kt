package posidon.android.conveniencelib

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

object Colors {

    @ColorInt
    inline fun blend(@ColorInt color1: Int, @ColorInt color2: Int, ratio: Float): Int {
        val ratio = when {
            ratio > 1f -> 1f
            ratio < 0f -> 0f
            else -> ratio
        }
        val inverseRatio = 1f - ratio
        val a = alpha(color1) * ratio + alpha(color2) * inverseRatio
        val r = red(color1) * ratio + red(color2) * inverseRatio
        val g = green(color1) * ratio + green(color2) * inverseRatio
        val b = blue(color1) * ratio + blue(color2) * inverseRatio
        return argb(a.toInt(), r.toInt(), g.toInt(), b.toInt())
    }

    inline fun alpha(@ColorInt color: Int): Int = color ushr 24
    inline fun red(@ColorInt color: Int): Int = color shr 16 and 0xFF
    inline fun green(@ColorInt color: Int): Int = color shr 8 and 0xFF
    inline fun blue(@ColorInt color: Int): Int = color and 0xFF

    @ColorInt
    inline fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int = alpha shl 24 or (red shl 16) or (green shl 8) or blue

    @FloatRange(from = 0.0, to = 1.0)
    inline fun getLuminance(color: Int) = 0.2126 * red(color) / 255f + 0.7152 * green(color) / 255f + 0.0722 * blue(color) / 255f
}
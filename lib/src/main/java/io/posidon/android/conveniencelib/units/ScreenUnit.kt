package io.posidon.android.conveniencelib.units

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View

interface ScreenUnit {
    fun toPixels(displayMetrics: DisplayMetrics): Int
    fun toFloatPixels(displayMetrics: DisplayMetrics): Float
}

inline fun ScreenUnit.toPixels(view: View): Int = toPixels(view.context)
inline fun ScreenUnit.toPixels(context: Context): Int = toPixels(context.resources)
inline fun ScreenUnit.toPixels(resources: Resources): Int = toPixels(resources.displayMetrics)

inline fun ScreenUnit.toFloatPixels(view: View): Float = toFloatPixels(view.context)
inline fun ScreenUnit.toFloatPixels(context: Context): Float = toFloatPixels(context.resources)
inline fun ScreenUnit.toFloatPixels(resources: Resources): Float = toFloatPixels(resources.displayMetrics)
package io.posidon.android.conveniencelib

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import kotlin.math.min

inline fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

inline fun Activity.getNavigationBarHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowManager.currentWindowMetrics.windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom
    } else {
        val display = display ?: return 0
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        display.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        val navbarHeight = if (realHeight > usableHeight) realHeight - usableHeight else 0
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        min(if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0, navbarHeight)
    }
}
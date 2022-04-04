package io.posidon.android.conveniencelib

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources

object Device {

    inline fun screenWidth(context: Context): Int = context.resources.displayMetrics.widthPixels
    inline fun screenHeight(context: Context): Int = context.resources.displayMetrics.heightPixels

    inline fun isTablet(resources: Resources): Boolean = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    inline fun isTablet(context: Context): Boolean = isTablet(context.resources)

    inline fun hasNavigationBar(resources: Resources): Boolean {
        val navBarInteractionModeId = resources.getIdentifier(
            "config_navBarInteractionMode",
            "integer",
            "android"
        )
        if (navBarInteractionModeId > 0 && resources.getInteger(navBarInteractionModeId) > 0) {
            // nav gesture is enabled in the settings
            return false
        }
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        return id != 0 && resources.getBoolean(id)
    }

    inline fun hasNavigationBar(context: Context): Boolean = hasNavigationBar(context.resources)
}
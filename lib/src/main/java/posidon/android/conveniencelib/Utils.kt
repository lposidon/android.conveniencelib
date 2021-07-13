package posidon.android.conveniencelib

import android.animation.Animator
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager

inline fun ViewPropertyAnimator.onEnd(crossinline onEnd: (animation: Animator?) -> Unit): ViewPropertyAnimator = setListener(object : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?) {}
    override fun onAnimationEnd(animation: Animator?) = onEnd(animation)
})

inline fun Animator.onEnd(crossinline onEnd: (animation: Animator?) -> Unit) = addListener(object : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?) {}
    override fun onAnimationEnd(animation: Animator?) = onEnd(animation)
})

inline fun PackageManager.isInstalled(packageName: String): Boolean {
    try { getPackageInfo(packageName, 0) }
    catch (e: Exception) { return false }
    return true
}
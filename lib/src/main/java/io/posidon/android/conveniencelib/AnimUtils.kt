package io.posidon.android.conveniencelib

import android.animation.Animator
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.*
import android.os.Build
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import io.posidon.android.conveniencelib.drawable.MaskedDrawable
import kotlin.math.*

object AnimUtils {

    inline fun springInterpolate(x: Float): Float = 1 + (2f.pow(-10f * x) * sin(2 * PI * (x - 0.065f)) / 0.4).toFloat()

    fun <T : Drawable> tryAnimate(activity: Activity, d: T): T {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && d is Animatable2 -> {
                d.registerAnimationCallback(object : Animatable2.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable) = activity.runOnUiThread { d.start() }
                })
                d.start()
            }
            d is Animatable2Compat -> {
                d.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable) = activity.runOnUiThread { d.start() }
                })
                d.start()
            }
            d is AnimationDrawable -> d.start()
            d is LayerDrawable -> {
                for (i in 0 until d.numberOfLayers) {
                    tryAnimate(activity, d.getDrawable(i))
                }
            }
            d is MaskedDrawable -> tryAnimate(activity, d.drawable)
            d is Animatable -> d.start()
        }
        return d
    }

    fun <T : Drawable> tryAnimate(view: View, d: T): T {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && d is Animatable2 -> {
                d.registerAnimationCallback(object : Animatable2.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable) { view.post { d.start() } }
                })
                d.start()
            }
            d is Animatable2Compat -> {
                d.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                    override fun onAnimationEnd(drawable: Drawable) { view.post { d.start() } }
                })
                d.start()
            }
            d is AnimationDrawable -> d.start()
            d is LayerDrawable -> {
                for (i in 0 until d.numberOfLayers) {
                    tryAnimate(view, d.getDrawable(i))
                }
            }
            d is MaskedDrawable -> tryAnimate(view, d.drawable)
            d is Animatable -> d.start()
        }
        return d
    }

    fun clearAnimation(d: Drawable) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && d is Animatable2 -> d.clearAnimationCallbacks()
            d is Animatable2Compat -> d.clearAnimationCallbacks()
            d is Animatable -> d.stop()
            d is LayerDrawable -> {
                for (i in 0 until d.numberOfLayers) {
                    clearAnimation(d.getDrawable(i))
                }
            }
            d is MaskedDrawable -> clearAnimation(d.drawable)
        }
    }
}

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
package posidon.android.conveniencelib

import android.view.animation.Interpolator
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin

class SpringInterpolator : Interpolator {

    override fun getInterpolation(x: Float) = springInterpolate(x)

    companion object {
        inline fun springInterpolate(x: Float): Float = 1 + (2f.pow(-10f * x) * sin(2 * PI * (x - 0.065f)) / 0.4).toFloat()
    }
}
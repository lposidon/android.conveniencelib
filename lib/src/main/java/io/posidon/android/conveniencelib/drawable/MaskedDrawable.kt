package io.posidon.android.conveniencelib.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.graphics.withSave

class MaskedDrawable(
    val drawable: Drawable,
    val path: Path
) : Drawable(), Drawable.Callback {

    init {
        drawable.callback = this
    }

    private val maskPaint = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }

    private val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)

    private val c = Canvas(bitmap)

    override fun draw(canvas: Canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.withSave {
                canvas.translate(bounds.left.toFloat(), bounds.top.toFloat())
                canvas.scale(
                    bounds.width() / drawable.bounds.width().toFloat(),
                    bounds.height() / drawable.bounds.height().toFloat()
                )
                canvas.clipOutPath(path)
                drawable.draw(canvas)
            }
        } else {
            try {
                drawable.draw(c)
                c.drawPath(path, maskPaint)
                canvas.drawBitmap(bitmap, null, bounds, null)
            } catch (e: Exception) {}
        }
    }

    override fun getIntrinsicWidth() = drawable.intrinsicWidth
    override fun getIntrinsicHeight() = drawable.intrinsicHeight

    override fun setAlpha(alpha: Int) { drawable.alpha = alpha }
    override fun getAlpha() = drawable.alpha

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) { drawable.colorFilter = colorFilter }

    override fun unscheduleDrawable(
        who: Drawable,
        what: Runnable
    ) = unscheduleSelf(what)

    override fun invalidateDrawable(
        who: Drawable
    ) = invalidateSelf()

    override fun scheduleDrawable(
        who: Drawable,
        what: Runnable,
        `when`: Long
    ) = scheduleSelf(what, `when`)
}
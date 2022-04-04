package io.posidon.android.conveniencelib

import android.graphics.Bitmap
import android.graphics.Matrix

object Graphics {

    inline fun getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap {
        return Bitmap.createBitmap(
            bm,
            0,
            0,
            bm.width,
            bm.height,
            getResizedMatrix(bm, newHeight, newWidth),
            true
        )
    }

    inline fun getResizedMatrix(bm: Bitmap, newHeight: Int, newWidth: Int): Matrix {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return matrix
    }
}
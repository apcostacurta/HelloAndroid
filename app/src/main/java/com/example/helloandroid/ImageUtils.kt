package com.example.helloandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

object ImageUtils {
    // Faz o resize da imagem
    fun resize(file: File, reqWidth: Int, reqHeight: Int): Bitmap {
// Verifica as dimensões do arquivo
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(file.absolutePath, options)
// Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
// Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(file.absolutePath, options)
    }
    // Calcula o fator de escala
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int):
            Int {
// Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while
                    (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}
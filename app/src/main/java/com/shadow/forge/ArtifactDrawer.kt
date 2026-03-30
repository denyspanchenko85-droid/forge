// Responsibility: Advanced image manipulation, focusing on aesthetic hex placement.
package com.shadow.forge

import android.graphics.*

object ArtifactDrawer {

    // Responsibility: Drawing hex text to fill the visual void between rings.
    fun drawHexCircle(baseBitmap: Bitmap, hexText: String): Bitmap {
        val resultBitmap = baseBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(resultBitmap)
        
        val centerX = resultBitmap.width / 2f
        val centerY = resultBitmap.height / 2f
        // Радіус залишаємо, він підходить
        val radius = resultBitmap.width * 0.32f 

        val paint = Paint().apply {
            color = Color.parseColor("#D4AF37") // Золотий електрум
            
            // --- ЗМІНИ ТУТ ---
            textSize = 52f // Збільшили шрифт, щоб заповнити висоту проміжку
            textScaleX = 1.6f // Трохи розтягнули символи по горизонталі
            letterSpacing = 0.05f // Зменшили відступ, бо розтягнули символи
            // -----------------

            typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
            isAntiAlias = true
        }

        val path = Path().apply {
            addCircle(centerX, centerY, radius, Path.Direction.CW)
        }

        // JUSTIFY: Розподіляємо текст рівномірно на всі 360 градусів
        canvas.drawTextOnPath(hexText, path, 0f, 0f, paint)
        
        return resultBitmap
    }
}

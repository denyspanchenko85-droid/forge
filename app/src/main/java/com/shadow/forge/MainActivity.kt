// Responsibility: Main navigation hub and global crash logging.
package com.shadow.forge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupGlobalExceptionHandler()

        // Кнопка переходу до створення
        findViewById<Button>(R.id.btnGoToInput).setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }

        // Кнопка переходу до галереї (Та сама лінія 23, де була помилка)
        findViewById<Button>(R.id.btnGoToGallery).setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }

    private fun setupGlobalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            ShadowLogger.logError(this, "FATAL: ${throwable.message}")
            System.exit(1)
        }
    }
}

// Responsibility: Handling main navigation hub logic and global error interception.
package com.shadow.forge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Responsibility: Binding navigation buttons to their respective activities.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupGlobalExceptionHandler()

        findViewById<Button>(R.id.btnGoToInput).setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }

        findViewById<Button>(R.id.btnGoToGallery).setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
    }

    // Responsibility: Catching all unhandled exceptions for forge_logs.txt.
    private fun setupGlobalExceptionHandler() {
        val originalHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            ShadowLogger.logError(this, "FATAL: ${throwable.message}")
            originalHandler?.uncaughtException(thread, throwable)
        }
    }
}

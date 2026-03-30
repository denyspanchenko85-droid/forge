// Responsibility: Scanning local storage and displaying forged artifacts in a list.
package com.shadow.forge

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val rvGallery = findViewById<RecyclerView>(R.id.rvGallery)
        rvGallery.layoutManager = LinearLayoutManager(this)

        val artifacts = loadLocalArtifacts()
        rvGallery.adapter = ArtifactAdapter(artifacts)
    }

    private fun loadLocalArtifacts(): List<File> {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return storageDir?.listFiles { file ->
            file.extension == "png" && file.name.startsWith("artifact_")
        }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }
}

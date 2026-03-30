// Responsibility: Extracting EOF metadata from files to display hidden content in the gallery list.
package com.shadow.forge

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ArtifactAdapter(private val files: List<File>) : RecyclerView.Adapter<ArtifactAdapter.ArtifactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artifact, parent, false)
        return ArtifactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtifactViewHolder, position: Int) {
        val file = files[position]
        
        // 1. Назва файлу
        holder.tvFileName.text = "Файл: ${file.name}"

        // 2. Витягуємо та розшифровуємо прихований текст
        val hiddenHex = extractHexFromFile(file)
        val decodedText = if (hiddenHex != null) HexEncoder.decode(hiddenHex) else "Дані пошкоджени"
        holder.tvDecodedContent.text = "Зміст: $decodedText"

        // 3. Мініатюра
        val options = BitmapFactory.Options().apply { inSampleSize = 8 }
        val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
        holder.ivPreview.setImageBitmap(bitmap)
    }

    // Responsibility: Reading the end of the file to find the SHADOW_HEX marker.
    private fun extractHexFromFile(file: File): String? {
        return try {
            val content = file.readText(Charsets.ISO_8859_1) // Читаємо як бінарний текст
            val markerStart = "SHADOW_HEX_START:"
            val markerEnd = ":END"
            
            if (content.contains(markerStart) && content.contains(markerEnd)) {
                content.substringAfter(markerStart).substringBefore(markerEnd)
            } else null
        } catch (e: Exception) {
            null
        }
    }

    override fun getItemCount(): Int = files.size

    class ArtifactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPreview: ImageView = view.findViewById(R.id.ivArtifactPreview)
        val tvFileName: TextView = view.findViewById(R.id.tvArtifactName) // Це ID з item_artifact.xml
        val tvDecodedContent: TextView = view.findViewById(R.id.tvArtifactDescription) // Додамо це поле
    }
}

// Responsibility: Adapting artifact files to UI with proper UTF-8 metadata extraction.
package com.shadow.forge

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File // Додано цей критичний імпорт

class ArtifactAdapter(private val files: List<File>) : RecyclerView.Adapter<ArtifactAdapter.ArtifactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artifact, parent, false)
        return ArtifactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtifactViewHolder, position: Int) {
        val file = files[position]
        holder.tvFileName.text = file.name

        val hiddenHex = extractHexFromFile(file)
        val decodedText = if (hiddenHex != null) HexEncoder.decode(hiddenHex) else "Помилка даних"
        holder.tvDecodedContent.text = decodedText

        val options = BitmapFactory.Options().apply { inSampleSize = 8 }
        val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
        holder.ivPreview.setImageBitmap(bitmap)
    }

    private fun extractHexFromFile(file: File): String? {
        return try {
            val bytes = file.readBytes()
            val content = String(bytes, Charsets.UTF_8)
            val markerStart = "SHADOW_HEX_START:"
            val markerEnd = ":END"
            if (content.contains(markerStart) && content.contains(markerEnd)) {
                content.substringAfter(markerStart).substringBefore(markerEnd)
            } else null
        } catch (e: Exception) { null }
    }

    override fun getItemCount(): Int = files.size

    class ArtifactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPreview: ImageView = view.findViewById(R.id.ivArtifactPreview)
        val tvFileName: TextView = view.findViewById(R.id.tvArtifactName)
        val tvDecodedContent: TextView = view.findViewById(R.id.tvArtifactDescription)
    }
}

// Responsibility: Precise HEX to UTF-8 conversion for Cyrillic support.
package com.shadow.forge

object HexEncoder {

    fun encode(input: String): String {
        return input.toByteArray(Charsets.UTF_8).joinToString("") { byte ->
            "%02x".format(byte)
        }
    }

    fun decode(hex: String): String {
        return try {
            val bytes = ByteArray(hex.length / 2)
            for (i in bytes.indices) {
                bytes[i] = hex.substring(i * 2, i * 2 + 2).toInt(16).toByte()
            }
            String(bytes, Charsets.UTF_8)
        } catch (e: Exception) {
            "Decryption Error"
        }
    }
}

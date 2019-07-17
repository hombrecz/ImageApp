package cz.hombre.imageapp.services

import android.graphics.Bitmap

interface EncodingService {

    fun decodeImageFromB64(dataB64: String): Bitmap?

    fun encodeWithSHA1(openText: String): String
}
package cz.hombre.imageapp.services.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.common.hash.Hashing
import cz.hombre.imageapp.services.EncodingService
import java.nio.charset.Charset
import java.util.*

class EncodingServiceImpl : EncodingService {

    @Throws(IllegalArgumentException::class)
    override fun decodeImageFromB64(dataB64: String): Bitmap? {

        val decodedString = Base64.getDecoder().decode(dataB64)

        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            ?: throw IllegalArgumentException("Couldn't create image from decoded B64 string.")
    }

    override fun encodeWithSHA1(openText: String) =
        Hashing.sha1().hashString(openText, Charset.defaultCharset()).toString()
}
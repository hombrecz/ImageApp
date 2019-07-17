package cz.hombre.imageapp.services.impl

import cz.hombre.imageapp.model.ImageRequest
import cz.hombre.imageapp.services.RestService
import okhttp3.*

class RestServiceImpl : RestService {

    private val HEADER_CONTENT_TYPE = "content-type"
    private val HEADER_AUTHORIZATION = "Authorization"
    private val MEDIA_TYPE = "application/x-www-form-urlencoded"
    private val TARGET_URL = "https://mobility.cleverlance.com/download/bootcamp/image.php?="

    private val client by lazy { OkHttpClient() }

    override fun processImageRequest(imageRequest: ImageRequest, callback: Callback) {

        val request = getDownloadImageRequest(imageRequest)

        client.newCall(request).enqueue(callback)
    }

    private fun getDownloadImageRequest(imageRequest: ImageRequest): Request {

        val mediaType = MediaType.parse(MEDIA_TYPE)
        val body = getRequestBody(mediaType, imageRequest)

        return Request.Builder()
            .url(TARGET_URL)
            .post(body)
            .addHeader(HEADER_CONTENT_TYPE, MEDIA_TYPE)
            .addHeader(HEADER_AUTHORIZATION, imageRequest.password)
            .build()
    }

    private fun getRequestBody(mediaType: MediaType?, imageRequest: ImageRequest) =
        RequestBody.create(mediaType, "username=${imageRequest.username}")
}
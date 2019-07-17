package cz.hombre.imageapp.services

import cz.hombre.imageapp.model.ImageRequest
import okhttp3.Callback

interface RestService {

    fun processImageRequest(imageRequest: ImageRequest, callback: Callback)
}
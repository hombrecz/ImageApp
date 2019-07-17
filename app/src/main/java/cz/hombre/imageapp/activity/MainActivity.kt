package cz.hombre.imageapp.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beust.klaxon.Klaxon
import cz.hombre.imageapp.R
import cz.hombre.imageapp.layout.MainLayout
import cz.hombre.imageapp.layout.getValue
import cz.hombre.imageapp.model.ImageRequest
import cz.hombre.imageapp.model.ImageResponse
import cz.hombre.imageapp.services.EncodingService
import cz.hombre.imageapp.services.RestService
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.io.IOException
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    private val layout = MainLayout()
    private val restService: RestService by inject()
    private val encodingService: EncodingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout.setContentView(this)

        layout.submitButton.setOnClickListener {
            hideKeyboard()

            val username = layout.usernameInput.getValue()
            val password = layout.passwordInput.getValue()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                downloadImage(username, password)
            } else {
                toast(R.string.form_empty)
            }
        }
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun downloadImage(username: String, password: String) {
        val imageRequest = ImageRequest(username, encodingService.encodeWithSHA1(password))
        val callback = prepareCallback()

        toast(R.string.starting_image_download)
        restService.processImageRequest(imageRequest, callback)
    }

    private fun prepareCallback() = object : Callback {

        override fun onFailure(call: Call, e: IOException) {
            runOnUiThread {
                toast(R.string.server_not_reached)
            }
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response.body()?.string()

            if (response.isSuccessful && body != null) {
                setImageFromResponseBody(body)
            } else {
                runOnUiThread {
                    toast(R.string.communication_error)
                }
            }
        }
    }

    private fun setImageFromResponseBody(body: String) {
        val imageData = Klaxon().parse<ImageResponse>(body)

        imageData?.image?.let {
            try {
                val imageBitmap = encodingService.decodeImageFromB64(it)
                runOnUiThread {
                    layout.imageView.setImageBitmap(imageBitmap)
                }
            } catch (e: IllegalArgumentException) {
                Log.e(this.localClassName, "Couldn't decode image from response. Error in B64 decoding: ", e)
            }

        }
    }
}

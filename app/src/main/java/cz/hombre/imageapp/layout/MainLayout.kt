package cz.hombre.imageapp.layout

import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.text.InputType.*
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import cz.hombre.imageapp.R
import cz.hombre.imageapp.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton

class MainLayout : AnkoComponent<MainActivity> {

    lateinit var imageView: ImageView
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var submitButton: FloatingActionButton

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)

            linearLayout {
                linearLayout {
                    imageView = imageView {
                        imageResource = R.mipmap.ic_launcher_round
                        padding = dip(10)
                    }


                    gravity = Gravity.CENTER
                    orientation = LinearLayout.VERTICAL
                }.lparams(
                    width = matchParent,
                    height = dip(500)
                )

                linearLayout {
                    usernameInput = editText {
                        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_NORMAL
                        hintResource = R.string.username_hint
                        textSize = 20f
                        padding = dip(10)
                    }
                    passwordInput = editText {
                        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
                        hintResource = R.string.password_hint
                        textSize = 20f
                        padding = dip(10)
                    }

                    backgroundColor = ContextCompat.getColor(context, R.color.primary)
                    padding = dip(10)
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.VERTICAL
                }.lparams(
                    width = matchParent,
                    height = matchParent
                )

                gravity = Gravity.CENTER
                orientation = LinearLayout.VERTICAL
            }.lparams(
                width = matchParent,
                height = matchParent
            )

            submitButton = floatingActionButton {
                imageResource = R.drawable.ic_fab_download
                useCompatPadding = true
                scaleType = ImageView.ScaleType.CENTER
                backgroundColor = ContextCompat.getColor(context, R.color.accent)
            }.lparams {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(40)
            }
        }
    }
}

fun EditText.getValue() = this.text.toString()
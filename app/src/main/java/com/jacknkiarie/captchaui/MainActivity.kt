package com.jacknkiarie.captchaui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CaptchaLayout.OnButtonClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captcha_layout.setOnButtonClickedListener(this)
        verify_request_button.setOnClickListener {

        }
        CaptchaUI.Builder(this)
            .setCaptchaTextColor(resources.getColor(R.color.colorAccent))
            .setCaptchaLineColor(resources.getColor(R.color.colorAccent))
            .setCaptchaCodeLength(4)
            .setCaptchaPositiveText("OK")
            .setCaptchaPositiveTextColor(Color.WHITE)
            .setCaptchaNegativeText("NOPE")
            .setCaptchaButtonListener(this)
            .build()
    }

    override fun onNegativeButtonClicked() {
        // do something in case the user closes the captcha UI
    }

    override fun onVerificationCodeVerified() {
        // do something in case the user inputs the correct verification code
        Toast.makeText(this, "Everything is awesome", Toast.LENGTH_SHORT).show()
    }
}

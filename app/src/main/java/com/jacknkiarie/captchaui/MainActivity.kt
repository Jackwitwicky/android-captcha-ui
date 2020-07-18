package com.jacknkiarie.captchaui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CaptchaLayout.OnButtonClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val captchaLayout = CaptchaBottomSheet()
        captcha_layout.setOnButtonClickedListener(this)
        verify_request_button.setOnClickListener {
//            captcha_layout.visibility = View.VISIBLE
//            captchaLayout.show(supportFragmentManager.beginTransaction(), "CAPTCHA")

            CaptchaUI.Builder(this@MainActivity)
                .setCaptchaTitle("Captcha Verification").build()
        }
    }

    override fun onNegativeButtonClicked() {
        // do something in case the user closes the captcha UI
    }

    override fun onVerificationCodeVerified() {
        // do something in case the user inputs the correct verification code
        Toast.makeText(this, "Everything is awesome", Toast.LENGTH_SHORT).show()
    }
}

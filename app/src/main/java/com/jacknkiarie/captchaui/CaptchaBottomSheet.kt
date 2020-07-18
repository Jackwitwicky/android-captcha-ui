package com.jacknkiarie.captchaui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CaptchaBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.dialog_captcha, container, false)

        val captchaView = fragmentView.findViewById<CaptchaView>(R.id.captcha_view)
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_LINE_COLOR, DefaultAttributes.CAPTCHA_LINE_COLOR)
            ?.let { captchaView.setLineColor(it) }
//        captchaView.setTextColor(textColor)
//        captchaView.setVerificationCodeLength(verificationCodeLength)

        val captchaHeaderView = fragmentView.findViewById<View>(R.id.captcha_header) as TextView
        captchaHeaderView.text = arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_TITLE, "Tips")

        val captchaDescriptionView = fragmentView.findViewById<TextView>(R.id.captcha_description)
        captchaDescriptionView.text = arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_DESCRIPTION, "Tips")

        val captchaInputView = fragmentView.findViewById<EditText>(R.id.captcha_input)

        val captchaPositiveButton = fragmentView.findViewById<Button>(R.id.captcha_positive_button)
//        captchaPositiveButton.setTextColor(positiveButtonTextColor)
//        captchaPositiveButton.text = positiveButtonText
        captchaPositiveButton.setOnClickListener {
            if (captchaView.isInputCodeValid(captchaInputView.text.toString())) {
//                callback.onVerificationCodeVerified()
//                this.visibility = View.GONE
                this.dismiss()
            }
            else {
                Toast.makeText(context, "Incorrect verification code, please re-enter", Toast.LENGTH_SHORT).show()
            }
        }


        val captchaNegativeButton = fragmentView.findViewById<Button>(R.id.captcha_negative_button)
//        captchaNegativeButton.setTextColor(negativeButtonTextColor)
//        captchaNegativeButton.text = negativeButtonText
        captchaNegativeButton.setOnClickListener {
//            callback.onNegativeButtonClicked()
            this.dismiss()
        }
        return fragmentView
    }
}
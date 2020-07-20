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

    private lateinit var callback: CaptchaLayout.OnButtonClickedListener

    fun setOnButtonClickedListener(callback: CaptchaLayout.OnButtonClickedListener) {
        this.callback = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.dialog_captcha, container, false)

        val captchaView = fragmentView.findViewById<CaptchaView>(R.id.captcha_view)
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_LINE_COLOR, DefaultAttributes.CAPTCHA_LINE_COLOR)
            ?.let { captchaView.setLineColor(it) }
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_TEXT_COLOR, DefaultAttributes.CAPTCHA_TEXT_COLOR)
            ?.let { captchaView.setTextColor(it) }
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_CODE_LENGTH, DefaultAttributes.CAPTCHA_CODE_LENGTH)
            ?.let { captchaView.setVerificationCodeLength(it) }

        val captchaHeaderView = fragmentView.findViewById<View>(R.id.captcha_header) as TextView
        captchaHeaderView.text = arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_TITLE, DefaultAttributes.CAPTCHA_TITLE)

        val captchaDescriptionView = fragmentView.findViewById<TextView>(R.id.captcha_description)
        captchaDescriptionView.text = arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_DESCRIPTION, DefaultAttributes.CAPTCHA_DESCRIPTION)

        val captchaInputView = fragmentView.findViewById<EditText>(R.id.captcha_input)

        val captchaPositiveButton = fragmentView.findViewById<Button>(R.id.captcha_positive_button)
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR, DefaultAttributes.CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR)
            ?.let { captchaPositiveButton.setTextColor(it) }
        arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT, DefaultAttributes.CAPTCHA_POSITIVE_BUTTON_TEXT)
            ?.let { captchaPositiveButton.text = it }

        captchaPositiveButton.setOnClickListener {
            if (captchaView.isInputCodeValid(captchaInputView.text.toString())) {
                callback.onVerificationCodeVerified()
                this.dismiss()
            }
            else {
                Toast.makeText(context, "Incorrect verification code, please re-enter", Toast.LENGTH_SHORT).show()
            }
        }


        val captchaNegativeButton = fragmentView.findViewById<Button>(R.id.captcha_negative_button)
        arguments?.getInt(CaptchaUI.EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR, DefaultAttributes.CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR)
            ?.let { captchaNegativeButton.setTextColor(it) }
        arguments?.getString(CaptchaUI.EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT, DefaultAttributes.CAPTCHA_NEGATIVE_BUTTON_TEXT)
            ?.let { captchaNegativeButton.text = it }
        captchaNegativeButton.setOnClickListener {
            callback.onNegativeButtonClicked()
            this.dismiss()
        }
        return fragmentView
    }
}
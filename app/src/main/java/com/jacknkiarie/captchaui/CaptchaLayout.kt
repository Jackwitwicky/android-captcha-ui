package com.jacknkiarie.captchaui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*


class CaptchaLayout : RelativeLayout {

    private lateinit var callback: OnButtonClickedListener

    fun setOnButtonClickedListener(callback: OnButtonClickedListener) {
        this.callback = callback
    }

    interface OnButtonClickedListener {
        fun onNegativeButtonClicked()
        fun onVerificationCodeVerified()
    }

    var mInflater: LayoutInflater? = null
    private var lineColor : Int = Color.BLACK
    private var textColor : Int = Color.BLACK

    private lateinit var captchaHeader : String
    private lateinit var captchaDescription : String
    private var verificationCodeLength = CaptchaView.DEFAULT_VERIFICATION_CODE_LENGTH

    private var positiveButtonTextColor: Int = Color.BLACK
    private var positiveButtonText = "OK"

    private var negativeButtonTextColor: Int = Color.BLACK
    private var negativeButtonText = "CANCEL"

    constructor (context: Context?) : super(context) {
        mInflater = LayoutInflater.from(context)
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        mInflater = LayoutInflater.from(context)
        setupAttributes(attrs)
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mInflater = LayoutInflater.from(context)
        setupAttributes(attrs)
        init()
    }

    private fun init() {
        val v: View = mInflater!!.inflate(R.layout.dialog_captcha, this, true)
        val captchaView = v.findViewById<CaptchaView>(R.id.captcha_view)
        captchaView.setLineColor(lineColor)
        captchaView.setTextColor(textColor)
        captchaView.setVerificationCodeLength(verificationCodeLength)

        val captchaHeaderView = v.findViewById<View>(R.id.captcha_header) as TextView
        captchaHeaderView.text = captchaHeader

        val captchaDescriptionView = v.findViewById<TextView>(R.id.captcha_description)
        captchaDescriptionView.text = captchaDescription

        val captchaInputView = v.findViewById<EditText>(R.id.captcha_input)

        val captchaPositiveButton = v.findViewById<Button>(R.id.captcha_positive_button)
        captchaPositiveButton.setTextColor(positiveButtonTextColor)
        captchaPositiveButton.text = positiveButtonText
        captchaPositiveButton.setOnClickListener {
            if (captchaView.isInputCodeValid(captchaInputView.text.toString())) {
                callback.onVerificationCodeVerified()
                this.visibility = View.GONE
            }
            else {
                Toast.makeText(context, "Incorrect verification code, please re-enter", Toast.LENGTH_SHORT).show()
            }
        }


        val captchaNegativeButton = v.findViewById<Button>(R.id.captcha_negative_button)
        captchaNegativeButton.setTextColor(negativeButtonTextColor)
        captchaNegativeButton.text = negativeButtonText
        captchaNegativeButton.setOnClickListener {
            callback.onNegativeButtonClicked()
            this.visibility = View.GONE
        }
        v.invalidate()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }

        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CaptchaLayout
        )
        // get any passed in params
        lineColor = a.getColor(R.styleable.CaptchaLayout_line_color, Color.BLACK)
        textColor = a.getColor(R.styleable.CaptchaLayout_text_color, Color.BLACK)

        captchaHeader = a.getString(R.styleable.CaptchaLayout_captcha_header) ?: "Captcha Verification"
        captchaDescription = a.getString(R.styleable.CaptchaLayout_captcha_description) ?: "The operation you are about to do cannot be reversed or undone. Please proceed if you are certain this is what you would like to do."
        verificationCodeLength = a.getInt(R.styleable.CaptchaLayout_captcha_code_length, CaptchaView.DEFAULT_VERIFICATION_CODE_LENGTH)

        // positive button
        positiveButtonTextColor = a.getColor(R.styleable.CaptchaLayout_captcha_positive_button_text_color, Color.BLACK)
        if (a.getString(R.styleable.CaptchaLayout_captcha_positive_button_text) != null) {
            positiveButtonText = a.getString(R.styleable.CaptchaLayout_captcha_positive_button_text)!!
        }

        // negative button
        negativeButtonTextColor = a.getColor(R.styleable.CaptchaLayout_captcha_negative_button_text_color, Color.BLACK)
        if (a.getString(R.styleable.CaptchaLayout_captcha_negative_button_text) != null) {
            negativeButtonText = a.getString(R.styleable.CaptchaLayout_captcha_negative_button_text)!!
        }

        //Don't forget this
        a.recycle()
    }
}
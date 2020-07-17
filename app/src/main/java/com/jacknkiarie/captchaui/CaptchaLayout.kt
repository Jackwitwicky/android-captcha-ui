package com.jacknkiarie.captchaui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView


class CaptchaLayout : RelativeLayout {

    var mInflater: LayoutInflater? = null
    private var lineColor : Int = Color.BLACK
    private var textColor : Int = Color.BLACK

    private lateinit var captchaHeader : String
    private lateinit var captchaDescription : String

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
        val captchaHeaderView = v.findViewById<View>(R.id.captcha_header) as TextView
        captchaHeaderView.text = captchaHeader

        val captchaDescriptionView = v.findViewById<TextView>(R.id.captcha_description)
        captchaDescriptionView.text = captchaDescription

        val captchaPositiveButton = v.findViewById<Button>(R.id.captcha_positive_button)
        captchaPositiveButton.setTextColor(positiveButtonTextColor)
        captchaPositiveButton.text = positiveButtonText

        val captchaNegativeButton = v.findViewById<Button>(R.id.captcha_negative_button)
        captchaNegativeButton.setTextColor(negativeButtonTextColor)
        captchaNegativeButton.text = negativeButtonText

        val captchaView = v.findViewById<CaptchaView>(R.id.captcha_view)
        captchaView.setLineColor(lineColor)
        captchaView.setTextColor(textColor)
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
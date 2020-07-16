package com.jacknkiarie.captchaui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView


class CaptchaLayout : RelativeLayout {

    var mInflater: LayoutInflater? = null
    private var lineColor : Int = Color.BLACK
    private var textColor : Int = Color.BLACK

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
        val tv = v.findViewById<View>(R.id.captcha_header) as TextView
        tv.text = " Custom RelativeLayout"

        val captchaView = v.findViewById<CaptchaView>(R.id.captcha_view)

        captchaView.mPaint?.color = lineColor
        captchaView.mTextPaint?.color = textColor
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
        //Use a
        lineColor = a.getColor(R.styleable.CaptchaLayout_line_color, Color.BLACK)
        textColor = a.getColor(R.styleable.CaptchaLayout_text_color, Color.BLACK)

        //Don't forget this
        a.recycle()
    }
}
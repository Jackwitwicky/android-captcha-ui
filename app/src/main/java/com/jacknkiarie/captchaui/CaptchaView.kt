package com.jacknkiarie.captchaui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*


class CaptchaView : View {
    private lateinit var mContext : Context
    private lateinit var mTextPaint: Paint
    private lateinit var mLinePaint: Paint
    var textWidth = 0f
    var alphabet = arrayOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
    )

    constructor(context: Context) : super(context) {
        setupViews(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        setupViews(context)
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
//        val a = context.obtainStyledAttributes(
//            attrs,
//            R.styleable.CaptchaView
//        )
//        Log.i(
//            "test", a.getString(
//                R.styleable.CaptchaView_extraInformation
//            )
//        )

        //Don't forget this
//        a.recycle()
    }

    private fun setupViews(context: Context) {
        this.mContext = context
        mTextPaint =
            Paint(Paint.LINEAR_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG)
        mTextPaint.color = Color.BLACK
        mTextPaint.textSize = pxFromDp(context, 24f)
        textWidth = pxFromDp(context, 24f)
        mLinePaint = Paint()
        mLinePaint.isAntiAlias = true
        mLinePaint.style = Paint.Style.STROKE
        mLinePaint.color = Color.BLUE
        mLinePaint.strokeWidth = 3f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var x = 50
        val fontWidth = textWidth
        for (c in generatePassCode().toCharArray()) {
            canvas.save()
            canvas.rotate(
                generateRandomRotationAngle(-45, 45).toFloat(),
                x.toFloat(),
                height / 2.toFloat()
            )
            canvas.drawText(
                c.toString(), x.toFloat(), canvas.height / 2.toFloat(),
                mTextPaint!!
            )
            canvas.restore()
            x += fontWidth.toInt()
        }

//        canvas.save();
//        canvas.rotate(-20, getWidth()/2, getHeight()/2);
//        canvas.drawText("Canvas basics", (float) 200, (float) 200, mTextPaint);
//        canvas.restore();

//        canvas.drawText("Normal text", (float) 50, (float) 50, mTextPaint);

        for (c in (0..6)) {
            val lineAttributes = generateRandomLine()
            canvas.drawLine(lineAttributes.startingXCoordinate, lineAttributes.startingYCoordinate,
                lineAttributes.endingXCoordinate, lineAttributes.endingYCoordinate, mLinePaint)
        }
    }

    private val randomAlphabet: String
        get() {
            val random = Random()
            return alphabet[random.nextInt(25)]
        }

    private fun generatePassCode(): String {
        var passCodeString = ""
        for (i in 0..4) {
            passCodeString += randomAlphabet
        }
        return passCodeString
    }

    private fun generateRandomRotationAngle(min: Int, max: Int): Int {
        return min + (Math.random() * (max - min + 1)).toInt()
    }

    private fun generateRandomLine() : LineAttributes {
        val startingXCoordinate = (0..width).random()
        val startingYCoordinate = (0..height).random()

        val endingXCoordinate = (0..width).random()
        val endingYCoordinate = (0..height).random()

        return LineAttributes(startingXCoordinate.toFloat(), startingYCoordinate.toFloat(), endingXCoordinate.toFloat(), endingYCoordinate.toFloat())
    }

    public fun setTextColor(textColor : Int) {
        mTextPaint.color = textColor
    }

    fun setLineColor(lineColor: Int) {
        mLinePaint.color = lineColor
    }

    companion object {
        fun pxFromDp(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }
    }
}
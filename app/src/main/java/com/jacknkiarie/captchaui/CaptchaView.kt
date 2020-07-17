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
    private lateinit var verificationCode: String
    private var verificationCodeLength: Int = 4

    private var textWidth = 0f
    private var rotationalAngleList = mutableListOf<Int>()
    private var obfuscationLinesList = mutableListOf<LineAttributes>()
    private var alphabet = arrayOf(
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
        verificationCode = generateVerificationCode(verificationCodeLength)
        setupVerificationCodesRotationAngles(verificationCode.length)
        setupObfuscationLines()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var x = 50
        var characterIndex = 0
        val fontWidth = textWidth
        for (c in verificationCode.toCharArray()) {
            canvas.save()
            canvas.rotate(
                rotationalAngleList[characterIndex].toFloat(),
                x.toFloat(),
                height / 2.toFloat()
            )
            canvas.drawText(
                c.toString(), x.toFloat(), canvas.height / 2.toFloat(),
                mTextPaint
            )
            canvas.restore()
            x += fontWidth.toInt()
            characterIndex += 1
        }

        var lineIndex = 0

        for (index in 0..DEFAULT_OBFUSCATION_LINE_COUNT) {
            canvas.drawLine(
                obfuscationLinesList[lineIndex].startingXCoordinate, obfuscationLinesList[lineIndex].startingYCoordinate,
                obfuscationLinesList[lineIndex].endingXCoordinate, obfuscationLinesList[lineIndex].endingYCoordinate, mLinePaint)
            lineIndex += 1
        }
    }

    private val randomAlphabet: String
        get() {
            val random = Random()
            return alphabet[random.nextInt(25)]
        }

    private fun generateVerificationCode(verificationCodeLength: Int): String {
        var passCodeString = ""
        for (i in 0 until verificationCodeLength) {
            passCodeString += randomAlphabet
        }
        return passCodeString
    }

    private fun generateRandomRotationAngle(min: Int, max: Int): Int {
        return min + (Math.random() * (max - min + 1)).toInt()
    }

    private fun generateRandomLine() : LineAttributes {
        val startingXCoordinate = (0..pxFromDp(context, 200f).toInt()).random()
        val startingYCoordinate = (0..pxFromDp(context, 40f).toInt()).random()

        val endingXCoordinate = (0..pxFromDp(context, 200f).toInt()).random()
        val endingYCoordinate = (0..pxFromDp(context, 40f).toInt()).random()

        return LineAttributes(startingXCoordinate.toFloat(), startingYCoordinate.toFloat(), endingXCoordinate.toFloat(), endingYCoordinate.toFloat())
    }

    // create a list that will contain random angle to be used to rotate each
    // of the verification code character
    private fun setupVerificationCodesRotationAngles(verificationCodeLength : Int) {
        for (verificationCodeIndex in (0..verificationCodeLength)) {
            rotationalAngleList.add(generateRandomRotationAngle(MINIMUM_ROTATIONAL_ANGLE, MAXIMUM_ROTATIONAL_ANGLE))
        }
    }

    // create a list that will contain random lines to be used to obfuscate the
    // verification code
    private fun setupObfuscationLines() {
        for (c in (0..DEFAULT_OBFUSCATION_LINE_COUNT)) {
            obfuscationLinesList.add(generateRandomLine())
        }
    }

    fun setTextColor(textColor : Int) {
        mTextPaint.color = textColor
    }

    fun setLineColor(lineColor: Int) {
        mLinePaint.color = lineColor
    }

    fun setVerificationCodeLength(verificationCodeLength: Int) {
        this.verificationCodeLength = verificationCodeLength
        verificationCode = generateVerificationCode(verificationCodeLength)
        setupVerificationCodesRotationAngles(verificationCodeLength)
        this.invalidate()
    }

    fun getVerificationCode() : String {
        return verificationCode
    }

    fun isInputCodeValid(inputCode: String): Boolean {
        return verificationCode == inputCode
    }

    companion object {
        fun pxFromDp(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }

        private const val MINIMUM_ROTATIONAL_ANGLE = -45
        private const val MAXIMUM_ROTATIONAL_ANGLE = 45
        private const val DEFAULT_OBFUSCATION_LINE_COUNT = 6
        const val DEFAULT_VERIFICATION_CODE_LENGTH = 4
    }
}
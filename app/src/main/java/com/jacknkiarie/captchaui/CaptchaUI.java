package com.jacknkiarie.captchaui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CaptchaUI {

    // constant variables
    public static final String EXTRA_CAPTCHA_TITLE = "EXTRA_CAPTCHA_TITLE";
    public static final String EXTRA_CAPTCHA_DESCRIPTION = "EXTRA_CAPTCHA_DESCRIPTION";
    public static final String EXTRA_CAPTCHA_LINE_COLOR = "EXTRA_CAPTCHA_LINE_COLOR";
    public static final String EXTRA_CAPTCHA_TEXT_COLOR = "EXTRA_CAPTCHA_TEXT_COLOR";
    public static final String EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR = "EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR";
    public static final String EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT = "EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT";
    public static final String EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT = "EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT";
    public static final String EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR = "EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR";
    public static final String EXTRA_CAPTCHA_CODE_LENGTH = "EXTRA_CAPTCHA_CODE_LENGTH";

    private CaptchaUI(final Builder builder) {

        // check if button listener has not been provided and throw an error
        if (builder.buttonListener == null) {
            throw new IllegalStateException("The Captcha Button Click Listener needs to be provided");
        }
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CAPTCHA_TITLE, builder.captchaTitle);
        bundle.putString(EXTRA_CAPTCHA_DESCRIPTION, builder.captchaDescription);
        bundle.putInt(EXTRA_CAPTCHA_LINE_COLOR, builder.captchaLineColor);
        bundle.putInt(EXTRA_CAPTCHA_TEXT_COLOR, builder.captchaTextColor);
        bundle.putString(EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT, builder.captchaPositiveButtonText);
        bundle.putInt(EXTRA_CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR, builder.captchaPositiveButtonTextColor);
        bundle.putString(EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT, builder.captchaNegativeButtonText);
        bundle.putInt(EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR, builder.captchaNegativeButtonTextColor);
        bundle.putInt(EXTRA_CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR, builder.captchaNegativeButtonTextColor);
        bundle.putInt(EXTRA_CAPTCHA_CODE_LENGTH, builder.captchaCodeLength);
        CaptchaBottomSheet captchaLayout = new CaptchaBottomSheet();
        captchaLayout.setOnButtonClickedListener(builder.buttonListener);
        captchaLayout.setArguments(bundle);
        captchaLayout.show(builder.context.getSupportFragmentManager(), "CAPTCHA_BOTTOM_SHEET");
    }

    public static class Builder {
        private AppCompatActivity context;
        private String captchaTitle = DefaultAttributes.CAPTCHA_TITLE;
        private String captchaDescription = DefaultAttributes.CAPTCHA_DESCRIPTION;
        private int captchaLineColor = DefaultAttributes.CAPTCHA_LINE_COLOR;
        private int captchaTextColor = DefaultAttributes.CAPTCHA_TEXT_COLOR;
        private String captchaPositiveButtonText = DefaultAttributes.CAPTCHA_POSITIVE_BUTTON_TEXT;
        private int captchaPositiveButtonTextColor = DefaultAttributes.CAPTCHA_POSITIVE_BUTTON_TEXT_COLOR;
        private String captchaNegativeButtonText = DefaultAttributes.CAPTCHA_NEGATIVE_BUTTON_TEXT;
        private int captchaNegativeButtonTextColor = DefaultAttributes.CAPTCHA_NEGATIVE_BUTTON_TEXT_COLOR;
        private int captchaCodeLength = DefaultAttributes.CAPTCHA_CODE_LENGTH;
        private CaptchaLayout.OnButtonClickedListener buttonListener;

        public Builder(AppCompatActivity context) {
            this.context = context;
        }

        public Builder setCaptchaTitle(final String captchaTitle) {
            this.captchaTitle = captchaTitle;
            return this;
        }

        public Builder setCaptchaDescription(final String captchaDescription) {
            this.captchaDescription = captchaDescription;
            return this;
        }

        public Builder setCaptchaLineColor(final int captchaLineColor) {
            this.captchaLineColor = captchaLineColor;
            return this;
        }

        public Builder setCaptchaTextColor(final int captchaTextColor) {
            this.captchaTextColor = captchaTextColor;
            return this;
        }

        public Builder setCaptchaPositiveTextColor(final int captchaPositiveButtonTextColor) {
            this.captchaPositiveButtonTextColor = captchaPositiveButtonTextColor;
            return this;
        }

        public Builder setCaptchaPositiveText(final String captchaPositiveButtonText) {
            this.captchaPositiveButtonText = captchaPositiveButtonText;
            return this;
        }

        public Builder setCaptchaNegativeTextColor(final int captchaNegativeButtonTextColor) {
            this.captchaNegativeButtonTextColor = captchaNegativeButtonTextColor;
            return this;
        }

        public Builder setCaptchaNegativeText(final String captchaNegativeButtonText) {
            this.captchaNegativeButtonText = captchaNegativeButtonText;
            return this;
        }

        public Builder setCaptchaCodeLength(final int captchaCodeLength) {
            this.captchaCodeLength = captchaCodeLength;
            return this;
        }

        public Builder setCaptchaButtonListener(final CaptchaLayout.OnButtonClickedListener buttonListener ) {
            this.buttonListener = buttonListener;
            return this;
        }
        public CaptchaUI build() {
            return new CaptchaUI(this);
        }
    }
}

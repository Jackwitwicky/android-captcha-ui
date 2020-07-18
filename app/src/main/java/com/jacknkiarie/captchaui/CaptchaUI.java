package com.jacknkiarie.captchaui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CaptchaUI {

    // constant variables
    public static final String EXTRA_CAPTCHA_TITLE = "EXTRA_CAPTCHA_TITLE";
    public static final String EXTRA_CAPTCHA_DESCRIPTION = "EXTRA_CAPTCHA_DESCRIPTION";
    public static final String EXTRA_CAPTCHA_LINE_COLOR = "EXTRA_CAPTCHA_LINE_COLOR";

    private CaptchaUI(final Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CAPTCHA_TITLE, builder.captchaTitle);
        bundle.putString(EXTRA_CAPTCHA_DESCRIPTION, builder.captchaDescription);
        bundle.putInt(EXTRA_CAPTCHA_LINE_COLOR, builder.captchaLineColor);
        CaptchaBottomSheet captchaLayout = new CaptchaBottomSheet();
        captchaLayout.setArguments(bundle);
        captchaLayout.show(builder.context.getSupportFragmentManager(), "CAPTCHA_BOTTOM_SHEET");
    }

    public static class Builder {
        private AppCompatActivity context;
        private String captchaTitle = DefaultAttributes.CAPTCHA_TITLE;
        private String captchaDescription = DefaultAttributes.CAPTCHA_DESCRIPTION;
        private int captchaLineColor = DefaultAttributes.CAPTCHA_LINE_COLOR;
        private int captchaTextColor = DefaultAttributes.CAPTCHA_TEXT_COLOR;

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

        public CaptchaUI build() {
            return new CaptchaUI(this);
        }
    }
}

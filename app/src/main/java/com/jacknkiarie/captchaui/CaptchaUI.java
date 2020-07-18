package com.jacknkiarie.captchaui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CaptchaUI {

    // constant variables
    public static final String EXTRA_CAPTCHA_TITLE = "EXTRA_CAPTCHA_TITLE";
    public static final String EXTRA_CAPTCHA_DESCRIPTION = "EXTRA_CAPTCHA_DESCRIPTION";

    private CaptchaUI(final Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CAPTCHA_TITLE, builder.captchaTitle);
        bundle.putString(EXTRA_CAPTCHA_DESCRIPTION, builder.captchaDescription);
        CaptchaBottomSheet captchaLayout = new CaptchaBottomSheet();
        captchaLayout.setArguments(bundle);
        captchaLayout.show(builder.context.getSupportFragmentManager(), "CAPTCHA_BOTTOM_SHEET");
    }

    public static class Builder {
        private AppCompatActivity context;
        private String captchaTitle = "Captcha Verification";
        private String captchaDescription = "The operation you are about to do cannot be reversed or undone. Please proceed if you are certain this is what you would like to do.";

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

        public CaptchaUI build() {
            return new CaptchaUI(this);
        }
    }
}

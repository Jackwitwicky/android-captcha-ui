# Android Captcha UI

The popular robot-protection functionality now available on Android. The Android Captcha UI is an android library that allows you to offer an additional layer of security
before permitting your user to perform sensitive operations. e.g. Deleting of crucial records that cannot be undone. The library functions by generate a CAPTCHA-like verification code
that the user must input which is then validated against the randomly generated CAPTCHA, and a success callback is sent back to you for further steps.


CAPTCHA UI LAYOUT             |  CAPTCHA UI BOTTOM SHEET
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/8895134/87770353-9ce8be80-c827-11ea-84c2-bf4bc3cf78d8.png)  |  ![](https://user-images.githubusercontent.com/8895134/87907917-30abcc00-ca6e-11ea-8530-fc0ea09dfb95.png)

## Getting Started

This module is designed to be as seamless as possible and should work as a plug and play to your existing android application
requiring minimal modification.

## How it Works

The library is packaged as a custom view that is responsible for handling the entire verification process.

### Prerequisites

What things you need to install the software and how to install them

```
Android Studio

A working Android Project

Minimum sdk used :- 16

```

### Installing

Use the following steps to get up and running as fast as possible and get back to building cool content.

#### Gradle

```
//on your project wide build.gradle file, add the maven repo link

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then next step is to add the library dependency on your app build.grade file

```
//on your app build.gradle file, add the captcha ui

dependencies {
	        implementation 'com.github.Jackwitwicky:android-captcha-ui:v0.0.2'
	}
```

#### Customization

There are two ways to use the library. Either as a custom view via XML if you want control on where to display the UI on your activity,  
or as a bottom sheet that you can call from your activity programmatically

#### As a Layout

Add the following piece of code in your calling activity XML file

```
<com.jacknkiarie.captchaui.CaptchaLayout
        android:id="@+id/captcha_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:line_color="@color/colorAccent"
        app:text_color="@color/colorAccent"
        app:captcha_positive_button_text_color="@android:color/white"
        app:captcha_negative_button_text="NOPE"
        app:captcha_positive_button_text="OK"
        app:captcha_header="Captcha Verification"
        app:captcha_description="The operation you are about to do cannot be reversed or undone. Please proceed if you are certain this is what you would like to do."
        app:captcha_code_length="4" />

```

That's it! That is all you need to display a verification CAPTCHA screen that prompts the user to verify a potentially destructive request.
 The library will automatically handle when the user inputs invalid verification code and provide callbacks that will notify you when the user inputs the correct code
 or when they opt out of the UI using the cancel button.

 However the library offers multiple customization options you can use setup the UI to suit your particular app needs


```
        app:line_color="@android:color/black"
        app:text_color="@android:color/black"
        app:captcha_header="Captcha Verification"
        app:captcha_description="The operation you are about to do cannot be reversed or undone. Please proceed if you are certain this is what you would like to do."
        app:captcha_code_length="4"
        app:captcha_positive_button_text_color="@android:color/white"
        app:captcha_positive_button_text="OK"
        app:captcha_positive_button_color="@android:color/blue"
        app:captcha_negative_button_text="NOPE"
        app:captcha_negative_button_text_color="@android:color/white"
        app:captcha_negative_button_color="@android:color/blue"
        

```

In your calling activity. Implement the CaptchaLayout.OnButtonClickedListener to receive result of the user input so as to perform follow up
actions. e.g sending the request to the server to delete the verified record

```
    class MainActivity : AppCompatActivity(), CaptchaLayout.OnButtonClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captcha_layout.setOnButtonClickedListener(this)
    }

    override fun onNegativeButtonClicked() {
        // do something in case the user closes the captcha UI
    }

    override fun onVerificationCodeVerified() {
        // do something in case the user inputs the correct verification code
        Toast.makeText(this, "Everything is awesome", Toast.LENGTH_SHORT).show()
    }
}


```

### As a Bottom Sheet Dialog

The lib uses a Builder pattern to instantiate and provide attributes you would like to specify for the Captcha-UI. To show the UI, simply add the
following to your activity/fragment.

```
CaptchaUI.Builder(this)
            .setCaptchaButtonListener(this)
            .build()
```

That is the only method you need to specify to present the user with the default Captcha-UI. You can however use various parameters as shown below to get the exact look and feel
 that you would like.

```
CaptchaUI.Builder(this)
            .setCaptchaTitle("")
            .setCaptchaDescription("")
            .setCaptchaTextColor(Color.BLACK)
            .setCaptchaLineColor(Color.BLACK)
            .setCaptchaCodeLength(5)
            .setCaptchaPositiveText("COOL")
            .setCaptchaPositiveTextColor(Color.CYAN)
            .setCaptchaNegativeText("NOPE!")
            .setCaptchaNegativeTextColor(Color.RED)
            .setCaptchaButtonListener(this)
            .build()
```
### NB
Both the Captcha Layout and the Captcha Bottom Sheet expects the calling activity to implement the OnButtonClickedListener.
 Attempting to click the Positive or Negative Button without having defined the listener results in a un-initialized error and an Illegal State Exception error respectively

## Built With

* [Ubuntu 19.10(https://www.ubuntu.com/desktop/) - Operating System Used
* [Android Studio](https://developer.android.com/studio/index.html) - Development Environment
* Love - Key ingredient

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.
In case of any improvements that can be made on the lib, feel free to send me an email, create an issue or a pull request and I'd be happy to take a look at it.

## Author

 **Jack Kiarie** - *Initial work* - [Other Works](https://incobeta.com)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details



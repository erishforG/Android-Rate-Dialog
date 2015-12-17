Andialog / android-rate-dialog
============

[![Build Status](https://travis-ci.org/erishforG/Android-Rate-Dialog.svg?branch=master)](https://travis-ci.org/erishforG/Android-Rate-Dialog)

Andialog / Android-Rate-Dialog is a library to help you make RateDialog easier than before.

![screen shot](http://i.imgsafe.org/f845905.png)

## Getting Started

![Maven Badges](https://maven-badges.herokuapp.com/maven-central/com.erish.andialog/andialog/badge.svg)

You can download from maven central.

```groovy
dependencies {
  compile ‘com.erish.andialog:andialog:0.1.0’
}
```

## Sample

Please try to move the [sample](https://github.com/erishforG/Android-Rate-Dialog/tree/master/app).

## Support

andialog supports over API level 9.

### Configuration

andialog provides methods to configure its behavior.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Anidialog
         *
         * Andialog starts with with function.
         *
         * Each conditions (setInstallDate, setLaunchTimes, setRemindInterval, setEventTimes)
         * has no impacts until users set values.
         *
         * If users set values in this conditions,
         * then Andialog will work with users' setting values except non-set values.
         *
         */

        Andialog.with(this)
                .setInstallDate(0) // default -1, 0 means 1st launch day(install day).
                .setLaunchTimes(3) // default -1, 1 means 1st launch day(install day).
                .setRemindInterval(1) // default 1
                .setShowNeutralButton(true) // default true
                //.setEventsTimes(5) you can also add event trigger
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .setIsDebug(true) // this is for debug (ignore all conditions)
                .launch();

        Andialog.showRateDialogIfMeetsConditions(this);
    }
```

### Event Tracking

When you want to track significant events, write code as below.

```java

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  Andialog.with(this).setEventsTimes(2).monitor();
}

@Override
public void onClick() {
  Andialog.passSignificantEvent(this); // when user pass this line for the third time, dialog appears.
}
```

### Clear show dialog flag

When you want to show the dialog again, call `Andialog#clearAgreeShowDialog()`.

```java
Andialog.with(this).clearAgreeShowDialog();
```

### When the button presses on

call `Andialog.showDialog(Activity)`.

```java
Andialog.with(this).showDialog(this);
```

### Set custom view

call `Andialog.setView(View)`.

```java
LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
View view = inflater.inflate(R.layout.custom_dialog, (ViewGroup)findViewById(R.id.layout_root));
AppRate.with(this).setView(view).monitor();
```

### Custom dialog

If you want to use your own dialog labels, override string xml resources on your application.

```xml
<resources>
    <string name="app_name">Andialog</string>
    <string name="rate_dialog_title">Hello World</string>
    <string name="rate_dialog_message">If you enjoy playing this app, would you mind taking a moment to rate it? It won\'t take more than a minute. Thanks for your support!</string>
    <string name="rate_dialog_positive">Rate It Now</string>
    <string name="rate_diloag_neutral">Remind Me Later</string>
    <string name="rate_dialog_negative">No, Thanks</string>
</resources>
```

### Set Market Store

Basically the market is for google play store.
But in spcecially S.Korea, there are several stores(One Store and Naver app store)
You can use it.

```java
Andialog.with(this).setPackageType("One" or "Naver").setPackageId("your market id").
```

## Contribute

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Added some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request

## Copyright & Derivatives

[copyright](https://github.com/erishforG/Android-Rate-Dialog/blob/master/LISENCE) /
[Android-Rate](https://github.com/hotchemi/Android-Rate)

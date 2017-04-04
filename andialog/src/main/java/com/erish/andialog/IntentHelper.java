package com.erish.andialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

final class IntentHelper {

    private static final String GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending";

    private IntentHelper() {
    }

    static Uri getGooglePlay(String packageName) {
        return packageName == null ? null : Uri.parse(CommonInfo.PACKAGE_ADDRESS_GOOGLE + packageName);
    }

    static Uri getOneStore(String packageId) {
        return packageId == null ? null : Uri.parse(CommonInfo.PACKAGE_ADDRESS_ONE + packageId);
    }

    static Intent createIntentForGooglePlay(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, getGooglePlay(context.getPackageName()));
        intent.setPackage(GOOGLE_PLAY_PACKAGE_NAME);
        return intent;
    }

    static Intent createIntentForOneStore(Context context, String packageId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, getOneStore(packageId));
        intent.setPackage(GOOGLE_PLAY_PACKAGE_NAME);
        return intent;
    }
}
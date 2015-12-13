package com.erish.andialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;

final class DialogManager {

    private DialogManager() {
    }

    static private AlertDialog.Builder getDialogBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }

    static private android.support.v7.app.AlertDialog.Builder getMaterialDialogBuilder(Context context) {
        return new android.support.v7.app.AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
    }

    static private Dialog createDialog(final Context context, final DialogOptions options) {
        AlertDialog.Builder builder = getDialogBuilder(context);

        builder.setMessage(options.getMessageText(context));

        if (options.shouldShowTitle()) builder.setTitle(options.getTitleText(context));

        builder.setCancelable(options.getCancelable());

        View view = options.getView();
        if (view != null) builder.setView(view);

        final OnClickButtonListener listener = options.getListener();

        builder.setPositiveButton(options.getPositiveText(context), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intentToAppstore = null;

                if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_GOOGLE)
                    intentToAppstore = IntentHelper.createIntentForGooglePlay(context);
                else if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_ONE)
                    intentToAppstore = IntentHelper.createIntentForOneStore(context, options.getPackageId());
                else if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_NAVER)
                    intentToAppstore = IntentHelper.createIntentForNaverApps(context, options.getPackageId());

                context.startActivity(intentToAppstore);
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        });

        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setRemindIntervalDate(context);
                    if (listener != null) listener.onClickButton(which);
                }
            });
        }

        builder.setNegativeButton(options.getNegativeText(context), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        });

        return builder.create();
    }

    static private Dialog createMaterialDailog(final Context context, final DialogOptions options) {
        android.support.v7.app.AlertDialog.Builder builder = getMaterialDialogBuilder(context);

        builder.setMessage(options.getMessageText(context));

        if (options.shouldShowTitle()) builder.setTitle(options.getTitleText(context));

        builder.setCancelable(options.getCancelable());

        View view = options.getView();
        if (view != null) builder.setView(view);

        final OnClickButtonListener listener = options.getListener();

        builder.setPositiveButton(options.getPositiveText(context), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intentToAppstore = null;

                if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_GOOGLE)
                    intentToAppstore = IntentHelper.createIntentForGooglePlay(context);
                else if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_ONE)
                    intentToAppstore = IntentHelper.createIntentForOneStore(context, options.getPackageId());
                else if(options.getPackageType() == CommonInfo.PACKAGE_TITLE_NAVER)
                    intentToAppstore = IntentHelper.createIntentForNaverApps(context, options.getPackageId());

                context.startActivity(intentToAppstore);
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        });

        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setRemindIntervalDate(context);
                    if (listener != null) listener.onClickButton(which);
                }
            });
        }

        builder.setNegativeButton(options.getNegativeText(context), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) listener.onClickButton(which);
            }
        });

        return builder.create();
    }

    static Dialog create(final Context context, final DialogOptions options) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return createDialog(context, options);
        } else {
            return createMaterialDailog(context, options);
        }
    }

}
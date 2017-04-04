package com.erish.andialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

final class DialogManager {

    private static DialogManager singleton;

    private static Context context;

    static Dialog dialog = null;

    private DialogManager() {
    }

    private DialogManager(Context context) {
        this.context = context;
    }

    public static DialogManager with(Context context) {
        if (singleton == null) {
            synchronized (DialogManager.class) {
                if (singleton == null) {
                    singleton = new DialogManager(context);
                }
            }
        }
        return singleton;
    }

    public DialogManager create(final DialogOptions options) {
        if(dialog != null)
            return this;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            dialog = createDialog(context, options);
        } else {
            dialog = createMaterialDailog(context, options);
        }

        return this;
    }

    public void show() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            showDialog();
        } else {
            showMaterialDialog();
        }
    }

    static private void showDialog() {
        AlertDialog alertDialog = (AlertDialog) dialog;
        alertDialog.show();

        try{
            final Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout linearLayout = (LinearLayout) button.getParent();
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        } catch(Exception ex){
        }
    }

    static private void showMaterialDialog() {
        android.support.v7.app.AlertDialog alertDialog = (android.support.v7.app.AlertDialog) dialog;
        alertDialog.show();

        try{
            final Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout linearLayout = (LinearLayout) button.getParent();
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        } catch(Exception ex){
        }
    }

    static private AlertDialog.Builder getDialogBuilder(final Context context) {
        return new AlertDialog.Builder(context);
    }

    static private android.support.v7.app.AlertDialog.Builder getMaterialDialogBuilder(final Context context, final DialogOptions options) {
        return new android.support.v7.app.AlertDialog.Builder(context, options.getDialogStyleResId());
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
        android.support.v7.app.AlertDialog.Builder builder = getMaterialDialogBuilder(context, options);

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
}
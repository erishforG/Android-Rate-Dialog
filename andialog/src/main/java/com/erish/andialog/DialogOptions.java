package com.erish.andialog;

import android.content.Context;
import android.view.View;

final class DialogOptions {

    private boolean showNeutralButton = true;

    private boolean showTitle = true;

    private boolean cancelable = false;

    private String packageType = CommonInfo.PACKAGE_TITLE_GOOGLE;

    private String packageId = "";

    private int titleResId = R.string.rate_dialog_title;

    private int messageResId = R.string.rate_dialog_message;

    private int textPositiveResId = R.string.rate_dialog_positive;

    private int textNeutralResId = R.string.rate_diloag_neutral;

    private int textNegativeResId = R.string.rate_dialog_negative;

    private int dialogStyleResId = R.style.AppCompatAlertDialogStyle;

    private String titleText = null;

    private String messageText = null;

    private String positiveText = null;

    private String neutralText = null;

    private String negativeText = null;

    private View view;

    private OnClickButtonListener listener;

    public boolean shouldShowNeutralButton() {
        return showNeutralButton;
    }

    public void setShowNeutralButton(boolean showNeutralButton) {
        this.showNeutralButton = showNeutralButton;
    }

    public boolean shouldShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public boolean getCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    public int getMessageResId() {
        return messageResId;
    }

    public void setMessageResId(int messageResId) {
        this.messageResId = messageResId;
    }

    public int getTextPositiveResId() {
        return textPositiveResId;
    }

    public void setTextPositiveResId(int textPositiveResId) {
        this.textPositiveResId = textPositiveResId;
    }

    public int getTextNeutralResId() {
        return textNeutralResId;
    }

    public void setTextNeutralResId(int textNeutralResId) {
        this.textNeutralResId = textNeutralResId;
    }

    public int getTextNegativeResId() {
        return textNegativeResId;
    }

    public void setTextNegativeResId(int textNegativeResId) {
        this.textNegativeResId = textNegativeResId;
    }

    public int getDialogStyleResId() {
        return dialogStyleResId;
    }

    public void setDialogStyleResId(int dialogStyleResId) {
        this.dialogStyleResId = dialogStyleResId;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public OnClickButtonListener getListener() {
        return listener;
    }

    public void setListener(OnClickButtonListener listener) {
        this.listener = listener;
    }

    public String getTitleText(Context context) {
        if (titleText == null) {
            return context.getString(titleResId);
        }
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getMessageText(Context context) {
        if (messageText == null) {
            return context.getString(messageResId);
        }
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getPositiveText(Context context) {
        if (positiveText == null) {
            return context.getString(textPositiveResId);
        }
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNeutralText(Context context) {
        if (neutralText == null) {
            return context.getString(textNeutralResId);
        }
        return neutralText;
    }

    public void setNeutralText(String neutralText) {
        this.neutralText = neutralText;
    }

    public String getNegativeText(Context context) {
        if (negativeText == null) {
            return context.getString(textNegativeResId);
        }
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }
}
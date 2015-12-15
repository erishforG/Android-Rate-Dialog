package com.erish.andialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.Date;

/**
 * Created by EricShin on 15. 12. 9..
 */
public class Andialog {

    private static Andialog singleton;

    private Context context;

    private final DialogOptions options = new DialogOptions();

    private int installDate = -1;

    private int launchTimes = -1;

    private int remindInterval = 1;

    private int eventsTimes = -1;

    private boolean isDebug = false;

    public Andialog(Context context) {
        this.context = context;
    }

    public static Andialog with(Context context) {
        if (singleton == null) {
            synchronized (Andialog.class) {
                if (singleton == null) {
                    singleton = new Andialog(context);
                }
            }
        }
        return singleton;
    }

    public void launch() {
        if(PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }

        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
    }

    public static boolean showRateDialogIfMeetsConditions(Activity activity) {
        boolean isMeetsConditions = singleton.isDebug || singleton.shouldShowRateDialog();
        if (isMeetsConditions) {
            singleton.showRateDialog(activity);
        }
        return isMeetsConditions;
    }

    public static boolean passSignificantEventAndConditions(Activity activity) {
        return passSignificantEvent(activity, singleton.shouldShowRateDialog());
    }

    private static boolean passSignificantEvent(Activity activity, boolean shouldShow) {
        int eventTimes = PreferenceHelper.getEventTimes(activity);
        PreferenceHelper.setEventTimes(activity, ++eventTimes);
        boolean isMeetsConditions = singleton.isDebug || (singleton.isOverEventTimes() && shouldShow);
        if (isMeetsConditions) {
            singleton.showRateDialog(activity);
        }
        return isMeetsConditions;
    }

    public Andialog clearPreferenceSettings() {
        PreferenceHelper.setAgreeShowDialog(context, true);
        PreferenceHelper.clearSharedPreferences(context);
        return this;
    }

    private static boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= threshold * 24 * 60 * 60 * 1000;
    }

    public boolean shouldShowRateDialog() {
        return PreferenceHelper.getIsAgreeShowDialog(context) &&
                isOverLaunchTimes() &&
                isOverInstallDate() &&
                isOverIntervalDate();
    }

    public boolean isOverInstallDate() {
        if(installDate == -1)
            return true;

        return isOverDate(PreferenceHelper.getInstallDate(context), installDate);
    }

    public boolean isOverIntervalDate() {
        return isOverDate(PreferenceHelper.getRemindIntervalDate(context), remindInterval);
    }

    public boolean isOverLaunchTimes() {
        if(launchTimes == -1)
            return true;

        return PreferenceHelper.getLaunchTimes(context) >= launchTimes? true : false;
    }

    public boolean isOverEventTimes() {
        if(eventsTimes == -1)
            return true;

        return PreferenceHelper.getEventTimes(context) >= launchTimes? true : false;
    }

    public Andialog setInstallDate(int installDate) {
        this.installDate = installDate;
        return this;
    }

    public Andialog setLaunchTimes(int launchTimes) {
        this.launchTimes = launchTimes;
        return this;
    }

    public Andialog setRemindInterval(int remindInterval) {
        this.remindInterval = remindInterval;
        return this;
    }

    public Andialog setEventsTimes(int eventsTimes) {
        this.eventsTimes = eventsTimes;
        return this;
    }

    public Andialog setShowNeutralButton(boolean isShowNeutralButton) {
        options.setShowNeutralButton(isShowNeutralButton);
        return this;
    }

    public Andialog setShowTitle(boolean isShowTitle) {
        options.setShowTitle(isShowTitle);
        return this;
    }

    public Andialog setView(View view) {
        options.setView(view);
        return this;
    }

    public Andialog setOnClickButtonListener(OnClickButtonListener listener) {
        options.setListener(listener);
        return this;
    }

    public Andialog setTitle(int resourceId) {
        options.setTitleResId(resourceId);
        return this;
    }

    public Andialog setTitle(String title) {
        options.setTitleText(title);
        return this;
    }

    public Andialog setMessage(int resourceId) {
        options.setMessageResId(resourceId);
        return this;
    }

    public Andialog setMessage(String message) {
        options.setMessageText(message);
        return this;
    }

    public Andialog setTextPositive(int resourceId) {
        options.setTextPositiveResId(resourceId);
        return this;
    }

    public Andialog setTextPositive(String positiveText) {
        options.setPositiveText(positiveText);
        return this;
    }

    public Andialog setTextNeutral(int resourceId) {
        options.setTextNeutralResId(resourceId);
        return this;
    }

    public Andialog setTextNeutral(String neutralText) {
        options.setNeutralText(neutralText);
        return this;
    }

    public Andialog setTextNegative(int resourceId) {
        options.setTextNegativeResId(resourceId);
        return this;
    }

    public Andialog setTextNegative(String negativeText) {
        options.setNegativeText(negativeText);
        return this;
    }

    public Andialog setCancelable(boolean cancelable) {
        options.setCancelable(cancelable);
        return this;
    }

    public Andialog setPackageType(String packageType) {
        options.setPackageType(packageType);
        return this;
    }

    public Andialog setPackageId(String packageId) {
        options.setPackageId(packageId);
        return this;
    }

    public Andialog setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    public void showRateDialog(Activity activity) {
        if (!activity.isFinishing()) {
            DialogManager.create(activity, options).show();
        }
    }
}

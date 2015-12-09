package com.erish.andialog;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

final class PreferenceHelper {

    private static final String PREF_FILE_NAME = "andialog_rate_pref_file";
    private static final String PREF_KEY_INSTALL_DATE = "andialog_rate_install_date";
    private static final String PREF_KEY_LAUNCH_TIMES = "andialog_rate_launch_times";
    private static final String PREF_KEY_IS_AGREE_SHOW_DIALOG = "andialog_rate_is_agree_show_dialog";
    private static final String PREF_KEY_REMIND_INTERVAL = "andialog_rate_remind_interval";
    private static final String PREF_KEY_EVENT_TIMES = "andialog_rate_event_times";

    private PreferenceHelper() {
    }

    static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    static SharedPreferences.Editor getPreferencesEditor(Context context) {
        return getPreferences(context).edit();
    }

    /**
     * Clear data in shared preferences
     *
     * @param context context
     */
    static void clearSharedPreferences(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.remove(PREF_KEY_INSTALL_DATE);
        editor.remove(PREF_KEY_LAUNCH_TIMES);
        editor.apply();
    }

    /**
     * Set agree flag about show dialog.
     * If it is false, rate dialog will never shown unless data is cleared.
     *
     * @param context context
     * @param isAgree agree with showing rate dialog
     */
    static void setAgreeShowDialog(Context context, boolean isAgree) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(PREF_KEY_IS_AGREE_SHOW_DIALOG, isAgree);
        editor.apply();
    }

    /**
     * get agree flag about show dialog.
     * If it is false, rate dialog will never shown unless data is cleared.
     *
     * @param context context
     */
    static boolean getIsAgreeShowDialog(Context context) {
        return getPreferences(context).getBoolean(PREF_KEY_IS_AGREE_SHOW_DIALOG, true);
    }

    /**
     * set remind interval date.
     * If remind interval date is over than setting days, rate dialog will appear.
     *
     * @param context context
     */
    static void setRemindIntervalDate(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.remove(PREF_KEY_REMIND_INTERVAL);
        editor.putLong(PREF_KEY_REMIND_INTERVAL, new Date().getTime());
        editor.apply();
    }

    /**
     * get remind interval date.
     * If remind interval date is over than setting days, rate dialog will appear.
     *
     * @param context context
     */
    static long getRemindIntervalDate(Context context) {
        return getPreferences(context).getLong(PREF_KEY_REMIND_INTERVAL, 0);
    }

    /**
     * set install date.
     * If install date is over than setting days, rate dialog will appear.
     *
     * @param context context
     */
    static void setInstallDate(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putLong(PREF_KEY_INSTALL_DATE, new Date().getTime());
        editor.apply();
    }

    /**
     * get install date.
     * If install date is over than setting days, rate dialog will appear.
     *
     * @param context context
     */
    static long getInstallDate(Context context) {
        return getPreferences(context).getLong(PREF_KEY_INSTALL_DATE, 0);
    }

    /**
     * set luanch times date.
     * If launch time is over than setting times, rate dialog will appear.
     *
     * @param context context
     * @param launchTimes launchTimes
     */
    static void setLaunchTimes(Context context, int launchTimes) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putInt(PREF_KEY_LAUNCH_TIMES, launchTimes);
        editor.apply();
    }

    /**
     * get luanch times date.
     * If launch time is over than setting times, rate dialog will appear.
     *
     * @param context context
     */
    static int getLaunchTimes(Context context) {
        return getPreferences(context).getInt(PREF_KEY_LAUNCH_TIMES, 0);
    }

    /**
     * check if first launch or not.
     * If launching first time, andialog will set luanch date.
     *
     * @param context context
     */
    static boolean isFirstLaunch(Context context) {
        return getPreferences(context).getLong(PREF_KEY_INSTALL_DATE, 0) == 0L;
    }

    /**
     * set event times date.
     * If event time is over than setting times, rate dialog will appear.
     *
     * @param context context
     */
    static int getEventTimes(Context context) {
        return getPreferences(context).getInt(PREF_KEY_EVENT_TIMES, 0);
    }

    /**
     * set event times date.
     * If event time is over than setting times, rate dialog will appear.
     *
     * @param context context
     */
    static void setEventTimes(Context context, int eventTimes) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putInt(PREF_KEY_EVENT_TIMES, eventTimes);
        editor.apply();
    }

}
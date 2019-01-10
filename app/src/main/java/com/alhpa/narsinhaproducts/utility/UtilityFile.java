package com.alhpa.narsinhaproducts.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class UtilityFile {

    private static final String TAG = UtilityFile.class.getSimpleName();

    private Context context;

    public UtilityFile(Context context) {

    }

    //Works only with activities and fragments and not with services
    public Activity getActivity(Context context) {
        if (context == null) {
            return null;
        } else if (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            } else {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }

    public String getStringFromLongTimestamp(long lTimestamp, String sFormat) {

        Date date = new Date(lTimestamp * 1000);
        DateFormat df = new SimpleDateFormat(sFormat);
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        return df.format(date);

    }

    public boolean isPhoneNumberValid(String sPhoneNumber) {

        return sPhoneNumber != null && !sPhoneNumber.isEmpty() && sPhoneNumber.length() == 10;

    }

    public boolean isPasswordValid(String sPassword) {

        return sPassword.length() >= 6;

    }

    public boolean isEmailValid(String sEmail) {

        return !sEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(sEmail).matches();

    }

    public boolean isValidOTP(String sOTP) {

        return sOTP.length() >= 4;

    }

    public boolean doesStringExist(String string) {

        return string != null && !string.isEmpty();

    }

    public long convertToDateTimestamp(long lTimestamp) {

        lTimestamp = lTimestamp + 19800;
        long lNumberOfDays = lTimestamp / (24 * 60 * 60);
        lTimestamp = lNumberOfDays * (24 * 60 * 60);
        return lTimestamp;

    }

    public long combineDateAndTime(long lMessageDate, long lMessageTime) {

        long ONE_DAY_VALUE = 24 * 60 * 60;
        long lPerfectDateTimestamp = convertToDateTimestamp(lMessageDate);
        long lPerfectTimeTimestamp = lMessageTime % ONE_DAY_VALUE;
        return (lPerfectDateTimestamp + lPerfectTimeTimestamp);

    }

    public long getCurrentTimestamp() {

        return (new Date().getTime() / 1000) + 19800;

    }

    public long getTodayDateTimestamp() {

        return convertToDateTimestamp(getCurrentTimestamp());

    }

    //https://stackoverflow.com/questions/13627308/add-st-nd-rd-and-th-ordinal-suffix-to-a-number
    public String dayDateWithSuffix(int iDate) {

        int j = iDate % 10;
        int k = iDate % 100;

        if (j == 1 && k != 11) {
            return "st";
        }
        if (j == 2 && k != 12) {
            return "nd";
        }
        if (j == 3 && k != 13) {
            return "rd";
        }

        return "th";

    }

}


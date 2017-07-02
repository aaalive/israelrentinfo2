// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.DatePicker;
import java.util.Date;

public class Helper
{

    public Helper()
    {
    }

    public static String getDateDiffString(Date date, Date date1)
    {
        long l = date.getTime();
        return (new StringBuilder(String.valueOf((date1.getTime() - l) / 0x5265c00L))).toString();
    }
    public static int getDateDiff(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        return diffInDays;
    }

    public static String getDateFromDatePicket(DatePicker datepicker)
    {
        int i = datepicker.getDayOfMonth();
        int j = 1 + datepicker.getMonth();
        int k = datepicker.getYear();
        return (new StringBuilder(String.valueOf(i))).append("/").append(j).append("/").append(k).toString();
    }

    public static int getScreenWidthResolution(Context context)
    {
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        int i = displaymetrics.widthPixels;
        int _tmp = displaymetrics.heightPixels;
        return i;
    }
}

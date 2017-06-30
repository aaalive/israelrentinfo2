// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.timessquare.CalendarPickerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import info.samson.R;
import info.samson.helpers.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_RANGE;

public class MailFrag extends Fragment {

    private NumberPicker mAdults;
    private DatePicker mCheckIn;
    private DatePicker mCheckOut;
    private EditText mEmailBody;
    private NumberPicker mKids;
    private int mPrevYear;
    private int mPrevMonth;
    private int mPrevDay;

    public MailFrag() {
        mEmailBody = null;
    }

    public TextView createViews() {
        TextView textview = new TextView(getActivity());
        textview.setText("bababbabbababab");
        return textview;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        if (viewgroup == null) {
            return null;
        } else {
            return layoutinflater.inflate(R.layout.mail, viewgroup, false);
        }
    }

    public void onStart() {
        super.onStart();
        initCalendar();
        mEmailBody = (EditText) getView().findViewById(R.id.emailBody);
        mCheckIn = (DatePicker) getView().findViewById(R.id.checkIn);

        mCheckOut = (DatePicker) getView().findViewById(R.id.checkOut);
        mAdults = (NumberPicker) getView().findViewById(R.id.adults);
        mKids = (NumberPicker) getView().findViewById(R.id.kids);
        String as[] = new String[11];
        int i = 0;
        do {
            if (i >= as.length) {
                mAdults.setMinValue(0);
                mAdults.setMaxValue(10);
                mAdults.setWrapSelectorWheel(true);
                mAdults.setDisplayedValues(as);
                mAdults.setValue(1);
                mKids.setMinValue(0);
                mKids.setMaxValue(10);
                mKids.setWrapSelectorWheel(true);
                mKids.setDisplayedValues(as);
                mKids.setValue(0);
                ((Button) getActivity().findViewById(R.id.button1)).setOnClickListener(new android.view.View.OnClickListener() {
                    public void onClick(View v) {
                        final MailFrag this$0;
                        Date date = new Date(-1900 + mCheckOut.getYear(), mCheckOut.getMonth(), mCheckOut.getDayOfMonth());
                        String s = (new StringBuilder(String.valueOf(Helper.getDateDiffString(new Date(-1900 + mCheckIn.getYear(), mCheckIn.getMonth(), mCheckIn.getDayOfMonth()), date)))).append(" \u043D\u043E\u0447\u0435\u0439: ").append(Helper.getDateFromDatePicket(mCheckIn)).append("-").append(Helper.getDateFromDatePicket(mCheckOut)).append(";").append(mAdults.getValue()).append("+").append(mKids.getValue()).toString();
                        String s1 = mEmailBody.getText().toString();
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.putExtra("android.intent.extra.EMAIL", new String[]{
                                "israelrent@israelrent.info"
                        });
                        intent.putExtra("android.intent.extra.SUBJECT", s);
                        intent.putExtra("android.intent.extra.TEXT", s1);
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent, "Choose an Email client"));

                        this$0 = MailFrag.this;
                        //      super();
                    }
                });
                return;
            }
            as[i] = Integer.toString(i);
            i++;
        } while (true);
    }

    private void initCalendar() {

        final CalendarView simpleCalendarView = (CalendarView) getView().findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(mPrevYear!=year& mPrevMonth!=month& mPrevDay!=dayOfMonth) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" );
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                    Date date = null;
                    try {
                        date = sdf.parse(year+"-"+month+"-"+(dayOfMonth-3)+" 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in milliseconds: " + date.getDate());
                }

                mPrevYear=year;
                mPrevMonth = month;
                mPrevDay = dayOfMonth;
            }
        });
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

        @MaterialCalendarView.SelectionMode
        MaterialCalendarView mcv = (MaterialCalendarView) getView().findViewById(R.id.calendarView);
                mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .commit();
        mcv.setSelectionMode(SELECTION_MODE_RANGE);
        mcv.setOnRangeSelectedListener(new OnRangeSelectedListener() {
            @Override
            public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
                for(CalendarDay date:dates) {
                    widget.setDateSelected(date,true);
                }
        } });



    }

}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc

package info.samson.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
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
    private CalendarDay mStartDay;
    private CalendarDay mEndDay;
    private int mAdultsNum;
    private int mInfantsNum;
    private int mKidsNum;

    public MailFrag() {
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
        initNumOfPeople();
        initShareBnt();
//        disableFloatingBtn();
    }

    private void initShareBnt() {
        ((Button) getActivity().findViewById(R.id.shareBtn)).setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                String s= ((TextView) getView().findViewById(R.id.checkInDay)).getText() + " -  "+ ((TextView) getView().findViewById(R.id.checkOutDay)).getText() +
                          "\n Days:   " + ((TextView) getView().findViewById(R.id.numOfDays)).getText()+
                          "\n Adults: " +  ((TextView) getView().findViewById(R.id.adults_num)).getText()+
                          "\n Kids:   " + ((TextView) getView().findViewById(R.id.kids_num)).getText() +
                          "\n Babies: " + ((TextView) getView().findViewById(R.id.infants_num)).getText();
                String s1 = s+ "\n Msg: " + ((EditText) getView().findViewById(R.id.emailBody)).getText().toString();
                Intent intent = new Intent("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{
                        "israelrent@israelrent.info"
                });
                intent.putExtra("android.intent.extra.SUBJECT", s);
                intent.putExtra("android.intent.extra.TEXT", s1);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client"));
            }
        });
    }

    private void initNumOfPeople() {
        ((EditText) getView().findViewById(R.id.emailBody)).getBackground().clearColorFilter();

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

        TextView substract = (TextView) getView().findViewById(R.id.adults_decrease);
        substract.setTypeface(font);
        substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdultsNum > 0) {
                    ((TextView) getView().findViewById(R.id.adults_num)).setText((--mAdultsNum) + "");
                }
            }
        });
        TextView add = (TextView) getView().findViewById(R.id.adults_increase);
        add.setTypeface(font);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) getView().findViewById(R.id.adults_num)).setText((++mAdultsNum) + "");
            }
        });

        substract = (TextView) getView().findViewById(R.id.kids_decrease);
        substract.setTypeface(font);
        substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKidsNum > 0) {
                    ((TextView) getView().findViewById(R.id.kids_num)).setText((--mKidsNum) + "");
                }
            }
        });
        add = (TextView) getView().findViewById(R.id.kids_increase);
        add.setTypeface(font);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) getView().findViewById(R.id.kids_num)).setText((++mKidsNum) + "");
            }
        });

        substract = (TextView) getView().findViewById(R.id.infants_decrease);
        substract.setTypeface(font);
        substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInfantsNum > 0) {
                    ((TextView) getView().findViewById(R.id.infants_num)).setText((--mInfantsNum) + "");
                }
            }
        });
        add = (TextView) getView().findViewById(R.id.infants_increase);
        add.setTypeface(font);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) getView().findViewById(R.id.infants_num)).setText((++mInfantsNum) + "");
            }
        });

    }

    private void initCalendar() {
        @MaterialCalendarView.SelectionMode
        final MaterialCalendarView mcv = (MaterialCalendarView) getView().findViewById(R.id.calendarView);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .commit();
        mcv.setSelectionMode(SELECTION_MODE_RANGE);
        mcv.setDynamicHeightEnabled(true);
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mcv.setSelectedDate(date);
                ((TextView) getView().findViewById(R.id.checkInDay)).setText(getDate(date));
                ((TextView) getView().findViewById(R.id.checkOutDay)).setText(getDate(date));
                ((TextView) getView().findViewById(R.id.numOfDays)).setText("");

            }
        });
        mcv.setOnRangeSelectedListener(new OnRangeSelectedListener() {
            @Override
            public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
                int numOfDays = dates.size();
                mStartDay = dates.get(0);
                mEndDay = dates.get(numOfDays - 1);

                if (numOfDays > 1) {
                    //TODO show end and start
                    ((TextView) getView().findViewById(R.id.checkInDay)).setText(getDate(mStartDay));
                    ((TextView) getView().findViewById(R.id.checkOutDay)).setText(getDate(mEndDay));

                }
                for (CalendarDay date : dates) {
                    widget.setDateSelected(date, true);
                }
                if (mEndDay != null || mStartDay != null) {
                    ((TextView) getView().findViewById(R.id.numOfDays)).setTextColor(Color.BLACK);
                    Helper.getDateDiff(mStartDay.getDate(), mEndDay.getDate());
                    ((TextView) getView().findViewById(R.id.numOfDays)).setText("" + Helper.getDateDiff(mStartDay.getDate(), mEndDay.getDate()));
                }
            }
        });


    }

    private String getDate(CalendarDay date) {
        return date.getDay() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
    }
}

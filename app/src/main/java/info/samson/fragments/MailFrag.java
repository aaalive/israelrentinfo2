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

    private NumberPicker mAdults;
    private DatePicker mCheckIn;
    private DatePicker mCheckOut;
    private EditText mEmailBody;
    private NumberPicker mKids;
    private int mPrevYear;
    private int mPrevMonth;
    private int mPrevDay;
    private CalendarDay mStartDay;
    private CalendarDay mEndDay;
    private int mAdultsNum;

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
        initNumOfPeople();
        mEmailBody = (EditText) getView().findViewById(R.id.emailBody);

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

    private void initNumOfPeople() {
        Typeface font = Typeface.createFromAsset( getActivity().getAssets(), "fontawesome-webfont.ttf" );

        TextView substract = (TextView)getView().findViewById( R.id.adults_decrease);
        substract.setTypeface(font);
        substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)getView().findViewById( R.id.adults_num)).setText((--mAdultsNum)+"");
            }
        });
        TextView add = (TextView)getView().findViewById( R.id.adults_increase);
        add.setTypeface(font);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)getView().findViewById( R.id.adults_num)).setText((++mAdultsNum)+"");
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

                final MailFrag this$0;
                if (mEndDay != null || mStartDay != null) {
                    ((TextView) getView().findViewById(R.id.numOfDays)).setTextColor(Color.BLACK);
                    Helper.getDateDiff(mStartDay.getDate(), mEndDay.getDate());
                    ((TextView) getView().findViewById(R.id.numOfDays)).setText(""+Helper.getDateDiff(mStartDay.getDate(), mEndDay.getDate()));
                }
            }
        });



    }

    private String getDate(CalendarDay date) {
        return date.getDay() + "/" + (date.getMonth()+1) + "/" + date.getYear();
    }
}

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package info.samson.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import info.samson.R;
import info.samson.helpers.Helper;

import java.util.Calendar;
import java.util.Date;

public class MailFrag extends Fragment {

    private NumberPicker mAdults;
    private DatePicker mCheckIn;
    private DatePicker mCheckOut;
    private EditText mEmailBody;
    private NumberPicker mKids;

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

                    final MailFrag this$0;

                    public void onClick(View view) {
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
                    }


                    {
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
        final CalendarPickerView calendar_view = (CalendarPickerView) getView().findViewById(R.id.calendar_view);
//getting current
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();

//add one year to calendar from todays date
        calendar_view.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE);


        //action while clicking on a date
        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                Toast.makeText(getActivity(), "Selected Date is : " + date.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDateUnselected(Date date) {

                Toast.makeText(getActivity(), "UnSelected Date is : " + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}

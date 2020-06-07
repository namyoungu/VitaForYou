package com.example.vita_1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CalenderFragment extends Fragment {

    CalendarView calendar_view;
    MainActivity mainActivity;


    //해당 연도및 월 설정 생성자
    public CalenderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_calendar, container, false);


        calendar_view = view.findViewById(R.id.calendar_view);
        calendar_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                        Log.e("change",year+" "+month+" "+day);
                mainActivity = (MainActivity)getActivity();
                mainActivity.setDate(String.format("%04d-%02d-%02d",year,month+1,day));
                mainActivity.setFrag(39);
            }
        });

        // 툴바 셋팅
        mainActivity = (MainActivity)getActivity();
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);

        //날짜 셋팅
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);

        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        //toolbar.setTitle(year+"년 "+month+"월");
        //toolbar.setTitleMarginStart(300);


        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);



        return view;
    }


}
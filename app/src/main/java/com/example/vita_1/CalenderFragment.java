package com.example.vita_1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class CalenderFragment extends Fragment {

    CalendarView calendar_view;

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
                ((MainActivity)getActivity()).setDate(String.format("%04d-%02d-%02d",year,month+1,day));
                ((MainActivity)getActivity()).setFrag(39);
            }
        });



        return view;
    }


}
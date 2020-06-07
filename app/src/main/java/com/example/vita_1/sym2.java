package com.example.vita_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class sym2 extends Fragment {

    private CheckBox fatigue, stress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_sym2, container, false);


        return view;
    }

    // +이미지 클릭시 체크박스 값 가져옴
    public void clickMethod(View doctorView){
        fatigue = (CheckBox)doctorView.findViewById( R.id.fatigue);
        stress = (CheckBox)doctorView.findViewById(R.id.stress);
    }

}

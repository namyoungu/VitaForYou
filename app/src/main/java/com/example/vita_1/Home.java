package com.example.vita_1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Home extends Fragment {

    ImageView profile_image;
    ImageView select_interest_image;
    ImageView my_diet_view;
    ImageView board_image;
    ImageView doctor_image;
    ImageView setting_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // 띄울 뷰 받아오기
        View HomeView =  inflater.inflate(R.layout.fragment_home, container, false);

        // 내 정보 이미지 받아오기
        profile_image = (ImageView) HomeView.findViewById(R.id.my_profile_view);

        // 관심분야나 증상 선택(제품 추천 ) 이미지 받아오기
        select_interest_image = (ImageView) HomeView.findViewById(R.id.select_interest_view);

        //내 식단
        my_diet_view = HomeView.findViewById(R.id.my_diet_view);

        // 거래 이미지 받아오기
        board_image = (ImageView) HomeView.findViewById(R.id.my_deal_view);

        doctor_image = (ImageView) HomeView.findViewById(R.id.doctor);

        setting_image = (ImageView) HomeView.findViewById(R.id.setting_view);


        // 내 정보 클릭시
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(1);
            }
        });

        // 내 식단 클릭시
        my_diet_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setFrag(2);
            }
        });

        //거래 클릭시
        board_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(3);
            }
        });

        // 제품 추천 클릭시
        select_interest_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(4);
            }
        });

        //문진 클릭시
        doctor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(5);
            }
        });

        setting_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(6);
            }
        });





        return HomeView;

    }

}

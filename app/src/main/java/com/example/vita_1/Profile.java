package com.example.vita_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class Profile extends Fragment {

    TextView nameText;
    TextView emailText;
    TextView genderText;
    TextView phoneText;
    Button modiProfileButton;
    String userID;
    String userName;
    String userEMail;
    String userGender;
    String userPhone;
    MainActivity mainActivity;
    LinearLayout myDietLayout;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            userID = bundle.getString("userID");
            userName = bundle.getString("userName");
            userEMail = bundle.getString("userEMail");
            userGender = bundle.getString("userGender");
            userPhone = bundle.getString("userPhone");

            if(userGender.equals("1")){
                userGender = "여성";
            }else{
                userGender = "남성";
            }
        }else {
            System.out.println("budle 안들고옴");
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);

        // 툴바 셋팅
        mainActivity = (MainActivity)getActivity();
        Toolbar toolbar = (Toolbar)profileView.findViewById(R.id.toolbar);


        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginStart(340);


        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = (TextView) profileView.findViewById(R.id.name);
        emailText = (TextView) profileView.findViewById(R.id.email);
        genderText = (TextView) profileView.findViewById(R.id.gender);
        phoneText = (TextView) profileView.findViewById(R.id.phone_num);

        modiProfileButton = (Button) profileView.findViewById(R.id.modi_profile);


        modiProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserModiActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("userName", userName);
                intent.putExtra("userEMail", userEMail);
                intent.putExtra("userGender", userGender);
                intent.putExtra("userPhone", userPhone);
                startActivity(intent);
            }
        }
        );
        nameText.setText(userName);
        emailText.setText(userEMail);
        genderText.setText(userGender);
        phoneText.setText(userPhone);

        myDietLayout=(LinearLayout)profileView.findViewById(R.id.my_diet);
        myDietLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity)getActivity();
                mainActivity.setFrag(2);
            }
        });



        return profileView;
    }
}

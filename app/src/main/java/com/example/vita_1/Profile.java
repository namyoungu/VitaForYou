package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Profile extends Fragment {

    TextView nameText;
    TextView emailText;
    TextView genderText;
    String userName;
    String userEMail;
    String userGender;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            userName = bundle.getString("userName");
            userEMail = bundle.getString("userEMail");
            userGender = bundle.getString("userGender");
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

        nameText = (TextView) profileView.findViewById(R.id.name);
        emailText = (TextView) profileView.findViewById(R.id.email);
        genderText = (TextView) profileView.findViewById(R.id.gender);

        nameText.setText(userName);
        emailText.setText(userEMail);
        genderText.setText(userGender);

        return profileView;
    }
}

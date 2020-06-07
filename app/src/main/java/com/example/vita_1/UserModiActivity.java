package com.example.vita_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModiActivity extends AppCompatActivity {

    private Button modifyBtn,btn_cancel;
    private EditText et_pass, et_name, et_email,et_phone;
    private RadioGroup rag_gender;


    private Intent intent;

    private String userID;
    private String userName;
    private String userEMail;
    private String  userGender;
    private String userPhone;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modi);

        intent = getIntent();
        userID = intent.getStringExtra("userID");
        userName = intent.getStringExtra("userName");
        userEMail = intent.getStringExtra("userEMail");
        userGender = intent.getStringExtra("userGender");
        userPhone = intent.getStringExtra("userPhone");

        if(userGender == "여성"){
            userGender = "1";
        }
        else{
            userGender = "0";
        }

        System.out.println(userName);
        System.out.println(userEMail);
        System.out.println(userGender);
        System.out.println(userPhone);

        //아이디값 찾아주기
        et_pass = findViewById( R.id.et_pass );
        et_name = findViewById( R.id.et_name );
        et_email = findViewById( R.id.et_email );
        et_phone = findViewById(R.id.et_phone);
        rag_gender = findViewById(R.id.rag_gender);

        et_pass.setText(userID);
        et_name.setText(userName);
        et_email.setText(userEMail);
        et_phone.setText(userPhone);

        System.out.println(userName);
        System.out.println(userEMail);
        System.out.println(userGender);
        System.out.println(userPhone);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);


        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginStart(340);


       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        rag_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.ra_female){
                    //여자 눌렀을때
                    userGender = "1";
                }
                if(checkedId == R.id.ra_male){
                    //남자 눌렀을때
                    userGender = "0";
                }
            }
        });

        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        //취소버튼 클릭시 초기화
        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                et_pass.setText(null);
                et_name.setText(null);
                et_email.setText(null);
                et_phone.setText(null);
            }
        });


        modifyBtn = (Button)findViewById(R.id.btn_modify);
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userPW = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userEMail = et_email.getText().toString();
                String userPhone = et_phone.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            //정보수정 성공시
                            if (success) {

                                Toast.makeText(getApplicationContext(), "수정 성공! 재로그인 해주세요.", Toast.LENGTH_SHORT).show();

                                //정보수정 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "수정 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                //서버로 Volley를 이용해서 요청
                UserModiRequest userModiRequest = new UserModiRequest(userID,userPW, userName, userGender, userPhone, userEMail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserModiActivity.this);
                queue.add(userModiRequest);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }





}

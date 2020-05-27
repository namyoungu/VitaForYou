package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_email,et_phone;
    private RadioGroup rag_gender;
    private Button btn_register,btn_cancel,btn_overlap;
    private Boolean validate = false;
    private AlertDialog dialog;
    private String  userGender="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_register);

        //아이디값 찾아주기
        et_id = findViewById( R.id.et_id );
        et_pass = findViewById( R.id.et_pass );
        et_name = findViewById( R.id.et_name );
        et_email = findViewById( R.id.et_email );
        et_phone = findViewById(R.id.et_phone);
        rag_gender = findViewById(R.id.rag_gender);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_overlap = findViewById(R.id.btn_overlap);

        //취소버튼 클릭시 초기화
        btn_cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                et_id.setText(null);
                et_pass.setText(null);
                et_name.setText(null);
                et_email.setText(null);
                et_phone.setText(null);
            }
        });

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





        //회원가입 버튼 클릭 시 수행
        btn_register = findViewById( R.id.btn_register);
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = et_id.getText().toString();
                String userPW = et_pass.getText().toString();
                String userName = et_name.getText().toString();
                String userEMail = et_email.getText().toString();
                String userPhone = et_phone.getText().toString();


                // 널 체크
                if(userID==null||userID.equals("")||userPW==null||userPW.equals("")||userName==null||userName.equals("")||userEMail==null||userEMail.equals("")||userPhone==null||userPhone.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("모두 기입해주세요 :)").setPositiveButton("확인", null).create();
                    dialog.show();
                }else{

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");

                                    //회원가입 성공시
                                    if (success) {

                                        Toast.makeText(getApplicationContext(), "가입 성공", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                        //회원가입 실패시
                                    } else {
                                        Toast.makeText(getApplicationContext(), "가입 실패", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };


                        //서버로 Volley를 이용해서 요청
                        RegisterRequest registerRequest = new RegisterRequest(userID, userPW, userName, userGender, userPhone, userEMail, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registerRequest);
                    }
            }
        });

        //중복체크
        btn_overlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_id.getText().toString();
                if(validate)
                {
                    return;
                }
                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다 :)").setPositiveButton("확인", null).create();
                                dialog.show();
                                et_id.setEnabled(false);
                                validate=true;
                                btn_overlap.setText("확인");
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다 :(").setNegativeButton("확인", null).create();
                                dialog.show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);

            }
        });


    }
}

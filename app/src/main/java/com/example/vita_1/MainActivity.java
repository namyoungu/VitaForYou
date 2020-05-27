package com.example.vita_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Home homeFrag;
    private Profile profileFrag;
    private Select_Interst selectInterstFrag;
    private SplList splListFrag;
    private ItemDetail itemDetailFrag ;
    public Bundle bundle1 =  new Bundle();
    public Bundle splListbundle =  new Bundle();
    public Bundle itemDetailBundle = new Bundle();
    public String userID;
    public String userName;
    public String userGender;
    public String userEMail;
    public String splpCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userGender = intent.getStringExtra("userGender");
        userName = intent.getStringExtra("userName");
        userEMail = intent.getStringExtra("userEMail");


        homeFrag = new Home();
        profileFrag = new Profile();
        selectInterstFrag = new Select_Interst();
        splListFrag = new SplList();
        itemDetailFrag = new ItemDetail();

        setFrag(0);


        // 하단 메뉴 선택시
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.bottom_home:
                        setFrag(0);
                        break;
                    case R.id.bottom_profile:
                        setFrag(1);
                        break;
                }
                return true;
            }
        });


        
    }



    // 프레그먼트 교체

    public void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();

        switch (n)
        {
            // 하단의 Home 클릭시
            case 0:
                ft.replace(R.id.Main_Frame,homeFrag);
                ft.commit();
                break;

            // 하단의 내 정보나 Home의 내 정보 클릭시
            case 1:
                bundle1.putString("userName",userName);
                bundle1.putString("userGender",userGender);
                bundle1.putString("userEMail",userEMail);

                profileFrag.setArguments(bundle1);
                ft.replace(R.id.Main_Frame,profileFrag);
                ft.commit();
                break;

            // 제품 추천 클릭시
            case 4:
                ft.replace(R.id.Main_Frame,selectInterstFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

                // 제품 상세 화면
            case 10:
                itemDetailBundle.putString("splpCode",splpCode);
                itemDetailFrag.setArguments(itemDetailBundle);
                ft.replace(R.id.Main_Frame,itemDetailFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 11:
                splListbundle.putString("title","노화 방지 / 장수");
                splListbundle.putString("param","11");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 12:
                splListbundle.putString("title","순환기계");
                splListbundle.putString("param","12");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 13:
                splListbundle.putString("title","면역력 강화");
                splListbundle.putString("param","13");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;


            case 14:
                splListbundle.putString("title","두뇌 / 인지력");
                splListbundle.putString("param","14");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;


            case 15:
                splListbundle.putString("title","소화 기계");
                splListbundle.putString("param","15");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;


            case 16:
                splListbundle.putString("title","뼈,관절 / 연골");
                splListbundle.putString("param","16");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 17:
                splListbundle.putString("title","감기 / 독감");
                splListbundle.putString("param","17");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 18:
                splListbundle.putString("title","다이어트");
                splListbundle.putString("param","18");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;


            case 21:
                splListbundle.putString("title","유아 / 어린이");
                splListbundle.putString("param","21");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 22:
                splListbundle.putString("title","노인");
                splListbundle.putString("param","22");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 23:
                splListbundle.putString("title","여성");
                splListbundle.putString("param","23");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 24:
                splListbundle.putString("title","남성");
                splListbundle.putString("param","24");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 31:
                splListbundle.putString("title","종합비타민");
                splListbundle.putString("param","31");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 32:
                splListbundle.putString("title","오메가 3-6-9");
                splListbundle.putString("param","32");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 33:
                splListbundle.putString("title","유산균");
                splListbundle.putString("param","33");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 34:
                splListbundle.putString("title","항산화제");
                splListbundle.putString("param","33");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 35:
                splListbundle.putString("title","칼슘");
                splListbundle.putString("param","34");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 36:
                splListbundle.putString("title","마그네숨");
                splListbundle.putString("param","35");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 37:
                splListbundle.putString("title","마카");
                splListbundle.putString("param","36");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 38:
                splListbundle.putString("title","커큐민");
                splListbundle.putString("param","37");
                splListFrag.setArguments(splListbundle);
                ft.replace(R.id.Main_Frame,splListFrag);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }



    // 상단 툴바 뒤로가기 눌렀을 시 이벤트
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home ){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
            if(getFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStack();
            }
            else{
                super.onBackPressed();
            }
        }
    }


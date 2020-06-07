package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Vector;

public class Recommend extends AppCompatActivity {
    int dbCnt = 19;
    int nutCnt = 17;
    int foodCnt;

    int symptom1;
    int symptom2;
    int wheneat;
    int drug;
    JSONArray jsonArr = null;

    String[] spl = new String[]{"종합비타민", "유산균", "오메가3", "CoQ10", "니아신", "비타민D", "L-테아닌", "", "칼슘-마그네슘", "비타민C", "녹차 추출물", "비타민C", "비타민B 컴플렉스"};
    int[] spl1;
    int[] spl2;
    double[][] foodNutArr = new double[5][19];

    StringBuffer str1 = new StringBuffer();
    StringBuffer str2 = new StringBuffer();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);



        Intent intent = getIntent();
        /*
        intent.putExtra("userID", userID);
                intent.putExtra("date1", date);
                intent.putExtra("symptom1", pos1);
                intent.putExtra("symptom2", pos2);
                intent.putExtra("drug", pos3);
                intent.putExtra("wheneat", pos4);
                intent.putExtra("jsonArr", jsonArr);
        */
        symptom1 = Integer.parseInt(intent.getStringExtra("symptom1"));
        symptom2 = Integer.parseInt(intent.getStringExtra("symptom2"));
        drug = Integer.parseInt(intent.getStringExtra("drug"));
        wheneat = Integer.parseInt(intent.getStringExtra("wheneat"));

        try {
            jsonArr = new JSONArray(intent.getStringExtra("jsonArr"));
            JSONArray jsonArrayM = jsonArr.getJSONArray(0);
            JSONArray jsonArrayL = jsonArr.getJSONArray(1);
            JSONArray jsonArrayD = jsonArr.getJSONArray(2);

            switch(wheneat){
                case 0:
                    foodCnt = jsonArrayM.length();
                    break;
                case 1:
                    foodCnt = jsonArrayL.length();
                    break;
                case 2:
                    foodCnt = jsonArrayD.length();
                    break;
            }

            System.out.println("jsonArrayM " + jsonArrayM.length());
            System.out.println("jsonArrayL " + jsonArrayL.length());
            System.out.println("jsonArrayD " + jsonArrayD.length());

        } catch (JSONException e) {
            e.printStackTrace();
        }



        //음식 영양소 받아오기
        setFoodNut();


    }

    public void printView(){
        TextView rec1Title =  (TextView)findViewById(R.id.textTitle1);
        TextView rec1Content = (TextView)findViewById(R.id.textContent1);        TextView rec2Title =  (TextView)findViewById(R.id.textTitle2);
        TextView rec2Content = (TextView)findViewById(R.id.textContent2);

        //출력할 배열 rec1, rec2

        //배열 String 값 텍스트 뷰에 넣기
        rec1Title.setText("추천하는 영양제 조합");
        rec1Content.setText(str1.substring(0, str1.length()-1));
        rec2Title.setText("추가로 드시면 좋은 영양제");
        rec2Content.setText(str2.substring(0, str2.length()-1));
    }

    public void setFoodNut(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("음식 성분 제이슨 받아오기"+response);
                //음식 성분 제이슨 받아오기
                //{"result":[[{"0":"100006","food_nutNo":"100006","1":"500","food_nutBasis":"500","2":"105.61","food_nutCalcium":"105.61","3":"85.39","food_nutMagnesium":"85.39","4":"3.99","food_nutZinc":"3.99","5":"0.32","food_nutCopper":"0.32","6":"47.55","food_nutSelenium":"47.55","7":"0","food_nutIodine":"0","8":"0","food_nutVitaminA":"0","9":"0","food_nutVitaminD3":"0","10":"0","food_nutVitaminE":"0","11":"0","food_nutVitaminK1":"0","12":"0","food_nutThiamine":"0","13":"0.33","food_nutRiboflavin":"0.33","14":"3.61","food_nutNiacin":"3.61","15":"0","food_nutVitaminB6":"0","16":"0","food_nutVitaminB12":"0","17":"2.99","food_nutVitaminC":"2.99","18":"0","food_nutOmega3":"0"}],[{"0":"100007","food_nutNo":"100007","1":"400","food_nutBasis":"400","2":"98.64","food_nutCalcium":"98.64","3":"104.42","food_nutMagnesium":"104.42","4":"3.55","food_nutZinc":"3.55","5":"0.34","food_nutCopper":"0.34","6":"57.56","food_nutSelenium":"57.56","7":"0","food_nutIodine":"0","8":"0","food_nutVitaminA":"0","9":"0","food_nutVitaminD3":"0","10":"0","food_nutVitaminE":"0","11":"0","food_nutVitaminK1":"0","12":"0.24","food_nutThiamine":"0.24","13":"0.37","food_nutRiboflavin":"0.37","14":"1.23","food_nutNiacin":"1.23","15":"0","food_nutVitaminB6":"0","16":"1.12","food_nutVitaminB12":"1.12","17":"5.54","food_nutVitaminC":"5.54","18":"0","food_nutOmega3":"0"}]]}

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    System.out.println(result);
                    //System.out.println(result.getJSONArray(0));

                    for(int i =0 ; i<result.length(); i++) {
                        JSONArray result2 = result.getJSONArray(i);
                        for(int j =0; j<result2.length(); j++) {
                            String val = result2.getString(j);
                            foodNutArr[i][j] = Double.parseDouble(val);
                            //System.out.println(result2.getString(j));
                        }
                    }
                } catch (JSONException e) {
                    System.out.println("setFoodNut error");
                    e.printStackTrace();
                }
                makeSpl();
                printView();
            }
        };

        FoodNutRequest foodNutRequest = new FoodNutRequest(wheneat+"", jsonArr.toString(),responseListener);
        RequestQueue queue = Volley.newRequestQueue(Recommend.this);
        queue.add(foodNutRequest);
    }

    public void makeSpl(){
        //추천할 영양제
        //["종합비타민", "유산균", "오메가3", "CoQ10", "니아신", "비타민D", "L-테아닌", "", "칼슘-마그네슘", "비타민C", "녹차 추출물", "비타민C", "비타민B 컴플렉스"]
        //     0            1        2         3         4         5        6        7        8             9             10         11           12
        spl1 = new int[13];
        spl2 = new int[13];

        Arrays.fill(spl1, 0);
        Arrays.fill(spl2, 0);
        spl1[0] = 1;
        spl1[1] = 1;

        //증상1 고려
        int idx = 0;
        if(symptom1 >= 1) {
            idx = 2 + symptom1;
            spl1[idx] = 1;
        }

        //증상2 고려
        idx = 0;
        if(symptom2 >= 1) {
            idx = 2 + symptom2;
            spl1[idx] = 1;
        }


        //영양소 고려
        double VitaminA = 800/3;      //0
        double VitaminB6 = 100/3;     //1
        double VitaminB12 = 10/3;     //2
        double VitaminC = 1000/3;     //3
        double VitaminD3 = 400/3;     //4
        double VitaminE = 15/3;       //5
        double VitaminK = 0;          //6
        double Thiamine = 1.5/3;      //7
        double Riboflavin = 1.5/3;    //8
        double Niacin = 20/3;         //9
        double Copper = 800/3;        //10
        double Iodine = 150/3;        //11
        double Calcium = 2000/3;      //12
        double Magnesium = 500/3;     //13
        double Zinc = 50/3;           //14
        double Selenium = 60/3;       //15
        double Omega3 = 2000/3;       //16


        double sum_VitaminC = 0;
        double sum_VitaminB12= 0;
        double sum_Magnesium = 0;

        System.out.println("foodCnt+ : " + foodCnt);
        System.out.println("dbCnt+ : " + dbCnt);

        for(int i=0; i<foodCnt; i++){
            for(int j=0; j<dbCnt; j++){
                System.out.println("foodNutArr[i][j] + :"+foodNutArr[i][j]);
                if(j == 5) {
                    sum_VitaminC = sum_VitaminC + foodNutArr[i][j];
                    System.out.println(foodNutArr[i][j]);
                }

                if(j == 4) {
                    sum_VitaminB12 = sum_VitaminB12 + foodNutArr[i][j];
                    System.out.println(foodNutArr[i][j]);
                }

                if(j == 15) {
                    sum_Magnesium = sum_Magnesium + foodNutArr[i][j];
                    System.out.println(foodNutArr[i][j]);
                }
            }
        }
        System.out.println("영양소 확인 C" + sum_VitaminC);
        System.out.println("영양소 확인 B" + sum_VitaminB12);
        System.out.println("영양소 확인 Mag" + sum_Magnesium);

        //추천할 영양제
        //["종합비타민", "유산균", "오메가3", "CoQ10", "니아신", "비타민D", "L-테아닌", "", "칼슘-마그네슘", "비타민C", "녹차 추출물", "비타민C", "비타민B 컴플렉스"]
        //     0            1        2         3         4         5        6        7        8             9             10         11           12

        if(spl1[9] == 0 && sum_VitaminC < VitaminC){
            spl2[9] = 1;
        }
        if(spl1[12] == 0 && sum_VitaminB12 < VitaminB12){
            spl2[12] = 1;
        }
        if(spl1[8] == 0 && sum_Magnesium < Magnesium){
            spl2[8] = 1;
        }

        /*약 고려
        항생제 -> 유산균 X
        골다공증약 -> 마그네슘, 철분제 X
        혈액응고방지약 -> 오메가3 X*/
        switch (drug){
            case 1:
                spl1[1] = 0;
                spl2[1] = 0;
                break;
            case 2:
                spl1[8] = 0;
                spl2[1] = 0;
                break;
            case 3:
                spl1[2] = 0;
                spl2[1] = 0;
                break;
            default:
                break;
        }

        if(foodCnt >= 3){
            spl1[0] = 0;
        }

        System.out.println(Arrays.toString(spl1));
        System.out.println(Arrays.toString(spl2));

        Vector<String> rec1 = new Vector<String>();
        Vector<String> rec2 = new Vector<String>();

        for(int i=0; i<spl1.length; i++) {
            if(spl1[i] == 1){
                rec1.add(spl[i]);
                str1.append(spl[i]);
                str1.append("+");
            }
            if(spl2[i] == 1){
                rec2.add(spl[i]);
                str2.append(spl[i]);
                str2.append("+");
            }
        }

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

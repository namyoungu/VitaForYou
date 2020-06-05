package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.vita_1.adapter.FoodAdapter;
import com.example.vita_1.adapter.MyEventListener;
import com.example.vita_1.item.FoodData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mydiet extends Fragment{
    TextView textView;
    TextView textView2;
    TextView textView3;
    String[] items = {"", "장수", "순환기계", "면역력", "두뇌", "소화기계", "뼈", "감기", "다이어트"};
    String[] drugs = {"", "항생제", "골다공증약", "혈액응고방지약"};

    ImageButton imageButton1,imageButton2,imageButton3,imageButton4;

    String date;
    String userID;

    Spinner spinner1, spinner2, spinner3;
    //각포지션
    int pos1=-1,pos2=-1,pos3=-1;

    ArrayList<FoodData> alFoodID1 = new ArrayList<>();
    ArrayList<FoodData> alFoodID2 = new ArrayList<>();
    ArrayList<FoodData> alFoodID3 = new ArrayList<>();

    RecyclerView recycler1;
    RecyclerView recycler2;
    RecyclerView recycler3;

    LinearLayoutManager mLayoutManager;
    FoodAdapter mAdapter1;
    FoodAdapter mAdapter2;
    FoodAdapter mAdapter3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View diet = inflater.inflate(R.layout.my_diet, container, false);


        Bundle bundle = getArguments();
        if(bundle != null){
            date = bundle.getString("date");
            userID = bundle.getString("userID");
            System.out.println("budle 들고옴");
        }else {
            System.out.println("budle 안들고옴");
        }

        textView = (TextView) diet.findViewById(R.id.textView);
        spinner1 = (Spinner) diet.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View diet, int position, long id) {
                //Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT); //본인이 원하는 작업.
                textView.setText(items[position]);
                pos1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("");
            }
        });

        textView2 = (TextView) diet.findViewById(R.id.textView2);
        spinner2 = (Spinner) diet.findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View diet, int position, long id) {
                //Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT); //본인이 원하는 작업.
                textView2.setText(items[position]);
                pos2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView2.setText("");
            }
        });

        textView3 = (TextView) diet.findViewById(R.id.textView3);
        spinner3 = (Spinner) diet.findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, drugs);
        adapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View diet, int position, long id) {
                //Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT); //본인이 원하는 작업.
                textView3.setText(drugs[position]);
                pos3 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView3.setText("");
            }
        });

        imageButton1 = (ImageButton) diet.findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setType(1);
                ((MainActivity)getActivity()).setFrag(40);
            }
        });
        imageButton2 = (ImageButton) diet.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setType(2);
                ((MainActivity)getActivity()).setFrag(40);
            }
        });
        imageButton3 = (ImageButton) diet.findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setType(3);
                ((MainActivity)getActivity()).setFrag(40);
            }
        });
        imageButton4 = (ImageButton) diet.findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDiet();
            }
        });
        getRecycler(diet);
        if(alFoodID1.size()==0 && alFoodID2.size() ==0 && alFoodID3.size() ==0){
            getDiet();
        }




        return diet;
    }

    private void getRecycler(View view) {
        // RecyclerView binding


        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL

        recycler1 = (RecyclerView) view.findViewById(R.id.recycler1);
        recycler1.setLayoutManager(mLayoutManager);

        mAdapter1 = new FoodAdapter();
        mAdapter1.setEvent(new MyEventListener() {
            @Override
            public void onEvent(int pos) {
               // ((MainActivity)getActivity()).setFoodData(alFoodID1.get(pos));
            }

            @Override
            public void onLongEvent(int pos) {

            }
        });
        mAdapter1.setData(alFoodID1);

        recycler1.setAdapter(mAdapter1);


        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        mLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        recycler2 = (RecyclerView) view.findViewById(R.id.recycler2);
        recycler2.setLayoutManager(mLayoutManager2);

        mAdapter2 = new FoodAdapter();
        mAdapter2.setEvent(new MyEventListener() {
            @Override
            public void onEvent(int pos) {
             //   ((MainActivity)getActivity()).setFoodData(alFoodID2.get(pos));
            }

            @Override
            public void onLongEvent(int pos) {

            }
        });
        mAdapter2.setData(alFoodID2);

        recycler2.setAdapter(mAdapter2);

        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(getContext());
        mLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        recycler3 = (RecyclerView) view.findViewById(R.id.recycler3);
        recycler3.setLayoutManager(mLayoutManager3);

        mAdapter3 = new FoodAdapter();
        mAdapter3.setEvent(new MyEventListener() {
            @Override
            public void onEvent(int pos) {
                //((MainActivity)getActivity()).setFoodData(alFoodID3.get(pos));
            }

            @Override
            public void onLongEvent(int pos) {

            }
        });
        mAdapter3.setData(alFoodID3);

        recycler3.setAdapter(mAdapter3);
    }
    public void initList(){
        alFoodID1.clear();
        alFoodID2.clear();
        alFoodID3.clear();
    }

    public void addData(int type,int id,String name){
        Log.e("type",type+" id"+id+"name "+name+" "+alFoodID1.size()+" "+alFoodID2.size()+" "+alFoodID3.size()+" ");
        if(type==1) {
            alFoodID1.add(new FoodData(id, name));
            mAdapter1.notifyDataSetChanged();
        }

        if(type==2) {
            alFoodID2.add(new FoodData(id, name));
            mAdapter2.notifyDataSetChanged();
        }

        if(type==3) {
            alFoodID3.add(new FoodData(id, name));
            mAdapter3.notifyDataSetChanged();
        }
    }


    public void setDiet(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getContext(), "섭취가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    System.out.println("제이슨 받아오기"+response);
                  /*  response = "{\"result\":[{\"userID\":\"2222\",\"foodID\":\"2020-05-31\",\"morning\":\"100006\",\"lunch\":\"1\",\"dinner\":null}," +
                            "{\"userID\":\"2222\",\"foodID\":\"2020-05-31\",\"morning\":\"100006\",\"lunch\":null,\"dinner\":\"1\"}]}";
                  */  JSONObject jsonObject = new JSONObject(response);

                    JSONArray result = jsonObject.getJSONArray("result");
                    /*   if(success){

                     *//* String userID=jsonObject.getString("userID");
                        String userPW=jsonObject.getString("userPW");
                        String userName=jsonObject.getString("userName");
                        String userGender=jsonObject.getString("userGender");
                        String userEMail=jsonObject.getString("userEMail");


                        Toast.makeText(getActivity(), "환영합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("userPW", userPW);
                        intent.putExtra("userName",userName);
                        intent.putExtra("userGender",userGender);
                        intent.putExtra("userEMail",userEMail);
                        startActivity(intent);*//*
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "실패", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //SetDietRequest(String userID, String date1,String symptom1,String symptom2,String drug,String foodID, Response.Listener<String> listener)
        String foodID = getFoodID();
        Log.e("foodID",foodID);
        SetDietRequest dietRequest = new SetDietRequest(userID, date,pos1+"",pos2+"",pos3+"",foodID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(dietRequest);
    }

    private String getFoodID() {
        JSONArray jsonArray = new JSONArray();

        JSONArray jsonArraymld = new JSONArray();
        //아침데이터
        JSONArray jsonArrayM = new JSONArray();
        for(FoodData item : alFoodID1){

                jsonArrayM.put(item.getFoodID());

        }
        //점심데이터
        JSONArray jsonArrayL = new JSONArray();
        for(FoodData item : alFoodID2){

                jsonArrayL.put(item.getFoodID());

        }

        //저녁데이터
        JSONArray jsonArrayD = new JSONArray();
        for(FoodData item : alFoodID3){

            jsonArrayD.put(item.getFoodID());

        }

        jsonArraymld.put(jsonArrayM);
        jsonArraymld.put(jsonArrayL);
        jsonArraymld.put(jsonArrayD);

        jsonArray.put(jsonArraymld);

        return jsonArray.toString();

    }


    public void getDiet(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("제이슨 받아오기"+response);
                   /* response = "{\"result\":[{\"userID\":\"2222\",\"foodID\":\"2020-05-31\",\"morning\":\"100006\",\"lunch\":\"1\",\"dinner\":null}," +
                            "{\"userID\":\"2222\",\"foodID\":\"2020-05-31\",\"morning\":\"100006\",\"lunch\":null,\"dinner\":\"1\"}]}";*/
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray result = jsonObject.getJSONArray("result");
                    for(int i=0;i<result.length();i++){
                        JSONObject object = result.getJSONObject(i);
                        int morning = object.getInt("morning");
                        int lunch = object.getInt("lunch");
                        int dinner = object.getInt("dinner");
                        int id = object.getInt("foodID");
                        String foodName = object.getString("foodName");
                        if(morning==1){
                            alFoodID1.add(new FoodData(id,foodName));
                        }else if(lunch==1){
                            alFoodID2.add(new FoodData(id,foodName));
                        }else if(dinner==1){
                            alFoodID3.add(new FoodData(id,foodName));
                        }
                    }
                 /*   if(success){

                       *//* String userID=jsonObject.getString("userID");
                        String userPW=jsonObject.getString("userPW");
                        String userName=jsonObject.getString("userName");
                        String userGender=jsonObject.getString("userGender");
                        String userEMail=jsonObject.getString("userEMail");


                        Toast.makeText(getActivity(), "환영합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userID", userID);
                        intent.putExtra("userPW", userPW);
                        intent.putExtra("userName",userName);
                        intent.putExtra("userGender",userGender);
                        intent.putExtra("userEMail",userEMail);
                        startActivity(intent);*//*
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "실패", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    mAdapter1.notifyDataSetChanged();
                    mAdapter2.notifyDataSetChanged();
                    mAdapter3.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        DietRequest dietRequest = new DietRequest(userID, date, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(dietRequest);
    }





}

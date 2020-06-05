package com.example.vita_1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.vita_1.adapter.FoodAdapter;
import com.example.vita_1.adapter.MyEventListener;
import com.example.vita_1.item.FoodData;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class AddFood extends Fragment{

    EditText et_filter;

    RecyclerView recycler;
    LinearLayoutManager mLayoutManager;
    FoodAdapter mAdapter;

    ArrayList<FoodData> alFood = new ArrayList<>();
    ArrayList<FoodData> items = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addfood, container, false);

        et_filter = view.findViewById(R.id.et_filter);
        et_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(et_filter.getText().toString());
            }
        });
        // RecyclerView binding
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL


        recycler.setLayoutManager(mLayoutManager);

        mAdapter = new FoodAdapter();
        mAdapter.setEvent(new MyEventListener() {
            @Override
            public void onEvent(int pos) {
                ((MainActivity)getActivity()).setFoodData(items.get(pos));
            }

            @Override
            public void onLongEvent(int pos) {

            }
        });

        mAdapter.setData(items);

        recycler.setAdapter(mAdapter);


        getFood();

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(alFood);
        } else {
            for (FoodData recent : alFood) {
                String name = recent.getFoodName();
                if (name.toLowerCase().contains(charText)) {
                    items.add(recent);
                }
            }
        }
        mAdapter.setData(items);
        mAdapter.notifyDataSetChanged();
    }






    public void getFood(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("제이슨 받아오기"+response);
                    alFood.clear();
                    JSONObject jsonObject = new JSONObject(response);
        //{"result":[{"id":"100006","name":"꿩불고기","cat":"외식"},{"id":"100007","name":"닭갈비","cat":"외식"},{"id":"100008"
                    JSONArray result = jsonObject.getJSONArray("result");
                    for(int i =0 ;i<result.length();i++) {
                        JSONObject object = result.getJSONObject(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        alFood.add(new FoodData(id,name));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.setData(alFood);
                filter(et_filter.getText().toString());
                mAdapter.notifyDataSetChanged();
            }

        };

        FoodRequest foodRequest = new FoodRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(foodRequest);
    }





}

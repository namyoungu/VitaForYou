package com.example.vita_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vita_1.R;
import com.example.vita_1.item.FoodData;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private ArrayList<FoodData> Datas = new ArrayList<>();

    public void setData(ArrayList<FoodData> list){
        Datas = list;
    }

    public ArrayList<FoodData> getDatas() {
        return Datas;
    }

    Context context;


    MyEventListener event;


    FoodViewHolder holder;

    public void setEvent(MyEventListener event) {
        this.event = event;
        if(holder!=null) {
            holder.setEvent(event);
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false);

         holder = new FoodViewHolder(view,event);

        return holder;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        FoodData data = Datas.get(position);

        holder.tv_id.setText(""+data.getFoodID());
        holder.tv_name.setText(""+data.getFoodName());



    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }
}
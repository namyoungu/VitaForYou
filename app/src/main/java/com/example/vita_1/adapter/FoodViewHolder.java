package com.example.vita_1.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vita_1.R;


class FoodViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_id;
    public TextView tv_name;
    public MyEventListener event;


    public FoodViewHolder(View itemView,  MyEventListener event) {
        super(itemView);
        this.event = event;
        tv_id = (TextView) itemView.findViewById(R.id.tv_id);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition() ;
                if (pos != RecyclerView.NO_POSITION) {
                    // TODO : use pos.
                    FoodViewHolder.this.event.onEvent(pos);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int pos = getAdapterPosition() ;
                if (pos != RecyclerView.NO_POSITION) {
                    FoodViewHolder.this.event.onLongEvent(pos);
                }
                return true;
            }
        });

    }

    public void setEvent(MyEventListener event) {
        this.event =event;
    }
}
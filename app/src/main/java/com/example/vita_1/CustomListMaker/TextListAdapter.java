package com.example.vita_1.CustomListMaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vita_1.R;

import java.util.ArrayList;

public class TextListAdapter extends BaseAdapter {


    private ArrayList<TextListViewItem> textArrayList = new ArrayList<>();

    public TextListAdapter(){}

    @Override
    public int getCount() {
        return textArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return textArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_text_list,parent,false);
        }

        TextView splName = (TextView)convertView.findViewById(R.id.textSplName);

        TextListViewItem listViewItem = textArrayList.get(position);
        splName.setText(listViewItem.getSplName());

        return convertView;

    }

    public void addItem(String splName){

        TextListViewItem textListViewItem = new TextListViewItem();
        textListViewItem.setSplName(splName);

        textArrayList.add(textListViewItem);
    }
}

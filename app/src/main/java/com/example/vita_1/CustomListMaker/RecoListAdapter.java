package com.example.vita_1.CustomListMaker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vita_1.R;

import java.util.ArrayList;
public class RecoListAdapter extends BaseAdapter {

    private ArrayList<RecoViewItem> arrayList = new ArrayList<>();

    public RecoListAdapter(){}

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            convertView = inflater.inflate(R.layout.custom_reco_list,parent,false);
        }


        TextView title = (TextView)convertView.findViewById(R.id.textTitle);
        TextView content = (TextView) convertView.findViewById(R.id.textContent);

        RecoViewItem recoViewItem = arrayList.get(position);
        title.setText(recoViewItem.getTitle());
        content.setText(recoViewItem.getContent());

        return convertView;

    }

    public void addItem(String title, String content){

        RecoViewItem recoViewItem = new RecoViewItem();
        recoViewItem.setTitle(title);
        recoViewItem.setContent(content);

        arrayList.add(recoViewItem);
    }
}

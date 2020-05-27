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
public class ListAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> arrayList = new ArrayList<>();

    public ListAdapter(){}

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
            convertView = inflater.inflate(R.layout.custom_list,parent,false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imgSplSelect);
        TextView title = (TextView)convertView.findViewById(R.id.textTitle);

        ListViewItem listViewItem = arrayList.get(position);
        imageView.setImageDrawable(listViewItem.getDrawable());
        title.setText(listViewItem.getTitle());

        return convertView;

    }

    public void addItem(Drawable drawable , String title){

        ListViewItem listViewItem = new ListViewItem();
        listViewItem.setDrawable(drawable);
        listViewItem.setTitle(title);

        arrayList.add(listViewItem);
    }
}

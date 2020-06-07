package com.example.vita_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.vita_1.CustomListMaker.ListViewItem;
import com.example.vita_1.R;
import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {

    private ArrayList<AlarmItems> alarmItemList = new ArrayList<AlarmItems>();

    public AlarmAdapter(){

    }

    @Override
    public int getCount() {
        return alarmItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarmlist_item, parent, false);
        }

        TextView listTxt_AlarmName = (TextView) convertView.findViewById(R.id.li_txt_AlarmName);
        TextView listTxt_MornAlarm = (TextView) convertView.findViewById(R.id.li_txt_morn);
        TextView listTxt_LunAlarm = (TextView) convertView.findViewById(R.id.li_txt_lun);
        TextView listTxt_DnrAlarm = (TextView) convertView.findViewById(R.id.li_txt_dnr);

        AlarmItems alarmItems = alarmItemList.get(position);

        listTxt_AlarmName.setText(alarmItems.getAlarmName());
        listTxt_MornAlarm.setText(alarmItems.getMornAlarm());
        listTxt_LunAlarm.setText(alarmItems.getLunAlarm());
        listTxt_DnrAlarm.setText(alarmItems.getDnrAlarm());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return alarmItemList.get(position);
    }

    public void addItem(int no, String alarmName, String mornTime, String lunTime, String dnrTime){
        AlarmItems item = new AlarmItems();

        item.setAlarmNo(no);
        item.setAlarmName(alarmName);
        item.setMornAlarm(mornTime);
        item.setLunAlarm(lunTime);
        item.setDnrAlarm(dnrTime);

        alarmItemList.add(item);
    }
}

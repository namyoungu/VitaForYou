package com.example.vita_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;

public class SettingOpt extends Fragment implements View.OnClickListener {

    private Button bt_alarmSetting;
    private Intent activityIntent;
    private Bundle settingBundle;

    String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingBundle = getArguments();
        if(settingBundle != null) {
            userID = settingBundle.getString("userID");
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        bt_alarmSetting = (Button) view.findViewById(R.id.bt_alarmSetting);
        activityIntent = new Intent(getActivity(), AlarmListActivity.class);

        bt_alarmSetting.setOnClickListener(this);

        return view;
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.bt_alarmSetting:
                activityIntent.putExtra("userID", userID);
                startActivity(activityIntent);
        }
    }
}

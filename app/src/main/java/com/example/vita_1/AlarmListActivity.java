package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmListActivity extends AppCompatActivity {

    private ListView list_alarm;
    private Button bt_addAlarm;
    private Intent activityIntent;

    protected String[] LIST_MENU;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        // Init
        LIST_MENU = new String[] {};
        activityIntent = new Intent(this, AlarmActivity.class);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_alarmlist, LIST_MENU);

        // Set ID
        list_alarm = (ListView) findViewById(R.id.list_alarm);
        bt_addAlarm = (Button) findViewById(R.id.bt_addAlarm);
        list_alarm.setAdapter(adapter);

        bt_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(activityIntent);
            }
        });
    }
}

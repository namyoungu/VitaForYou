package com.example.vita_1;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.vita_1.adapter.AlarmAdapter;
import com.example.vita_1.adapter.AlarmItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class AlarmListActivity extends AppCompatActivity {

    private ListView list_alarm;
    private Button bt_addAlarm;

    public Intent intent;
    public Intent setAlarmActivityIntent;

    private String userID;
    private String alarmName;

    private ArrayList<AlarmItems> itemList;

    private int alarmNo;
    private boolean newAlarm;
    private int mornStat;
    private int mornHour;
    private int mornMin;
    private int lunStat;
    private int lunHour;
    private int lunMin;
    private int dnrStat;
    private int dnrHour;
    private int dnrMin;

    final AlarmAdapter adapter = new AlarmAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        // Init
        intent = getIntent();
        userID = intent.getStringExtra("userID");

        setAlarmActivityIntent = new Intent(this, AlarmActivity.class);

        // Set ID
        list_alarm = (ListView) findViewById(R.id.list_alarm);
        bt_addAlarm = (Button) findViewById(R.id.bt_addAlarm);
        list_alarm.setAdapter(adapter);
        itemList = new ArrayList<AlarmItems>();

        // Get Name, Items
        Response.Listener<String> responseNameListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        boolean success = jsonObject.getBoolean("success");
                        alarmName = jsonObject.getString("name");

                        if (success) {
                            Response.Listener<String> responseDataListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        String mornState, lunState, dnrState;
                                        int mornHourS, lunHourS, dnrHourS;
                                        JSONObject jsonObject = new JSONObject(response);

                                        boolean success = jsonObject.getBoolean("success");
                                        if(success){
                                            alarmNo = jsonObject.getInt(("no"));
                                            alarmName = jsonObject.getString("name");
                                            mornStat = jsonObject.getInt("mornStat");
                                            mornHour = jsonObject.getInt("mornHour");
                                            mornMin = jsonObject.getInt("mornMin");
                                            lunStat = jsonObject.getInt("lunStat");
                                            lunHour = jsonObject.getInt("lunHour");
                                            lunMin = jsonObject.getInt("lunMin");
                                            dnrStat = jsonObject.getInt("dnrStat");
                                            dnrHour = jsonObject.getInt("dnrHour");
                                            dnrMin = jsonObject.getInt("dnrMin");
                                        }

                                        if(mornHour > 12) {mornHourS = mornHour - 12; mornState = "PM";} else { mornHourS = mornHour; mornState = "AM";}
                                        if(lunHour > 12) {lunHourS = lunHour - 12; lunState = "PM";} else { lunHourS = lunHour; lunState = "AM";}
                                        if(dnrHour > 12) {dnrHourS = dnrHour - 12; dnrState = "PM";} else { dnrHourS = dnrHour; dnrState = "AM";}

                                        String mornTime = "아침 : " + mornState + " " + Integer.toString(mornHourS) + " : " + Integer.toString(mornMin);
                                        String lunTime = "점심 : " + lunState + " " + Integer.toString(lunHourS) + " : " + Integer.toString(lunMin);
                                        String dnrTime = "저녁 : " + dnrState + " " + Integer.toString(dnrHourS) + " : " + Integer.toString(dnrMin);

                                        adapter.addItem(alarmNo, alarmName, mornTime, lunTime, dnrTime);
                                        adapter.notifyDataSetChanged();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
;
                            RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                            GetAlarmRequest getAlarmRequest = new GetAlarmRequest(alarmName, responseDataListener);
                            queue.add(getAlarmRequest);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
        GetAlarmName getAlarmRequest = new GetAlarmName(userID, responseNameListener);
        queue.add(getAlarmRequest);

        Comparator<AlarmItems> noDesc = new Comparator<AlarmItems>() {
            @Override
            public int compare(AlarmItems item1, AlarmItems item2) {
                int ret = 0;
                if(item1.getAlarmNo() < item2.getAlarmNo()) ret = 1;
                else if(item1.getAlarmNo() == item2.getAlarmNo()) ret = 0;
                else ret = -1;
                return ret;
            }
        };

        Collections.sort(itemList, noDesc);
        adapter.notifyDataSetChanged();


        // Click Event

        list_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlarmItems item = (AlarmItems) adapterView.getItemAtPosition(position);
                alarmName = item.getAlarmName();

                Response.Listener<String> responseDataListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");
                            if(success){

                                mornStat = jsonObject.getInt("mornStat");
                                mornHour = jsonObject.getInt("mornHour");
                                mornMin = jsonObject.getInt("mornMin");
                                lunStat = jsonObject.getInt("lunStat");
                                lunHour = jsonObject.getInt("lunHour");
                                lunMin = jsonObject.getInt("lunMin");
                                dnrStat = jsonObject.getInt("dnrStat");
                                dnrHour = jsonObject.getInt("dnrHour");
                                dnrMin = jsonObject.getInt("dnrMin");
                                newAlarm = false;

                                setAlarmActivityIntent.putExtra("userID", userID);
                                setAlarmActivityIntent.putExtra("newAlarm", newAlarm);
                                setAlarmActivityIntent.putExtra("alarmNo", alarmNo);
                                setAlarmActivityIntent.putExtra("alarmName", alarmName);
                                setAlarmActivityIntent.putExtra("mornStat", mornStat);
                                setAlarmActivityIntent.putExtra("mornHour", mornHour);
                                setAlarmActivityIntent.putExtra("mornMin", mornMin);
                                setAlarmActivityIntent.putExtra("lunStat", lunStat);
                                setAlarmActivityIntent.putExtra("lunHour", lunHour);
                                setAlarmActivityIntent.putExtra("lunMin", lunMin);
                                setAlarmActivityIntent.putExtra("dnrStat", dnrStat);
                                setAlarmActivityIntent.putExtra("dnrHour", dnrHour);
                                setAlarmActivityIntent.putExtra("dnrMin", dnrMin);
                                startActivityForResult(setAlarmActivityIntent, 0);

                            } else {
                                Toast.makeText(getApplicationContext(), "DataBase 호출 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                GetAlarmRequest getAlarmRequest = new GetAlarmRequest(alarmName, responseDataListener);
                queue.add(getAlarmRequest);
            }
        });

        // Event Listener
        list_alarm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlarmItems item = (AlarmItems) adapterView.getItemAtPosition(position);
                alarmNo = item.getAlarmNo();

                AlertDialog.Builder builder = new AlertDialog.Builder(AlarmListActivity.this);
                builder.setTitle("알림");
                builder.setMessage("현재 설정된 알람을 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            Response.Listener<String> responseDelListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        boolean success = jsonObject.getBoolean("success");

                                        if (success) {
                                            Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_LONG).show();
                                            adapter.notifyDataSetChanged();
                                            return;

                                        } else {
                                            Toast.makeText(getApplicationContext(), "다시 시도하여주십시오.", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                            AlarmDeleteRequest alarmDeleteRequest = new AlarmDeleteRequest(Integer.toString(alarmNo), responseDelListener);
                            queue.add(alarmDeleteRequest);

                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        return;
                    }
                });
                AlertDialog delAlart = builder.create();
                delAlart.show();

                return true;
            }
        });

        bt_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAlarm = true;
                alarmName = "알람";
                mornStat = 0; mornHour = 0; mornMin = 0;
                lunStat = 0; lunHour = 0; lunMin = 0;
                dnrStat = 0; dnrHour = 0; dnrMin = 0;
                setAlarmActivityIntent.putExtra("newAlarm", newAlarm);
                setAlarmActivityIntent.putExtra("userID", userID);
                setAlarmActivityIntent.putExtra("alarmName", alarmName);
                setAlarmActivityIntent.putExtra("mornStat", mornStat);
                setAlarmActivityIntent.putExtra("mornHour", mornHour);
                setAlarmActivityIntent.putExtra("mornMin", mornMin);
                setAlarmActivityIntent.putExtra("lunStat", lunStat);
                setAlarmActivityIntent.putExtra("lunHour", lunHour);
                setAlarmActivityIntent.putExtra("lunMin", lunMin);
                setAlarmActivityIntent.putExtra("dnrStat", dnrStat);
                setAlarmActivityIntent.putExtra("dnrHour", dnrHour);
                setAlarmActivityIntent.putExtra("dnrMin", dnrMin);
                startActivityForResult(setAlarmActivityIntent, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case 0: // Alarm Update
                adapter.notifyDataSetChanged();

                Calendar mornCalendar = Calendar.getInstance();
                Calendar lunCalendar = Calendar.getInstance();
                Calendar dnrCalendar = Calendar.getInstance();

                mornStat = setAlarmActivityIntent.getIntExtra("mornStat", mornStat);
                mornHour = setAlarmActivityIntent.getIntExtra("mornHour", mornHour);
                mornMin = setAlarmActivityIntent.getIntExtra("mornMin", mornMin);
                lunStat = setAlarmActivityIntent.getIntExtra("lunStat", lunStat);
                lunHour = setAlarmActivityIntent.getIntExtra("lunHour", lunHour);
                lunMin = setAlarmActivityIntent.getIntExtra("lunMin", lunMin);
                dnrStat = setAlarmActivityIntent.getIntExtra("dnrStat", dnrStat);
                dnrHour = setAlarmActivityIntent.getIntExtra("dnrHour", dnrHour);
                dnrMin = setAlarmActivityIntent.getIntExtra("dnrMin", dnrMin);

                if (mornStat == 1){
                    mornCalendar.set(Calendar.HOUR_OF_DAY, mornHour);
                    mornCalendar.set(Calendar.MINUTE, mornMin);
                    diaryNotification(mornCalendar);
                }
                if (lunStat == 1){
                    lunCalendar.set(Calendar.HOUR_OF_DAY, lunHour);
                    lunCalendar.set(Calendar.MINUTE, lunMin);
                    diaryNotification(lunCalendar);
                }
                if (dnrStat == 1){
                    dnrCalendar.set(Calendar.HOUR_OF_DAY, dnrHour);
                    dnrCalendar.set(Calendar.MINUTE, dnrMin);
                    diaryNotification(dnrCalendar);
                }
                break;

            case 1:
                break;
        }
    }

    void diaryNotification(Calendar calendar){

        Boolean dailyNotify = true;

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // if Set DailyNotify
        if(dailyNotify){
            if (alarmManager != null){

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }
            }
        }

        // After Boot, Start Receiver
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}



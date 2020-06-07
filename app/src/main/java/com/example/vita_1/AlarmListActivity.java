package com.example.vita_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class AlarmListActivity extends AppCompatActivity {

    private ListView list_alarm;
    private Button bt_addAlarm;

    private Intent intent;
    private Intent activityIntent;

    private String userID;
    private String alarmName;

    private String st_mornHour;
    private String st_mornMin;
    private String st_lunHour;
    private String st_lunMin;
    private String st_dnrHour;
    private String st_dnrMin;

    private int mornHour;
    private int mornMin;
    private int lunHour;
    private int lunMin;
    private int dnrHour;
    private int dnrMin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        // Init
        intent = getIntent();
        userID = intent.getStringExtra("userID");

        final AlarmAdapter adapter = new AlarmAdapter();
        activityIntent = new Intent(this, AlarmActivity.class);

        // Set ID
        list_alarm = (ListView) findViewById(R.id.list_alarm);
        bt_addAlarm = (Button) findViewById(R.id.bt_addAlarm);
        list_alarm.setAdapter(adapter);

        // Get Name, Items
        Response.Listener<String> responseNameListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i =0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            alarmName = jsonObject.getString("name");
                            Response.Listener<String> responseDataListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        boolean success = jsonObject.getBoolean("success");
                                        if(success){

                                            st_mornHour = jsonObject.getString("mornHour");
                                            st_mornMin = jsonObject.getString("mornMin");
                                            st_lunHour = jsonObject.getString("lunHour");
                                            st_lunMin = jsonObject.getString("lunMin");
                                            st_dnrHour = jsonObject.getString("dnrHour");
                                            st_dnrMin = jsonObject.getString("dnrMin");

                                        } else { return; }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            GetAlarmRequest getAlarmRequest = new GetAlarmRequest(alarmName, responseDataListener);
                            RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                            queue.add(getAlarmRequest);

                            String mornTime = st_mornHour + " : " + st_mornMin;
                            String lunTime = st_lunHour + " : " + st_lunMin;
                            String dnrTime = st_dnrHour + " : " + st_dnrHour;
                            adapter.addItem(alarmName, mornTime, lunTime, dnrTime);

                        } else { return; }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GetAlarmName getAlarmRequest = new GetAlarmName(userID, responseNameListener);
        RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
        queue.add(getAlarmRequest);

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
                                mornHour = Integer.parseInt(jsonObject.getString("mornHour"));
                                mornMin = Integer.parseInt(jsonObject.getString("mornMin"));
                                lunHour = Integer.parseInt(jsonObject.getString("lunHour"));
                                lunMin = Integer.parseInt(jsonObject.getString("lunMin"));
                                dnrHour = Integer.parseInt(jsonObject.getString("dnrHour"));
                                dnrMin = Integer.parseInt(jsonObject.getString("dnrMin"));

                            } else { return; }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                GetAlarmRequest getAlarmRequest = new GetAlarmRequest(alarmName, responseDataListener);
                RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                queue.add(getAlarmRequest);

            }
        });

        // Event Listener
        list_alarm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlarmItems item = (AlarmItems) adapterView.getItemAtPosition(position);
                alarmName = item.getAlarmName();

                Response.Listener<String> responseDataListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean success = jsonObject.getBoolean("success");
                            if(success){

                                mornHour = Integer.parseInt(jsonObject.getString("mornHour"));
                                mornMin = Integer.parseInt(jsonObject.getString("mornMin"));
                                lunHour = Integer.parseInt(jsonObject.getString("lunHour"));
                                lunMin = Integer.parseInt(jsonObject.getString("lunMin"));
                                dnrHour = Integer.parseInt(jsonObject.getString("dnrHour"));
                                dnrMin = Integer.parseInt(jsonObject.getString("dnrMin"));

                                intent.putExtra("userID", userID);
                                intent.putExtra("alarmName", alarmName);
                                intent.putExtra("mornHour", mornHour);
                                intent.putExtra("mornMin", mornMin);
                                intent.putExtra("lunHour", lunHour);
                                intent.putExtra("lunMin", lunMin);
                                intent.putExtra("dnrHour", dnrHour);
                                intent.putExtra("dnrMin", dnrMin);
                                startActivity(activityIntent);

                            } else {
                                Toast.makeText(getApplicationContext(), "DataBase 호출 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                GetAlarmRequest getAlarmRequest = new GetAlarmRequest(alarmName, responseDataListener);
                RequestQueue queue = Volley.newRequestQueue(AlarmListActivity.this);
                queue.add(getAlarmRequest);

                return true;
            }
        });

        bt_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmName = "알람";
                intent.putExtra("userID", userID);
                intent.putExtra("alarmName", alarmName);
                intent.putExtra("mornHour", mornHour);
                intent.putExtra("mornMin", mornMin);
                intent.putExtra("lunHour", lunHour);
                intent.putExtra("lunMin", lunMin);
                intent.putExtra("dnrHour", dnrHour);
                intent.putExtra("dnrMin", dnrMin);
                startActivity(activityIntent);
            }
        });
    }
}



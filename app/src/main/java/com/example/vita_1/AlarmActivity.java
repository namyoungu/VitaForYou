package com.example.vita_1;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import org.json.*;

public class AlarmActivity extends AppCompatActivity {

    private int timeCase = 0;
    static final int MORNING = 1000001;
    static final int LUNCH = 1000002;
    static final int DINNER = 1000003;

    private EditText edit_mornTime;
    private EditText edit_lunTime;
    private EditText edit_dnrTime;

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

    private int alarmNo;
    private String userID;
    private String alarmName;

    private EditText al_name;
    private TextView sel_morn;
    private TextView sel_lun;
    private TextView sel_dnr;
    private Button bt_setAlarm;
    private Button bt_delAlarm;
    private Button bt_delMornAlarm;
    private Button bt_delLunAlarm;
    private Button bt_delDnrAlarm;

    public Intent setAlarmActivityIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Init Data
        setAlarmActivityIntent = getIntent();
        userID = setAlarmActivityIntent.getStringExtra("userID");
        alarmNo = setAlarmActivityIntent.getIntExtra("alarmNo", 0);
        newAlarm = setAlarmActivityIntent.getBooleanExtra("newAlarm", false);
        alarmName = setAlarmActivityIntent.getStringExtra("alarmName");
        mornStat = setAlarmActivityIntent.getIntExtra("mornStat", 0);
        mornHour = setAlarmActivityIntent.getIntExtra("mornHour",0);
        mornMin = setAlarmActivityIntent.getIntExtra("mornMin",0);
        lunStat = setAlarmActivityIntent.getIntExtra("lunStat",0);
        lunHour = setAlarmActivityIntent.getIntExtra("lunHour",0);
        lunMin = setAlarmActivityIntent.getIntExtra("lunMin",0);
        dnrStat = setAlarmActivityIntent.getIntExtra("dnrStat",0);
        dnrHour = setAlarmActivityIntent.getIntExtra("dnrHour",0);
        dnrMin = setAlarmActivityIntent.getIntExtra("dnrMin",0);

        // Set ID
        al_name = (EditText) findViewById(R.id.edit_alarmName);

        sel_morn = (TextView) findViewById(R.id.txt_selMornAlarm);
        sel_lun = (TextView) findViewById(R.id.txt_selLunAlarm);
        sel_dnr = (TextView) findViewById(R.id.txt_selDnrAlarm);

        edit_mornTime = (EditText) findViewById(R.id.edit_morningAlarm);
        edit_lunTime = (EditText) findViewById(R.id.edit_lunchAlarm);
        edit_dnrTime = (EditText) findViewById(R.id.edit_dinnerAlarm);

        bt_setAlarm = (Button) findViewById(R.id.bt_saveAlarm);
        bt_delAlarm = (Button) findViewById(R.id.bt_deleteAlarm);
        bt_delMornAlarm = (Button) findViewById(R.id.bt_morningAlRmv);
        bt_delLunAlarm = (Button) findViewById(R.id.bt_lunchAlRmv);
        bt_delDnrAlarm = (Button) findViewById(R.id.bt_dinnerAlRmv);

        // Init View
        String mornState, lunState, dnrState;
        int mornHourS, lunHourS, dnrHourS;

        if(mornStat == 1) sel_morn.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
        if(lunStat == 1) sel_lun.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
        if(dnrStat == 1) sel_dnr.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));

        if(mornHour > 12) {mornHourS = mornHour - 12; mornState = "PM";} else { mornHourS = mornHour; mornState = "AM";}
        if(lunHour > 12) {lunHourS = lunHour - 12; lunState = "PM";} else { lunHourS = lunHour; lunState = "AM";}
        if(dnrHour > 12) {dnrHourS = dnrHour - 12; dnrState = "PM";} else { dnrHourS = dnrHour; dnrState = "AM";}

        al_name.setText(alarmName);
        edit_mornTime.setText(mornState + " " + Integer.toString(mornHourS) + " : " + Integer.toString(mornMin));
        edit_lunTime.setText(lunState + " " + Integer.toString(lunHourS) + " : " + Integer.toString(lunMin));
        edit_dnrTime.setText(dnrState + " " + Integer.toString(dnrHourS) + " : " + Integer.toString(dnrMin));

        // Listener
        sel_morn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mornStat == 0){
                    sel_morn.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    mornStat = 1;
                } else {
                    sel_morn.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                    mornStat = 0;
                }
            }
        });

        sel_lun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lunStat == 0){
                    sel_lun.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    lunStat = 1;
                } else {
                    sel_lun.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                    lunStat = 0;
                }
            }
        });

        sel_dnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dnrStat == 0){
                    sel_dnr.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    dnrStat = 1;
                } else {
                    sel_dnr.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                    dnrStat = 0;
                }
            }
        });

        edit_mornTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();

                timeCase = MORNING;
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int min = currentTime.get(Calendar.MINUTE);

                TimePickerDialog dlg_timePicker =
                    new TimePickerDialog(AlarmActivity.this, timelistener, hour, min, false);
                dlg_timePicker.show();
            }
        });

        edit_lunTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();

                timeCase = LUNCH;
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int min = currentTime.get(Calendar.MINUTE);

                TimePickerDialog dlg_timePicker =
                        new TimePickerDialog(AlarmActivity.this, timelistener, hour, min, false);
                dlg_timePicker.show();
            }
        });

        edit_dnrTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();

                timeCase = DINNER;
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int min = currentTime.get(Calendar.MINUTE);

                TimePickerDialog dlg_timePicker =
                        new TimePickerDialog(AlarmActivity.this, timelistener, hour, min, false);
                dlg_timePicker.show();
            }
        });

        bt_delMornAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mornStat = 0; mornHour = 0; mornMin = 0;
                sel_morn.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                edit_mornTime.setText(null);
            }
        });

        bt_delLunAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunStat = 0; lunHour = 0; lunMin = 0;
                sel_lun.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                edit_lunTime.setText(null);
            }
        });

        bt_delDnrAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dnrStat = 0; dnrHour = 0; dnrMin = 0;
                sel_dnr.setTextColor(getResources().getColor(R.color.gray, getResources().newTheme()));
                edit_dnrTime.setText(null);
            }
        });

        bt_setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSetAlert();
            }
        });

        bt_delAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCancelAlert();
            }
        });
    }

    private void callSetAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("현재 설정한 알람을 저장하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String st_alarmNo = String.valueOf(alarmNo);
                alarmName = al_name.getText().toString();
                String st_mornStat = String.valueOf(mornStat);
                String st_mornHour = String.valueOf(mornHour);
                String st_mornMin = String.valueOf(mornMin);
                String st_lunStat = String.valueOf(lunStat);
                String st_lunHour = String.valueOf(lunHour);
                String st_lunMin = String.valueOf(lunMin);
                String st_dnrStat = String.valueOf(dnrStat);
                String st_dnrHour = String.valueOf(dnrHour);
                String st_dnrMin = String.valueOf(dnrMin);

                if(newAlarm == true){

                    Response.Listener<String> responseSetListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(),"저장되였습니다.",Toast.LENGTH_LONG).show();
                                setAlarmActivityIntent.putExtra("mornStat", mornStat);
                                setAlarmActivityIntent.putExtra("mornHour", mornHour);
                                setAlarmActivityIntent.putExtra("mornMin", mornMin);
                                setAlarmActivityIntent.putExtra("lunStat", lunStat);
                                setAlarmActivityIntent.putExtra("lunHour", lunHour);
                                setAlarmActivityIntent.putExtra("lunMin", mornStat);
                                setAlarmActivityIntent.putExtra("dnrStat", mornStat);
                                setAlarmActivityIntent.putExtra("dnrHour", mornStat);
                                setAlarmActivityIntent.putExtra("dnrMin", mornStat);
                                setResult(0, setAlarmActivityIntent);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "다시 시도하여주십시오.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        }
                };

                    RequestQueue queue = Volley.newRequestQueue(AlarmActivity.this);
                    AlarmInsertRequest alarmInsertRequest = new AlarmInsertRequest(userID, alarmName,
                        st_mornStat, st_mornHour, st_mornMin, st_lunStat, st_lunHour, st_lunMin,
                        st_dnrStat, st_dnrHour, st_dnrMin, responseSetListener);
                    queue.add(alarmInsertRequest);
                }

                if (newAlarm == false){

                    Response.Listener<String> responseSetListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");

                                if (success) {
                                    Toast.makeText(getApplicationContext(),"저장되였습니다.",Toast.LENGTH_LONG).show();
                                    setAlarmActivityIntent.putExtra("mornStat", mornStat);
                                    setAlarmActivityIntent.putExtra("mornHour", mornHour);
                                    setAlarmActivityIntent.putExtra("mornMin", mornMin);
                                    setAlarmActivityIntent.putExtra("lunStat", lunStat);
                                    setAlarmActivityIntent.putExtra("lunHour", lunHour);
                                    setAlarmActivityIntent.putExtra("lunMin", mornStat);
                                    setAlarmActivityIntent.putExtra("dnrStat", mornStat);
                                    setAlarmActivityIntent.putExtra("dnrHour", mornStat);
                                    setAlarmActivityIntent.putExtra("dnrMin", mornStat);
                                    setResult(0, setAlarmActivityIntent);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "다시 시도하여주십시오.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(AlarmActivity.this);
                    AlarmModifyRequest alarmModifyRequest = new AlarmModifyRequest(st_alarmNo, alarmName,
                            st_mornStat, st_mornHour, st_mornMin, st_lunStat, st_lunHour, st_lunMin,
                            st_dnrStat, st_dnrHour, st_dnrMin, responseSetListener);
                    queue.add(alarmModifyRequest);
                }
            }

        }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                return;
             }
         });

        AlertDialog saveAlart = builder.create();
        saveAlart.show();
    }

    private void callCancelAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("알람 설정을 종료 하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setResult(2, setAlarmActivityIntent);
                finish();

            }}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                return;
            }
        });

        AlertDialog cancelAlart = builder.create();
        cancelAlart.show();
    }

    private TimePickerDialog.OnTimeSetListener timelistener = new TimePickerDialog.OnTimeSetListener(){

        public void onTimeSet(TimePicker timePicker, int selHour, int selMin){

            int hour = selHour;
            int min = selMin;

            // Push String
            String state = "AM";
            if (selHour > 12){
                selHour -= 12; state = "PM";
            }

            switch (timeCase) {

                case MORNING: {
                    mornStat = 1; mornHour = hour; mornMin = min;
                    sel_morn.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    edit_mornTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case LUNCH:{
                    lunStat = 1; lunHour = hour; lunMin = min;
                    sel_lun.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    edit_lunTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case DINNER:{
                    dnrStat = 1; dnrHour = hour; dnrMin = min;
                    sel_dnr.setTextColor(getResources().getColor(R.color.colorMain, getResources().newTheme()));
                    edit_dnrTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }
            }

            timeCase = 0;
        }
    };


}

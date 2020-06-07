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

    private int mornHour;
    private int mornMin;
    private int lunHour;
    private int lunMin;
    private int dnrHour;
    private int dnrMin;

    private String userID;
    private String alarmName;
    private String st_mornHour;
    private String st_mornMin;
    private String st_lunHour;
    private String st_lunMin;
    private String st_dnrHour;
    private String st_dnrMin;

    private EditText al_name;
    private Button bt_setAlarm;
    private Button bt_delAlarm;
    private Button bt_delMornAlarm;
    private Button bt_delLunAlarm;
    private Button bt_delDnrAlarm;

    protected Intent intent;
    protected Intent activityIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Init Data
        activityIntent = new Intent(this, AlarmListActivity.class);

        intent = getIntent();
        userID = intent.getStringExtra("userID");
        alarmName = intent.getStringExtra("alarmName");
        mornHour = intent.getIntExtra("mornHour",0);
        mornMin = intent.getIntExtra("mornMin",0);
        lunHour = intent.getIntExtra("lunHour",0);
        lunMin = intent.getIntExtra("lunMin",0);
        dnrHour = intent.getIntExtra("dnrHour",0);
        dnrMin = intent.getIntExtra("dnrMin",0);

        // Set ID
        al_name = (EditText) findViewById(R.id.edit_alarmName);

        edit_mornTime = (EditText) findViewById(R.id.edit_morningAlarm);
        edit_lunTime = (EditText) findViewById(R.id.edit_lunchAlarm);
        edit_dnrTime = (EditText) findViewById(R.id.edit_dinnerAlarm);

        bt_setAlarm = (Button) findViewById(R.id.bt_saveAlarm);
        bt_delAlarm = (Button) findViewById(R.id.bt_deleteAlarm);
        bt_delMornAlarm = (Button) findViewById(R.id.bt_morningAlRmv);
        bt_delLunAlarm = (Button) findViewById(R.id.bt_lunchAlRmv);
        bt_delDnrAlarm = (Button) findViewById(R.id.bt_dinnerAlRmv);

        // Init View
        al_name.setText(alarmName);

        // Listener

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
                mornHour = 0; mornMin = 0;
                edit_mornTime.setText(null);
            }
        });

        bt_delLunAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunHour = 0; lunMin = 0;
                edit_lunTime.setText(null);
            }
        });

        bt_delDnrAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dnrHour = 0; dnrMin = 0;
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
                callDelAlert();
            }
        });
    }

    private void callSetAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("현재 설정된 알람을 저장하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alarmName = al_name.getText().toString();
                st_mornHour = String.valueOf(mornHour);
                st_mornMin = String.valueOf(mornMin);
                st_lunHour = String.valueOf(lunHour);
                st_lunMin = String.valueOf(lunMin);
                st_dnrHour = String.valueOf(dnrHour);
                st_dnrMin = String.valueOf(dnrMin);

                Response.Listener<String> responseSetListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(),"저장하였습니다.",Toast.LENGTH_LONG).show();
                                startActivity(activityIntent); finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "다시 시도하여주십시오.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AlarmInsertRequest alarmInsertRequest = new AlarmInsertRequest(userID, alarmName,
                        st_mornHour, st_mornMin, st_lunHour, st_lunMin, st_dnrHour, st_dnrMin, responseSetListener);
                RequestQueue queue = Volley.newRequestQueue(AlarmActivity.this);
                queue.add(alarmInsertRequest);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){}
        });

        builder.show();
    }

    private void callDelAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("현재 설정된 알람을 모두 해제하시겠습니까?");
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
                                Toast.makeText(getApplicationContext(),"모두 해제 완료하였습니다.",Toast.LENGTH_LONG).show();
                                startActivity(activityIntent); finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "다시 시도하여주십시오.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AlarmDeleteRequest alarmDeleteRequest = new AlarmDeleteRequest(userID, alarmName, responseDelListener);
                RequestQueue queue = Volley.newRequestQueue(AlarmActivity.this);
                queue.add(alarmDeleteRequest);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){}
        });
        builder.show();
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
                    mornHour = hour; mornMin = min;
                    edit_mornTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case LUNCH:{
                    lunHour = hour; lunMin = min;
                    edit_lunTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case DINNER:{
                    dnrHour = hour; dnrMin = min;
                    edit_dnrTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }
            }

            timeCase = 0;
        }
    };
}

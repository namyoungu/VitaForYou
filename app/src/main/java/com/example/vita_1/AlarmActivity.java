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

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private int timeCase = 0;
    static final int MORNING = 1000001;
    static final int LUNCH = 1000002;
    static final int DINNER = 1000003;

    private int[] mornTime;
    private int[] lunTime;
    private int[] dnrTime;

    private String alarmName;

    private EditText al_name;
    private EditText edit_mornTime;
    private EditText edit_lunTime;
    private EditText edit_dnrTime;
    private Button bt_setAlarm;
    private Button bt_delAlarm;
    private Button bt_delMornAlarm;
    private Button bt_delLunAlarm;
    private Button bt_delDnrAlarm;

    protected Intent activityIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Init Data
        mornTime = new int[] {0, 0};
        lunTime = new int[] {0, 0};
        dnrTime = new int[] {0, 0};

        activityIntent = new Intent(this, AlarmListActivity.class);

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
                timeCase = 0;
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
                timeCase = 0;
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
                timeCase = 0;
            }
        });

        bt_delMornAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mornTime[0] = 0; mornTime[1] = 0;
                edit_mornTime.setText("");
            }
        });

        bt_delLunAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunTime[0] = 0; lunTime[1] = 0;
                edit_lunTime.setText("");
            }
        });

        bt_delDnrAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dnrTime[0] = 0; dnrTime[1] = 0;
                edit_dnrTime.setText("");
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
                Toast.makeText(getApplicationContext(),"저장하였습니다.",Toast.LENGTH_LONG).show();
                startActivity(activityIntent); finish();
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
                alarmName = "";
                mornTime[0] = 0; mornTime[1] = 0; edit_mornTime.setText("");
                lunTime[0] = 0; lunTime[1] = 0; edit_lunTime.setText("");
                dnrTime[0] = 0; dnrTime[1] = 0; edit_dnrTime.setText("");
                Toast.makeText(getApplicationContext(),"모두 해제 완료하였습니다.",Toast.LENGTH_LONG).show();
                startActivity(activityIntent); finish();
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
                    mornTime[0] = hour; mornTime[1] = min;
                    edit_mornTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case LUNCH:{
                    lunTime[0] = hour; lunTime[1] = min;
                    edit_lunTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }

                case DINNER:{
                    dnrTime[0] = hour; dnrTime[1] = min;
                    edit_dnrTime.setText(state + " " + selHour + " : " + selMin);
                    break;
                }
            }
        }
    };

    private void getAlarm(){

    }

}

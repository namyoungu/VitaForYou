package com.example.vita_1.adapter;

public class AlarmItems {

    private String alarmName;
    private String mornAlarm;
    private String lunAlarm;
    private String dnrAlarm;

    public void setAlarmName(String alarmName){
        this.alarmName = alarmName;
    }

    public void setMornAlarm(String mornAlarm){
        this.mornAlarm = mornAlarm;
    }

    public void setLunAlarm(String lunAlarm){
        this.lunAlarm = lunAlarm;
    }

    public void setDnrAlarm(String dnrAlarm){
        this.dnrAlarm = dnrAlarm;
    }

    public String getAlarmName(){
        return this.alarmName;
    }

    public String getMornAlarm(){
        return this.mornAlarm;
    }

    public String getLunAlarm(){
        return this.lunAlarm;
    }

    public String getDnrAlarm(){
        return this.dnrAlarm;
    }
}

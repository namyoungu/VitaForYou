package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AlarmInsertRequest extends StringRequest {

    final static String URL = "http://limiteknj.iptime.org/Board/alarm/setAlarmData.php";
    private Map<String, String> map;

    public AlarmInsertRequest(String userID, String name, String mornHour, String mornMin,
                        String lunHour, String lunMin, String dnrHour, String dnrMin,
                        Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("name", name);
        map.put("mornHour",mornHour);
        map.put("mornMin",mornMin);
        map.put("lunHour",lunHour);
        map.put("lunMin",lunMin);
        map.put("dnrHour",dnrHour);
        map.put("dnrMin",dnrMin);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

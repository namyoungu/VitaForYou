package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AlarmModifyRequest extends StringRequest {

    final static String URL = "http://limiteknj.iptime.org:80/Board/alarm/modifyAlarmData.php";
    private Map<String, String> map;

    public AlarmModifyRequest(String no, String name, String mornStat, String mornHour, String mornMin,
                              String lunStat, String lunHour, String lunMin, String dnrStat, String dnrHour, String dnrMin,
                              Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("no", no);
        map.put("name", name);
        map.put("mornStat",mornStat);
        map.put("mornHour",mornHour);
        map.put("mornMin",mornMin);
        map.put("lunStat",lunStat);
        map.put("lunHour",lunHour);
        map.put("lunMin",lunMin);
        map.put("dnrStat",dnrStat);
        map.put("dnrHour",dnrHour);
        map.put("dnrMin",dnrMin);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetAlarmRequest extends StringRequest {

    final static String URL = "http://limiteknj.iptime.org:80/Board/alarm/getAlarmData.php";
    private Map<String, String> map;

    public GetAlarmRequest(String name, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("name", name);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

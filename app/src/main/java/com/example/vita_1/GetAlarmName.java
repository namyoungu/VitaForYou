package com.example.vita_1;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetAlarmName extends StringRequest {

    final static String URL = "http://limiteknj.iptime.org:80/Board/alarm/getAlarmName.php";
    private Map<String, String> map;

    public GetAlarmName(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DietRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://limiteknj.iptime.org/Board/diet.php";
    private Map<String, String> map;

    public DietRequest(String userID,String date1, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("date1", date1);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

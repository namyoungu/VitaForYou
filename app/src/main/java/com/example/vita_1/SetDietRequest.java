package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SetDietRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://limiteknj.iptime.org/Board/recommand.php";
    private Map<String, String> map;

    public SetDietRequest(String userID, String date1,String symptom1,String symptom2,String drug,String foodID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        //‘userID’, ‘symptom1’, ‘symptom2’, ‘drug’, ‘foodID’
        map.put("userID", userID);
        map.put("date1", date1);
        map.put("symptom1", symptom1);
        map.put("symptom2", symptom2);
        map.put("drug", drug);
        map.put("foodID", foodID);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

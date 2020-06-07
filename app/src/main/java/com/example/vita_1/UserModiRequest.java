package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserModiRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://limiteknj.iptime.org:80/Board/change_userinfo.php";
    private Map<String, String> map;

    public UserModiRequest(String userID,String userPW, String userName , String userGender, String userPhone, String userEMail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPW", userPW);
        map.put("userName",userName);
        map.put("userGender", userGender);
        map.put("userPhone", userPhone );
        map.put("userEMail", userEMail );
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

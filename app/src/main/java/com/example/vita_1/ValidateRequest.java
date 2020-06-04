package com.example.vita_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

   final static private String URL = "http://limiteknj.iptime.org:80/Board/UserValidate.php";
   private Map<String, String>map;

   public ValidateRequest(String userID, Response.Listener<String>listener){
       super(Request.Method.POST, URL, listener, null);
       map = new HashMap<>();
       map.put("userID", userID);
   }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}


package com.example.vita_1;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientClass extends WebViewClient {

    MainActivity mainActivity;
    String splpCode;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        mainActivity = (MainActivity)MainActivity.mContext;

        if(url.startsWith("app://splDetail/")){

            mainActivity.splpCode = url.substring(url.lastIndexOf("/")+1);
            mainActivity.setFrag(10);

            return true;
        }else{
            System.out.println("웹에서 화면전환 실패");
            return true;
        }
    }
}

package com.example.vita_1;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientClass2 extends WebViewClient {


    @Override

    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);

        return true;

    }

}

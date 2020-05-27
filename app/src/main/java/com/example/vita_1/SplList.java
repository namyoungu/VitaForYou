package com.example.vita_1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SplList extends Fragment{

    WebView webview_spl;
    Bundle splListbundle;
    String url;
    String title;
    String param;
    String postData;
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);



        splListbundle = getArguments();

        if(splListbundle != null) {
            title = splListbundle.getString("title");
            param = splListbundle.getString("param");
        }



    }



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View splListView = inflater.inflate(R.layout.fragment_spl_list, container, false);

        // 툴바 셋팅
        mainActivity = (MainActivity)getActivity();
        Toolbar toolbar = (Toolbar)splListView.findViewById(R.id.toolbar);


        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        toolbar.setTitleMarginStart(330);


        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //웹뷰 셋팅
        webview_spl = (WebView)splListView.findViewById(R.id.webview_spl);
        webview_spl.getSettings().setJavaScriptEnabled(true);




        try {
            postData = "title="+ URLEncoder.encode(title,"UTF-8")+"&param="+param;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url="http://limiteknj.iptime.org:80/Board/splListTest.php";
        webview_spl.setWebViewClient(new WebViewClientClass());
        webview_spl.postUrl(url,postData.getBytes());

        return splListView;
    }

}

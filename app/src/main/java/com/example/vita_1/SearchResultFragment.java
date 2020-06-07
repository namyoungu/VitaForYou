package com.example.vita_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class SearchResultFragment extends Fragment {


    WebView webview_spl;
    Bundle searchBundle;
    String url;
    String search_name;
    String postData;
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);



        searchBundle = getArguments();

        if(searchBundle != null) {
            search_name = searchBundle.getString("search_name");
        }



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View searchResultView = inflater.inflate(R.layout.fragment_search_result, container, false);

        // 툴바 셋팅
        mainActivity = (MainActivity)getActivity();
        Toolbar toolbar = (Toolbar)searchResultView.findViewById(R.id.toolbar);


        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        //toolbar.setTitle(title);
        //toolbar.setTitleMarginStart(330);


        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);


        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);




        //웹뷰 셋팅
        webview_spl = (WebView)searchResultView.findViewById(R.id.search_spl);
        webview_spl.getSettings().setJavaScriptEnabled(true);

            postData = "searchName="+ search_name;

        url="http://limiteknj.iptime.org:80/Board/search_spl_result.php";
        webview_spl.setWebViewClient(new WebViewClientClass());
        webview_spl.postUrl(url,postData.getBytes());

        return searchResultView;
    }

}

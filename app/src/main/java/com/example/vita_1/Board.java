package com.example.vita_1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class Board extends Fragment{

    WebView webview_board;
    Bundle boardBundle;
    String userId;
    String postData;
    String url;
    MainActivity mainActivity;
    CookieManager cookieManager1;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //쿠키로 세션관리
        cookieManager1 = CookieManager.getInstance();

        boardBundle = getArguments();

        if(boardBundle != null) {
            userId = boardBundle.getString("userId");
        }

    }



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View boardView = inflater.inflate(R.layout.fragment_board, container, false);

        //웹뷰 셋팅
        webview_board = (WebView)boardView.findViewById(R.id.webview_board);
        webview_board.getSettings().setJavaScriptEnabled(true);

        postData = "userId="+ userId;
        url="http://limiteknj.iptime.org:80/Board/board/index.php";


        cookieManager1.setCookie("http://limiteknj.iptime.org:80/Board/board/index.php","cookie="+userId);
        CookieSyncManager.getInstance().sync();

        webview_board.postUrl(url,postData.getBytes());

        return boardView;
    }

}

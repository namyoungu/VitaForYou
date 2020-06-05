package com.example.vita_1;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

<<<<<<< Updated upstream
=======
import java.util.Vector;

>>>>>>> Stashed changes
public class ItemDetail extends Fragment{

    WebView webview_item_detail;
    String pid;
    String url;
    String postData;
<<<<<<< Updated upstream
=======
    String userID;
>>>>>>> Stashed changes
    Bundle itemDetailBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemDetailBundle = getArguments();

        if(itemDetailBundle != null) {
            pid = itemDetailBundle.getString("splpCode");
<<<<<<< Updated upstream
            postData = "pid="+pid;
=======
            userID = itemDetailBundle.getString("userID");
            postData = "pid="+pid +"&userID="+userID;
>>>>>>> Stashed changes
            System.out.println("postData는 ..? " + postData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View itemDetailView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // 툴바 셋팅
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        Toolbar toolbar = (Toolbar)itemDetailView.findViewById(R.id.toolbar);


        // 툴바 세부사항 셋팅
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(" Vita For You ");
        toolbar.setTitleMarginStart(330);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //웹뷰 셋팅
        webview_item_detail = (WebView)itemDetailView.findViewById(R.id.webview_item_detail);
        webview_item_detail.getSettings().setJavaScriptEnabled(true);

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        System.out.println("postData는 ? :"+postData);

        url="http://limiteknj.iptime.org/Board/spl_detail/";
        webview_item_detail.setWebViewClient(new WebViewClientClass());
        webview_item_detail.postUrl(url,postData.getBytes());

        return itemDetailView;
    }
}

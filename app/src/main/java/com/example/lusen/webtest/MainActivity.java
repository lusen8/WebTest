package com.example.lusen.webtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    protected static final int SHOW_RESPONSE = 0;
    private TextView responseText;
    private MyHttpURL myHttpURL;
//    private WebView web;
    private URL url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        web = (WebView) findViewById(R.id.web);
//        web.getSettings().setJavaScriptEnabled(true);
//        web.setWebViewClient(new WebViewClient());
//        web.loadUrl("http://www.baidu.com");
        responseText = (TextView) findViewById(R.id.send);
        try {
            url = new URL("http://news-at.zhihu.com/api/4/news/latest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        myHttpURL = new MyHttpURL(url);     //传入网址
        myHttpURL.sendHttpURLConnection();  //获取网络请求
        Log.d("Maintivity","iiiiiiiiiiiiiiiiiiiiii...........");
        while (true){
            if (myHttpURL.GetResponse()!=null){
                responseText.setText(myHttpURL.GetResponse());
                Log.d("Maintivity","endddddddddddddddd");
                break;
            }
        }

    
    }




}
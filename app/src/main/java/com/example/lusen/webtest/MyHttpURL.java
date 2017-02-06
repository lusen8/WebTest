package com.example.lusen.webtest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lusen on 2017/2/6.
 */

public class MyHttpURL {
    private static final int SHOW_RESPONSE=0;
    private URL url;
    private String responseText = null;

    public MyHttpURL( URL url){
        this.url = url;
    }

    public String GetResponse(){
        return responseText;
    }
    protected void sendHttpURLConnection() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler(){
                    public void handleMessage(Message msg){
                        switch (msg.what){
                            case SHOW_RESPONSE:
                                String response = (String) msg.obj;
                                if(response!=null) {
                                    responseText = response;
                                    Log.d("MyHttpURL","endfddddddddddddd");
                                }

                        }
                    }

                };
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    InputStreamReader inread = new InputStreamReader(in);
                    BufferedReader br = new BufferedReader(inread);
                    StringBuilder response = new StringBuilder();
                    String line = null;
                    Log.d("MyHttpURL","bbbbbbbbbbbbbbbbbbbbbb");
                    while ((line = br.readLine())!=null){
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    Log.d("Exception","由于各种原因抛异常");
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        Log.d("MyHttpURL","NOT  nullllllllllllllllllll");
                        connection.disconnect();
                    }
                }
                Looper.loop();
            }
        }).start();
    }

}

package com.shadt.iptv_qx;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.shadt.iptv_qx.R;


public class WebActivity extends BaseActivity {


    Context mContext = WebActivity.this;

    WebView mweb;
    private  String weburl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        weburl=getIntent().getStringExtra("url");
        init();
    }
    public void init(){
        mweb=findViewById(R.id.web);
        WebSettings settings = mweb.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        //设置可以支持缩放
        settings.setSupportZoom(true);
        //设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setTextZoom(150);
        settings.setLoadWithOverviewMode(true);
        mweb.loadUrl(""+weburl);
    }






}

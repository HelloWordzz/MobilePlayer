package com.gnohz.oahz.mobileplay2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class HtmlActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html2);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        int x = bundle.getInt("data");
//        Toast.makeText(this,x+"",Toast.LENGTH_LONG).show();
        if(x == 1){
            url = "http://www.baidu.com/";
        }
        //给webView添加目标
        webView.loadUrl(url);
        //获取Websettings
        WebSettings webSettings = webView.getSettings();
        //设置支持JS
        webSettings.setJavaScriptEnabled(true);
        //设置不调用系统浏览器
        webView.setWebViewClient(new WebViewClient());


    }
}

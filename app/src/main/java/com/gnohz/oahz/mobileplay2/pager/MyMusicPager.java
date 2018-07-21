package com.gnohz.oahz.mobileplay2.pager;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gnohz.oahz.mobileplay2.R;
import com.gnohz.oahz.mobileplay2.base.BasePager;

public class MyMusicPager extends BasePager {
    private WebView webView;
    private ProgressBar progressBar;
    public MyMusicPager(Context context) {
        super(context);
    }

    @Override
    public View intiview() {
        View view = View.inflate(context, R.layout.activity_mymusic,null);
       webView = view.findViewById(R.id.yinyuetai);
       progressBar = view.findViewById(R.id.progressBar1);
        return view;
    }

    @Override
    public void intidata() {
        super.intidata();
        webView.loadUrl("file:///android_asset/index.html");
        //获取Websettings
        WebSettings webSettings = webView.getSettings();
        //设置支持JS
        webSettings.setJavaScriptEnabled(true);
        //设置不调用系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //第一个参数把自身传给js 第二个参数是this的一个名字
        //这个方法用于让H5调用android方法


        //设置进度条
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }

            }

        });








    }
}

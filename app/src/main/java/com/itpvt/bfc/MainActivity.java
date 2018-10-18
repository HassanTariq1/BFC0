package com.itpvt.bfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    android.webkit.WebView webView;
    boolean loadingFinished = true;
    boolean redirect = false;
    ProgressBar progress;
    boolean Disableornot=false;
    TextView title;
    String url;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        webView = (android.webkit.WebView) findViewById(R.id.webView);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        title = (TextView) findViewById(R.id.title);
        progress.setMax(100);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.loadUrl("http://bfc.com.pk");
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progress.setVisibility(View.VISIBLE);
                title.setText("Loading....");



                String title1=view.getTitle().toString().replace(""," ");
                title.setText(title1);
                Disableornot=false;


            }


            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
//                    tvNoInternet.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                String title1=view.getTitle().toString().replace(""," ");
                title.setText(title1);

                Disableornot=true;

            }
        });
    }

    public void previouspage()
    {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onBackPressed()
    {
        if (Disableornot)
        {
            previouspage();
        }

    }


}

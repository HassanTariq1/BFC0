package com.itpvt.bfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private  String web="http://bfc.com.pk";

    private WebView webview;
    private  ProgressBar prog;
    private FrameLayout frame;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        webview = (android.webkit.WebView) findViewById(R.id.webView);
        prog = (ProgressBar) findViewById(R.id.progress);

        frame = (FrameLayout) findViewById(R.id.frame);
       // title = (TextView) findViewById(R.id.title);
        prog.setMax(100);



        webview.setWebViewClient(new HelpClient());
        webview.setWebChromeClient(new WebChromeClient(){
            public  void onProgressChanged(WebView view, int progress){

                frame.setVisibility(View.VISIBLE);
                prog.setProgress(progress);
                setTitle("Loading...");

                if(progress ==100 ){

                    frame.setVisibility(View.GONE);
                    setTitle(view.getTitle());

                }

                super.onProgressChanged(view, progress);


            }






        });

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(false);
        webview.loadUrl(web);
        prog.setProgress(0);



//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webView.loadUrl("http://bfc.com.pk");
//        webView.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                progress.setVisibility(View.VISIBLE);
//                title.setText("Loading....");
//
//
//
//                String title1=view.getTitle().toString().replace(""," ");
//                title.setText(title1);
//                Disableornot=false;
//
//
//            }
//
//
//            @Override
//            public void onPageFinished(android.webkit.WebView view, String url) {
//                super.onPageFinished(view, url);
////                    tvNoInternet.setVisibility(View.GONE);
//                progress.setVisibility(View.GONE);
//                String title1=view.getTitle().toString().replace(""," ");
//                title.setText(title1);
//
//                Disableornot=true;
//
//            }
//        });
//    }
//
//    public void previouspage()
//    {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public void onBackPressed()
//    {
//        if (Disableornot)
//        {
//            previouspage();
//        }

    }


    private class HelpClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(request);

            frame.setVisibility(View.VISIBLE);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode ==KeyEvent.KEYCODE_BACK){
            if(webview.canGoBack()){
                webview.goBack();
                return true;


            }



        }
        return super.onKeyDown(keyCode, event);
    }
}

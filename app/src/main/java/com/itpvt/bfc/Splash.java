package com.itpvt.bfc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                    if(isNetworkAvailable())
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else

                    {
                        Intent intent = new Intent(getApplicationContext(), Network_Error.class);
                        startActivity(intent);
                        finish();
                    }

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}

package com.example.amir.abcube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Amir on 3/11/2017.
 */
public class Splash extends Activity {

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle amir) {
        super.onCreate(amir);
        setContentView(R.layout.splash);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);                //time to display logo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openMainactivity = new Intent("com.example.amir.MAINACTIVITY");  //activity to display after logo
                    startActivity(openMainactivity);

                }

            }
        };
        timer.start();

    }
}

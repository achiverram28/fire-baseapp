package com.example.firefire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView textView=findViewById(R.id.textSplashScreen);
        textView.animate().translationY(-1500).setDuration(1000);
        Thread thread=new Thread(){
            public void run(){
                try{
                    Thread.sleep(5000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent=new Intent(SplashActivity.this,StartActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}
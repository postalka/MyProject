package com.example.raksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash_Screen_Activity extends AppCompatActivity {
    private int SLEEP_TIMER =1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     requestWindowFeature(Window.FEATURE_NO_TITLE);
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

     setContentView(R.layout.activity_splash__screen_);
     getSupportActionBar().hide();
     SafetyLauncher safetyLauncher=new SafetyLauncher();
     safetyLauncher.start();


    }

    private class SafetyLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);

            }catch (InterruptedException e){
                e.printStackTrace();

            }
            Intent intent=new Intent(Splash_Screen_Activity.this,MainActivity.class);
            startActivity(intent);
            Splash_Screen_Activity.this.finish();
        }
    }
}

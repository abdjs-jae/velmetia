package app.rocketship.velmetia;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.rocketship.natrapharmutil.ActivityHandler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Activity a = this;

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    // Go to LandingActivity
                   // ActivityHandler.afterSplashActivity(a, RegisterActivity.class, MainActivity.class);
                    ActivityHandler.goToMenuOffline(a, MainActivity.class);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

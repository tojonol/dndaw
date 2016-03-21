package dnd.com.dndaw;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Remove title bar


        setContentView(R.layout.activity_splash);


        Runnable run3sec = new Runnable()
        {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };
        Handler myhandler = new Handler();
        myhandler.postDelayed(run3sec, 3000);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                nextActivity();
//            }
//        }, 3000);
    }

    public void nextActivity()
    {
        Intent inte = new Intent(this, MainActivity.class);
        startActivity(inte);
    }
}

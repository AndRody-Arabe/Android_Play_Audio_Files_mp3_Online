package com.andrody.play_media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Abboudi_Aliwi on 14.06.2017.
 * Website : http://andrody.com/
 * our channel on YouTube : https://www.youtube.com/c/Andrody2015
 * our page on Facebook : https://www.facebook.com/andrody2015/
 * our group on Facebook : https://www.facebook.com/groups/Programming.Android.apps/
 * our group on Whatsapp : https://chat.whatsapp.com/56JaImwTTMnCbQL6raHh7A
 * our group on Telegram : https://t.me/joinchat/AAAAAAm387zgezDhwkbuOA
 * Preview the app from Google play : https://play.google.com/store/apps/details?id=nasser_alqtami.andrody.com
 */

public class Main extends Activity {
    private static final int WAIT_TIME = 5000;
    private Timer waitTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.main_bg);
        setContentView(image);



        waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startMainActivity();
                    }
                });
            }
        }, WAIT_TIME);
    }

    private void startMainActivity() {
        startActivity(new Intent(this, List.class));
        finish();
    }
}
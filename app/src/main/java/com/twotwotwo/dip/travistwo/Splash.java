package com.twotwotwo.dip.travistwo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    private Thread timer;
    private Intent appListIntent;
    private MediaPlayer splashSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appListIntent = new Intent("com.twotwotwo.dip.APPMENU");
        splashSound = MediaPlayer.create(Splash.this, R.raw.splash_sound);

        SharedPreferences preferences = PreferenceManager
                                   .getDefaultSharedPreferences(getBaseContext());
        boolean isMusicOn = preferences.getBoolean(getString(R.string.music_preferences_key),
                                                                                  true);

        if(isMusicOn) {
            splashSound.start();
        }
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(Splash.this,
                            "Error. Close and Open Again", Toast.LENGTH_SHORT).show();
                } finally {
                    startActivity(appListIntent);
                }
            }
        });
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashSound.release();
        finish();
    }
}

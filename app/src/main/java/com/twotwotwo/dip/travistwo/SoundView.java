package com.twotwotwo.dip.travistwo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SoundView extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private SoundPool soundPool;
    private int explosion = 0;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = new View(SoundView.this);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        setContentView(view);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = soundPool.load(this, R.raw.explosion, 1);
        player = MediaPlayer.create(this, R.raw.splash_sound);
    }

    @Override
    public void onClick(View v) {
        if (explosion != 0)
            soundPool.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View v) {
        player.start();
        return false;
    }
}

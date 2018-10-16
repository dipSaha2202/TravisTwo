package com.twotwotwo.dip.travistwo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

public class Sliding extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {
    private SlidingDrawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);

        drawer = findViewById(R.id.sliding);
        CheckBox checkBox = findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(this);
        drawer.setOnDrawerOpenListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            drawer.lock();
        } else {
            drawer.unlock();
        }
    }

    @Override
    public void onDrawerOpened() {
        MediaPlayer player = MediaPlayer.create(Sliding.this, R.raw.explosion);
        player.start();

    }

    public void openDrawer(View view) {
        drawer.open();
    }

    public void toggleDrawer(View view) {
        drawer.toggle();
    }

    public void closeDrawer(View view) {
        drawer.close();
    }
}

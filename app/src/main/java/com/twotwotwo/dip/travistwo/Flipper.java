package com.twotwotwo.dip.travistwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class Flipper extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        flipper = findViewById(R.id.viewFlipper);
        flipper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewFlipper:
                flipper.showNext();
                break;
        }
    }
}

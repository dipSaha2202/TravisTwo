package com.twotwotwo.dip.travistwo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class SurfaceGraphicsBasic extends AppCompatActivity implements View.OnTouchListener{

    private CustomSurface surface;
    float x, y;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surface = new CustomSurface(SurfaceGraphicsBasic.this);
        surface.setOnTouchListener(this);

        setContentView(surface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        surface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        surface.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       // v.performClick();
        x = event.getX();
        y = event.getY();

        return true;
    }

    private class CustomSurface extends SurfaceView implements Runnable{
        SurfaceHolder holder;
        Thread surfaceThread;
        boolean isRunning = false;
        Bitmap gBall;

        public CustomSurface(Context context) {
            super(context);
            holder = getHolder();
            gBall = BitmapFactory.decodeResource(getResources(), R.drawable.green_button);
        }

        @Override
        public void run() {
            while (isRunning){
                if(!holder.getSurface().isValid()){
                    continue;
                }

                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(50, 100, 200);
                if(x != 0 && y != 0){
                    canvas.drawBitmap(gBall, x - gBall.getWidth()/2
                            , y - gBall.getHeight()/2, null);
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            isRunning = false;
            while (true) {
                try {
                    surfaceThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }

            surfaceThread = null;
        }

        public void resume(){
            isRunning = true;
            surfaceThread = new Thread(this);
            surfaceThread.start();
        }

      /*  @Override
        public boolean performClick() {
             super.performClick();
             return true;
        }*/
    }
}

package com.twotwotwo.dip.travistwo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BasicGraphics extends AppCompatActivity {
    CustomViewForBasicGraphics graphics;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphics = new CustomViewForBasicGraphics(BasicGraphics.this);
        setContentView(graphics);
    }

    private class CustomViewForBasicGraphics extends View {
        Bitmap greenBall, redBall;
        float changingY = 0;
        Paint rectPaint;
        Paint textPaint;
        Typeface typefaceOne, typefaceTwo, typefaceThree;

        public CustomViewForBasicGraphics(Context context) {
            super(context);
            greenBall = BitmapFactory.decodeResource(getResources(), R.drawable.green_button);
            rectPaint = new Paint();
            textPaint = new Paint();
            typefaceOne = Typeface.createFromAsset(context.getAssets(), "fonts/Buitenzorg.otf");
            typefaceTwo = Typeface.createFromAsset(context.getAssets(), "fonts/Mercy.ttf");
            typefaceThree = Typeface.createFromAsset(context.getAssets(), "fonts/Snicker_Snack.otf");
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);

            textPaint.setTypeface(typefaceOne);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTextSize(80);
            textPaint.setARGB(255, 200, 100, 50);
            canvas.drawText("This is Canvas", canvas.getWidth()/2,
                        canvas.getHeight() / 2 - 200, textPaint );

            canvas.drawBitmap(greenBall, (canvas.getWidth() - greenBall.getWidth()) / 2,
                    changingY, null);
            if(changingY < canvas.getHeight()){
                changingY += 4;
            } else {
                changingY = 0;
            }

            rectPaint.setColor(Color.BLUE);
            canvas.drawRect(0, 600, getWidth(), 800, rectPaint);

            invalidate();
        }
    }
}

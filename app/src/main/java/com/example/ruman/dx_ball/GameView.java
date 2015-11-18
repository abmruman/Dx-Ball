package com.example.ruman.dx_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by A B M Ruman on 18/11/2015.
 */
public class GameView extends View{
    Paint paint;
    float x, y;
    int batHeight=10, batWidth=20, ballRadius=20;
    boolean gameStarted = false;
    public GameView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!gameStarted){
            gameStarted = true;
            x=canvas.getWidth()/2;
            y=canvas.getHeight()-ballRadius;
        }
        canvas.drawRGB(0,0,0);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,ballRadius,paint);
    }

    private void drawBat(){

    }
}

package com.androidApps.ruman.dx_ball;

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
    float paddleX, paddleY, ballX, ballY;
    int paddleHeight = 50, paddleWidth = 400, ballRadius = 20;
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
            paddleX = canvas.getWidth() / 2;
            paddleY = canvas.getHeight();
            ballX = canvas.getWidth() / 2;
            ballY = canvas.getHeight() - paddleHeight - ballRadius;
        }
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRGB(0, 0, 0);
        drawBar(canvas);
        drawBall(canvas);
    }

    private void drawBar(Canvas canvas) {
        float x = paddleX, y = paddleY;
        canvas.drawRect(x -= paddleWidth / 2, y -= paddleHeight, x + paddleWidth, y + paddleHeight, paint);
    }

    private void drawBall(Canvas canvas) {

        canvas.drawCircle(ballX, ballY, ballRadius, paint);
    }
}

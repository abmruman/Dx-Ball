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
    float paddleX, paddleY, ballX, ballY, paddleRatioX, paddleRatioY, radiusRatio;
    int width, height, paddleHeight, paddleWidth, ballRadius;
    boolean gameStarted;
    public GameView(Context context) {
        super(context);
        gameStarted = false;
        paddleRatioX = 400 / 1600f;
        paddleRatioY = 50 / 1200f;
        radiusRatio = 25 / 1200f;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = canvas.getHeight();
        width = canvas.getWidth();
        paddleHeight = (int) (paddleRatioY * height);
        paddleWidth = (int) (paddleRatioX * width);
        ballRadius = (int) (radiusRatio * ((width < height) ? width : height));

        if(!gameStarted){
            gameStarted = true;
            paddleX = width / 2;
            paddleY = height;
            ballX = width / 2;
            ballY = height - paddleHeight - ballRadius;
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

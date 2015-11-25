package com.androidApps.ruman.dx_ball;

import android.view.MotionEvent;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class DxBall {
    boolean started;
    Paddle paddle;
    Ball ball;

    public DxBall() {
        started = false;
        paddle = new Paddle();
        ball = new Ball();
    }

    public void newGame() {
        started = true;
        setMeasurements();
        setInitialPosition();
    }

    private void setInitialPosition() {
        int x = Screen.getWidth(), y = Screen.getHeight();
        paddle.setInitialPosition(x /= 2, y -= paddle.getHeight());
        ball.setInitialPosition(x, y - paddle.getHeight() - ball.getRadius());
    }

    private void setMeasurements() {
        paddle.setDimension();
        ball.setRadius();
    }

    public void draw() {
        if (ball.fallen) {
            ball.fallen = false;
            ball.isOnAir = false;
            setInitialPosition();
            //ball.bounce(5,-5);
        }
        paddle.draw();
        ball.draw();
    }

    public boolean onTouchEvent(MotionEvent event) {
        Mouse.x = event.getX();
        Mouse.y = event.getY();

        switch (event.getAction()) {
            //TODO: Make paddle movement smoother.
            case MotionEvent.ACTION_DOWN:
                paddle.isMovable = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //paddle.move(x);
                break;
            case MotionEvent.ACTION_UP:
                paddle.isMovable = false;
                if (!ball.isOnAir) {
                    ball.isOnAir = true;
                    ball.bounce(5, -5);
                }
                break;
        }
        return true;
    }
}

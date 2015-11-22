package com.androidApps.ruman.dx_ball;

import android.view.MotionEvent;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class DxBall {
    boolean started;
    private Paddle paddle;
    private Ball ball;

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
        int x = Screen.width, y = Screen.height;
        paddle.setInitialPosition(x /= 2, y);
        ball.setInitialPosition(x, y - paddle.height - ball.radius);
    }

    private void setMeasurements() {
        paddle.setDimension();
        ball.setRadius();
    }

    public void draw() {
        paddle.draw();
        ball.draw();
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        //float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                paddle.move(x);
        }
        return true;
    }
}

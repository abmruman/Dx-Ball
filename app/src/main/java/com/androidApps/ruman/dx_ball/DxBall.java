package com.androidApps.ruman.dx_ball;

import android.view.MotionEvent;

/**
 *
 * @author A B M Ruman
 *
 **/
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
        }
        paddle.draw();
        ball.draw();
        if (ball.isOnAir && hadCollision())
            ball.bounce(paddle.getCollisionDirection(ball.getX()), -Math.abs(ball.getDy()));
    }

    private boolean hadCollision() {
        float left = paddle.getLeftSide() - ball.getRadius(),
                top = paddle.getTop() - ball.getRadius(),
                right = paddle.getRightSide() + ball.getRadius(),
                bottom = paddle.getBottom() - paddle.getHeight() / 2;

        return ball.getX() > left
                && ball.getX() < right
                && ball.getY() > top
                && ball.getY() < bottom;
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                paddle.isMovable = true;
                break;
            case MotionEvent.ACTION_MOVE:
                Mouse.dx = event.getX() - Mouse.x;
                //Mouse.dy = event.getY() - Mouse.y;
                //Mouse.y = event.getY();
                //paddle.move(x);
                break;
            case MotionEvent.ACTION_UP:
                paddle.isMovable = false;
                if (!ball.isOnAir) {
                    ball.bounce(3, -10);
                    ball.isOnAir = true;
                }
                break;
        }
        Mouse.x = event.getX();
        return true;
    }
}

package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;
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
        }
        paddle.draw();
        ball.draw();
        if (ball.isOnAir && hadCollision())
            ball.bounce(ball.getDx(), -Math.abs(ball.getDy()));
    }

    private boolean hadCollision() {
        int rad = 3;
        Paint paint = Screen.newPaint(Color.RED, Paint.Style.STROKE);

        float left = paddle.getLeftSide() - ball.getRadius(),
                top = paddle.getTop() - ball.getRadius(),
                right = paddle.getRightSide() + ball.getRadius(),
                bottom = paddle.getBottom() - paddle.getHeight() / 2;

        Screen.getCanvas().drawCircle(left, bottom, rad, paint);
        Screen.getCanvas().drawCircle(left, top, rad, paint);
        Screen.getCanvas().drawCircle(right, top, rad, paint);
        Screen.getCanvas().drawCircle(right, bottom, rad, paint);
        Screen.getCanvas().drawRect(left, top, right, bottom, paint);

        return ball.getX() > (left)
                && ball.getX() < (right)
                && ball.getY() > (top)
                && ball.getY() < (bottom);
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
                    ball.bounce(5, -5);
                    ball.isOnAir = true;
                }
                break;
        }
        Mouse.x = event.getX();
        return true;
    }
}

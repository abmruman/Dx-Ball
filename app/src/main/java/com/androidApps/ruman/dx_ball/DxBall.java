package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A B M Ruman
 *
 **/
public class DxBall {
    boolean started;
    int score;
    int life;

    Paddle paddle;
    Ball ball;
    List<Brick> bricks;

    public DxBall() {
        started = false;
        started = false;
        paddle = new Paddle();
        ball = new Ball();
    }

    public void newGame() {
        started = true;
        ball.isOnAir = false;
        life = 2;
        score = 0;
        bricks = new ArrayList<Brick>();

        for (int i = 0; i < 5; i++) {
            bricks.add(new Brick());
        }
        setMeasurements();
        setInitialPosition();
    }

    private void setInitialPosition() {
        int x = Screen.getWidth(), y = Screen.getHeight();
        paddle.setInitialPosition(x /= 2, y -= paddle.getHeight());
        ball.setInitialPosition(x, y - paddle.getHeight() - ball.getRadius());

        float m = 100, n = 100;
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (i == 0) {
                m = 500 / 1900f * Screen.getWidth();
                n = 275 / 1200f * Screen.getHeight();
            }
            brick.setInitialPosition(m, n);
            m = brick.getRight() + brick.getWidth() / 2 + Brick.space;
        }
    }

    private void setMeasurements() {
        paddle.setDimension();
        ball.setRadius();
        for (int i = 0; i < bricks.size(); i++) {
            bricks.get(i).setDimension();
        }
    }

    public void draw() {
        if (Brick.count == 0 || life == 0) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            newGame();
        }
        if (ball.fallen) {
            ball.fallen = false;
            ball.isOnAir = false;
            life--;
            setInitialPosition();
        }
        paddle.draw();
        ball.draw();
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (!brick.isBroke)
                brick.draw();
        }
        if (ball.isOnAir) {
            if (hadCollisionWithPaddle()) {
                ball.bounce(paddle.getCollisionDirection(ball.getX()), -Math.abs(ball.getDy()));
            }

            handleCollisionWithBricks();
        }
        Paint paint = Screen.newPaint(Color.WHITE, Paint.Style.STROKE);
        paint.setTextSize(50);

        Screen.getCanvas().drawText("Score: " + score, 50, 50, paint);
        Screen.getCanvas().drawText("Life : " + life, 50, 100, paint);
    }

    private boolean hadCollisionWithPaddle() {
        float left = paddle.getLeftSide() - ball.getRadius(),
                top = paddle.getTop() - ball.getRadius(),
                right = paddle.getRightSide() + ball.getRadius(),
                bottom = paddle.getBottom() - paddle.getHeight() / 2;

        return ball.getX() > left
                && ball.getX() < right
                && ball.getY() > top
                && ball.getY() < bottom;
    }

    private void handleCollisionWithBricks() {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (!brick.isBroke)
                brick.handleCollision(ball);
        }
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

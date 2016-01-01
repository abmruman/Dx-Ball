package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

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
    ArrayList<Brick> bricks;
    private int stage;
    private int maxStage;

    public DxBall() {
        started = false;
        started = false;
        maxStage = 2;
        paddle = new Paddle();
        ball = new Ball();
        bricks = new ArrayList<>();
    }

    public void newGame() {
        started = true;
        ball.isOnAir = false;
        life = 3;
        score = 0;
        stage = 1;
        Brick.resetCount();
        bricks.clear();
        setMeasurements();
        setInitialPosition();
    }

    private void setInitialPosition() {
        int x = Screen.getWidth(), y = Screen.getHeight();
        paddle.setInitialPosition(x /= 2, y -= paddle.getHeight());
        ball.setInitialPosition(x, y - paddle.getHeight() - ball.getRadius());

        float m = 100, n = 100;
        switch (stage) {
            case 1:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (i == 0) {
                        m = 500 / 1900f * Screen.getWidth() + (brick.getWidth() + Brick.getSpace()) * 2;
                        n = 275 / 1200f * Screen.getHeight();
                    }
                    if (i == 1) {
                        m -= (brick.getWidth() + Brick.getSpace()) * 2;
                        n += (brick.getHeight() + Brick.getSpace());
                    }
                    if (i == 4) {
                        m -= (brick.getWidth() + Brick.getSpace()) * 4;
                        n += brick.getHeight() + Brick.getSpace();

                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;
            case 2:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (i == 0) {
                        m = 500 / 1900f * Screen.getWidth();
                        n = 275 / 1200f * Screen.getHeight();
                    }
                    if (i == 5) {
                        n += brick.getHeight() + Brick.getSpace();
                        m = 500 / 1900f * Screen.getWidth() + brick.getWidth() + Brick.getSpace();
                    }
                    if (i == 8) {
                        n += brick.getHeight() + Brick.getSpace();
                        m = 500 / 1900f * Screen.getWidth() + (brick.getWidth() + Brick.getSpace()) * 2;
                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;
        }
    }

    private void setMeasurements() {
        paddle.setDimension();
        ball.setRadius();
        for (int i = 0; i < 9; i++) {
            bricks.add(new Brick());
        }
        for (Brick brick : bricks) {
            brick.setDimension();
        }
    }

    public void draw() {
        if (life == 0) {
            newGame();
        }
        if (Brick.getCount() == 0) {
            levelUp();
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

        Screen.getCanvas().drawText("Stage : " + stage, Screen.getWidth() - 220, 100, paint);
        Screen.getCanvas().drawText("Score: " + score, 50, 100, paint);
        Screen.getCanvas().drawText("Life : " + life, 50, 150, paint);
    }

    private void levelUp() {
        if (stage < maxStage)
            stage++;
        else {
            stage = 1;
        }
        ball.isOnAir = false;
        Brick.resetCount();
        bricks.clear();
        setMeasurements();
        setInitialPosition();
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

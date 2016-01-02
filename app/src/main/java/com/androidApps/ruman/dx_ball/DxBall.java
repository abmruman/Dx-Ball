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

    public DxBall() {
        started = false;
        started = false;
        paddle = new Paddle();
        ball = new Ball();
        Level.bricks = new ArrayList<>();
    }

    public void newGame() {
        started = true;
        ball.isOnAir = false;
        life = 3;
        score = 0;
        Level.stage = 1;
        Brick.resetCount();
        Level.bricks.clear();
        setMeasurements();
        setInitialPosition();
        Level.makeStage();
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
        if (life == 0) {
            newGame();
        } else if (Brick.getCount() == 0) {
            levelUp();
        } else if (ball.fallen) {
            ball.fallen = false;
            ball.isOnAir = false;
            life--;
            setInitialPosition();
        } else {
            paddle.draw();
            ball.draw();
            Level.draw();
            if (ball.isOnAir) {
                if (paddle.hadCollision(ball)) {
                    ball.bounce(paddle.getCollisionDirection(ball.getX()), -Math.abs(ball.getDy()));
                }

                handleCollisionWithBricks();
            }
            Paint paint = Screen.newPaint(Color.WHITE, Paint.Style.STROKE);
            paint.setTextSize(50);

            Screen.getCanvas().drawText("Stage : " + Level.stage, Screen.getWidth() - 220, 100, paint);
            Screen.getCanvas().drawText("Score: " + score, 50, 100, paint);
            Screen.getCanvas().drawText("Life : " + life, 50, 150, paint);
        }
    }

    private void levelUp() {
        Level.up();
        ball.isOnAir = false;
        setMeasurements();
        setInitialPosition();
        Level.makeStage();
    }

    private void handleCollisionWithBricks() {
        for (Brick brick : Level.bricks) {
            if (!brick.isBroken)
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

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
    boolean isStarted;
    int score;
    int life;
    Paddle paddle;
    Ball ball;

    public DxBall() {
        isStarted = false;
        isStarted = false;
        paddle = new Paddle();
        ball = new Ball();
        Level.bricks = new ArrayList<>();
    }

    public void newGame() {
        isStarted = true;
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
        ball.setInitialSpeed();
    }

    private void setMeasurements() {
        paddle.setDimension();
        ball.setRadius();
    }

    public void draw() {
        int bannerTextSize = (int) (250 / 1920f * Screen.getWidth());

        Paint paint = Screen.newPaint(Color.WHITE, Paint.Style.STROKE);
        paint.setTextSize(bannerTextSize);
        paint.setTextAlign(Paint.Align.CENTER);

        if (life == 0) {
            Screen.getCanvas().drawText("Game Over!", Screen.getWidth() / 2, Screen.getHeight() / 2, paint);
//            newGame();
        } else if (Brick.getCount() == 0) {
            Screen.getCanvas().drawText("Level Up!", Screen.getWidth() / 2, Screen.getHeight() / 2, paint);
//            levelUp();
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

            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.LEFT);

            //Screen.getCanvas().drawText("Level : " + Level.stage, 50, 150, paint);
            Screen.getCanvas().drawText("Score: " + score, 50, 100, paint);

            paint.setTextAlign(Paint.Align.RIGHT);
            Screen.getCanvas().drawText("Life : " + life, Screen.getWidth() - 50, 100, paint);
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
                if (life == 0 || Brick.getCount() == 0) {
                    Screen.isTappedOnText = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Mouse.dx = event.getX() - Mouse.x;
                //Mouse.dy = event.getY() - Mouse.y;
                //Mouse.y = event.getY();
                //paddle.move(x);
                break;
            case MotionEvent.ACTION_UP:
                paddle.isMovable = false;
                if (life == 0) {
                    if (Screen.isTappedOnText) {
                        newGame();
                        Screen.isTappedOnText = false;
                    }
                } else if (Brick.getCount() == 0) {
                    if (Screen.isTappedOnText) {
                        levelUp();
                        Screen.isTappedOnText = false;
                    }
                } else {
                    if (!ball.isOnAir) {
                        ball.bounce(ball.getDx(), ball.getDy());
                        ball.isOnAir = true;
                    }
                }

                break;
        }
        Mouse.x = event.getX();
        return true;
    }
}

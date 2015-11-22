package com.androidApps.ruman.dx_ball;

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
}

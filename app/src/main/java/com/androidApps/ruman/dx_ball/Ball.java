package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Ball {
    Paint paint;
    boolean isOnAir;
    boolean fallen;
    private float x, y, radiusRatio;
    private int radius, dx, dy;
    public Ball() {
        dx = dy = 0;
        isOnAir = false;
        fallen = false;
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        radiusRatio = 25 / 1200f;
    }

    public void draw() {
        calculateMove();
        Screen.getCanvas().drawCircle(x, y, radius, paint);
    }

    private void calculateMove() {
        if (!isOnAir && Game.dxBall.paddle.isMovable) {
            x = Game.dxBall.paddle.getX();
        }
        if (isOnAir) {
            x += dx;
            y += dy;
            if (Wall.hitLeft(x, y, radius) || Wall.hitRight(x, y, radius)) {
                dx = -dx;
            } else if (Wall.hitTop(x, y, radius)) {
                dy = -dy;
            } else if (Wall.hitDown(x, y, radius)) {
                dx = dy = 0;
                fallen = true;
                isOnAir = false;
            } else if (Game.dxBall.paddle.collisionSide(x, y, radius)) {
                dx = -dx;
                dy = -dy;
            } else if (Game.dxBall.paddle.collisionTop(x, y, radius)) {
                dy = (dy > 0) ? -dy : dy;
            }

        }

    }

    public void setRadius() {
        radius = (int) (radiusRatio * ((Screen.getWidth() < Screen.getHeight()) ? Screen.getWidth() : Screen.getHeight()));
    }

    public void setInitialPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void bounce(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getRadius() {
        return radius;
    }
}

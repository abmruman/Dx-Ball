package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 * @author A B M Ruman
 *
 **/
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
        radiusRatio = 30 / 1200f;
    }

    public void draw() {
        calculateMove();
        Screen.getCanvas().drawCircle(x, y, radius, paint);
    }

    private void calculateMove() {
        if (!isOnAir) {
            if (Game.dxBall.paddle.isMovable) {
                x = Game.dxBall.paddle.getX();
            }
        } else {
            x += dx;
            y += dy;
            if (Wall.hitLeft(x, y, radius)) {
                dx = Math.abs(dx);
            } else if (Wall.hitRight(x, y, radius)) {
                dx = -Math.abs(dx);
            } else if (Wall.hitTop(x, y, radius)) {
                dy = Math.abs(dy);
            } else if (Wall.hitBottom(x, y, radius)) {
                dx = dy = 0;
                fallen = true;
                isOnAir = false;
            }
        }

    }

    public void setRadius() {
        radius = (int) (radiusRatio * ((Screen.getWidth() < Screen.getHeight()) ? Screen.getWidth() : Screen.getHeight()));
    }

    public void setInitialSpeed() {
        dx = (int) (4 / 1920f * Screen.getWidth());
        dy = (int) (-10 / 1280f * Screen.getHeight());
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}

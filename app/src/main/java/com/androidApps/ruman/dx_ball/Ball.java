package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Ball {
    Paint paint;
    float x, y, radiusRatio;
    int radius, dx, dy;
    boolean isOnAir;
    boolean fallen;
    public Ball() {
        dx = dy = 0;
        isOnAir = false;
        fallen = false;
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        radiusRatio = 25 / 1200f;
    }

    public void draw() {
        calculateMove();
        Screen.canvas.drawCircle(x, y, radius, paint);
    }

    private void calculateMove() {
        if (isOnAir) {
            x += dx;
            y += dy;
            if (Wall.hitLeft(x, y, radius) || Wall.hitRight(x, y, radius)) {
                dx = -dx;
            } else if (Wall.hitTop(x, y, radius) || Game.dxBall.paddle.collision(x, y, radius)) {
                dy = -dy;
            } else if (Wall.hitDown(x, y, radius)) {
                fallen = true;
                isOnAir = false;
            }

        }

    }

    public void setRadius() {
        radius = (int) (radiusRatio * ((Screen.width < Screen.height) ? Screen.width : Screen.height));
    }

    public void setInitialPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void bounce(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

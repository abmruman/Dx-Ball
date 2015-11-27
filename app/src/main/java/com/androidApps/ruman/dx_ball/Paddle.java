package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Paddle {
    public boolean isMovable;
    Paint paint;
    private float x, y, heightRatio, widthRatio, left, top, right, bottom;
    private int height, width;

    public Paddle() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        heightRatio = 400 / 1600f;
        widthRatio = 50 / 1200f;
    }

    public void draw() {
        calculateMove();
        Screen.getCanvas().drawRect(left, top, right, bottom, paint);
    }

    private void calculateMove() {
        if (isMovable) {
            float movement = width / 2 + Mouse.dx;
            if (Mouse.dx > 0) {
                if (Wall.hitRight(this.x, this.y, movement)) {
                    setX(this.x + (Wall.getRight() - this.right));
                } else {
                    setX(this.x + Mouse.dx);
                }
            } else if (Mouse.dx < 0) {
                if (Wall.hitLeft(this.x, this.y, movement)) {
                    setX(this.x + (Wall.getLeft() - this.left));
                } else {
                    setX(this.x + Mouse.dx);
                }
            }
        }
    }

    public void setDimension() {
        height = (int) (widthRatio * Screen.getHeight());
        width = (int) (heightRatio * Screen.getWidth());
    }

    public void setInitialPosition(int x, int y) {
        /** y is set before x,
         * because calculateCorners() in setX(x),
         * works with value of y.**/
        this.y = y;
        setX(x);
    }

    public boolean collisionTop(float x, float y, int radius) {
        return y < top
                && y + radius >= top
                && x >= left
                && x <= right;
    }

    public boolean collisionSide(float x, float y, int radius) {
        return ((right <= x && right >= x - radius)
                || (left >= x && left <= x + radius)
        )
                && top <= y + radius && bottom > y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        calculateCorners();
    }

    private void calculateCorners() {
        left = x - width / 2;
        if (left < 0) {
            left = 0;
            x = width / 2;
        }
        top = y - height;
        right = left + width;
        bottom = top + height;
    }

    public int getHeight() {
        return height;
    }
}

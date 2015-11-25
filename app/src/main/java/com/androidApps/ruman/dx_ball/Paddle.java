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
            if (this.x > Mouse.x && !Wall.hitLeft(this.x, this.y, width / 2 - 5))
                setX(this.x - 10);
            else if (this.x < Mouse.x && !Wall.hitRight(this.x, this.y, width / 2 + 5))
                setX(this.x + 10);
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

    public boolean collisionTop(float x, float y, int size) {
        return right >= x && left <= x && top <= y + size && bottom >= y;
    }

    public boolean collisionSide(float x, float y, int size) {
        return ((right <= x && right >= x - size)
                || (left >= x && left <= x + size)
        )
                && top <= y + size && bottom > y;
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
        top = y - height;
        right = left + width;
        bottom = top + height;
    }

    public int getHeight() {
        return height;
    }
}

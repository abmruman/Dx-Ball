package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Paddle {
    public boolean isMovable;
    Paint paint;
    private float x, y, heightRatio, widthRatio, radiusRatio, left, top, right, bottom, leftSide, rightSide;
    private int height, width, radius;

    public Paddle() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        heightRatio = 400 / 1600f;
        widthRatio = 50 / 1200f;
        radiusRatio = 25 / 1200f;
    }

    public void draw() {
        calculateMove();
        Screen.getCanvas().drawCircle(left, top + radius, radius, paint);
        Screen.getCanvas().drawCircle(right, top + radius, radius, paint);
        Screen.getCanvas().drawRect(left, top, right, bottom, paint);
    }

    private void calculateMove() {
        if (isMovable) {
            float movement = width / 2 + radius + Mouse.dx;
            if (Mouse.dx > 0) {
                if (Wall.hitRight(this.x, this.y, movement)) {
                    setX(this.x + (Wall.getRight() - this.right - radius));
                } else {
                    setX(this.x + Mouse.dx);
                }
            } else if (Mouse.dx < 0) {
                if (Wall.hitLeft(this.x, this.y, movement)) {
                    setX(this.x + (Wall.getLeft() - this.left + radius));
                } else {
                    setX(this.x + Mouse.dx);
                }
            }
        }
    }

    public void setDimension() {
        height = (int) (widthRatio * Screen.getHeight());
        width = (int) (heightRatio * Screen.getWidth());
        radius = (int) (radiusRatio * Screen.getHeight());
    }

    public void setInitialPosition(int x, int y) {
        /** y is set before x,
         * because calculateCorners() in setX(x),
         * works with value of y.**/
        this.y = y;
        setX(x);
    }

    private void calculateCorners() {
        left = x - width / 2;
        leftSide = left - radius;
        if (leftSide < 0) {
            left = radius;
            x = width / 2 + radius;
        }
        top = y - height;
        right = left + width;
        bottom = y;
        rightSide = right + radius;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        calculateCorners();
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public float getLeftSide() {
        return leftSide;
    }

    public float getRightSide() {
        return rightSide;
    }
}

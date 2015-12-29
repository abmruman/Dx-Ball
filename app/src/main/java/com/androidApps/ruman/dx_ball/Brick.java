package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author A B M Ruman
 **/
public class Brick {
    public static int count = 0;
    Paint paint;
    private float x, y, heightRatio, widthRatio, left, top, right, bottom;
    private int height, width;

    public Brick() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        heightRatio = 100 / 1600f;
        widthRatio = 50 / 1200f;
        Brick.count++;
    }

    public Brick(float x, float y) {
        this();
        setInitialPosition(x, y);
    }

    public void draw() {
        //calculateMove();
        Screen.getCanvas().drawRect(left, top, right, bottom, paint);
    }

    /*private void calculateMove() {
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
    }*/

    public void setDimension() {
        height = (int) (widthRatio * Screen.getHeight());
        width = (int) (heightRatio * Screen.getWidth());
    }

    public void setInitialPosition(float x, float y) {
        this.y = y;
        this.x = x;
        calculateCorners();
    }

    private void calculateCorners() {
        if (x < width / 2) {
            left = 0;
            x = width / 2;
        } else {
            left = x - width / 2;
        }

        if (x > Screen.getWidth() - width) {
            right = Screen.getWidth();
            x = Screen.getWidth() - width / 2;
            left = x - width / 2;
        } else {
            right = left + width;
        }
        top = y - height / 2;
        bottom = y + height / 2;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public int getCollisionDirection(float x) {
        float dist = Math.abs(this.x - x) / 15;
        dist = (float) Math.ceil(dist);
        return (int) ((this.x > x) ? -dist : dist);
    }

}

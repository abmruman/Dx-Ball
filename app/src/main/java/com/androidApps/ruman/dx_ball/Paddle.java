package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Paddle {
    Paint paint;
    float x, y, heightRatio, widthRatio, left, top, right, bottom;
    int height, width;

    public Paddle() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        heightRatio = 400 / 1600f;
        widthRatio = 50 / 1200f;
    }

    public void draw() {
        Screen.canvas.drawRect(left, top, right, bottom, paint);
    }

    public void setDimension() {
        height = (int) (widthRatio * Screen.height);
        width = (int) (heightRatio * Screen.width);
    }

    public void setInitialPosition(int x, int y) {
        /** y is set before x,
         * because calculateCorners() in setX(x),
         * works with value of y.**/
        this.y = y;
        setX(x);
    }

    public void move(float x) {
        if (this.x > x && !Wall.hitLeft(this.x, this.y, width / 2 - 5))
            setX(this.x - 10);
        else if (this.x < x && !Wall.hitRight(this.x, this.y, width / 2 + 5))
            setX(this.x + 10);
    }

    public boolean collisionTop(float x, float y, int size) {
        return right >= x && left <= x && top <= y + size;
    }

    public boolean collisionSide(float x, float y, int size) {
        return ((right <= x && right >= x + size)
                || (left >= x && left <= x + size)
        )
                && top <= y + size;
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

}

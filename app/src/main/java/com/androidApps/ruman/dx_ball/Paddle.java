package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Paddle {
    Paint paint;
    float x, y, heightRatio, widthRatio;
    int height, width;

    public Paddle() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        heightRatio = 400 / 1600f;
        widthRatio = 50 / 1200f;
    }

    public void draw() {
        float x = this.x, y = this.y;
        Screen.canvas.drawRect(x -= width / 2, y -= height, x + width, y + height, paint);
    }

    public void setDimension() {
        height = (int) (widthRatio * Screen.height);
        width = (int) (heightRatio * Screen.width);
    }

    public void setInitialPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(float x) {
        if (this.x > x)
            this.x -= 10;
        else
            this.x += 10;
    }

    public boolean collision(float x, float y, int size) {
        return this.x + width / 2 >= x && this.x - width / 2 <= x && this.y - height <= y + size;
    }

}

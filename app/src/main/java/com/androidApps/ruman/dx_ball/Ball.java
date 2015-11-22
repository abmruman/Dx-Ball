package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Ball {
    Paint paint;
    float x, y, radiusRatio;
    int radius;

    public Ball() {
        paint = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        radiusRatio = 25 / 1200f;
    }

    public void draw() {
        Screen.canvas.drawCircle(x, y, radius, paint);
    }

    public void setRadius() {
        radius = (int) (radiusRatio * ((Screen.width < Screen.height) ? Screen.width : Screen.height));
    }

    public void setInitialPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

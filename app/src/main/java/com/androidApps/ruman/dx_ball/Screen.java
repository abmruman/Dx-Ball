package com.androidApps.ruman.dx_ball;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Screen {
    private static Canvas canvas;
    private static int height;
    private static int width;

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Screen.canvas = canvas;
        Screen.height = canvas.getHeight();
        Screen.width = canvas.getWidth();
    }

    public static Paint newPaint(int color, Paint.Style style) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        return paint;
    }

}

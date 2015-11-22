package com.androidApps.ruman.dx_ball;

import android.graphics.Canvas;

/**
 * Created by A B M Ruman on 22/11/2015.
 */
public class Screen {
    public static Canvas canvas;
    public static int height;
    public static int width;

    public static void setCanvas(Canvas canvas) {
        Screen.canvas = canvas;
        Screen.height = canvas.getHeight();
        Screen.width = canvas.getWidth();
    }
}

package com.androidApps.ruman.dx_ball;

/**
 * Created by A B M Ruman on 23/11/2015.
 */
public class Wall {
    static boolean hitLeft(float x, float y, int size) {
        return x - size <= 0;
    }

    static boolean hitRight(float x, float y, int size) {
        return x + size >= Screen.width;
    }

    static boolean hitTop(float x, float y, int size) {
        return y - size <= 0;
    }

    public static boolean hitDown(float x, float y, int radius) {
        return y + radius >= Screen.height;
    }
}

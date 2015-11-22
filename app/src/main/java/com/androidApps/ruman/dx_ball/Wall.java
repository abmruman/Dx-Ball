package com.androidApps.ruman.dx_ball;

/**
 * Created by A B M Ruman on 23/11/2015.
 */
public class Wall {
    static boolean left(float x, float y, int size) {
        return x - size <= 0;
    }

    static boolean right(float x, float y, int size) {
        return x + size >= Screen.width;
    }

    static boolean top(float x, float y, int size) {
        return y - size <= 0;
    }
}

package com.androidApps.ruman.dx_ball;

/**
 *
 * @author A B M Ruman
 *
 **/
public class Wall {
    private static int left, top, right, bottom;

    static boolean hitLeft(float x, float y, float size) {
        return x - size <= left;
    }

    static boolean hitRight(float x, float y, float size) {
        return x + size >= right;
    }

    static boolean hitTop(float x, float y, int size) {
        return y - size <= top;
    }

    public static boolean hitBottom(float x, float y, int size) {
        return y + size >= bottom;
    }

    public static void setWalls() {
        Wall.left = 0;
        Wall.top = 0;
        Wall.right = Screen.getWidth();
        Wall.bottom = Screen.getHeight();
    }

    public static int getBottom() {
        return bottom;
    }

    public static int getRight() {
        return right;
    }

    public static int getLeft() {
        return left;
    }

    public static int getTop() {
        return top;
    }

}

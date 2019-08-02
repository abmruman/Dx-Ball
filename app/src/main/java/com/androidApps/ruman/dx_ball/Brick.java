package com.androidApps.ruman.dx_ball;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author A B M Ruman
 **/
public class Brick {
    private static int count = 0;
    private static int space = 1;
    private static int shade = 12;
    Paint paint, paint2;
    boolean isBroken;
    private float x, y, heightRatio, widthRatio, left, top, right, bottom;
    private int height, width;

    public Brick() {
        paint = Screen.newPaint(Color.GRAY, Paint.Style.FILL);
        paint2 = Screen.newPaint(Color.WHITE, Paint.Style.FILL);
        widthRatio = 240 / 1920f;
        heightRatio = 100 / 1200f;

        Brick.count++;
        isBroken = false;
    }

    public Brick(float x, float y) {
        this();
        setInitialPosition(x, y);
    }

    public static int getCount() {
        return count;
    }

    public static int getShade() {
        return shade;
    }

    public static int getSpace() {
        return space;
    }

    public static void resetCount() {
        count = 0;
    }

    public void draw() {
        //calculateMove();
        Screen.getCanvas().drawRect(left, top, right, bottom, paint);
        Screen.getCanvas().drawRect(left + shade, top + shade, right - shade, bottom - shade, paint2);

        Screen.getCanvas().drawLine(left, top, left + shade, top + shade, paint2);
        Screen.getCanvas().drawLine(right, top, right - shade, top + shade, paint2);
        Screen.getCanvas().drawLine(left, bottom, left + shade, bottom - shade, paint2);
        Screen.getCanvas().drawLine(right, bottom, right - shade, bottom - shade, paint2);
    }

    public void setDimension() {
        height = (int) (heightRatio * Screen.getHeight());
        width = (int) (widthRatio * Screen.getWidth());

        if (height > (int) (width * 4 / 5f)) {
            height = (int) (width * 4 / 5f);
        }
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

    public void handleCollision(Ball ball) {
        float left = getLeft() - ball.getRadius(),
                top = getTop() - ball.getRadius(),
                right = getRight() + ball.getRadius(),
                bottom = getBottom() + ball.getRadius();

//        Screen.getCanvas().drawRect(left, getTop(), getLeft(), getBottom(), Screen.newPaint(Color.RED, Paint.Style.STROKE));
//        Screen.getCanvas().drawRect(getLeft(), top, getRight(), getTop(), Screen.newPaint(Color.RED, Paint.Style.STROKE));
//        Screen.getCanvas().drawRect(getRight(), getTop(), right, getBottom(), Screen.newPaint(Color.RED, Paint.Style.STROKE));
//        Screen.getCanvas().drawRect(getLeft(), getBottom(), getRight(), bottom, Screen.newPaint(Color.RED, Paint.Style.STROKE));

        if (ball.getX() > left
                && ball.getY() > getTop() - ball.getRadius() / 2
                && ball.getX() < getLeft()
                && ball.getY() < getBottom() + ball.getRadius() / 2) {

            ball.bounce(-Math.abs(ball.getDx()), ball.getDy());
            destroy();
            reward();
        } else if (ball.getX() > getLeft()
                && ball.getY() > top
                && ball.getX() < getRight()
                && ball.getY() < getTop()) {

            ball.bounce(ball.getDx(), -Math.abs(ball.getDy()));
            destroy();
            reward();
        } else if (ball.getX() > getRight()
                && ball.getY() > getTop() - ball.getRadius() / 2
                && ball.getX() < right
                && ball.getY() < getBottom() + ball.getRadius() / 2) {

            ball.bounce(Math.abs(ball.getDx()), ball.getDy());
            destroy();
            reward();
        } else if (ball.getX() > getLeft()
                && ball.getY() > getBottom()
                && ball.getX() < getRight()
                && ball.getY() < bottom) {

            ball.bounce(ball.getDx(), Math.abs(ball.getDy()));
            destroy();
            reward();
        }
    }

    private void reward() {
        Game.dxBall.score += 5;
    }

    public void destroy() {
        isBroken = true;
        if (Brick.count > 0)
            Brick.count--;
    }


    public float getHeightWithSpace() {
        return height + Brick.space;
    }

    public float getWidthWithSpace() {
        return width + Brick.space;
    }
}

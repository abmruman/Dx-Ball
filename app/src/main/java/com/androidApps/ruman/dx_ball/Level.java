package com.androidApps.ruman.dx_ball;

import java.util.ArrayList;

/**
 * @author A B M Ruman
 **/
public class Level {
    public static final int maxStage = 2;
    public static ArrayList<Brick> bricks;
    public static int stage;

    public static void setInitialPosition() {
        Brick.resetCount();
        Level.bricks.clear();
        for (int i = 0; i < 9; i++) {
            bricks.add(new Brick());
        }
        for (Brick brick : bricks) {
            brick.setDimension();
        }

        float m = 100, n = 100;
        switch (stage) {
            case 1:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (i == 0) {
                        m = 500 / 1900f * Screen.getWidth() + (brick.getWidth() + Brick.getSpace()) * 2;
                        n = 275 / 1200f * Screen.getHeight();
                    }
                    if (i == 1) {
                        m -= (brick.getWidth() + Brick.getSpace()) * 2;
                        n += (brick.getHeight() + Brick.getSpace());
                    }
                    if (i == 4) {
                        m -= (brick.getWidth() + Brick.getSpace()) * 4;
                        n += brick.getHeight() + Brick.getSpace();

                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;
            case 2:
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (i == 0) {
                        m = 500 / 1900f * Screen.getWidth();
                        n = 275 / 1200f * Screen.getHeight();
                    }
                    if (i == 5) {
                        n += brick.getHeight() + Brick.getSpace();
                        m = 500 / 1900f * Screen.getWidth() + brick.getWidth() + Brick.getSpace();
                    }
                    if (i == 8) {
                        n += brick.getHeight() + Brick.getSpace();
                        m = 500 / 1900f * Screen.getWidth() + (brick.getWidth() + Brick.getSpace()) * 2;
                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;
        }
    }

    public static void draw() {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            if (!brick.isBroken)
                brick.draw();
        }
    }

    public static void up() {
        if (stage < maxStage)
            stage++;
        else {
            stage = 1;
        }
        Brick.resetCount();
        bricks.clear();
    }
}

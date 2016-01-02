package com.androidApps.ruman.dx_ball;

import java.util.ArrayList;

/**
 *
 * @author A B M Ruman
 *
 **/
public class Level {
    public static final int maxStage = 6;
    public static ArrayList<Brick> bricks;
    public static int stage;

    public static void makeStage() {
        float leftMargin = 500 / 1900f * Screen.getWidth();
        float topMargin = 275 / 1200f * Screen.getHeight();
        float m = 100, n = 100;

        switch (stage) {
            case 1:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 9; i++) {
                        bricks.add(new Brick());
                    }
                }

                for (Brick brick : bricks) {
                    brick.setDimension();
                }

                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);

                    if (i == 0) {
                        m = leftMargin;
                        n = topMargin;
                    }
                    if (i == 5) {
                        n += brick.getHeightWithSpace();
                        m = leftMargin + brick.getWidthWithSpace();
                    }
                    if (i == 8) {
                        n += brick.getHeightWithSpace();
                        m = leftMargin + (brick.getWidthWithSpace()) * 2;
                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;

            case 2:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 9; i++) {
                        bricks.add(new Brick());
                    }
                }
                for (Brick brick : bricks) {
                    brick.setDimension();
                }

                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = bricks.get(i);
                    if (i == 0) {
                        m = leftMargin + (brick.getWidthWithSpace()) * 2;
                        n = topMargin;
                    }
                    if (i == 1) {
                        m -= (brick.getWidthWithSpace()) * 2;
                        n += (brick.getHeightWithSpace());
                    }
                    if (i == 4) {
                        m -= (brick.getWidthWithSpace()) * 4;
                        n += brick.getHeightWithSpace();
                    }
                    brick.setInitialPosition(m, n);
                    m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                }
                break;

            case 3:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 8; i++) {
                        bricks.add(new Brick());
                    }
                }
                for (Brick brick : bricks) {
                    brick.setDimension();
                }
                for (int i = 0, k = 0; i < bricks.size(); k++) {
                    Brick brick = bricks.get(i);
                    for (int j = 0; j < 5; j++) {
                        if (i == 0) {
                            m = leftMargin;
                            n = topMargin;
                        }

                        if (k % 2 == 0) {
                            if (j % 2 == 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        } else {
                            if (j % 2 != 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        }
                        m += brick.getWidthWithSpace();
                    }
                    n += brick.getHeightWithSpace();
                    m = leftMargin;
                }
                break;

            case 4:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 10; i++) {
                        bricks.add(new Brick());
                    }
                }
                for (Brick brick : bricks) {
                    brick.setDimension();
                }
                for (int i = 0, k = 0; i < bricks.size(); k++) {
                    Brick brick = bricks.get(i);
                    for (int j = 0; j < 5; j++) {
                        if (i == 0) {
                            m = leftMargin + brick.getWidthWithSpace();
                            n = topMargin;
                        }

                        if (k % 2 == 0) {
                            if (j % 2 != 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        } else {
                            if (j % 2 == 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        }
                        //m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                        m += brick.getWidthWithSpace();
                    }
                    n += brick.getHeightWithSpace();
                    m = leftMargin;
                }
                break;

            case 5:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 7; i++) {
                        bricks.add(new Brick());
                    }
                }
                for (Brick brick : bricks) {
                    brick.setDimension();
                }
                for (int i = 0, k = 0; i < bricks.size(); k++) {
                    Brick brick = bricks.get(i);
                    for (int j = 0; j < 5; j++) {
                        if (i == 0) {
                            m = leftMargin + brick.getWidthWithSpace();
                            n = topMargin;
                        }
                        if (k % 2 == 0) {
                            if (j % 2 != 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        } else {
                            if (j % 2 == 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        }
                        //m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                        m += brick.getWidthWithSpace();
                    }
                    n += brick.getHeightWithSpace();
                    m = leftMargin;
                }
                break;

            case 6:

                if (bricks.isEmpty()) {
                    for (int i = 0; i < 13; i++) {
                        bricks.add(new Brick());
                    }
                }
                for (Brick brick : bricks) {
                    brick.setDimension();
                }
                for (int i = 0, k = 0; i < 7; k++) {
                    Brick brick = bricks.get(i);
                    for (int j = 0; j < 5; j++) {
                        if (i == 0) {
                            m = leftMargin + brick.getWidthWithSpace();
                            n = topMargin;
                        }
                        if (k % 2 == 0) {
                            if (j % 2 != 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        } else {
                            if (j % 2 == 0) {
                                brick.setInitialPosition(m, n);
                                ++i;
                                if (i == bricks.size())
                                    break;
                                brick = bricks.get(i);
                            }
                        }
                        //m = brick.getRight() + brick.getWidth() / 2 + Brick.getSpace();
                        m += brick.getWidthWithSpace();
                    }
                    n += brick.getHeightWithSpace();
                    m = leftMargin;
                }
                int i = 7;
                Brick brick = bricks.get(i++);
                m += (brick.getWidthWithSpace()) * 2;
                n += brick.getHeightWithSpace();
                brick.setInitialPosition(m, n);

                brick = bricks.get(i++);
                m = leftMargin;
                n += brick.getHeightWithSpace();
                brick.setInitialPosition(m, n);

                brick = bricks.get(i++);
                m += (brick.getWidthWithSpace()) * 4;
                brick.setInitialPosition(m, n);

                m = leftMargin;
                n += brick.getHeightWithSpace();

                for (; i < 13; i++) {
                    brick = bricks.get(i);
                    m += brick.getWidthWithSpace();
                    brick.setInitialPosition(m, n);
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

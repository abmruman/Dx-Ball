package com.androidApps.ruman.dx_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by A B M Ruman on 18/11/2015.
 */
public class GameView extends View{
    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Screen.setCanvas(canvas);
        if (!Game.dxBall.started) {
            Game.dxBall.started = true;
            Game.dxBall.newGame();
        }
        canvas.drawRGB(0, 0, 0);
        Game.dxBall.draw();
    }


}

package com.androidApps.ruman.dx_ball;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by A B M Ruman on 18/11/2015.
 */
public class GameView extends View{
    private Activity activity;
    public GameView(Context context) {
        super(context);
        this.activity = (Activity) context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Screen.setCanvas(canvas);
        if (!Game.dxBall.started) {
            Game.dxBall.started = true;
            Game.dxBall.newGame();
            if (Screen.getHeight() > Screen.getWidth())
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            else
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        canvas.drawRGB(0, 0, 0);
        Game.dxBall.draw();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return Game.dxBall.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            setFullScreen();
        }
    }

    public void setFullScreen() {
        this.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}

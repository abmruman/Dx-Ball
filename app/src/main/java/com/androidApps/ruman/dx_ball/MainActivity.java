package com.androidApps.ruman.dx_ball;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.dxBall = new DxBall();
        Game.gameView = new GameView(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Game.gameView.setFullScreen();
        AddListeners();
        setContentView(Game.gameView);
    }

    private void AddListeners() {
        Game.gameView.setLongClickable(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


}

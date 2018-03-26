package pucaberta.pucminas.com.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(this::checkIn, DELAY);
    }

    private void checkIn(){
        finish();
        openActivityNewTask(LoginActivity.class);

    }
}

package pucaberta.pucminas.com.app;

import android.app.Application;

import pucaberta.pucminas.com.helper.PreferencesManager;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class PucApp extends Application {

    private static PucApp instance;

    public static PreferencesManager prefs;

    public static PucApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initPrefs();
    }

    private void initPrefs() {
        PreferencesManager.initializeInstance(this);
        prefs = PreferencesManager.getInstance();
    }
}

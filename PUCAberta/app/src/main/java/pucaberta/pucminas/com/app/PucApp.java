package pucaberta.pucminas.com.app;

import android.app.Application;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class PucApp extends Application {

    private static PucApp instance;

    public static PucApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}

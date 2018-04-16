package pucaberta.pucminas.com.viewmodel;

import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.helper.PreferencesManager;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.ui.activity.LoginActivity;
import pucaberta.pucminas.com.ui.activity.TwitterActivity;

/**
 * Created by lucas on 15/04/2018.
 * Update at 15/04/2018
 */

public class MenuViewModel extends BaseViewModel {
    public MenuViewModel(CallbackBasicViewModel callback) {
        super(callback);
    }

    public void logOut(){
        PreferencesManager.getInstance().clear();
        openActivityNewTask(LoginActivity.class);
    }

    public void twitter(){
        openActivity(TwitterActivity.class);
    }
}

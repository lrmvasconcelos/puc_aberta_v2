package pucaberta.pucminas.com.viewmodel;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.ui.activity.RegisterActivity;

/**
 * Created by lucas on 26/03/2018.
 * Update at 26/03/2018
 */

public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(CallbackBasicViewModel callback) {
        super(callback);
        titleTextToolbar.set(PucApp.getInstance().getString(R.string.login));
    }

    public void callRegister(){
        openActivity(RegisterActivity.class);
    }
}

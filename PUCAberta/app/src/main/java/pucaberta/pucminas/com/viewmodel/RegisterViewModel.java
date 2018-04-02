package pucaberta.pucminas.com.viewmodel;

import android.os.Bundle;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.ui.activity.WebViewActivity;

import static pucaberta.pucminas.com.app.Constants.API.Register.ESCOLA;
import static pucaberta.pucminas.com.app.Constants.API.Register.INDIVIDUAL;
import static pucaberta.pucminas.com.app.Constants.API.Register.REGISTER;

/**
 * Created by lucas on 27/03/2018.
 * Update at 27/03/2018
 */

public class RegisterViewModel extends BaseViewModel {



    public RegisterViewModel(CallbackBasicViewModel callback) {
        super(callback);
        titleTextToolbar.set(PucApp.getInstance().getString(R.string.registrar));
    }


    public void individual(){
        Bundle bundle = new Bundle();
        bundle.putCharSequence(REGISTER, INDIVIDUAL);
        openActivity(WebViewActivity.class, bundle);
    }

    public void escola(){
        Bundle bundle = new Bundle();
        bundle.putCharSequence(REGISTER, ESCOLA);
        openActivity(WebViewActivity.class, bundle);
    }
}

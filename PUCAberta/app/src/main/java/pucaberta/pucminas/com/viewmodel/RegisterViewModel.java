package pucaberta.pucminas.com.viewmodel;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;

/**
 * Created by lucas on 27/03/2018.
 * Update at 27/03/2018
 */

public class RegisterViewModel extends BaseViewModel {
    public RegisterViewModel(CallbackBasicViewModel callback) {
        super(callback);
        titleTextToolbar.set(PucApp.getInstance().getString(R.string.registrar));
    }
}

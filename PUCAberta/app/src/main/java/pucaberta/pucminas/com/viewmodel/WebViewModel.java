package pucaberta.pucminas.com.viewmodel;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;

/**
 * Created by lucas on 02/04/2018.
 * Update at 02/04/2018
 */

public class WebViewModel extends BaseViewModel {
    public WebViewModel(CallbackBasicViewModel callback) {
        super(callback);
        titleTextToolbar.set(PucApp.getInstance().getString(R.string.registrar));
    }
}

package pucaberta.pucminas.com.viewmodel;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.PucApp;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;

/**
 * Created by lucas on 15/04/2018.
 * Update at 15/04/2018
 */

public class TwitterViewModel extends BaseViewModel {
    public TwitterViewModel(CallbackBasicViewModel callback) {
        super(callback);
        titleTextToolbar.set(PucApp.getInstance().getString(R.string.twitter));
    }
}

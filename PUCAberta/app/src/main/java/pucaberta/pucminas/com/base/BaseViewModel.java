package pucaberta.pucminas.com.base;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;
import pucaberta.pucminas.com.interfaces.ViewModel;


/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */


public class BaseViewModel implements ViewModel {


    public final ObservableField<String> titleTextToolbar = new ObservableField<>("");
    public final ObservableBoolean searchType = new ObservableBoolean();
    protected CallbackBasicViewModel callback;

    public BaseViewModel(CallbackBasicViewModel callback) {
        this.callback = callback;
    }

    protected void showProgress() {
        if (callback != null) callback.showDialogProgress();
    }

    protected void showProgress(String text) {
        if (callback != null) callback.showDialogProgress(text);
    }

    protected void showProgress(int resString) {
        if (callback != null) callback.showDialogProgress(resString);
    }

    protected void hideProgress() {
        if (callback != null) callback.hideDialogProgress();
    }

    protected void showError(Throwable t, MaterialDialog.SingleButtonCallback callback2) {
        hideProgress();
        if (callback != null) callback.showError(t, callback2);
    }

    protected void openActivity(Class<?> open) {
        if (callback != null) callback.openActivity(open);
    }

    public void openActivity(Class<?> openActivity, Bundle bundle) {
        if (callback != null) callback.openActivity(openActivity, bundle);
    }

    protected void openActivityNewTask(Class<?> open) {
        if (callback != null) callback.openActivityNewTask(open);
    }

    protected void finish(int resultCode) {
        if (callback != null) callback.finish(resultCode);
    }

    protected void finish(int resultCode, Bundle bundle) {
        if (callback != null) callback.finish(resultCode, bundle);
    }

    protected void  showSimpleDialog(String title, String text) {
        if(callback != null) callback.showSimpleDialog(title, text);
    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {

    }
}

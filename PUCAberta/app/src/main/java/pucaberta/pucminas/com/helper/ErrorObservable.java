package pucaberta.pucminas.com.helper;

import android.databinding.BaseObservable;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class ErrorObservable extends BaseObservable {
    private String error;

    public ErrorObservable() {
    }

    public ErrorObservable(String errorMessage) {
        this.error = errorMessage;
    }

    public void set(String error) {
        this.error = error;
        notifyChange();
    }

    public String get() {
        return error;
    }

    public void clear() {
        this.error = null;
        notifyChange();
    }

    public boolean hasError() {
        return error != null;
    }
}

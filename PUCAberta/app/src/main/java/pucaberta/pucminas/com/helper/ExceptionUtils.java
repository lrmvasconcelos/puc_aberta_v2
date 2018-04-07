package pucaberta.pucminas.com.helper;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonSyntaxException;

import pucaberta.pucminas.com.R;
import retrofit2.HttpException;


/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class ExceptionUtils {
    private Context mContext;
    private Throwable mThrowable;
    private MaterialDialog.SingleButtonCallback callback;

    public ExceptionUtils(Context mContext, Throwable mThrowable, MaterialDialog.SingleButtonCallback callback) {
        this.mContext = mContext;
        this.mThrowable = mThrowable;
        this.callback = callback;
    }

    private void showDialogError(String title, String content) {
        try {
            new MaterialDialog.Builder(mContext)
                    .title(title)
                    .content(content)
                    .cancelable(false)
                    .positiveText(R.string.ok)
                    .onPositive(callback)
                    .show();
        } catch (Exception ex) {

        }
    }

    private void getMensagemThrowable() {
        String msgError = mContext.getString(R.string.connection_failure);
        String titleError = "Oops!";

        if (mThrowable instanceof HttpException) {
            if(((HttpException) mThrowable).code() == 404) {
                msgError = mContext.getString(R.string.user_not_found);
            }
        }else if (mThrowable instanceof JsonSyntaxException){
            msgError = mContext.getString(R.string.user_not_found);
        }
// else if (mThrowable instanceof UserNotFoundException) {
//            titleError = mContext.getString(R.string.connection_failure);
//            msgError = ((UserNotFoundException) mThrowable).getErrorMessage();
//        } else if (mThrowable instanceof NotAuthorizedException) {
//            titleError = mContext.getString(R.string.not_authorized);
//            msgError = ((NotAuthorizedException) mThrowable).getErrorMessage();
//        }

        showDialogError(titleError, msgError);
    }

    public void show() {
        getMensagemThrowable();
    }
}

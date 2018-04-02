package pucaberta.pucminas.com.interfaces;

import android.app.DialogFragment;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public interface CallbackBasicViewModel {
    void showDialogProgress();

    void showDialogProgress(String text);

    void showDialogProgress(int text);

    void hideDialogProgress();

    void showError(Throwable t, MaterialDialog.SingleButtonCallback callback);

    void openActivity(Class<?> openActivity);

    void openActivityNewTask(Class<?> openActivity);

    void openActivity(Class<?> openActivity, Bundle bundle);

    void openActivityNewTaskAnimation(Class<?> openActivity, int in, int out);

    MaterialDialog getBasicMaterialDialog();

    void showDialogFragment(DialogFragment dialogFragment);

    void finish(int resultCode);

    void finish(int resultCode, Bundle bundle);

    void showSimpleDialog(String title, String msg);

    void showSimpleDialog(int title, int msg);

    String getString(int resString);
}

package pucaberta.pucminas.com.base;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;

/**
 * Created by lrmvasconcelos on 22/02/18.
 * Update at 23/03/18 by lrmvasconcelos
 */

public class BaseFragment extends Fragment implements CallbackBasicViewModel {


    private SweetAlertDialog dialogProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initDialogProgress() {
        dialogProgress = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        dialogProgress.getProgressHelper().setBarColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        dialogProgress.setTitleText(getString(R.string.loading));
        dialogProgress.setCancelable(false);
    }

    @Override
    public void openActivity(Class<?> openActivity) {
        Intent intent = new Intent();
        intent.setClass(getContext(), openActivity);
        startActivity(intent);
    }

    //
    @Override
    public void openActivityNewTask(Class<?> openActivity) {
        Intent intent = new Intent();
        intent.setClass(getContext(), openActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void openActivity(Class<?> openActivity, Bundle bundle) {

    }

    @Override
    public void openMailIntent(String mailQuery) {

    }

    @Override
    public void openActivityNewTaskAnimation(Class<?> openActivity, int in, int out) {

    }

    @Override
    public MaterialDialog getBasicMaterialDialog() {
        return null;
    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment) {

    }


    @Override
    public void finish(int resultCode) {
        getActivity().setResult(resultCode);
        getActivity().finish();
    }

    @Override
    public void finish(int resultCode, Bundle bundle) {
        getActivity().getIntent().putExtras(bundle);
        getActivity().setResult(resultCode, getActivity().getIntent());
        getActivity().finish();
    }

    @Override
    public void showSimpleDialog(String title, String msg) {

    }

    @Override
    public void showSimpleDialog(int title, int msg) {

    }

    @Override
    public void showDialogProgress() {
        if (dialogProgress == null) initDialogProgress();
        dialogProgress.setTitleText(getString(R.string.loading));
        if (!dialogProgress.isShowing()) dialogProgress.show();
    }

    @Override
    public void showDialogProgress(String text) {
        if (dialogProgress == null) initDialogProgress();
        dialogProgress.setTitleText(text);
        if (!dialogProgress.isShowing()) dialogProgress.show();
    }

    @Override
    public void showDialogProgress(int text) {
        if (dialogProgress == null) initDialogProgress();
        dialogProgress.setTitleText(getString(text));
        if (!dialogProgress.isShowing()) dialogProgress.show();
    }

    @Override
    public void hideDialogProgress() {
        if (dialogProgress != null && dialogProgress.isShowing()) dialogProgress.dismiss();
    }

    //
    @Override
    public void showError(Throwable t, MaterialDialog.SingleButtonCallback callback) {
        Utils.showDialogError(getActivity(), t, callback);
    }
}

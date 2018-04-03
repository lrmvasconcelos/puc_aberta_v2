package pucaberta.pucminas.com.base;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.app.Constants;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.interfaces.CallbackBasicViewModel;

import static pucaberta.pucminas.com.app.Constants.API.Register.REGISTER;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class BaseActivity extends AppCompatActivity implements CallbackBasicViewModel {
    protected Toolbar toolbar;
    private SweetAlertDialog dialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initDialogProgress() {
        dialogProgress = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialogProgress.getProgressHelper().setBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        dialogProgress.setTitleText(getString(R.string.loading));
        dialogProgress.setCancelable(false);
    }

    @Override
    public MaterialDialog getBasicMaterialDialog() {
        return null;
    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment) {

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public void changeFragment(View view, Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(view.getId(), fragment);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    public void setLogoToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar_logo);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            assert getSupportActionBar() != null;
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }
    }

    public void setUpToolbar(boolean isBack) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle(R.string.login);
            if(isBack){
                toolbar.findViewById(R.id.imageView_back).setOnClickListener(v -> finish());
            }
        }
    }

    //
//    public void setUpToolbarWhite(boolean isBack) {
//        setUpToolbar(isBack, false);
//    }
//
//    public void setUpToolbarBlack(boolean isBack) {
//        setUpToolbar(isBack, true);
//    }
//
//    public void setUpToolbarText(boolean isBack, String text) {
////        setUpToolbar(isBack);
////        TextView textTitle = (TextView) toolbar.findViewById(R.id.text_title_toolbar);
////        textTitle.setText(text);
////        textTitle.setVisibility(View.VISIBLE);
//    }
//
//
    @Override
    public void openActivity(Class<?> openActivity) {
        Intent intent = new Intent();
        intent.setClass(this, openActivity);
        startActivity(intent);
    }

    //
    @Override
    public void openActivityNewTask(Class<?> openActivity) {
        Intent intent = new Intent();
        intent.setClass(this, openActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void openActivity(Class<?> openActivity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, openActivity);
        intent.putExtra(REGISTER, bundle);
        startActivity(intent);
    }


    @Override
    public void openActivityNewTaskAnimation(Class<?> openActivity, int in, int out) {
        Intent intent = new Intent();
        intent.setClass(this, openActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition(in, out);
        startActivity(intent);
    }



    //
//    @Override
//    public MaterialDialog getBasicMaterialDialog() {
//        MaterialDialog dialog = new MaterialDialog.Builder(this).build();
//        return dialog;
//    }
//
//    @Override
//    public void showDialogFragment(DialogFragment dialogFragment) {
//        dialogFragment.show(getFragmentManager(), "dialog");
//    }
//
    @Override
    public void finish(int resultCode) {
        setResult(resultCode);
        finish();
    }

    @Override
    public void finish(int resultCode, Bundle bundle) {
        getIntent().putExtras(bundle);
        setResult(resultCode, getIntent());
        finish();
    }

    @Override
    public void showSimpleDialog(String title, String msg) {
        Utils.showSimpleDialog(this, title, msg, (dialog, which) -> {
        });
    }

    @Override
    public void showSimpleDialog(int title, int msg) {
        Utils.showSimpleDialog(this, title, msg, (dialog, which) -> {
        });
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
        Utils.showDialogError(this, t, callback);
    }
}

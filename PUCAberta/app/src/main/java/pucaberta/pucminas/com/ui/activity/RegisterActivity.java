package pucaberta.pucminas.com.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityRegisterBinding;
import pucaberta.pucminas.com.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivityViewModel<ActivityRegisterBinding, RegisterViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mViewModel = new RegisterViewModel(this);
        mBinding.setViewModel(mViewModel);
        setUpToolbar(true);
    }
}

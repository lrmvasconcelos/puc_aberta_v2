package pucaberta.pucminas.com.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityLoginBinding;
import pucaberta.pucminas.com.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivityViewModel<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel = new LoginViewModel(this);
        mBinding.setViewModel(mViewModel);
        setUpToolbar();
    }
}

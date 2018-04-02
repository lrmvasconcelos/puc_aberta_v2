package pucaberta.pucminas.com.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityWebViewBinding;
import pucaberta.pucminas.com.viewmodel.WebViewModel;

/**
 * Created by lucas on 02/04/2018.
 * Update at 02/04/2018
 */

public class WebViewActivity extends BaseActivityViewModel<ActivityWebViewBinding, WebViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        mViewModel = new WebViewModel(this);
        mBinding.setViewModel(mViewModel);
    }
}

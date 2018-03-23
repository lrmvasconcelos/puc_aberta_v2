package pucaberta.pucminas.com.ui.activity.base;

import android.databinding.ViewDataBinding;

import pucaberta.pucminas.com.viewmodel.base.ViewModel;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class BaseActivityViewModel<T extends ViewDataBinding, V extends ViewModel> extends BaseActivity {

    protected T mBinding;
    protected V mViewModel;


    @Override
    protected void onDestroy() {
        if (mViewModel != null) {
            mViewModel.destroy();
            mViewModel = null;
        }
        super.onDestroy();
    }
}

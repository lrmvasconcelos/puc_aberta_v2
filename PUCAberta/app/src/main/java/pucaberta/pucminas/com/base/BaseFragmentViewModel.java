package pucaberta.pucminas.com.base;

import android.databinding.ViewDataBinding;

import pucaberta.pucminas.com.interfaces.ViewModel;


/**
 * Created by lrmvasconcelos on 22/02/18.
 */

public class BaseFragmentViewModel<T extends ViewDataBinding, V extends ViewModel> extends BaseFragment {

    protected T mBinding;
    protected V mViewModel;


    @Override
    public void onDestroy() {
        if (mViewModel != null) {
            mViewModel.destroy();
            mViewModel = null;
        }
        super.onDestroy();
    }
}

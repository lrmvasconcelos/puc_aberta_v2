package pucaberta.pucminas.com.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseFragmentViewModel;
import pucaberta.pucminas.com.base.BaseViewModel;
import pucaberta.pucminas.com.databinding.FragmentMenuBinding;
import pucaberta.pucminas.com.viewmodel.MenuViewModel;

/**
 * Created by lucas on 09/04/2018.
 * Update at 09/04/2018
 */

public class MenuFragment extends BaseFragmentViewModel<FragmentMenuBinding, MenuViewModel> implements View.OnClickListener{

    public static MenuFragment newInstance() {

        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        if(mViewModel == null){
            mViewModel = new MenuViewModel(this);
        }
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.log_out:

                break;

            default:
                break;
        }
    }
}

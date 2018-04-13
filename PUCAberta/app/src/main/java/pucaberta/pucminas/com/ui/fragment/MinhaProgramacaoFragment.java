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
import pucaberta.pucminas.com.databinding.FragmentMinhaProgramacaoBinding;
import pucaberta.pucminas.com.viewmodel.MinhaProgramacaoViewModel;

/**
 * Created by lucas on 12/04/2018.
 * Update at 12/04/2018
 */

public class MinhaProgramacaoFragment extends
        BaseFragmentViewModel<FragmentMinhaProgramacaoBinding, MinhaProgramacaoViewModel> {

    public static MinhaProgramacaoFragment newInstance() {

        Bundle args = new Bundle();

        MinhaProgramacaoFragment fragment = new MinhaProgramacaoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_minha_programacao, container, false);

        if (mViewModel == null) {
            mViewModel = new MinhaProgramacaoViewModel(this);
        }
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}

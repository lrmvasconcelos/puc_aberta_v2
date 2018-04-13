package pucaberta.pucminas.com.ui.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseFragmentViewModel;
import pucaberta.pucminas.com.databinding.FragmentProgramacaoBinding;
import pucaberta.pucminas.com.viewmodel.ProgramacaoViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramacaoFragment extends BaseFragmentViewModel<FragmentProgramacaoBinding, ProgramacaoViewModel> {


    public static ProgramacaoFragment newInstance() {

        Bundle args = new Bundle();

        ProgramacaoFragment fragment = new ProgramacaoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_programacao, container, false);


        if (mViewModel == null) {
            mViewModel = new ProgramacaoViewModel(this);
        }
        mBinding.setViewModel(mViewModel);


        return mBinding.getRoot();

    }


}

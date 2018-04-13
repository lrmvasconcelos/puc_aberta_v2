package pucaberta.pucminas.com.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.widget.TextView;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityHomeBinding;
import pucaberta.pucminas.com.ui.fragment.CursosFragment;
import pucaberta.pucminas.com.ui.fragment.MapsFragment;
import pucaberta.pucminas.com.ui.fragment.MenuFragment;
import pucaberta.pucminas.com.ui.fragment.MinhaProgramacaoFragment;
import pucaberta.pucminas.com.ui.fragment.ProgramacaoFragment;
import pucaberta.pucminas.com.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivityViewModel<ActivityHomeBinding, HomeViewModel> {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_map:
                if (!(mStack.peek() instanceof MapsFragment)) {
                    changeFragmentAddStack(mBinding.container, MapsFragment.newInstance());
                }
                return true;
            case R.id.navigation_programming:
                if (!(mStack.peek() instanceof ProgramacaoFragment)) {
                    changeFragmentAddStack(mBinding.container, ProgramacaoFragment.newInstance());
                }
                return true;
            case R.id.navigation_my_programming:
                if (!(mStack.peek() instanceof MinhaProgramacaoFragment)) {
                    changeFragmentAddStack(mBinding.container, MinhaProgramacaoFragment.newInstance());
                }
                return true;
            case R.id.navigation_courses:
                if (!(mStack.peek() instanceof CursosFragment)) {
                    changeFragmentAddStack(mBinding.container, CursosFragment.newInstance());
                }
                return true;
            case R.id.navigation_menu:
                if (!(mStack.peek() instanceof MenuFragment)) {
                    changeFragmentAddStack(mBinding.container, MenuFragment.newInstance());
                }
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mViewModel = new HomeViewModel(this);
        mBinding.setViewModel(mViewModel);
        setLogoToolbar();
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragmentAddStack(mBinding.container, MapsFragment.newInstance());
    }


}

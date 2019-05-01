package pucaberta.pucminas.com.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityHomeBinding;
import pucaberta.pucminas.com.ui.fragment.CursosFragment;
import pucaberta.pucminas.com.ui.fragment.MapsFragment;
import pucaberta.pucminas.com.ui.fragment.MenuFragment;
import pucaberta.pucminas.com.ui.fragment.MinhaProgramacaoFragment;
import pucaberta.pucminas.com.ui.fragment.ProgramacaoFragment;
import pucaberta.pucminas.com.viewmodel.HomeViewModel;

import static android.Manifest.permission.CAMERA;

public class HomeActivity extends BaseActivityViewModel<ActivityHomeBinding, HomeViewModel> {

    private TextView mTextMessage;
    private int lastMenuSelected;

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
                Uri uri = Uri.parse("https://www.pucminas.br/_layouts/15/cursos/graduacao.aspx?tipo=152f25a5-fa8d-4d04-a7ba-57b6b4c21265");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(this.getPackageManager()) == null) {
                    intent.setData(Uri.parse("https://www.pucminas.br/_layouts/15/cursos/graduacao.aspx?tipo=152f25a5-fa8d-4d04-a7ba-57b6b4c21265"));
                }
                this.startActivity(intent);
//                if (!(mStack.peek() instanceof CursosFragment)) {
//                    changeFragmentAddStack(mBinding.container, CursosFragment.newInstance());
//                }
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
        mBinding.brnCamera.setOnClickListener(view ->
                new RxPermissions(HomeActivity.this)
                        .request(CAMERA)
                        .filter(allGranted -> allGranted)
                        .subscribe(allGranted -> {
                            openActivity(CameraActivity.class);
                        }, error -> Toast.makeText(HomeActivity.this, "É necessário a permissão de acesso a câmera do dispositivo", Toast.LENGTH_SHORT).show()));
    }

    @Override
    public void onBackPressed() {

        if (mStack.size() > 1) {
            mStack.pop();
            if (mStack.peek() instanceof MapsFragment) {
                changeFragment(mBinding.container, (Fragment) mStack.peek());
                mBinding.navigation.setSelectedItemId(R.id.navigation_map);
            } else if (mStack.peek() instanceof ProgramacaoFragment) {
                changeFragment(mBinding.container, (Fragment) mStack.peek());
                mBinding.navigation.setSelectedItemId(R.id.navigation_programming);
            } else if (mStack.peek() instanceof MinhaProgramacaoFragment) {
                changeFragment(mBinding.container, (Fragment) mStack.peek());
                mBinding.navigation.setSelectedItemId(R.id.navigation_my_programming);
            } else if (mStack.peek() instanceof CursosFragment) {
                changeFragment(mBinding.container, (Fragment) mStack.peek());
                mBinding.navigation.setSelectedItemId(R.id.navigation_courses);
            } else if (mStack.peek() instanceof MenuFragment) {
                changeFragment(mBinding.container, (Fragment) mStack.peek());
                mBinding.navigation.setSelectedItemId(R.id.navigation_menu);
            }

        } else {
            super.onBackPressed();
        }

    }

}

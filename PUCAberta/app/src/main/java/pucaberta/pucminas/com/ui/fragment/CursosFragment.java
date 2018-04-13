package pucaberta.pucminas.com.ui.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseFragmentViewModel;
import pucaberta.pucminas.com.databinding.FragmentCursosBinding;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.viewmodel.CursosViewModel;

/**
 * Created by lucas on 10/04/2018.
 * Update at 10/04/2018
 */

public class CursosFragment extends BaseFragmentViewModel<FragmentCursosBinding, CursosViewModel> {


    public static CursosFragment newInstance() {

        return new CursosFragment();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cursos, container, false);

        if (mViewModel == null) {
            mViewModel = new CursosViewModel(this);
        }

        mBinding.setViewModel(mViewModel);

        mBinding.webView.getSettings().setJavaScriptEnabled(true);

        layoutVisibility();

        mBinding.buttonReload.setOnClickListener(view -> layoutVisibility());

        return mBinding.getRoot();
    }

    private void layoutVisibility() {

        if (Utils.isOnline()) {

            mBinding.webView.setVisibility(View.VISIBLE);


            mBinding.webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    showDialogProgress();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    hideDialogProgress();
                }

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(getContext(), "Oops! " + description, Toast.LENGTH_SHORT).show();
                }
            });

            mBinding.webView.loadUrl("http://www.pucminas.br/Graduacao/Paginas/default.aspx");
        } else {
            Toast.makeText(getActivity(), R.string.sem_conexao_internet, Toast.LENGTH_SHORT).show();
            mBinding.webView.setVisibility(View.GONE);
            mBinding.relativeLayout.setVisibility(View.VISIBLE);
        }
    }
}

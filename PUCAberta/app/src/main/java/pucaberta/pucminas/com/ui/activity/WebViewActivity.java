package pucaberta.pucminas.com.ui.activity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityWebViewBinding;
import pucaberta.pucminas.com.helper.PreferencesManager;
import pucaberta.pucminas.com.viewmodel.WebViewModel;

import static pucaberta.pucminas.com.app.Constants.API.Register.REGISTER;

/**
 * Created by lucas on 02/04/2018.
 * Update at 02/04/2018
 */

public class WebViewActivity extends BaseActivityViewModel<ActivityWebViewBinding, WebViewModel> {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        mViewModel = new WebViewModel(this);
        mBinding.setViewModel(mViewModel);
        setUpToolbar(true);
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

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(WebViewActivity.this,
                        "Ops. Não foi possível acessar a página de incrição, tente novamente mais tarde!",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        });

        mBinding.webView.getSettings().setJavaScriptEnabled(true);
        mBinding.webView.loadUrl(PreferencesManager.getInstance().getString(REGISTER));

    }
}

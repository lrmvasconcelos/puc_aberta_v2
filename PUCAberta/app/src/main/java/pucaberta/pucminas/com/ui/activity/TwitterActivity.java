package pucaberta.pucminas.com.ui.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import pucaberta.pucminas.com.R;
import pucaberta.pucminas.com.base.BaseActivityViewModel;
import pucaberta.pucminas.com.databinding.ActivityTwitterBinding;
import pucaberta.pucminas.com.helper.Utils;
import pucaberta.pucminas.com.viewmodel.TwitterViewModel;

/**
 * Created by lucas on 15/04/2018.
 * Update at 15/04/2018
 */

public class TwitterActivity extends BaseActivityViewModel<ActivityTwitterBinding, TwitterViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_twitter);
        if(mViewModel == null){
            mViewModel = new TwitterViewModel(this);
        }
        mBinding.setViewModel(mViewModel);
        setUpToolbar(true);
        layoutVisibility();
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
                    Toast.makeText(getApplicationContext(), "Oops! " + description, Toast.LENGTH_SHORT).show();
                }
            });

            mBinding.webView.loadUrl("https://twitter.com/hashtag/pucaberta");
        } else {
            Toast.makeText(this, R.string.sem_conexao_internet, Toast.LENGTH_SHORT).show();
            mBinding.webView.setVisibility(View.GONE);
            mBinding.relativeLayout.setVisibility(View.VISIBLE);
        }
    }
}

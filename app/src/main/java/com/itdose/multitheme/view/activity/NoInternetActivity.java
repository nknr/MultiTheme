package com.itdose.multitheme.view.activity;


import android.app.Activity;
import android.os.Bundle;

import com.itdose.multitheme.R;
import com.itdose.multitheme.core.base.BaseActivity;
import com.itdose.multitheme.databinding.ActivityNoInternetBinding;
import com.itdose.multitheme.utils.ConnectionUtils;
import com.itdose.multitheme.viewmodel.NoInternetViewModel;

import javax.inject.Inject;

public class NoInternetActivity extends BaseActivity<NoInternetViewModel, ActivityNoInternetBinding> {

    @Inject
    ConnectionUtils utils;

    @Override
    protected Class<NoInternetViewModel> getViewModel() {
        return NoInternetViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_no_internet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupConnectionObserver();
    }

    private void setupConnectionObserver() {
        utils.observe(this, connection -> {
            if (connection.getIsConnected()) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}

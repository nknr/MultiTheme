package com.itdose.multitheme.core.base;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends ViewModel, D extends ViewDataBinding> extends AppCompatActivity{

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected V viewModel;

    protected D dataBinding;

    protected abstract Class<V> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(getViewModel());

    }

    public void setToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}

package com.itdose.multitheme.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.itdose.multitheme.R;
import com.itdose.multitheme.core.base.BaseActivity;
import com.itdose.multitheme.data.remote.lib.Status;
import com.itdose.multitheme.databinding.ActivityPeopleBinding;
import com.itdose.multitheme.utils.ConnectionUtils;
import com.itdose.multitheme.view.adapter.PeopleAdapter;
import com.itdose.multitheme.viewmodel.PeopleViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class PeopleActivity extends BaseActivity<PeopleViewModel, ActivityPeopleBinding> {

    private PeopleAdapter adapter;
    private int mLastDayNightMode;
    @Inject
    ConnectionUtils connectionUtils;

    @Override
    protected Class<PeopleViewModel> getViewModel() {
        return PeopleViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_people;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setupRecyclerView();
        observerPeopleResource();
        setupConnectionObserver();
    }

    private void initBinding() {
        mLastDayNightMode = AppCompatDelegate.getDefaultNightMode();
        dataBinding.setViewModel(viewModel);
        dataBinding.setErrorMessage("");
        dataBinding.setLifecycleOwner(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("start called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("resume called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.d("restart called");
        if (AppCompatDelegate.getDefaultNightMode() != mLastDayNightMode) {
            recreate();
        }
    }


    private void setupRecyclerView() {
        adapter = new PeopleAdapter();
        dataBinding.listPeople.setAdapter(adapter);
        dataBinding.listPeople.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.listPeople.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    public void onFab(View view) {
        viewModel.loadPeople(false);
    }

    private void observerPeopleResource() {
        viewModel.resourceLiveData.observe(this, resource -> {
            String errorMessage;
            dataBinding.setResource(resource);
            if (resource != null && resource.getStatus() == Status.LOADING) {
                dataBinding.setErrorMessage("");
            } else if (resource != null && (resource.getStatus() == Status.SUCCESS)) {
                if (resource.getData() != null && !resource.getData().getPeopleList().isEmpty())
                    adapter.setPeople(resource.getData().getPeopleList());
            } else if (resource != null && resource.getStatus() == Status.ERROR) {
                errorMessage = resource.getMessage();
                if (adapter.getItemCount() > 0) showMessage(errorMessage);
                else dataBinding.setErrorMessage(errorMessage);
            }
        });

    }

    private void setupConnectionObserver() {
        connectionUtils.observe(this, connection -> {
            if (!connection.getIsConnected()) {
                startActivityForResult(new Intent(this, NoInternetActivity.class), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            viewModel.loadPeople(true);
            observerPeopleResource();
        }
    }

    private void showMessage(String message) {
        Snackbar.make(dataBinding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // This demonstrates how to programmatically tint a drawable
        /*MenuItem item = menu.findItem(R.id.action_more);
        Drawable drawableWrap = DrawableCompat.wrap(item.getIcon()).mutate();
        DrawableCompat.setTint(drawableWrap, ColorUtils.getThemeColor(this, R.attr));
        item.setIcon(drawableWrap);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_more) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

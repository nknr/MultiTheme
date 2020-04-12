package com.itdose.multitheme;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.itdose.multitheme.core.dagger.components.DaggerMainComponent;
import com.itdose.multitheme.utils.ThemeHelper;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;


public class DarkThemeApplication extends DaggerApplication {

    private static DarkThemeApplication singleton;

    public static synchronized DarkThemeApplication getInstance() {
        return singleton;
    }

    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE);
        ThemeHelper.applyTheme(themePref);
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.builder().create(this);
    }
}

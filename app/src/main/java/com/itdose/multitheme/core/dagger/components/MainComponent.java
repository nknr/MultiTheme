package com.itdose.multitheme.core.dagger.components;


import com.itdose.multitheme.DarkThemeApplication;
import com.itdose.multitheme.core.dagger.builders.ActivityBuilder;
import com.itdose.multitheme.core.dagger.modules.AppModule;
import com.itdose.multitheme.core.dagger.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ActivityBuilder.class})
public interface MainComponent extends AndroidInjector<DarkThemeApplication> {


    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DarkThemeApplication> {}

}

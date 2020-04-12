package com.itdose.multitheme.core.dagger.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.itdose.multitheme.DarkThemeApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(DarkThemeApplication application){
        return application;
    }

    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

    /*@Singleton
    @Provides
    Utils provideUtils(Context context){
        return new Utils(context);
    }*/

}

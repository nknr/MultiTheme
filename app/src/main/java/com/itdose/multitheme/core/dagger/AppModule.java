package com.itdose.multitheme.core.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.itdose.multitheme.utils.ConnectionUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;


@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {


    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    ConnectionUtils provideConnectionUtils(@ApplicationContext Context context){
        return new ConnectionUtils(context);
    }

}

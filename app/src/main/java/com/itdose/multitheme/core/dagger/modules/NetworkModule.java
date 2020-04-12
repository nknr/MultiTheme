package com.itdose.multitheme.core.dagger.modules;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itdose.multitheme.core.constant.ConstantVariable;
import com.itdose.multitheme.data.remote.RestApi;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    @SuppressWarnings("unused")
     OkHttpClient providesClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    @SuppressWarnings("unused")
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(ConstantVariable.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();
    }



    @Singleton
    @Provides
    @SuppressWarnings("unused")
    RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}

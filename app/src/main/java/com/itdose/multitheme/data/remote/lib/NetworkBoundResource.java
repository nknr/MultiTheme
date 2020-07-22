package com.itdose.multitheme.data.remote.lib;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetworkBoundResource<T> {
    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        fetchFromNetwork();

    }

    private void fetchFromNetwork() {
        result.setValue(Resource.loading(null));
        createCall().enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    result.setValue(Resource.success(response.body()));

                } else {
                    result.setValue(Resource.error("Unsuccessful", null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                result.setValue(Resource.error(getCustomErrorMessage(t), null));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error) {

        if (error instanceof SocketTimeoutException) {
            return "Timeout error";
        } else if (error instanceof MalformedJsonException) {
            return  "Json format incorrect";
        } else if (error instanceof IOException) {
            return  "No internet connection";
        } else{
            return error.getMessage();
        }

    }

    /*@SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReInit(V response) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                //saveCallResult(response);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                *//*result.addSource(loadFromDb(), newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                });*//*
            }
        }.execute();
    }
*/
    /*@WorkerThread
    protected abstract void saveCallResult(V item);*/

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    /*@NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDb();*/

    @NonNull
    @MainThread
    protected abstract Call<T> createCall();

    public final MutableLiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}


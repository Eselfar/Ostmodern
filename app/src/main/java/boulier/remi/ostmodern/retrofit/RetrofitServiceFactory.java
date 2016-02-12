package boulier.remi.ostmodern.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Created by Remi BOULIER on 11/02/2016.
 * email: boulier.r+dev@gmail.com
 */
public class RetrofitServiceFactory {

    public static GsonConverter getGsonConverter(Type type, Object typeAdapter) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, typeAdapter)
                .create();

        return new GsonConverter(gson);
    }

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        return createRetrofitService(clazz, endPoint, null);
    }

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint, GsonConverter gsonConverter) {
        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLog(new AndroidLog("retrofit"))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if (gsonConverter != null) {
            restAdapterBuilder.setConverter(gsonConverter);
        }


        T service = restAdapterBuilder
                .build()
                .create(clazz);

        return service;
    }
}

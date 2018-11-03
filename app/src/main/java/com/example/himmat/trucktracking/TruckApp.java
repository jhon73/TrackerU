package com.example.himmat.trucktracking;

import android.app.Application;
import android.content.Context;
import com.example.himmat.trucktracking.retrofit.ApiTask;
import com.example.himmat.trucktracking.retrofit.HttpLoggingInterceptor;
import com.example.himmat.trucktracking.retrofit.WebAPI;
import com.example.himmat.trucktracking.utility.MyPref;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TruckApp extends Application {
    private static Retrofit retrofit;
    private ApiTask apiTask;
    private MyPref myPref;
    private static String token;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(WebAPI.BASE_URL);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
            retrofitBuilder.client(getClient());
            retrofit = retrofitBuilder.build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
//                        .header("Accept", "application/json")
//                        .header(WebAPI.HEADER_KEY, token)
//                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();
        return client;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Freezer.onCreate(this);
//        setUniversal();
//        myPref = new MyPref(this);
//        token = myPref.getData(MyPref.Keys.TOKEN);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

//    public void refreshToken() {
//        token = myPref.getData(MyPref.Keys.TOKEN);
//    }

//    private void setUniversal() {
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//                .memoryCacheExtraOptions(480, 800) // default_img = device screen dimensions
//                .diskCacheExtraOptions(480, 800, null)
//                .threadPoolSize(3) // default_img
//                .threadPriority(Thread.NORM_PRIORITY - 2) // default_img
//                .tasksProcessingOrder(QueueProcessingType.FIFO) // default_img
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13) // default_img
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default_img
//                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default_img
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default_img
//                .writeDebugLogs()
//                .build();
//        ImageLoader.getInstance().init(config);
//    }
    public ApiTask getApiTask() {
        if (apiTask == null)
            apiTask = new ApiTask();
        return apiTask;
    }
}

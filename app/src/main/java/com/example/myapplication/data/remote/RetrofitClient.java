package com.example.myapplication.data.remote;

import com.example.myapplication.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            // Create HttpClient
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        // Add Auth Header for every request
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("apikey", Constants.SUPABASE_API_KEY)
                                .header("Authorization", "Bearer " + Constants.SUPABASE_API_KEY)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    })
                    .build();

            // Connect with the header created
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SUPABASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
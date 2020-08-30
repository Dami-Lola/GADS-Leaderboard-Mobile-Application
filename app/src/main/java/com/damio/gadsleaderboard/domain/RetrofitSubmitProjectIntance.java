package com.damio.gadsleaderboard.domain;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitSubmitProjectIntance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

package com.andyadr.apps.autotrash.API;

import com.andyadr.apps.autotrash.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
	private static Retrofit retrofit = null;

	public static Retrofit getClient() {

		Gson gson = new GsonBuilder()
				.setLenient()
				.create();

		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
				Request RequestMe = chain.request();
				HttpUrl httpUrl = RequestMe.url()
						.newBuilder()
						.build();

				RequestMe = RequestMe.newBuilder()
						.url(httpUrl)
						.addHeader("X-M2M-Origin", "5412378e242f5814:29a48f99807600d4")
						.addHeader("Content-Type", "application/json;ty=4")
						.addHeader("Accept", "application/json")
						.build();

				return chain.proceed(RequestMe);
			}
		}).build();

		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.client(okHttpClient)
					.baseUrl(BuildConfig.URL_API)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.build();
		}
		return retrofit;
	}

}

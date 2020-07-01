package com.andyadr.apps.autotrash.API;

import com.andyadr.apps.autotrash.pojo.M2mCin;
import com.andyadr.apps.autotrash.pojo.Response;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoints {

	//user
	@GET("TrashBot/la")
	Call<Response> getTrash();



//	@FormUrlEncoded
	@POST("TrashBot")
	Call<Response> postSampah(@Body RequestBody body);

}
 

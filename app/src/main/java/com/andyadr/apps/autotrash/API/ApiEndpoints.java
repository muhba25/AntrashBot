package com.andyadr.apps.autotrash.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndpoints {

	//user
	@GET("trash")
	Call<ResponseGetSampah> getTrash();

	@FormUrlEncoded
	@POST("trash")
	Call<ResponsePostPutDelSampah> postKeranjang(@Field("id") String id,
                                                 @Field("status") String status);

}

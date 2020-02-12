package com.andyadr.apps.autotrash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andyadr.apps.autotrash.API.ApiClient;
import com.andyadr.apps.autotrash.API.ApiEndpoints;
import com.andyadr.apps.autotrash.API.ResponseGetSampah;
import com.andyadr.apps.autotrash.API.ResponsePostPutDelSampah;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.status_sampah)
    TextView tv_status_sampah;
    @BindView(R.id.img_sampah)
    ImageView iv_sampah;
    @BindView(R.id.btn_sampah)
    Button btn_sampah;
    private String Id, Status;
    private ApiEndpoints apiService = ApiClient.getClient().create(ApiEndpoints.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Call<ResponseGetSampah> loginreqCall = apiService.getTrash();
        loginreqCall.enqueue(new Callback<ResponseGetSampah>() {
            @Override
            public void onResponse(Call<ResponseGetSampah> call, Response<ResponseGetSampah> response) {
                if (response.isSuccessful()) {
                    Id = response.body().getmSampah().get(0).getId();
                    Status = response.body().getmSampah().get(0).getStatus_sampah();
                    if(Status.length()==4){
                        tv_status_sampah.setText("Opening");
                        iv_sampah.setImageResource(R.drawable.oke3);
                        btn_sampah.setText("Close Trash");
                    }else{
                        tv_status_sampah.setText("Close");
                        iv_sampah.setImageResource(R.drawable.oke4);
                        btn_sampah.setText("Open Trash");
                    }
                    Toast.makeText(MainActivity.this, "Trash is "+Status, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseGetSampah> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });




        btn_sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponsePostPutDelSampah> postKontakCall = apiService.postKeranjang(Id,Status);
                postKontakCall.enqueue(new Callback<ResponsePostPutDelSampah>() {
                    @Override
                    public void onResponse(Call<ResponsePostPutDelSampah> call, Response<ResponsePostPutDelSampah> response) {
                        ResponsePostPutDelSampah serverResponse = response.body();
                        if (serverResponse != null) {
                            if (serverResponse.getSuccess()) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(MainActivity.this, serverResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            assert serverResponse != null;
                            Log.v("Response", serverResponse.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePostPutDelSampah> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });
                //Toast.makeText(DaftarActivity.this,username+" "+password+" "+email+" "+tgl_lahir+" "+jk,Toast.LENGTH_LONG).show();


            }
        });



    }
}

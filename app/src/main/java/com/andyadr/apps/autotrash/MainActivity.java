package com.andyadr.apps.autotrash;

import android.app.ProgressDialog;
import android.content.Context;
//import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.andyadr.apps.autotrash.API.ApiClient;
import com.andyadr.apps.autotrash.API.ApiEndpoints;
import com.andyadr.apps.autotrash.pojo.Response;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity  {
    @BindView(R.id.status_sampah)
    TextView tv_status_sampah;
    @BindView(R.id.img_sampah)
    ImageView iv_sampah;
    @BindView(R.id.btn_sampah)
    Button btn_sampah;
    private String Id,ss;
    private String Status;
    private Integer statSampah;
    private int state;
    private ApiEndpoints apiService = ApiClient.getClient().create(ApiEndpoints.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Call<Response> loginreqCall = apiService.getTrash();
        loginreqCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Id = response.body().getM2mCin().getCon();
//                    okes = response.body();
                    try {
                        JSONObject oke = new JSONObject(Id);
                        Status = oke.getString("status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    state = Integer.parseInt(Status);
                    if(state==1){
                        ss = "Open";
                        tv_status_sampah.setText("\"Open\"");
                        iv_sampah.setImageResource(R.drawable.oke3);
                        btn_sampah.setText("Close Trash");
                        statSampah = 0;

                    }else{
                        ss="Close";
                        tv_status_sampah.setText("\"Close\"");
                        iv_sampah.setImageResource(R.drawable.oke4);
                        btn_sampah.setText("Open Trash");
                        statSampah = 1;
                    }
                    Toast.makeText(MainActivity.this, "Trash is "+ss , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Ambil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.andyadr.apps.autotrash.pojo.Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        btn_sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AsyncT asyncT = new AsyncT(MainActivity.this);
                asyncT.execute();

            }
        });

    }

    private class AsyncT extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;

        public AsyncT(Context activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
//            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Void doInBackground(Void... args) {
            String url ="https://platform.antares.id:8443/~/antares-cse/antares-id/{your-application-name}/{your-device-name}";
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            JsonObject con = new JsonObject();
            con.addProperty("con","{\"status\": "+statSampah+"}");
            final JsonObject m2mcin = new JsonObject();
            m2mcin.add("m2m:cin",con);
            final String mRequestBody = m2mcin.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_RESPONSE", response);
//                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return com.android.volley.Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    //headers.put("Content-Type", "application/json");
                    headers.put("X-M2M-Origin", "5412378e242f5814:29a48f99807600d4");
                    headers.put("Content-Type", "application/json;ty=4");
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };

            requestQueue.add(stringRequest);

            return null;

        }
        @Override
        protected void onPostExecute(Void result) {
            // do UI work here
            if (dialog.isShowing()) {
                dialog.dismiss();
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);

            }
        }

    }

}

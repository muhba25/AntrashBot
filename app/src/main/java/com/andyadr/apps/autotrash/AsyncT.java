package com.andyadr.apps.autotrash;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncT extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground (String...params){

            try {
                URL url = new URL("https://platform.antares.id:8443/~/antares-cse/antares-id/Adrian_IOT/TrashBot"); //Enter URL here
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("X-M2M-Origin", "5412378e242f5814:29a48f99807600d4");
                httpURLConnection.setRequestProperty("Content-Type", "application/json;ty=4");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.connect();

                JSONObject con = new JSONObject();
                con.put("con", "{\\\"status\\\": 0}");
                JSONObject m2mcin = new JSONObject();
                m2mcin.put("m2m:cin", con);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(m2mcin.toString());
                wr.flush();
                wr.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }


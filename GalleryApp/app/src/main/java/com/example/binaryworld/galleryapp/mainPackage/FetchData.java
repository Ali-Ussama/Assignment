package com.example.binaryworld.galleryapp.mainPackage;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BinaryWorld on 28-Feb-17.
 */
public class FetchData extends AsyncTask<Void, Void, List<String>> {
    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;
    StringBuffer stringBuffer = new StringBuffer();
    String jsonData = null;

    @Override
    protected List<String> doInBackground(Void... params) {

        String BASE_URL = "http://api.androidhive.info/json/glide.json";
        Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();

        try {
            URL url = new URL(builtUri.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

            jsonData = stringBuffer.toString();
            Log.v("DataAccessed: ", jsonData);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (stringBuffer.length() == 0) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return getDataFormatJson(jsonData);
    }

    ArrayList<String> img_meduim_arr;
    ArrayList<String> img_large_arr;
    ArrayList<String> img_name_arr;
    ArrayList<String> img_date_arr;

    private List<String> getDataFormatJson(String jsonData) {

        img_meduim_arr = new ArrayList<String>();
        img_large_arr = new ArrayList<String>();
        img_name_arr = new ArrayList<String>();
        img_date_arr = new ArrayList<String>();

        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String img_name = jsonObject1.getString("name");

                JSONObject jsonObject2 = jsonObject1.getJSONObject("url");
                String img_med = jsonObject2.getString("medium");
                String img_large = jsonObject2.getString("large");
                String img_date = jsonObject1.getString("timestamp");

                img_name_arr.add(img_name);
                img_meduim_arr.add(img_med);
                img_large_arr.add(img_large);
                img_date_arr.add(img_date);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return img_meduim_arr;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        super.onPostExecute(result);


        MainActivity.recAdapter.data.clear();
        for (int i = 0; i < result.size(); i++) {
            MainActivity.recAdapter.data.add(new Model(img_name_arr.get(i),img_meduim_arr.get(i),img_large_arr.get(i),
                    img_date_arr.get(i)));
        }

        MainActivity.recAdapter.notifyDataSetChanged();


    }
}

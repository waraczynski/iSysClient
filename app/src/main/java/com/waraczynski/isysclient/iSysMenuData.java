package com.waraczynski.isysclient;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class iSysMenuData {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;
        try {
            if (url != null) {
                urlConnection = (HttpURLConnection) url.openConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;
        try {
            if (urlConnection != null) {
                in = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        if (in != null) {
            try {
                while ((inputLine = in.readLine()) != null)
                    json += inputLine;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
                jObj = new JSONObject(json);
        } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
}

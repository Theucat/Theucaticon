package com.zxzq.car.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonUtils {
    public static String getJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            if (connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String temp = null;
                if ((temp = br.readLine()) != null){
                    json.append(temp);
                }
                br.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}

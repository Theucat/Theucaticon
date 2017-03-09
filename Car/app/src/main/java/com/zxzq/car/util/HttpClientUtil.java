package com.zxzq.car.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class HttpClientUtil {
    private static HttpClient httpClient;
    private static int Timeout=5000;
    private static int MaxTotalConnections=8;
    //设置HttpClient对象超时时间和最大连接数量
    public static synchronized HttpClient getHttpClient(){
        if(httpClient==null){
            BasicHttpParams params = new BasicHttpParams();
            ConnManagerParams.setTimeout(params,Timeout);
            ConnManagerParams.setMaxTotalConnections(params, MaxTotalConnections);
            HttpConnectionParams.setConnectionTimeout(params, Timeout);
            httpClient=new DefaultHttpClient(params);
        }
        return httpClient;
    }
    //获取连接后返回的数据
    public static String HttpGet(String url){
        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

package com.bs.demo.myapplication.utils;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HttpUtil<B>{
    public final static String host ="http://10.95.72.153:8080/TInfoWebServer/servlet/";
    public static final String RESPONSE_ERROR= "001";
    private Context context;
    private Map<String,Object> paramsmap =new HashMap<>();
    private Observable<String> observable;


    private int REQ_TYPE=1001;
    private int POST=1000;
    private int GET =1001;

    public interface Callback{
        void onSuccess(String json);
        void onFailure(String msg);
    }


    public void sendGetRequest(final String path ,Callback bCallback){
        REQ_TYPE=GET;
        sendRequest(path,bCallback);
    }

    private void sendRequest(final String path, final Callback bCallback){
        observable =Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (REQ_TYPE==POST){
                    emitter.onNext(postRequest(path,paramsmap));
                }
                if (REQ_TYPE==GET){
                    emitter.onNext(getRequest(path,paramsmap));
                }
            }
        });
        Consumer<String> stringConsumer=new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("http Response","------------");
                Log.d("http Response",s);
                Log.d("http Response","------------");
                try {
                    if (s.equals(RESPONSE_ERROR)) {
                        bCallback.onFailure("操作出错!");
                    } else {
                        bCallback.onSuccess(s);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stringConsumer);
    }
    public void sendPostRequest(final String path,Callback bCallback){
        REQ_TYPE=POST;
        sendRequest(path,bCallback);
    }
    public void addParams (String key, String value){
        paramsmap.put(key,value);
    }

    private static String getRequest(String path, Map<String, Object> params)throws IOException {
        StringBuilder url = new StringBuilder(host +path);
        url.append("?");
        for (Map.Entry<String,Object> entity : params.entrySet()) {
            String getkey = URLEncoder.encode(entity.getKey(), "utf-8");
            String getvalue =  URLEncoder.encode((String) entity.getValue(), "utf-8");
            url.append(getkey).append("=");
            url.append(getvalue);
            url.append("&");
        }
        url.deleteCharAt(url.length() - 1);
        Log.d("http_url: ", url + "");
        HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
                .openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        try {
            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();
                int len;
                byte[] buffer = new byte[10 * 1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = in.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                String s = bos.toString();
                return s;
            } else {
                return RESPONSE_ERROR;
            }
        }catch (Exception e){
            return RESPONSE_ERROR;
        }
    }
    private static String postRequest(String path, Map<String,Object> params)throws IOException {
        StringBuilder s = new StringBuilder(host +path);

        try {
            // 根据地址创建URL对象
            URL url = new URL(s.toString());
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 传递的数据
            StringBuilder data =new StringBuilder();
            for (Map.Entry<String,Object> entity : params.entrySet()) {
                String getkey = URLEncoder.encode(entity.getKey(), "utf-8");
                String getvalue =  URLEncoder.encode((String) entity.getValue(), "utf-8");
                data.append(getkey).append("=");
                data.append(getvalue);
                data.append("&");
            }
            Log.d("zjw", data.toString().getBytes() + "");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
//            // 设置请求的头
//            urlConnection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.toString().getBytes("utf-8").length));
            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.toString().getBytes("utf-8"));
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[10*1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());
                return result;
            } else {
                return RESPONSE_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return RESPONSE_ERROR;
    }



}

package com.wangly.httpclient.utils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class AsyncHttpUtils {
    private static AsyncHttpUtils instance;
    private AsyncHttpClient httpClient;
    private RequestParams requestParams;
    private static final int TIME_OUT = 1000;
    private static final int MAX_CONNECT_TIME = 1000;
    private final Gson gson;

    private AsyncHttpUtils() {
        httpClient = new AsyncHttpClient();
        httpClient.setTimeout(TIME_OUT);
        httpClient.setMaxConnections(MAX_CONNECT_TIME);

        gson = new Gson();


    }

    public static AsyncHttpUtils getInstance() {
        if (instance == null) {
            instance = new AsyncHttpUtils();
        }
        return instance;
    }

    /**
     * GET
     *
     * @param url
     * @param callBack
     */
    public void get(String url, ResultCallBack callBack) {
        httpClient.get(url, new HttpResponseHandler(callBack));

    }

    /**
     * GET
     *
     * @param url
     * @param params
     * @param callback
     */
    public void get(String url, Map<String, Object> params, ResultCallBack callback) {
        RequestParams requestParams = doRequestParams(params);
        httpClient.get(url, requestParams, new HttpResponseHandler(callback));
    }


    /**
     * POST
     *
     * @param url
     * @param params
     * @param callback
     */
    public void post(String url, Map<String, Object> params, final ResultCallBack callback) {
        RequestParams requestParams = doRequestParams(params);
        httpClient.post(url, requestParams, new HttpResponseHandler(callback));

    }


    public RequestParams doRequestParams(Map<String, Object> params) {
        requestParams = new RequestParams();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            requestParams.put(entry.getKey(), entry.getValue());
        }
        return requestParams;
    }


    // exectue AsyncHttpClient
    class HttpResponseHandler extends AsyncHttpResponseHandler {
        private ResultCallBack callBack;

        public HttpResponseHandler(ResultCallBack back) {
            this.callBack = back;

        }

        @Override
        public void onStart() {
            super.onStart();
            if (callBack != null) {
                callBack.onStart();
            }
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            if (statusCode == 200) {
                String result = new String(responseBody);
                if (!"".equals(result) && null != result) {
                    if (result.startsWith("\uFEFF")) {
                        result = result.substring(1, result.length());
                    }

                    if (callBack.mType == String.class) {
                        doSuccess(callBack, statusCode, result);
                    } else {
                        try {
                            Object object = gson.fromJson(result, callBack.mType);
                            if (callBack != null) {
                                doSuccess(callBack, statusCode, object);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            ApiExceptionTest.getApiExceptionMessage(e.getCause());
                        }

                    }
                }

            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            doFailed(callBack, statusCode, error);
        }
    }


    public void doSuccess(ResultCallBack callBack, int stateCode, Object object) {
        if (callBack != null) {
            callBack.Success(stateCode, object);
        }
    }

    public void doFailed(ResultCallBack callBack, int errorCode, Throwable throwable) {
        if (callBack != null) {
            callBack.Failure(errorCode, ApiExceptionTest.getApiExceptionMessage(throwable));
        }
    }


    // 其他类型，不在封装，
    // 如：追踪源码可知:JsonHttpResponseHandler 是 AsyncHttpResponseHandler 的子类
    // 所以可以共用 同一个，但是需要进行转型
    //以下提供了枚举类型，可供参考，封装

    enum OtherType {
        down_json,
        down_byte
    }

}


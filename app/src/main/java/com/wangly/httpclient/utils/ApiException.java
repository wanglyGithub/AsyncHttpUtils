package com.wangly.httpclient.utils;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cz.msebera.android.httpclient.client.HttpResponseException;

/**
 * Created by wangly on 2017/1/14.
 *
 * @description 描述: 异常管理类
 */

public class ApiException {
    public static final String NET_WORK_ERROR = "网络不可用";
    public static final String CONNECT_EXCEPTION = "网络不给力，请稍后重试";
    public static final String SERVER_ADDRES_ERROR = "URL 地址有误！";
    public static final String JSON_PARSE_ERROR = "Json 解析出错";

    public static String getApiExceptionMessage(Throwable throwable) {
        if (throwable instanceof UnknownHostException || throwable instanceof ConnectException) {
            return NET_WORK_ERROR;
        } else if (throwable instanceof SocketTimeoutException) {
            return CONNECT_EXCEPTION;
        } else if (throwable instanceof HttpResponseException) {
            return SERVER_ADDRES_ERROR;
        } else if (throwable instanceof JsonParseException) {
            return JSON_PARSE_ERROR;
        } else {
            return "未知错误";
        }
    }
}

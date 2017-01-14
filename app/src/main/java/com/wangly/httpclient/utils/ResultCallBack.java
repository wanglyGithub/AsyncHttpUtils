package com.wangly.httpclient.utils;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangly on 2017/1/14.
 */

public abstract class ResultCallBack<T> {

    public Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    public ResultCallBack () {
        mType = getSuperclassTypeParameter(getClass());
    }

    public abstract void onStart();

    public abstract void Success(int statusCode, T t);

    public abstract void Failure(int errorCode, String exception);

}

package com.wangly.httpclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wangly.httpclient.R;
import com.wangly.httpclient.bean.UserInfo;
import com.wangly.httpclient.utils.AsyncHttpUtils;
import com.wangly.httpclient.utils.ResultCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String url = "http://192.168.20.244/*******";
    private String postUrl = "http://192.168.20.244/*********";
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        tv_result = (TextView) findViewById(R.id.tv_result);
    }


    public void getRequest(View view) {

        AsyncHttpUtils.getInstance().get(url, new ResultCallBack<UserInfo>() {
            @Override
            public void onStart() {
                Log.d("wangly", "onStart····");
            }

            @Override
            public void Success(int statusCode, UserInfo o) {
                Log.i("wangly", "Success····结果值：" + o);
                tv_result.setText(o.toString());
            }

            @Override
            public void Failure(int errorCode, String exception) {
                Log.e("wangly", "Failure····：" + "错误码：" + errorCode + " ,异常描述" + exception);
            }
        });
    }


    public void postRequest(View view) {
        String filePath = "/storage/emulated/0/Download/img_1474963491.jpg";
        File file = new File(filePath);
        if (!file.exists() && file.length() < 0) {
            Log.e("wangly", "file not exists");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userid", "40");
        map.put("path", file);
        AsyncHttpUtils.getInstance().post(postUrl, map, new ResultCallBack<String>() {
            @Override
            public void onStart() {
                Log.d("wangly", "上传中····");
            }

            @Override
            public void Success(int statusCode, String result) {

                if("0".equals(result)){
                    Log.i("wangly", "····上传成功····");
                }
                tv_result.setText("上传成功啦···");
            }

            @Override
            public void Failure(int errorCode, String exception) {
                Log.e("wangly", "上传失败啦····：" + "错误码：" + errorCode + " ,异常描述," + exception);
            }
        });

    }

}

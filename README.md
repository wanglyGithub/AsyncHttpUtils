# AsyncHttpUtils
AsyncHttp_Client
先看简单的效果，并没有对它们进行下载进度、上传进度的直观上的感受，开发者可以自己设置并添加

![](https://github.com/wanglyGithub/AsyncHttpUtils/blob/master/app/src/main/res/preview/test.gif)

Gradle配置：

	repositories {
	   mavenCentral()
	}

	dependencies {
	    compile 'com.loopj.android:android-async-http:1.4.9'
	    compile 'com.google.code.gson:gson:2.8.0'
	}

## 概要 ##

在 2013年之前这个网络请求库还是不错！随着技术的更新，这个逐渐被放弃使用！接近年关，更多的是闲暇无事，看到老项目中在使用网络请求并没有为此做二次封装，在接口回调当中写下了很多Json解析等其他的逻辑为此，简单的封装了改网络请求！并统一的管理Gson数据的处理。

### 介绍 ###
- 本示例Demo 封装了get/post（文件上传）等等····
- 统一的异常管理
- 虽然说 android-async-http 这个网络请求库，本人已经不再使用了，但是可以学习当中
的设计思想和编码规范。为此做出封装。
- 针对这个框架的思想，自己做的请求框架，可以参考了解。地址：https://github.com/wanglyGithub/AsynHttpClient

## 层次结构 ##

  ![](https://github.com/wanglyGithub/AsyncHttpUtils/blob/master/app/src/main/res/preview/cengci.png)
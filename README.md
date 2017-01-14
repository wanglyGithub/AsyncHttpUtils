# AsyncHttpUtils
AsyncHttp_Client

## 概要**
接近年关，更多的是闲暇无事，看到老项目中在使用网络
请求并没有为此做二次封装，在接口回调当中写下了很多
Json解析等其他的逻辑为此，简单的封装了改网络请求！并统一的管理Gson数据的处理

*** 介绍***
虽然说 android-async-http 这个网络请求库，本人已经不再使用了，但是可以学习当中
的设计思想和编码规范。为此做出封装、

- 层次结构

---utils 包
      |
	  |
    AsyncHttpUtils
		   |
		   |------ get(url,ResultCallBack<T>)
		   |
		   |------ get(url,params,ResultCallBack<T>)
		   |
		   |------ post(url,params,ResultCallBack<T>)
		   |
		   |
   ResultCallBack
           |
		   |----封装了统GSON解析管理，泛型<T>
		   |在 AsyncHttpResponseHandler中的体现
		                |
						|
						|----- onSuccess (进行了Gson 的统一管理)
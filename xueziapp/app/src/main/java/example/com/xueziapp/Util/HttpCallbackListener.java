package example.com.xueziapp.Util;

/**
 * Created by rhm on 2017/4/5.
 * 回调方法
 */

public interface HttpCallbackListener {
    void onFinish(String response);//服务器成功响应时操作
    void onError(Exception e);//进行网络操作失败
}

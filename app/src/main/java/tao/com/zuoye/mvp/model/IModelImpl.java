package tao.com.zuoye.mvp.model;

import com.google.gson.Gson;

import tao.com.zuoye.mvp.callback.MyCallBack;
import tao.com.zuoye.network.Retrofit;

public class IModelImpl implements IModel {
    @Override
    public void requestDataGet(String url, String params, final Class clazz, final MyCallBack myCallBack) {
        Retrofit.getInstance().get(url, new Retrofit.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try{
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    if (myCallBack != null) {
                        myCallBack.onFailed(e.getMessage());
                    }
                }
            }
            @Override
            public void onFail(String error) {
                if(myCallBack != null){
                    myCallBack.onFailed(error);
                }
            }
        });
    }
}

package tao.com.zuoye.mvp.model;

import tao.com.zuoye.mvp.callback.MyCallBack;

public interface IModel {
    void requestDataGet(String url, String params, Class clazz, MyCallBack myCallBack);
}

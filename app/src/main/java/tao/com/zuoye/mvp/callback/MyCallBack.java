package tao.com.zuoye.mvp.callback;

public interface MyCallBack<T> {
    void onSuccess(T data);
    void onFailed(String error);
}

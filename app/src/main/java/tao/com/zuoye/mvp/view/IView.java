package tao.com.zuoye.mvp.view;

public interface IView<T> {
    void getDataSuccess(T data);
    void getDataFail(String error);
}

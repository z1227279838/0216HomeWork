package tao.com.zuoye.mvp.presenter;

import tao.com.zuoye.mvp.callback.MyCallBack;
import tao.com.zuoye.mvp.model.IModelImpl;
import tao.com.zuoye.mvp.view.IView;

public class IPresenterImpl implements IPresenter {

    private IModelImpl mModel;
    private IView mIview;

    public IPresenterImpl(IView iView) {
        this.mIview = iView;
        mModel = new IModelImpl();
    }

    @Override
    public void startRequestGet(String url, String params, Class clazz) {
        mModel.requestDataGet(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                mIview.getDataSuccess(data);
            }

            @Override
            public void onFailed(String error) {
                mIview.getDataFail(error);
            }
        });
    }

    public void onDetach() {
        if (mModel != null) {
            mModel = null;
        }
        if (mIview != null) {
            mIview = null;
        }
    }
}
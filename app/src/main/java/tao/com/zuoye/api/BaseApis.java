package tao.com.zuoye.api;

import java.net.ResponseCache;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


public interface  BaseApis<T> {
    @GET
    Observable<ResponseBody> get(@Url String url);

}

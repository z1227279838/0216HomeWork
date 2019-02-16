package tao.com.zuoye;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tao.com.zuoye.bean.DetailsBean;
import tao.com.zuoye.bean.EventBean;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.commodityName)
    TextView commodityName;
    private DetailsBean detailsBean;
    private int commodityId;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(EventBean evBean) {
        if (evBean.getName().equals("details")) {
            detailsBean = (DetailsBean) evBean.getClazz();
            commodityId = detailsBean.getResult().getCommodityId();
            load();
        }
    }

    private void load() {
        String details = detailsBean.getResult().getDetails();
        String picture = detailsBean.getResult().getPicture();
        String[] split = picture.split(",");
        List<String> list = Arrays.asList(split);
        banner.setImageLoader((ImageLoaderInterface) new GlideImageLoader());
        banner.setImages(list);
        banner.start();
        price.setText("￥" + detailsBean.getResult().getPrice() + "");
        commodityName.setText(detailsBean.getResult().getCommodityName());

    }
    private class GlideImageLoader extends ImageLoader {

        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

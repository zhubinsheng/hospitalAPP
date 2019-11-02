package com.hjq.demo.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.hjq.demo.GlideImageLoader;
import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyLazyFragment;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.HospitalSpecificActivity;
import com.hjq.demo.ui.adapter.RecordQueryAdapter;
import com.hjq.demo.vo.BadCode;
import com.hjq.demo.vo.HospitalVo;
import com.hjq.demo.vo.ResultObject;
import com.hjq.demo.vo.TStudent;
import com.hjq.demo.widget.XCollapsingToolbarLayout;
import com.hjq.dialog.MessageDialog;
import com.hjq.image.ImageLoader;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目炫酷效果示例
 */
public class TestFragmentA extends MyLazyFragment
        implements XCollapsingToolbarLayout.OnScrimsListener {

    @BindView(R.id.abl_test_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.ctl_test_bar)
    XCollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.t_test_title)
    Toolbar mToolbar;
    @BindView(R.id.tb_test_a_bar)
    TitleBar mTitleBar;

    @BindView(R.id.tv_test_address)
    TextView mAddressView;
    @BindView(R.id.tv_test_search)
    TextView mSearchView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.hospitalItem)
    ListView listViewForScrollView;
   /* @BindView(R.id.ToSpecific)
    RelativeLayout relativeLayout;*/
    Unbinder unbinder;
    private RecordQueryAdapter adapter;
    private static final int UPDATE_TEXT = 1 ;
    private List<HospitalVo> hospitalVos;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                    listViewForScrollView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };


    public static TestFragmentA newInstance() {
        return new TestFragmentA();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_a;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_test_a_bar;
    }

    @Override
    protected void initView() {
        // 给这个ToolBar设置顶部内边距，才能和TitleBar进行对齐
        ImmersionBar.setTitleBar(getFragmentActivity(), mToolbar);

        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
    }

    @Override
    protected void initData() {

        List<String> mDatas = new ArrayList<>();
        mDatas.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1555168876&di=af13225dc3e5c96322f2bb34f930caf5&src=http://files.cn-healthcare.com/upload/image/2015-06-17/1434518886328.png");
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555178961001&di=e5b95f15a936a8e3e50fc895061203cc&imgtype=0&src=http%3A%2F%2Fpic90.huitu.com%2Fres%2F20161115%2F851091_20161115090158343040_1.jpg");
        mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555178960999&di=0bebbcf89ac4b685f0dfd45d35248d28&imgtype=0&src=http%3A%2F%2Fpic90.huitu.com%2Fres%2F20161115%2F851091_20161115055714382040_1.jpg");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(mDatas);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

/*    @OnClick({R.id.ToSpecific})
    public void onViewClicked(View view) {
        switch (view.getId()) {
           case  R.id.ToSpecific:
               startActivity(new Intent(getActivity(), HospitalSpecificActivity.class));

                break;
        }
    }*/

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean statusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @Override
    public void onScrimsStateChange(boolean shown) {
        // CollapsingToolbarLayout 发生了渐变
        if (shown) {
            mAddressView.setTextColor(getResources().getColor(R.color.black));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mAddressView.setTextColor(getResources().getColor(R.color.white));
            mSearchView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ListView listView = (ListView) findViewById(R.id.hospitalItem);//在视图中找到ListView 弃用
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.43:8088/HuiShi/selectHos")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                String stirng =response.body().string();
                Gson gson = new Gson();
                Type userType=new TypeToken<ResultObject<List<HospitalVo>>>(){}.getType();
                ResultObject<List<HospitalVo>> userResult=gson.fromJson(stirng,userType);
                hospitalVos =userResult.getData();
                /*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*/
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);

            }
        });





        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
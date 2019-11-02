package com.hjq.demo.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.bar.TitleBar;
import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyLazyFragment;
import com.hjq.demo.ui.activity.LoginActivity;
import com.hjq.demo.ui.activity.UserInfoActivity;
import com.hjq.demo.vo.TStudent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目界面跳转示例
 */
public class TestFragmentD extends MyLazyFragment {

    @BindView(R.id.tb_test_d_title)
    TitleBar tbTestDTitle;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    Unbinder unbinder;
    @BindView(R.id.pb_web_progress)
    WebView pbWebProgress;
    /*@BindView(R.id.tvshenfenzhenhao)
    TextView tvshenfenzhenhao;*/

    public static TestFragmentD newInstance() {
        return new TestFragmentD();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_d;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_test_d_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        if(MyApplication.getInstance().getLoginInfo()!=null){
            TStudent loginInfo =MyApplication.getInstance().getLoginInfo();

        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
               /* intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://hrss.tj.gov.cn/ecdomain/framework/tj/index.jsp");
                intent.setData(content_url);*/

                startActivity(intent);
                break;
            case R.id.tv2:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv3:
                toast("退出成功");
                MyApplication.getInstance().setLoginInfo(null);
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
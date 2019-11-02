package com.hjq.demo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hjq.demo.R;
import com.hjq.demo.common.MyLazyFragment;
import com.hjq.dialog.MessageDialog;
import com.hjq.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目自定义控件展示
 */
public class TestFragmentB extends MyLazyFragment
        implements SwitchButton.OnCheckedChangeListener {


    @BindView(R.id.b5)
    Button b5;
    @BindView(R.id.b4)
    Button b4;
    @BindView(R.id.b3)
    Button b3;
    @BindView(R.id.b2)
    Button b2;
    @BindView(R.id.b1)
    Button b1;
    Unbinder unbinder;

    public static TestFragmentB newInstance() {
        return new TestFragmentB();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_b;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_test_b_title;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.b5, R.id.b4, R.id.b3, R.id.b2, R.id.b1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b5:
            case R.id.b4:
            case R.id.b3:
            case R.id.b2:
            case R.id.b1:

                new MessageDialog.Builder(getActivity())
                        .setTitle("预约") // 标题可以不用填写
                        .setMessage("是否预约黄医生")
                        .setConfirm("确定")
                        .setCancel("取消") // 设置 null 表示不显示取消按钮
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(Dialog dialog) {
                                toast("已经预约上");
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("预约失败");
                            }
                        })
                        .show();
                break;
        }
    }
}
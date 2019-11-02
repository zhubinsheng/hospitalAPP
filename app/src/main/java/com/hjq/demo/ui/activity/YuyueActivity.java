package com.hjq.demo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class YuyueActivity extends MyActivity {
    @BindView(R.id.cardView1)
    CardView cardView;
    @Override
    protected int getLayoutId() {
        return R.layout.yuyue_type;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.cardView1})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardView1:
                Intent intent = new Intent(this,KeshiItemActivity.class);
                startActivityFinish(intent);
        }
    }
}

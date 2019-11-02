package com.hjq.demo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HospitalSpecificActivity extends MyActivity {

    @BindView(R.id.ToOfficeWebsite)
    LinearLayout linearLayout ;

    @BindView(R.id.ToMap)
    Button ToMap;

    @Override
    protected int getLayoutId() {
        return R.layout.hospital_specific;
    }

    @OnClick({R.id.ToOfficeWebsite,R.id.ToMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ToOfficeWebsite:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://hrss.tj.gov.cn/ecdomain/framework/tj/index.jsp");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.ToMap:
                Intent intent2 = new Intent("android.intent.action.VIEW"/*, Uri.parse(stringBuffer.toString())*/);
                intent2.setPackage("com.autonavi.minimap");
                startActivity(intent2);
                break;
            case R.id.tv3:
                toast("退出成功");
                break;
        }
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
}

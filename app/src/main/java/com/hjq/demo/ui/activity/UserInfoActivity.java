package com.hjq.demo.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.helper.ActivityStackManager;
import com.hjq.demo.helper.CacheDataManager;
import com.hjq.demo.vo.TStudent;
import com.hjq.demo.widget.SettingBar;
import com.hjq.image.ImageLoader;
import com.hjq.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends MyActivity
        implements SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.textView1)
TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @Override
    protected int getLayoutId() {
        return R.layout.user_info;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_setting_title;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

   /* @OnClick({R.id.sb_setting_language, R.id.sb_setting_update, R.id.sb_setting_agreement, R.id.sb_setting_about,
            R.id.sb_setting_cache, R.id.sb_setting_auto, R.id.sb_setting_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_setting_language:
                break;
            case R.id.sb_setting_update:
                break;
            case R.id.sb_setting_agreement:
                startActivity(WebActivity.class);
                break;
            case R.id.sb_setting_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.sb_setting_auto: // 自动登录
                mAutoSwitchView.setChecked(!mAutoSwitchView.isChecked());
                break;
            case R.id.sb_setting_cache: // 清空缓存
                ImageLoader.clear(this);
                CacheDataManager.clearAllCache(this);
                // 重新获取应用缓存大小
                mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
                break;
            case R.id.sb_setting_exit: // 退出登录
                startActivity(LoginActivity.class);
                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities(LoginActivity.class);
                break;
            default:
                break;
        }
    }

    *//***/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().onActivityCreated(this);
        if (MyApplication.getInstance().getLoginInfo()==null){
            toast("请先登录");
            return;
        }else {
            TStudent loginInfo = new TStudent();
            loginInfo=MyApplication.getInstance().getLoginInfo();
            textView1.setText(loginInfo.getStudentName());
            textView2.setText(loginInfo.getStudentSex());
            textView3.setText(loginInfo.getScore1());
            textView4.setText(loginInfo.getScore2());
        }

       /* mRightView.setText(MyApplication.getInstance().getLoginInfo().getStudentName());*/
    }


    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}

package com.hjq.demo.common;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.hjq.demo.vo.TStudent;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目中的Application基类
 */
public class MyApplication extends UIApplication {
    private TStudent loginInfo;
    private String hospitalname;
    private String keid;
    private Integer huid;
    private String kename;
    private static MyApplication instance;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyApplication.context = this.getApplicationContext();
        // 为了优化启动速度，请将一些没必须一定要在 Application 初始化的第三方框架移步至 LauncherActivity 中的 initData 方法中

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        MultiDex.install(this);
    }
    public static MyApplication getInstance() {
        return instance;
    }
    public TStudent getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(TStudent loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getKeid() {
        return keid;
    }

    public void setKeid(String keid) {
        this.keid = keid;
    }

    public Integer getHuid() {
        return huid;
    }

    public void setHuid(Integer huid) {
        this.huid = huid;
    }

    public String getKename() {
        return kename;
    }

    public void setKename(String kename) {
        this.kename = kename;
    }
}
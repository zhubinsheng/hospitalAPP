package com.hjq.demo.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.vo.BadCode;
import com.hjq.demo.vo.ResultObject;
import com.hjq.demo.vo.TStudent;
import com.hjq.demo.vo.UserVo;
import com.hjq.toast.ToastUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 登录界面
 */
public class LoginActivity extends MyActivity {

    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;

    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    private InputTextHelper mInputTextHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_login_title;
    }

    @Override
    protected void initView() {
        mInputTextHelper = new InputTextHelper(mCommitView);
        mInputTextHelper.addViews(mPhoneView, mPasswordView);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        // 跳转到注册界面
        startActivity(RegisterActivity.class);

//        startActivityForResult(new Intent(this, RegisterActivity.class), new ActivityCallback() {
//
//            @Override
//            public void onActivityResult(int resultCode, @Nullable Intent data) {
//                toast(String.valueOf(resultCode));
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        mInputTextHelper.removeViews();
        super.onDestroy();
    }

    @Override
    public boolean isSupportSwipeBack() {
        //不使用侧滑功能
        return !super.isSupportSwipeBack();
    }

    @OnClick({R.id.tv_login_forget, R.id.btn_login_commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:
                startActivity(PasswordForgetActivity.class);
                break;
            case R.id.btn_login_commit:
                /*if (mPhoneView.getText().toString().length() != 11) {
                    ToastUtils.show(getResources().getString(R.string.phone_input_error));
                    break;
                }else */
                    String acc = mPhoneView.getText().toString();
                    String pss = mPasswordView.getText().toString();

                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userName", acc)
                            .add("password", pss)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://192.168.43.43:8088/user/studentLogin")
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
                            Gson gsonbad = new Gson();
                            BadCode badCode = gsonbad.fromJson(stirng,BadCode.class);
                            if (!badCode.getCode().equals("0")){
                                toast("登录失败，请验证帐号密码");
                            }else {
                                Gson gson = new Gson();
                                Type userType=new TypeToken<ResultObject<List<TStudent>>>(){}.getType();
                                ResultObject<List<TStudent>> userResult=gson.fromJson(stirng,userType);
                                List<TStudent> userVos =userResult.getData();
                                TStudent userVo = userVos.get(0);
                                //此处 保存信息持久化
                                MyApplication.getInstance().setLoginInfo(userVo);
                                if (userVo!=null){
                                    toast("登陆成功");
                                    startActivityFinish(HomeActivity.class);
                                }else {toast("无此账号，请先注册");}
                            }


                        }
                    });


                break;

            default:
                break;
        }
    }
}
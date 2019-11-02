package com.hjq.demo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.vo.BadCode;
import com.hjq.demo.vo.ResultObject;
import com.hjq.demo.vo.UserVo;
import com.hjq.widget.CountdownView;

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
 *    desc   : 注册界面
 */
public class RegisterActivity extends MyActivity {

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;//身份证号码
    /*@BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;*/

    @BindView(R.id.et_register_code)
    EditText mCodeView; //医保卡号

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;//姓名
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;//年龄
    @BindView(R.id.et_register_password3)
    EditText mPasswordView3;//性别
    @BindView(R.id.et_register_password4)
    EditText mPasswordView4;//密码

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    private InputTextHelper mInputTextHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_register_title;
    }

    @Override
    protected void initView() {
        mInputTextHelper = new InputTextHelper(mCommitView);
        mInputTextHelper.addViews(mPhoneView, mCodeView, mPasswordView1, mPasswordView2);
    }

    @Override
    protected void initData() {
//        getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finishResult(RESULT_OK);
//            }
//        }, 2000);
    }

    @OnClick({ R.id.btn_register_commit})
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.cv_register_countdown: //获取验证码

                if (mPhoneView.getText().toString().length() != 11) {
                    // 重置验证码倒计时控件
                    mCountdownView.resetState();
                    toast(getResources().getString(R.string.phone_input_error));
                    break;
                }

                toast(getResources().getString(R.string.countdown_code_send_succeed));

                break;*/
            case R.id.btn_register_commit: //提交注册

                /*if (mPhoneView.getText().toString().length() != 11) {
                    toast(getResources().getString(R.string.phone_input_error));
                    break;
                }

                if (!mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString())) {
                    toast(getResources().getString(R.string.two_password_input_error));
                    break;
                }*/
               String  xingming = mPasswordView1.getText().toString();
               String niangling = mPasswordView2.getText().toString();
               String xingbie =mPasswordView3.getText().toString();
                String yibaokahao =mCodeView.getText().toString(); //医保卡号;//身份证号码
                String shenfenzhenhao = mPhoneView.getText().toString();
                String mima  = mPasswordView4.getText().toString();
if (xingming==null||niangling==null||xingbie==null||yibaokahao==null||shenfenzhenhao==null||xingming==""||niangling==""||xingbie==""||yibaokahao==""||shenfenzhenhao==""||mima==null||mima==""){
    toast("请填写完整");
    return;
}

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("xingming", xingming)
                        .add("niangling", niangling)
                        .add("xingbie", xingbie)
                        .add("yibaokahao", yibaokahao)
                        .add("shenfenzhenhao", shenfenzhenhao)
                        .add("mima", mima)
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.43.43:8088/user/studentLogon")
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
                        if (badCode.getCode().equals("0")){
                            toast(badCode.getMsg());
                            startActivityFinish(LoginActivity.class);
                        }else {
                               /* Gson gson = new Gson();
                                Type userType=new TypeToken<ResultObject<List<UserVo>>>(){}.getType();
                                ResultObject<List<UserVo>> userResult=gson.fromJson(stirng,userType);
                                List<UserVo> userVos =userResult.getData();
                                UserVo userVo = userVos.get(0);
                                //此处 保存信息持久化
                                if (userVo!=null){
                                    toast("登陆成功");
                                    startActivityFinish(HomeActivity.class);
                                }else {toast("无此账号，请先注册");}*/
                            toast(badCode.getMsg());
                        }
                    }
                });





                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mInputTextHelper.removeViews();
        super.onDestroy();
    }
}
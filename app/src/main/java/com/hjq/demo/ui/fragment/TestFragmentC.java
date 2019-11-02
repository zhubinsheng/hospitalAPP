package com.hjq.demo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyLazyFragment;
import com.hjq.demo.ui.adapter.RecordAdapter;
import com.hjq.demo.ui.adapter.RecordQueryAdapter;
import com.hjq.demo.vo.HuShiVo;
import com.hjq.demo.vo.RecordVo;
import com.hjq.demo.vo.ResultObject;
import com.hjq.dialog.MessageDialog;

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
 * desc   : 项目框架使用示例
 */
public class TestFragmentC extends MyLazyFragment {


    Unbinder unbinder;
    @BindView(R.id.record)
    ListView record;

    @BindView(R.id.button)
    Button button;



    private List<RecordVo> recordVos = new ArrayList<>();

    private RecordAdapter adapter;
    private static final int UPDATE_TEXT = 1 ;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    adapter =new RecordAdapter(getContext(),R.layout.fragment_test_a,recordVos);
                    record.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };







    public static TestFragmentC newInstance() {
        return new TestFragmentC();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_c;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_test_c_title;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        if (MyApplication.getInstance().getLoginInfo()!=null){
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("user", MyApplication.getInstance().getLoginInfo().getStudentName())
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.43:8088/HuiShi/record")
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
                    Type userType=new TypeToken<ResultObject<List<RecordVo>>>(){}.getType();
                    ResultObject<List<RecordVo>> userResult=gson.fromJson(stirng,userType);
                    recordVos =userResult.getData();
                /*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*/
                    if (recordVos!=null){
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }

                }
            });
        }


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (MyApplication.getInstance().getLoginInfo()!=null){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("user",MyApplication.getInstance().getLoginInfo().getScore1())
                            .build();
                    Request request = new Request.Builder()
                            .url("http://192.168.43.43:8088/HuiShi/selectrecord")
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
                            Type userType=new TypeToken<ResultObject<List<RecordVo>>>(){}.getType();
                            ResultObject<List<RecordVo>> userResult=gson.fromJson(stirng,userType);
                            recordVos =userResult.getData();
                /*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*/
                            if (recordVos!=null){
                                Message message = new Message();
                                message.what = UPDATE_TEXT;
                                handler.sendMessage(message);
                            }

                        }
                    });
                }
                break;
        }
    }

}
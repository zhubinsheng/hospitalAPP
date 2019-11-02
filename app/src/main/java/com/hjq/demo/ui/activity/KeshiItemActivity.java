package com.hjq.demo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.helper.ActivityStackManager;
import com.hjq.demo.ui.adapter.KeshiQueryAdapter;
import com.hjq.demo.vo.KeShiVo;
import com.hjq.demo.vo.ResultObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KeshiItemActivity extends MyActivity {
    @BindView(R.id.tv_test_search)
    ListView listViewForScrollView;
    @BindView(R.id.hospitalname)
    TextView hospitalname;
    private List<KeShiVo> keShiVo;
    private static final int UPDATE_TEXT = 1 ;
    private KeshiQueryAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    adapter =new KeshiQueryAdapter(getContext(),R.layout.keshi_select,keShiVo);
                    listViewForScrollView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.keshi_select;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().onActivityCreated(this);
        hospitalname.setText(MyApplication.getInstance().getHospitalname());

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("keid",MyApplication.getInstance().getKeid())
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.43:8088/HuiShi/selectKe")
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
                Type userType=new TypeToken<ResultObject<List<KeShiVo>>>(){}.getType();
                ResultObject<List<KeShiVo>> userResult=gson.fromJson(stirng,userType);
                keShiVo =userResult.getData();
                /*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*/
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);

            }
        });



    }
}

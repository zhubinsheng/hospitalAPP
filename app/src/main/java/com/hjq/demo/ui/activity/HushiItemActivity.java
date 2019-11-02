package com.hjq.demo.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.helper.ActivityStackManager;
import com.hjq.demo.vo.HuShiVo;
import com.hjq.demo.vo.ResultObject;
import com.hjq.dialog.MessageDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
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

public class HushiItemActivity extends MyActivity {
    @BindView(R.id.tv_test_search)
    ListView listViewForScrollView;
    @BindView(R.id.Keshiname)
    TextView Keshiname;
    private List<HuShiVo> huShiVos;
    private static final int UPDATE_TEXT = 1 ;
    private HushiQueryAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    adapter =new HushiQueryAdapter(getContext(),R.layout.hushi_select,huShiVos);
                    listViewForScrollView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected int getLayoutId()  {
        return R.layout.hushi_select;
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
        Keshiname.setText(MyApplication.getInstance().getKename());

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("huid", String.valueOf(MyApplication.getInstance().getHuid()))
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.43.43:8088/HuiShi/selectAll")
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
                Type userType=new TypeToken<ResultObject<List<HuShiVo>>>(){}.getType();
                ResultObject<List<HuShiVo>> userResult=gson.fromJson(stirng,userType);
                huShiVos =userResult.getData();
                /*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*/
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);

            }
        });
    }

    public class HushiQueryAdapter extends ArrayAdapter<HuShiVo> {
        ViewHolder viewHolder;
        private Context context;
        private int resourceId;
        public HushiQueryAdapter(Context context, int textViewResourceId, List<HuShiVo> objects) {
            super(context,textViewResourceId,objects);
            resourceId = textViewResourceId;
        }
        @Override
        public View getView(int position , View convertView , ViewGroup parent) {
            HuShiVo huShiVo = getItem(position);
            /*MyListner myListner;*/

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.hushi_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tvname = (TextView)convertView.findViewById(R.id.tvname) ;
                viewHolder.introduce = (TextView)convertView.findViewById(R.id.introduce) ;
                viewHolder.button5 =(Button) convertView.findViewById(R.id.b5);
                /*viewHolder.b5 = (Button) convertView.findViewById(R.id.b5) ;*/
            /*viewHolder.yincangid =(TextView) convertView.findViewById(R.id.yincangid);
            viewHolder.textView3 =(TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 =(TextView) convertView.findViewById(R.id.textView4);
            viewHolder.textView5 =(TextView) convertView.findViewById(R.id.textView5);

            viewHolder.linearLayout =(View) convertView.findViewById(R.id.linearLayout);*/
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            assert huShiVo != null;
            viewHolder.tvname.setText(huShiVo.getName());
            viewHolder.introduce.setText(huShiVo.getIntroduce());
            viewHolder.button5.setOnClickListener(new MyListner(position));

       /* viewHolder.yincangid.setText(String.valueOf(logRelateDto.getId()));
        viewHolder.textView3.setText(logRelateDto.getOrderNo());
        viewHolder.textView4.setText(String.valueOf(logRelateDto.getActualNum()));
        viewHolder.textView5.setText(logRelateDto.getCreateTime());
        viewHolder.button5.setVisibility(View.GONE);*/
            /* context=MyApplication.getContext();*/

            return convertView;
        }

        class ViewHolder {
            TextView tvname;
            TextView introduce;
            TextView textView4;
            TextView textView5;
            Button button5;
            View linearLayout;

        }
        private class MyListner implements View.OnClickListener {
            int mPosition;
            public MyListner(int inPosition){
                mPosition = inPosition;
            }

            @Override
            public void onClick(View v) {


                if(
                        MyApplication.getInstance().getLoginInfo()==null
                ){
                    toast("请先登录");
                    return;
                }
                HuShiVo huShiVo = getItem(mPosition);
          /*  KeShiVo hospitalVo = getItem(mPosition);
            MyApplication.getInstance().setHuid(hospitalVo.getHuid());
            MyApplication.getInstance().setKename(hospitalVo.getKename());
            Intent intent1 = new Intent();

            intent1.setClass(getContext(), YuyueActivity.class);
            getContext().startActivity(intent1);*/
                new MessageDialog.Builder(getActivity())
                        .setTitle("预约") // 标题可以不用填写
                        .setMessage("是否预约医生")
                        .setConfirm("确定")
                        .setCancel("取消") // 设置 null 表示不显示取消按钮
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(Dialog dialog) {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                RequestBody requestBody = new FormBody.Builder()
                                        .add("user",MyApplication.getInstance().getLoginInfo().getScore1())
                                        .add("hushi", String.valueOf(huShiVo.getId()))
                                        .add("keshi",MyApplication.getInstance().getKename())
                                        .add("hushiname",huShiVo.getName())
                                        .add("bianhao", String.valueOf(Calendar.getInstance().getTimeInMillis()))
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
                                        /*Headers headers = response.headers();
                                        String stirng =response.body().string();
                                        Gson gson = new Gson();
                                        Type userType=new TypeToken<ResultObject<List<HuShiVo>>>(){}.getType();
                                        ResultObject<List<HuShiVo>> userResult=gson.fromJson(stirng,userType);
                                        huShiVos =userResult.getData();
                *//*adapter =new RecordQueryAdapter(getContext(),R.layout.fragment_test_a,hospitalVos);
                listViewForScrollView.setAdapter(adapter);*//*
                                        Message message = new Message();
                                        message.what = UPDATE_TEXT;
                                        handler.sendMessage(message);*/

                                    }
                                });
                                toast("已经预约上");
                                finish();
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("预约失败");
                            }
                        })
                        .show();

            }
        }
    }





}



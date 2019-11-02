package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.ui.activity.HushiItemActivity;
import com.hjq.demo.ui.activity.YuyueActivity;
import com.hjq.demo.vo.HospitalVo;
import com.hjq.demo.vo.KeShiVo;

import java.util.List;


public class KeshiQueryAdapter extends ArrayAdapter<KeShiVo> {
    ViewHolder viewHolder;
    private Context context;
    private int resourceId;
    public KeshiQueryAdapter(Context context, int textViewResourceId, List<KeShiVo> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        KeShiVo hospitalVo = getItem(position);
        /*MyListner myListner;*/

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.keshi_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvname = (TextView)convertView.findViewById(R.id.textView) ;
            /*viewHolder.b5 = (Button) convertView.findViewById(R.id.b5) ;*/
            /*viewHolder.yincangid =(TextView) convertView.findViewById(R.id.yincangid);
            viewHolder.textView3 =(TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 =(TextView) convertView.findViewById(R.id.textView4);
            viewHolder.textView5 =(TextView) convertView.findViewById(R.id.textView5);
            viewHolder.button5 =(Button) convertView.findViewById(R.id.button5);
            viewHolder.linearLayout =(View) convertView.findViewById(R.id.linearLayout);*/
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        assert hospitalVo != null;
        viewHolder.tvname.setText(hospitalVo.getKename());
        viewHolder.tvname.setOnClickListener(new MyListner(position));

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
        TextView textView3;
        TextView textView4;
        TextView textView5;
        Button b5;
        View linearLayout;

    }
    private class MyListner implements View.OnClickListener {
        int mPosition;
        public MyListner(int inPosition){
            mPosition = inPosition;
        }

        @Override
        public void onClick(View v) {
            KeShiVo hospitalVo = getItem(mPosition);
            MyApplication.getInstance().setHuid(hospitalVo.getHuid());
            MyApplication.getInstance().setKename(hospitalVo.getKename());
            Intent intent=new Intent(getContext(),HushiItemActivity.class);//你要跳转的界面
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }
}

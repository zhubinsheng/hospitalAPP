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
import com.hjq.demo.ui.activity.YuyueActivity;
import com.hjq.demo.vo.HospitalVo;
import com.hjq.demo.vo.RecordVo;

import java.util.List;


public class RecordAdapter extends ArrayAdapter<RecordVo> {
    ViewHolder viewHolder;
    private Context context;
    private int resourceId;
    public RecordAdapter(Context context, int textViewResourceId, List<RecordVo> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        RecordVo recordVo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvname2 =(TextView) convertView.findViewById(R.id.tvname2);
            viewHolder.textView3 =(TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 =(TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        assert recordVo != null;
        viewHolder.tvname2.setText(recordVo.getHushiname()+"预约成功");
        viewHolder.textView3.setText("订单编号："+recordVo.getBianhao());
        viewHolder.textView4.setText(recordVo.getKeshi());
        return convertView;
    }

    class ViewHolder {
        TextView tvname2;
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

        }
    }
}

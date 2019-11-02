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
import com.hjq.demo.ui.activity.HospitalSpecificActivity;
import com.hjq.demo.ui.activity.PasswordForgetActivity;
import com.hjq.demo.ui.activity.YuyueActivity;
import com.hjq.demo.vo.HospitalVo;
import java.util.List;



public class RecordQueryAdapter  extends ArrayAdapter<HospitalVo> {
    ViewHolder viewHolder;
    private Context context;
    private int resourceId;
    public RecordQueryAdapter(Context context, int textViewResourceId, List<HospitalVo> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        HospitalVo hospitalVo = getItem(position);
        /*MyListner myListner;*/

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_copy, null);
            viewHolder = new ViewHolder();
            viewHolder.tvname = (TextView)convertView.findViewById(R.id.tvname) ;
            viewHolder.b5 = (Button) convertView.findViewById(R.id.b5) ;
            /*viewHolder.waytogospital = (TextView) convertView.findViewById(R.id.waytogospital) ;*/
            viewHolder.linearLayout =(View) convertView.findViewById(R.id.ToSpecific);

            /*viewHolder.yincangid =(TextView) convertView.findViewById(R.id.yincangid);
            viewHolder.textView3 =(TextView) convertView.findViewById(R.id.textView3);
            viewHolder.textView4 =(TextView) convertView.findViewById(R.id.textView4);
            viewHolder.textView5 =(TextView) convertView.findViewById(R.id.textView5);
            viewHolder.button5 =(Button) convertView.findViewById(R.id.button5);
           ;*/
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        assert hospitalVo != null;
        viewHolder.tvname.setText(hospitalVo.getHosname());
        viewHolder.b5.setOnClickListener(new MyListner(position));
        viewHolder.linearLayout.setOnClickListener(new MyListner(position));
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
        TextView waytogospital;
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
            switch (v.getId()) {
                case R.id.ToSpecific:
                    Intent intent = new Intent();
                    intent.setClass(getContext(), HospitalSpecificActivity.class);
                    getContext().startActivity(intent);
                    break;

                case R.id.b5:
                    HospitalVo hospitalVo = getItem(mPosition);
                    MyApplication.getInstance().setHospitalname(hospitalVo.getHosname());
                    MyApplication.getInstance().setKeid(hospitalVo.getKeid());
                    Intent intent1 = new Intent();
                    intent1.setClass(getContext(), YuyueActivity.class);
                    getContext().startActivity(intent1);
                    break;
            }


        }
    }
}

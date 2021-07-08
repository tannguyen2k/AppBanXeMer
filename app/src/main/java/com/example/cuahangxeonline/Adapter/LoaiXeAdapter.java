package com.example.cuahangxeonline.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.cuahangxeonline.Model.LoaiXe;
import com.example.cuahangxeonline.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class LoaiXeAdapter extends BaseAdapter {

    ArrayList<LoaiXe> loaiXeArrayList;
    Context context;

    public LoaiXeAdapter(ArrayList<LoaiXe> loaiXeArrayList, Context context) {
        this.loaiXeArrayList = loaiXeArrayList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return loaiXeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiXeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_loaixe, null);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.tvloaixe);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageloaixe);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaiXe loaiXe = (LoaiXe) getItem(position);
        viewHolder.textView.setText(loaiXe.getTenloai());
        Picasso.with(context).load(loaiXe.getHinhanh()).placeholder(R.drawable.ic_baseline_image_24).into(viewHolder.imageView);
        return convertView;
    }
}

package com.example.cuahangxeonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangxeonline.Model.LoaiXe;
import com.example.cuahangxeonline.Model.XeMoi;
import com.example.cuahangxeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SedanAdapter extends BaseAdapter {
    Context context;
    ArrayList<XeMoi> listsedan;

    public SedanAdapter(Context context, ArrayList<XeMoi> listsedan) {
        this.context = context;
        this.listsedan = listsedan;
    }

    @Override
    public int getCount() {
        return listsedan.size();
    }

    @Override
    public Object getItem(int position) {
        return listsedan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder
    {
        public TextView txttenxe, txtgiaxe;
        public ImageView hinhxe;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new SedanAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_xesedan, null);
            viewHolder.txttenxe = (TextView)convertView.findViewById(R.id.tvtenxesedan);
            viewHolder.txtgiaxe = (TextView)convertView.findViewById(R.id.tvgiaxesedan);
            viewHolder.hinhxe = (ImageView)convertView.findViewById(R.id.imagexesedan);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (SedanAdapter.ViewHolder) convertView.getTag();
        }
        XeMoi xeMoi = (XeMoi) getItem(position);
        viewHolder.txttenxe.setText(xeMoi.getTenxe());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiaxe.setText("Giá: "+decimalFormat.format(xeMoi.getGia())+ "VNĐ");
        Picasso.with(context).load(xeMoi.getHinhanh()).placeholder(R.drawable.ic_baseline_image_24).into(viewHolder.hinhxe);
        return convertView;
    }
}

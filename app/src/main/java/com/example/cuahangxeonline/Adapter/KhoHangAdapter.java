package com.example.cuahangxeonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangxeonline.Model.GioHang;
import com.example.cuahangxeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KhoHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public KhoHangAdapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHoler
    {
        ImageView hinhxekhohang;
        TextView tenxekhohang, giaxekhohang;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler = null;
        if (convertView  == null)
        {
            viewHoler = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_giohang, null);
            viewHoler.hinhxekhohang = convertView.findViewById(R.id.imagekhohang);
            viewHoler.tenxekhohang = convertView.findViewById(R.id.tvtenxekhohang);
            viewHoler.giaxekhohang = convertView.findViewById(R.id.tvgiaxekhohang);
            convertView.setTag(viewHoler);
        }
        else
        {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        GioHang gioHang = (GioHang) getItem(position);
        viewHoler.tenxekhohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoler.giaxekhohang.setText(decimalFormat.format(gioHang.getGiasp())+"VNĐ");
        Picasso.with(context).load(gioHang.getHinhsp()).placeholder(R.drawable.ic_baseline_image_24).into(viewHoler.hinhxekhohang);

        return convertView;
    }
}

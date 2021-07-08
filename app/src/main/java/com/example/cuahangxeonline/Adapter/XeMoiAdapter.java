package com.example.cuahangxeonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangxeonline.Activity.ChiTietXeActivity;
import com.example.cuahangxeonline.Model.XeMoi;
import com.example.cuahangxeonline.R;
import com.example.cuahangxeonline.Util.KiemtraKetnoi;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XeMoiAdapter extends RecyclerView.Adapter<XeMoiAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<XeMoi> xeMoiArrayList;

    public XeMoiAdapter(Context context, ArrayList<XeMoi> xeMoiArrayList) {
        this.context = context;
        this.xeMoiArrayList = xeMoiArrayList;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_xemoi, null);
        KHUNGNHIN khungnhin = new KHUNGNHIN(v);
        return khungnhin;
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        XeMoi xeMoi  = xeMoiArrayList.get(position);
        holder.tenxe.setText(xeMoi.getTenxe());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giaxe.setText("Giá: "+decimalFormat.format(xeMoi.getGia())+"VNĐ");
        Picasso.with(context).load(xeMoi.getHinhanh()).placeholder(R.drawable.ic_baseline_image_24).into(holder.imageXe);
    }

    @Override
    public int getItemCount() {
        return xeMoiArrayList.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        public ImageView imageXe;
        public TextView tenxe, giaxe;
        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            imageXe = itemView.findViewById(R.id.imagexemoi);
            tenxe = itemView.findViewById(R.id.tenxemoi);
            giaxe = itemView.findViewById(R.id.giaxemoi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietXeActivity.class);
                    intent.putExtra("thongtinxe", xeMoiArrayList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    KiemtraKetnoi.ThongBaoNgan(context,xeMoiArrayList.get(getPosition()).getTenxe());
                    context.startActivity(intent);
                }
            });
        }
    }
}

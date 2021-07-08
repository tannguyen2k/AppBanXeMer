package com.example.cuahangxeonline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuahangxeonline.Adapter.KhoHangAdapter;
import com.example.cuahangxeonline.Model.GioHang;
import com.example.cuahangxeonline.R;

import java.text.DecimalFormat;


public class GioHangActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    TextView tvkhotrong;
    TextView tvtongtien;
    Button buttonthemxe, buttonthanhtoan;
    KhoHangAdapter khoHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        ActionToolBar();
        CheckKhoxe();
        TinhTongTien();
    }

    private void TinhTongTien() {
        double tongtien=0;
        for (int i=0; i<MainActivity.gioHangs.size(); i++)
        {
            tongtien += MainActivity.gioHangs.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvtongtien.setText(decimalFormat.format(tongtien)+ "VNĐ");
    }

    private void CheckKhoxe() {
        if(MainActivity.gioHangs.size() <=0)
        {
            khoHangAdapter.notifyDataSetChanged();
            tvkhotrong.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            khoHangAdapter.notifyDataSetChanged();
            tvkhotrong.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        listView = findViewById(R.id.lvkhohang);
        toolbar = findViewById(R.id.tbgiohang);
        tvkhotrong = findViewById(R.id.tvkhohang);
        tvtongtien = findViewById(R.id.tvtongtienkhoxe);
        khoHangAdapter = new KhoHangAdapter(GioHangActivity.this, MainActivity.gioHangs);
        listView.setAdapter(khoHangAdapter);
    }
}
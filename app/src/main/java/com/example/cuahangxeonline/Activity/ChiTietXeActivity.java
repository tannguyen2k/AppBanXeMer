package com.example.cuahangxeonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangxeonline.Model.GioHang;
import com.example.cuahangxeonline.Model.XeMoi;
import com.example.cuahangxeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietXeActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    TextView textViewTen, textViewGia, textVieưMota;
    Button buttonThem;
    String Idxe;
    String TenChiTiet;
    double GiaChiTiet = 0;
    int SoLuongTon = 0;
    String MaLoai;
    String Ngay;
    String HinhAnh;
    String MoTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_xe);
        AnhXa();
        ActionToolBar();
        GetChiTietXe();
        ClickButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.khoxe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.khoxe:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    private void ClickButton() {
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.gioHangs.size()>0)
                {
                    boolean exists = false;
                    for (int i=0; i< MainActivity.gioHangs.size(); i ++)
                    {
                        if(MainActivity.gioHangs.get(i).getIDsp() == Idxe)
                        {
                            AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());

                            b.setTitle("Thông báo");
                            b.setMessage("Bạn đã có xe này trong kho hàng rồi");
                            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });
                            exists = true;
                        }

                    }
                    if (exists==false)
                    {
                        int soluong = 1;
                        MainActivity.gioHangs.add(new GioHang(Idxe, TenChiTiet, GiaChiTiet, HinhAnh, soluong));
                    }
                }
                else
                {
                    int soluong = 1;
                    MainActivity.gioHangs.add(new GioHang(Idxe, TenChiTiet, GiaChiTiet, HinhAnh, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetChiTietXe() {

        XeMoi xeMoi = (XeMoi) getIntent().getSerializableExtra("thongtinxe");
        Idxe = xeMoi.getIdxe();
        TenChiTiet = xeMoi.getTenxe();
        GiaChiTiet = xeMoi.getGia();
        SoLuongTon = xeMoi.getSoluongton();
        MaLoai = xeMoi.getMaloai();
        Ngay = xeMoi.getNgay();
        HinhAnh = xeMoi.getHinhanh();
        MoTa = xeMoi.getMota();
        textViewTen.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewGia.setText("Giá: "+decimalFormat.format(GiaChiTiet)+ "VNĐ");
        textVieưMota.setText(MoTa);
        Picasso.with(getApplicationContext()).load(HinhAnh).placeholder(R.drawable.ic_baseline_image_24).into(imageView);

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
        toolbar = findViewById(R.id.toolbarchitiet);
        imageView = findViewById(R.id.imagechitietxe);
        textViewTen = findViewById(R.id.tvtenchitietxe);
        textViewGia = findViewById(R.id.tvgiachitietxe);
        textVieưMota = findViewById(R.id.tvmotachitietxe);
        buttonThem = findViewById(R.id.btnthemvaokho);
    }
}
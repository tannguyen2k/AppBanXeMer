package com.example.cuahangxeonline.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangxeonline.Adapter.LoaiXeAdapter;
import com.example.cuahangxeonline.Adapter.XeMoiAdapter;
import com.example.cuahangxeonline.Model.GioHang;
import com.example.cuahangxeonline.Model.LoaiXe;
import com.example.cuahangxeonline.Model.XeMoi;
import com.example.cuahangxeonline.R;
import com.example.cuahangxeonline.Util.KiemtraKetnoi;
import com.example.cuahangxeonline.Util.Sever;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<LoaiXe> loaiXeArrayList;
    LoaiXeAdapter loaiXeAdapter;
    ArrayList<XeMoi> xeMoiArrayList;
    XeMoiAdapter xeMoiAdapter;
    public static ArrayList<GioHang> gioHangs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ActionViewFlipper();
            LayChuDe();
            LayXeMoi();
            ChuyenManHinh();
        }
        else
        {
            KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Không có kết nối mạng");
            finish();;
        }
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

    private void ChuyenManHinh() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Vui lòng kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, SedanActivity.class);
                            intent.putExtra("maloai", loaiXeArrayList.get(position).getMaloai());
                            startActivity(intent);
                        } else {
                            KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Vui lòng kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, SUVAcvtivity.class);
                            intent.putExtra("maloai", loaiXeArrayList.get(position).getMaloai());
                            startActivity(intent);
                        } else {
                            KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Vui lòng kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, AMGActivity.class);
                            intent.putExtra("maloai", loaiXeArrayList.get(position).getMaloai());
                            startActivity(intent);
                        } else {
                            KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Vui lòng kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void LayXeMoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (Sever.UrlXemoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null)
                {
                    for (int i = 0 ; i < response.length(); i ++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            xeMoiArrayList.add(new XeMoi(
                                    jsonObject.getString("idxe"),
                                    jsonObject.getString("tenxe"),
                                    jsonObject.getDouble("gia"),
                                    jsonObject.getInt("soluongton"),
                                    jsonObject.getString("maloai"),
                                    jsonObject.getString("ngay"),
                                    jsonObject.getString("hinhanh"),
                                    jsonObject.getString("mota")));

                        }
                        catch ( JSONException e)
                        {
                            e.printStackTrace();
                        }
                        xeMoiAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void LayChuDe()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (Sever.UrlLoaiXe, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null)
                {
                    for (int i = 0 ; i < response.length(); i ++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            loaiXeArrayList.add(new LoaiXe (jsonObject.getString("maloai"), jsonObject.getString("tenloai"), jsonObject.getString("mota"), jsonObject.getString("hinhanh")));

                        }
                        catch ( JSONException e)
                        {
                            e.printStackTrace();
                        }
                        loaiXeAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private  void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ActionViewFlipper()
    {
        ArrayList<String> mangviewflipper = new ArrayList<>();
        mangviewflipper.add("https://www.bmw.vn/content/dam/bmw/common/home/teaser/bmw-g01-x3-teaser-image.jpg/_jcr_content/renditions/cq5dam.resized.img.1680.large.time1498575899659.jpg");
        mangviewflipper.add("https://www.bmw.vn/content/dam/bmw/common/all-models/3-series/sedan/2018/inspire/bmw-3series-3er-inspire-sp-xxl.jpg/_jcr_content/renditions/cq5dam.resized.img.1680.large.time1566827078382.jpg");
        mangviewflipper.add("https://www.bmw.vn/content/dam/bmw/common/home/teaser/bmw-7series-sedan-gklplus-teaser-desktop.jpg/_jcr_content/renditions/cq5dam.resized.img.1680.large.time1547636126503.jpg ");
        for (int i=0; i<mangviewflipper.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangviewflipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }
    private void AnhXa()
    {
        toolbar = findViewById(R.id.toolbarhome);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.rccvspmoi);
        navigationView = findViewById(R.id.nativationview);
        listView = findViewById(R.id.lisviewhome);
        drawerLayout = findViewById(R.id.drawerlayout);
        loaiXeArrayList = new ArrayList<>();
        loaiXeArrayList.add(0, new LoaiXe("0", "Trang Chủ", "", "https://static.carmudi.vn/wp-content/uploads/2016/04/Mercedes-Benz-Carmudi.jpg"));
        loaiXeAdapter = new LoaiXeAdapter(loaiXeArrayList, MainActivity.this);
        listView.setAdapter(loaiXeAdapter);
        xeMoiArrayList = new ArrayList<>();
        xeMoiAdapter = new XeMoiAdapter(getApplicationContext(), xeMoiArrayList);
        recyclerView.setAdapter(xeMoiAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        if (gioHangs !=null)
        {
        }
        else
        {
            gioHangs = new ArrayList<>();
        }
    }

}

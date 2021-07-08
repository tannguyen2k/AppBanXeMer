package com.example.cuahangxeonline.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangxeonline.Adapter.SUVAdapter;
import com.example.cuahangxeonline.Adapter.SedanAdapter;
import com.example.cuahangxeonline.Model.XeMoi;
import com.example.cuahangxeonline.R;
import com.example.cuahangxeonline.Util.KiemtraKetnoi;
import com.example.cuahangxeonline.Util.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SUVAcvtivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    SUVAdapter suvAdapter;
    ArrayList<XeMoi> xeSUVArrayList;
    String idloaixe;
    int page = 1;
    View footer;
    mHanler mHanler;
    boolean limitdata = false;
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_u_v_acvtivity);
        if (KiemtraKetnoi.haveNetworkConnection(getApplicationContext()))
        {
            AnhXa();
            GetIdLoaiXe();
            ActionToolBar();
            GetDuLieuVe(page);
            LoadThemXe();
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
    private void GetDuLieuVe(int _page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String Url = Sever.UrlXeSedan + String.valueOf(_page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length()!=2)
                {
                    listView.removeFooterView(footer);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0 ; i < jsonArray.length(); i ++)
                        { JSONObject jsonObject = jsonArray.getJSONObject(i);
                            xeSUVArrayList.add(new XeMoi(
                                    jsonObject.getString("idxe"),
                                    jsonObject.getString("tenxe"),
                                    jsonObject.getDouble("gia"),
                                    jsonObject.getInt("soluongton"),
                                    jsonObject.getString("maloai"),
                                    jsonObject.getString("ngay"),
                                    jsonObject.getString("hinhanh"),
                                    jsonObject.getString("mota")));
                            suvAdapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    limitdata = true;
                    listView.removeFooterView(footer);
                    KiemtraKetnoi.ThongBaoNgan(getApplicationContext(), "Đã hết xe trong kho");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("maloai", idloaixe);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void LoadThemXe() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getApplicationContext(), ChiTietXeActivity.class);
                intent.putExtra("thongtinxe",xeSUVArrayList.get(position));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount !=0 && isLoading == false && limitdata == false)
                {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }
    private void GetIdLoaiXe() {
        idloaixe = getIntent().getStringExtra("maloai");
        Log.d("Mã Loại ", idloaixe + "");
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.tbsuv);
        listView = findViewById(R.id.listviewsuv);
        xeSUVArrayList = new ArrayList<>();
        suvAdapter = new SUVAdapter(getApplicationContext(), xeSUVArrayList);
        listView.setAdapter(suvAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer = inflater.inflate(R.layout.processbar, null);
        mHanler = new mHanler();
    }
    public class mHanler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    listView.addFooterView(footer);
                    break;
                case 1:
                    GetDuLieuVe(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread
    {
        @Override
        public void run() {
            mHanler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHanler.obtainMessage(1);
            mHanler.sendMessage(message);
            super.run();
        }
    }
}
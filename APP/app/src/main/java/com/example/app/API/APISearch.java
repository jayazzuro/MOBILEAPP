package com.example.app.API;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.SanPham.SanPham;
import com.example.app.SanPhamAdapter.SanPhamAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class APISearch {

    // Hàm tìm kiếm sản phẩm theo keyword
    public static void searchProducts(Context context, String keyword, List<SanPham> sanPhamList, SanPhamAdapter adapter) {

        String url = "http://10.0.2.2:3000/api/Search?keyword=" + keyword;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    sanPhamList.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            SanPham sanPham = new SanPham(
                                    obj.getString("name"),
                                    obj.getInt("price"),
                                    obj.getString("image"),
                                    obj.getString("category")
                            );
                            sanPhamList.add(sanPham);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    Toast.makeText(context, "Lỗi khi tìm kiếm: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        queue.add(request);
    }
}

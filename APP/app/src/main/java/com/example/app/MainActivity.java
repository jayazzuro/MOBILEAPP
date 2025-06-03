package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.SanPhamAdapter.SanPhamAdapter;
import com.example.app.SanPham.SanPham;
import com.example.app.API.APItheloai;
import com.example.app.API.APIsanpham;
import com.example.app.utils.ImageSlider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SanPhamAdapter adapter;
    List<SanPham> sanPhamList;
    Spinner mySpinner;
    TextView idten;
    ImageView imageView;
    ImageSlider imageSlider;
    ImageButton idbuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mySpinner = findViewById(R.id.mySpinner);
        imageView = findViewById(R.id.id1);
        idten = findViewById(R.id.idten);
        idbuy = findViewById(R.id.idbuy);
        sanPhamList = new ArrayList<>();
        adapter = new SanPhamAdapter(this, sanPhamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        imageSlider = new ImageSlider(imageView, idten);
        imageSlider.start();

        APIsanpham.loadProducts(this, sanPhamList, adapter);
        APItheloai.loadSpinner(this, mySpinner);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                APIsanpham.filterByCategory(sanPhamList, adapter, selectedCategory);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Xử lý khi bấm nút idbuy
        idbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Buy
                Intent intent = new Intent(MainActivity.this, buy.class);
                startActivity(intent);
            }
        });

    }
}

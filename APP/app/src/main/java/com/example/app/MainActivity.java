package com.example.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.SanPhamAdapter.SanPhamAdapter;
import com.example.app.SanPham.SanPham;
import com.example.app.API.APItheloai;
import com.example.app.API.APIsanpham;
import com.example.app.API.APISearch;
import com.example.app.utils.ImageSlider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView, rvProducts;
    SanPhamAdapter adapter;
    List<SanPham> sanPhamList;
    Spinner mySpinner;
    TextView idten;
    ImageView imageView;
    ImageSlider imageSlider;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mySpinner = findViewById(R.id.mySpinner);
        imageView = findViewById(R.id.id1);
        idten = findViewById(R.id.idten);
        etSearch = findViewById(R.id.etSearch);
        rvProducts = findViewById(R.id.rvProducts);

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
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                APISearch.searchProducts(MainActivity.this, s.toString(), sanPhamList, adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}

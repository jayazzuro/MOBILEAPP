package com.example.app.SanPhamAdapter;

import com.example.app.SanPham.SanPham;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private Context context;
    private List<SanPham> sanPhamList;

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        SanPham sp = sanPhamList.get(position);
        holder.txtTen.setText(sp.getTenSanPham());
        holder.txtGia.setText(sp.getGia() + " VNĐ");

        String imageUrl = "http://10.0.2.2:3000/img/" + sp.getHinhAnh();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.left)
                .error(R.drawable.right)
                .into(holder.imgSanPham);
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public void updateList(List<SanPham> filteredList) {
        sanPhamList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTen, txtGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGia = itemView.findViewById(R.id.txtGia);
        }
    }
}
package com.example.mycart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;

    private ArrayList<Item> data = new ArrayList<>();

    private Context context;

    Adapter(Context context, ArrayList<Item> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the textview and data received
        holder.textTitle.setText(data.get(position).getTitle());
        holder.textDesc.setText(data.get(position).getDesc());
        holder.textPrice.setText("₹" + data.get(position).getPriceString());

        Glide.with(context).asBitmap().load(data.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textDesc, textPrice;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.imageView);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }
}

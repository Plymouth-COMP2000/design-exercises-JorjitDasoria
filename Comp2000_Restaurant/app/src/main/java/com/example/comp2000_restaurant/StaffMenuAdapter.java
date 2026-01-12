package com.example.comp2000_restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StaffMenuAdapter extends RecyclerView.Adapter<StaffMenuAdapter.ViewHolder> {

    private final List<MenuItem> menuItems;

    public StaffMenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.name.setText(menuItem.getName());
        holder.description.setText(menuItem.getDescription());
        holder.price.setText(String.format("£%.2f", menuItem.getPrice()));
        holder.image.setImageResource(menuItem.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView description;
        TextView price;
        ImageButton editButton;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_staff_item_image);
            name = itemView.findViewById(R.id.tv_staff_item_name);
            description = itemView.findViewById(R.id.tv_staff_item_description);
            price = itemView.findViewById(R.id.tv_staff_item_price);
            editButton = itemView.findViewById(R.id.ib_edit_item);
            deleteButton = itemView.findViewById(R.id.ib_delete_item);
        }
    }
}

package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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
        holder.bind(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, description, price;
        ImageButton editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_staff_item_image);
            name = itemView.findViewById(R.id.tv_staff_item_name);
            description = itemView.findViewById(R.id.tv_staff_item_description);
            price = itemView.findViewById(R.id.tv_staff_item_price);
            editButton = itemView.findViewById(R.id.ib_edit_item);
            deleteButton = itemView.findViewById(R.id.ib_delete_item);
        }

        public void bind(final MenuItem menuItem) {
            name.setText(menuItem.getName());
            description.setText(menuItem.getDescription());
            price.setText(String.format("£%.2f", menuItem.getPrice()));
            image.setImageResource(menuItem.getImageUrl());

            editButton.setOnClickListener(v -> {
                // Create a bundle to pass the menu item's ID
                Bundle bundle = new Bundle();
                bundle.putInt("menuItemId", menuItem.getId());
                
                // Navigate to the Edit screen, passing the ID
                Navigation.findNavController(v).navigate(R.id.action_staffMenuFragment_to_editMenuItemFragment, bundle);
            });

            // You can add a click listener for the deleteButton here as well
        }
    }
}

package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.comp2000_restaurant.databinding.FragmentEditMenuItemBinding;

public class EditMenuItemFragment extends Fragment {

    private FragmentEditMenuItemBinding binding;
    private DatabaseHelper dbHelper;
    private MenuItem currentMenuItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
        // Get the MenuItem passed from the previous fragment
        if (getArguments() != null) {
            int menuItemId = getArguments().getInt("menuItemId");
            // This is a simplified way to get the item. 
            // A more robust app might fetch it from the DB in a background thread.
            for (MenuItem item : dbHelper.getAllMenuItems()) {
                if (item.getId() == menuItemId) {
                    currentMenuItem = item;
                    break;
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditMenuItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (currentMenuItem != null) {
            // Populate the fields with the current item's data
            binding.etItemName.setText(currentMenuItem.getName());
            binding.etItemDescription.setText(currentMenuItem.getDescription());
            binding.etItemPrice.setText(String.valueOf(currentMenuItem.getPrice()));
        }

        binding.btnSaveChanges.setOnClickListener(v -> saveChanges());
    }

    private void saveChanges() {
        if (currentMenuItem != null) {
            String newName = binding.etItemName.getText().toString().trim();
            String newDescription = binding.etItemDescription.getText().toString().trim();
            double newPrice = Double.parseDouble(binding.etItemPrice.getText().toString());

            currentMenuItem.setName(newName);
            currentMenuItem.setDescription(newDescription);
            currentMenuItem.setPrice(newPrice);

            int rowsAffected = dbHelper.updateMenuItem(currentMenuItem);
            if (rowsAffected > 0) {
                Toast.makeText(getContext(), "Item updated successfully!", Toast.LENGTH_SHORT).show();
                // Navigate back to the staff menu
                NavHostFragment.findNavController(this).popBackStack();
            } else {
                Toast.makeText(getContext(), "Failed to update item.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

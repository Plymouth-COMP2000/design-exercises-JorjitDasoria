package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.comp2000_restaurant.databinding.FragmentStaffMenuBinding;
import java.util.List;

public class StaffMenuFragment extends Fragment {

    private FragmentStaffMenuBinding binding;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffMenuBinding.inflate(inflater, container, false);
        dbHelper = new DatabaseHelper(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<MenuItem> menuItems = dbHelper.getAllMenuItems();
        StaffMenuAdapter adapter = new StaffMenuAdapter(menuItems);
        binding.rvStaffMenuItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvStaffMenuItems.setAdapter(adapter);

        binding.fabAddMenuItem.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_staffMenuFragment_to_staffManageItemFragment));
        
        // Add the click listener for the new back button
        binding.btnBack.setOnClickListener(v -> 
                NavHostFragment.findNavController(this).popBackStack());
    }
}

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
import java.util.ArrayList;
import java.util.List;

public class StaffMenuFragment extends Fragment {

    private FragmentStaffMenuBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Placeholder data
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Pizza", "A thin crust base Design, pine nuts, rich creamy mozarella and sauce imported from spain", 12.99, R.drawable.pizza_pic));
        menuItems.add(new MenuItem("Burger", "Beef patty with cheese, lettuce, and tomato", 8.99, R.drawable.toast_slice));


        StaffMenuAdapter adapter = new StaffMenuAdapter(menuItems);
        binding.rvStaffMenuItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvStaffMenuItems.setAdapter(adapter);

        binding.fabAddMenuItem.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_staffMenuFragment_to_staffManageItemFragment));
    }
}

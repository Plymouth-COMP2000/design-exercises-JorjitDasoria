package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.comp2000_restaurant.databinding.FragmentStaffManageItemBinding;

public class StaffManageItemFragment extends Fragment {

    private FragmentStaffManageItemBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffManageItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSaveItem.setOnClickListener(v -> {
            // Add logic to save the item
            getParentFragmentManager().popBackStack();
        });
    }
}

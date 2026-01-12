package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.comp2000_restaurant.databinding.FragmentStaffDashboardBinding;

public class StaffDashboardFragment extends Fragment {

    private FragmentStaffDashboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnManageMenu.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_staffDashboardFragment_to_staffMenuFragment));

        binding.btnManageReservations.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_staffDashboardFragment_to_staffReservationsFragment));

        binding.btnAccountSettings.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_staffDashboardFragment_to_staffAccountSettingsFragment));
    }
}

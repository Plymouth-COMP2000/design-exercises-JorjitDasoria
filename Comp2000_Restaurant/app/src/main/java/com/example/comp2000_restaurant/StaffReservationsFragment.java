package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.comp2000_restaurant.databinding.FragmentStaffReservationsBinding;
import java.util.ArrayList;
import java.util.List;

public class StaffReservationsFragment extends Fragment {

    private FragmentStaffReservationsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffReservationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Placeholder data
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("John Doe", 5, "23rd October, 7:30 PM", 12));
        reservations.add(new Reservation("Jane Smith", 2, "24th October, 8:00 PM", 3));

        StaffReservationAdapter adapter = new StaffReservationAdapter(reservations);
        binding.rvStaffReservations.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvStaffReservations.setAdapter(adapter);
    }
}

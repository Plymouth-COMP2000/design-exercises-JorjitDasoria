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
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.comp2000_restaurant.databinding.FragmentStaffReservationsBinding;
import java.util.ArrayList;
import java.util.List;

public class StaffReservationsFragment extends Fragment {

    private FragmentStaffReservationsBinding binding;
    // --- THE FIX: Declare the adapter as a class-level field ---
    private StaffReservationAdapter adapter; 
    private List<Reservation> reservationList = new ArrayList<>();
    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStaffReservationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        binding.rvStaffReservations.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize the class-level adapter
        adapter = new StaffReservationAdapter(reservationList); 
        binding.rvStaffReservations.setAdapter(adapter);

        // Load reservations from the local database
        loadReservationsFromDb();

        binding.btnBack.setOnClickListener(v -> 
                NavHostFragment.findNavController(this).popBackStack());
    }

    private void loadReservationsFromDb() {
        List<Reservation> fetchedReservations = dbHelper.getAllReservations();
        reservationList.clear();
        reservationList.addAll(fetchedReservations);
        
        // Now the adapter is accessible and this line will work correctly
        adapter.notifyDataSetChanged(); 

        if (!reservationList.isEmpty()) {
            String title = "Reservation Alert";
            String content = "You have " + reservationList.size() + " active reservations to view.";
            NotificationHelper.showNotification(getContext(), 1, title, content);
        } else {
            Toast.makeText(getContext(), "No customer reservations found.", Toast.LENGTH_SHORT).show();
        }
    }
}

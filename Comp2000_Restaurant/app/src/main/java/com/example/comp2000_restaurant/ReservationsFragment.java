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
import com.example.comp2000_restaurant.databinding.FragmentReservationsBinding;
import java.util.ArrayList;
import java.util.List;

public class ReservationsFragment extends Fragment {

    private FragmentReservationsBinding binding;
    private ReservationAdapter adapter;
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
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        
        // Setup the RecyclerView and Adapter here one time
        adapter = new ReservationAdapter(reservationList, false); // false for upcoming reservations
        binding.rvReservations.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvReservations.setAdapter(adapter);
        
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnAddReservation.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_reservationsFragment_to_newReservationFragment));
    }

    @Override
    public void onResume() {
        super.onResume();
        // --- THE FIX ---
        // Load (or reload) the data every time the fragment becomes visible.
        loadReservationsFromDb();
    }

    private void loadReservationsFromDb() {
        List<Reservation> fetchedReservations = dbHelper.getAllReservations();
        
        reservationList.clear();
        reservationList.addAll(fetchedReservations);
        adapter.notifyDataSetChanged();
    }
}

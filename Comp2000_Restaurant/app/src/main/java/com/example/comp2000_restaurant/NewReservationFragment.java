package com.example.comp2000_restaurant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.comp2000_restaurant.databinding.FragmentNewReservationBinding;
import java.util.Calendar;
import java.util.Locale;

public class NewReservationFragment extends Fragment {

    private FragmentNewReservationBinding binding;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewReservationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());

        // Set click listeners
        binding.etDate.setOnClickListener(v -> showDatePickerDialog());
        binding.etTime.setOnClickListener(v -> showTimePickerDialog());
        binding.btnConfirmReservation.setOnClickListener(v -> handleConfirmReservation());
        binding.ivBackButton.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
        binding.btnCancelReservation.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear);
            binding.etDate.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view, selectedHour, selectedMinute) -> {
            String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
            binding.etTime.setText(selectedTime);
        }, hour, minute, true); // true for 24-hour format

        timePickerDialog.show();
    }

    private void handleConfirmReservation() {
        String name = binding.etReservationName.getText().toString().trim();
        String guestsStr = binding.etGuests.getText().toString().trim();
        String date = binding.etDate.getText().toString().trim();
        String time = binding.etTime.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(guestsStr) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time)) {
            Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        int guests;
        try {
            guests = Integer.parseInt(guestsStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter a valid number for guests.", Toast.LENGTH_SHORT).show();
            return;
        }

        Reservation newReservation = new Reservation(name, guests, date + " at " + time, 1);
        boolean isSuccess = dbHelper.addReservation(newReservation);

        if (isSuccess) {
            Toast.makeText(getContext(), "Reservation saved!", Toast.LENGTH_SHORT).show();

            String title = "Reservation Confirmed!";
            String content = "Your booking for " + name + " is confirmed.";
            int notificationId = (int) System.currentTimeMillis();
            NotificationHelper.showNotification(getContext(), notificationId, title, content);

            NavHostFragment.findNavController(this).popBackStack();
        } else {
            Toast.makeText(getContext(), "Error: Failed to save reservation.", Toast.LENGTH_LONG).show();
        }
    }
}

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
import com.example.comp2000_restaurant.databinding.FragmentAccountSettingsBinding;

public class AccountSettingsFragment extends Fragment {

    private FragmentAccountSettingsBinding binding;
    private String username;
    private User currentUser;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchUserDetails();

        binding.btnSaveChanges.setOnClickListener(v -> handleUpdateUser());
        binding.btnBack.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        // --- THE FIX: Add the click listener for the logout button ---
        binding.btnLogout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "You have been logged out.", Toast.LENGTH_SHORT).show();
            // Use the new navigation action to go back to the welcome screen and clear the history
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_accountSettingsFragment_to_welcomeFragment);
        });
    }

    // ... (rest of the file is correct)
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void fetchUserDetails() { /* ... */ }
    private void populateUI(User user) { /* ... */ }
    private void handleUpdateUser() { /* ... */ }
}

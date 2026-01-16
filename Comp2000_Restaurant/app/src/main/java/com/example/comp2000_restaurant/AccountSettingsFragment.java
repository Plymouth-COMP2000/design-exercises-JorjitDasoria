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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchUserDetails();

        binding.btnSaveChanges.setOnClickListener(v -> handleUpdateUser());

        // --- BACK BUTTON FIX ---
        // Add the click listener to handle the back navigation
        binding.btnBack.setOnClickListener(v -> 
                NavHostFragment.findNavController(this).popBackStack());
    }

    private void fetchUserDetails() {
        if (username != null) {
            Api.getUserByUsername(getContext(), username, new Api.GetUserCallback() {
                 @Override
                 public void onSuccess(User user) {
                     currentUser = user;
                     populateUI(user);
                 }

                 @Override
                 public void onError(String message) {
                     Toast.makeText(getContext(), "Error fetching details: " + message, Toast.LENGTH_LONG).show();
                 }
            });
        }
    }
    
    private void populateUI(User user) {
        binding.etFirstname.setText(user.getFirstname());
        binding.etLastname.setText(user.getLastname());
        binding.etEmail.setText(user.getEmail());
        binding.etContact.setText(user.getContact());
    }

    private void handleUpdateUser() {
        if (currentUser == null) {
            Toast.makeText(getContext(), "User data not loaded yet. Please wait.", Toast.LENGTH_SHORT).show();
            return;
        }

        currentUser.setFirstname(binding.etFirstname.getText().toString().trim());
        currentUser.setLastname(binding.etLastname.getText().toString().trim());
        currentUser.setEmail(binding.etEmail.getText().toString().trim());
        currentUser.setContact(binding.etContact.getText().toString().trim());

        Api.updateUser(getContext(), currentUser, new Api.GeneralApiCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(AccountSettingsFragment.this).popBackStack();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "Update Failed: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

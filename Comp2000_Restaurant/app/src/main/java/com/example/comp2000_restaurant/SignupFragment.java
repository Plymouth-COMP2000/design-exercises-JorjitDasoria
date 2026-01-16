package com.example.comp2000_restaurant;

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
import com.example.comp2000_restaurant.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSignupSubmit.setOnClickListener(v -> handleApiSignup());
        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }

    private void handleApiSignup() {
        String username = binding.etUsername.getText().toString().trim();
        String firstname = binding.etFirstname.getText().toString().trim();
        String lastname = binding.etLastname.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String contact = binding.etContact.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(username, password, firstname, lastname, email, contact);

        Api.createUser(getContext(), newUser, new Api.GeneralApiCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(getContext(), "Signup successful! Please log in.", Toast.LENGTH_LONG).show();
                NavHostFragment.findNavController(SignupFragment.this)
                        .navigate(R.id.action_signupFragment_to_loginFragment);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "Signup Failed: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

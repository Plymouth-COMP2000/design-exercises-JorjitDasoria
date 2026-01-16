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
import com.example.comp2000_restaurant.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Customize the shared layout for a general login
        binding.tvLoginTitle.setText("Login");
        binding.tilUsernameLogin.setHint("Username");

        binding.btnSignin.setOnClickListener(v -> handleApiLogin());
        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }

    private void handleApiLogin() {
        String username = binding.etUsernameLogin.getText().toString().trim();
        String password = binding.etPasswordLogin.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call the unified API login method
        Api.loginUser(getContext(), username, password, new Api.LoginCallback() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(getContext(), "Welcome, " + user.getFirstname() + "!", Toast.LENGTH_SHORT).show();

                // --- ROLE-BASED NAVIGATION LOGIC ---
                if ("staff".equalsIgnoreCase(user.getUserType())) {
                    // If usertype is 'staff', navigate to the staff dashboard
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_loginFragment_to_staffDashboardFragment);
                } else {
                    // Otherwise, proceed to the guest menu
                    Bundle bundle = new Bundle();
                    bundle.putString("username", user.getUsername());
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_loginFragment_to_menuFragment, bundle);
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "Login Failed: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

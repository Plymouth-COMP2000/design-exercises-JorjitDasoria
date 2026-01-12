package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.comp2000_restaurant.databinding.FragmentLoginBinding;

public class StaffLoginFragment extends Fragment {

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

        binding.buttonLogin.setOnClickListener(v -> {
            // Add authentication logic here
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_staffLoginFragment_to_staffDashboardFragment);
        });
    }
}

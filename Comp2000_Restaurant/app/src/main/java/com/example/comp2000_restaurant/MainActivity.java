package com.example.comp2000_restaurant;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        NotificationHelper.createNotificationChannel(this);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        navController.setGraph(R.navigation.main_nav_graph);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // --- THE FIX: Corrected the variable name ---
        Set<Integer> topLevelGuestDestinations = new HashSet<>();
        topLevelGuestDestinations.add(R.id.menuFragment);
        topLevelGuestDestinations.add(R.id.reservationsFragment); // Was topLevelGuest_destinations
        topLevelGuestDestinations.add(R.id.accountSettingsFragment); // Was topLevelGuest_destinations

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (topLevelGuestDestinations.contains(destination.getId())) {
                bottomNav.setVisibility(View.VISIBLE);
            } else {
                bottomNav.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}

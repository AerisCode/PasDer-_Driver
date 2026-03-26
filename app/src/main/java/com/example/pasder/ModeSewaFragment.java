package com.example.pasder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ModeSewaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mode_sewa, container, false);

        // 1. Spinner Keluhan (Dah bener, tapi kita pastiin requireContext aman)
        Spinner spinnerKeluhan = view.findViewById(R.id.spinner_keluhan);
        if (spinnerKeluhan != null) {
            String[] listKeluhan = {"Pilih Keluhan", "Ban Bocor", "Mesin Mogok", "Bensin Habis", "Lainnya"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, listKeluhan);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerKeluhan.setAdapter(adapter);
        }

        // 2. Navigasi Bottom Nav di dalam Mode Sewa
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_nav_sewa);
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                Fragment selectedFragment = null;
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (id == R.id.nav_assignment) {
                    selectedFragment = new AssignmentFragment();
                } else if (id == R.id.nav_saya) {
                    selectedFragment = new SayaFragment();
                }

                if (selectedFragment != null) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }
                return false;
            });
        }

        return view;
    }
}

package com.example.pasder; // Pastikan ini sesuai dengan nama package di manifest kamu

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class HomeFragment extends Fragment {

    private SwitchMaterial switchStatus;
    private Spinner spinnerKeluhan;
    private TextView tvUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate layout fragment_home.xml
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 2. Inisialisasi View sesuai ID XML terbaru kamu
        switchStatus = view.findViewById(R.id.switch_status_driver);
        spinnerKeluhan = view.findViewById(R.id.spinner_keluhan);
        tvUsername = view.findViewById(R.id.tv_username_home);

        // --- FITUR 1: LOGIC SWITCH STATUS (Gantiin ToggleButton) ---
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getActivity(), "Driver Aktif (Online)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Driver Istirahat (Offline)", Toast.LENGTH_SHORT).show();
            }
        });

        // --- FITUR 2: SPINNER KELUHAN (Custom Adapter Rata Tengah) ---
        String[] listKeluhan = {"Keluhan", "Ban Bocor", "Mesin Mogok", "Bensin Habis", "Lainnya"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listKeluhan) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (v instanceof TextView) {
                    ((TextView) v).setGravity(Gravity.CENTER);
                    ((TextView) v).setTextSize(12);
                }
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                if (v instanceof TextView) {
                    ((TextView) v).setGravity(Gravity.CENTER);
                }
                return v;
            }
        };

        spinnerKeluhan.setAdapter(adapter);

        return view;
    }
}
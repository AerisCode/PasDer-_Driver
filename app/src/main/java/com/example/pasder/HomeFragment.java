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
    private View layoutOffShift; // Tambahkan ini

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisialisasi View
        switchStatus = view.findViewById(R.id.switch_status_driver);
        spinnerKeluhan = view.findViewById(R.id.spinner_keluhan);
        tvUsername = view.findViewById(R.id.tv_username_home);
        layoutOffShift = view.findViewById(R.id.layout_off_shift);

        // Logika Switch
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Driver Online(Maps terlihat)
                layoutOffShift.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Driver Aktif (Online)", Toast.LENGTH_SHORT).show();
            } else {
                // Driver Offline(Maps tertutup)
                layoutOffShift.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Driver Istirahat (Offline)", Toast.LENGTH_SHORT).show();
            }
        });

        // --- Bagian Spinner ---
        String[] listKeluhan = {"Keluhan", "Ban Bocor", "Mesin Mogok", "Bensin Habis", "Lainnya"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listKeluhan) {
        };
        spinnerKeluhan.setAdapter(adapter);

        return view;
    }
}
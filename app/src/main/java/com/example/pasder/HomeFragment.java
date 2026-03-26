package com.example.pasder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class HomeFragment extends Fragment {

    private SwitchMaterial switchStatus;
    private Spinner spinnerKeluhan;
    private TextView tvActiveName;
    private View layoutOffShift;
    private CardView cardActiveOrder;
    private SharedPreferences prefs;
    private View btnShelter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        prefs = requireActivity().getSharedPreferences("PasDerPrefs", Context.MODE_PRIVATE);

        // Inisialisasi View
        btnShelter = view.findViewById(R.id.btn_shelter);
        switchStatus = view.findViewById(R.id.switch_status_driver);
        spinnerKeluhan = view.findViewById(R.id.spinner_keluhan);
        tvActiveName = view.findViewById(R.id.tv_nama_penyewa_active);
        layoutOffShift = view.findViewById(R.id.layout_off_shift);
        cardActiveOrder = view.findViewById(R.id.card_active_order);

        // Update UI berdasarkan status simpanan (is_sewa_active)
        boolean isSewaActive = prefs.getBoolean("is_sewa_active", false);
        if (isSewaActive) {
            cardActiveOrder.setVisibility(View.VISIBLE);
        } else {
            cardActiveOrder.setVisibility(View.GONE);
        }

        // Logika Switch (Online / Offline)
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnShelter.setVisibility(View.VISIBLE);
                spinnerKeluhan.setVisibility(View.VISIBLE);
                layoutOffShift.setVisibility(View.GONE);
                Toast.makeText(requireActivity(), "Driver Aktif (Online)", Toast.LENGTH_SHORT).show();
            } else {
                btnShelter.setVisibility(View.GONE);
                spinnerKeluhan.setVisibility(View.GONE);
                layoutOffShift.setVisibility(View.VISIBLE);
                Toast.makeText(requireActivity(), "Driver Istirahat (Offline)", Toast.LENGTH_SHORT).show();
            }
        });

        // Logika Selesaikan Sewa
        view.findViewById(R.id.btn_selesaikan).setOnClickListener(v -> {
            // Update status di SharedPreferences agar list kembali muncul
            prefs.edit().putBoolean("is_sewa_active", false).apply();
            cardActiveOrder.setVisibility(View.GONE);
            Toast.makeText(requireActivity(), "Sewa Selesai!", Toast.LENGTH_SHORT).show();
        });

        // --- Bagian Spinner Keluhan ---
        String[] listKeluhan = {"Pilih Keluhan", "Ban Bocor", "Mesin Mogok", "Bensin Habis", "Lainnya"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, listKeluhan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKeluhan.setAdapter(adapter);

        return view;
    }
}

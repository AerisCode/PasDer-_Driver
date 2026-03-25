package com.example.pasder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class HomeFragment extends Fragment {

    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        prefs = requireActivity().getSharedPreferences("PasDerPrefs", Context.MODE_PRIVATE);

        // Inisialisasi Komponen UI
        SwitchMaterial switchStatus = view.findViewById(R.id.switch_status_driver);
        LinearLayout layoutOffShift = view.findViewById(R.id.layout_off_shift);
        CardView cardActiveOrder = view.findViewById(R.id.card_active_order);
        Button btnSelesaikan = view.findViewById(R.id.btn_selesaikan);

        // Update UI berdasarkan status simpanan (is_sewa_active)
        boolean isSewaActive = prefs.getBoolean("is_sewa_active", false);
        if (isSewaActive) {
            cardActiveOrder.setVisibility(View.VISIBLE);
        } else {
            cardActiveOrder.setVisibility(View.GONE);
        }

        // Fungsionalitas Switch (Aktif / Istirahat)
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutOffShift.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Status: Aktif", Toast.LENGTH_SHORT).show();
            } else {
                layoutOffShift.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Status: Istirahat", Toast.LENGTH_SHORT).show();
            }
        });

        // Fungsionalitas Tombol Selesaikan Sewa
        btnSelesaikan.setOnClickListener(v -> {
            prefs.edit().putBoolean("is_sewa_active", false).apply();
            cardActiveOrder.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Sewa Telah Diselesaikan", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}

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
import androidx.fragment.app.Fragment;

public class DetailAssignmentFragment extends Fragment {

    private Button btnModeSewa;
    private LinearLayout btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Nama layout XML: fragment_detail_assignment
        View view = inflater.inflate(R.layout.fragment_detail_assignment, container, false);

        // Inisialisasi komponen UI
        btnModeSewa = view.findViewById(R.id.btn_mode_sewa);
        btnBack = view.findViewById(R.id.btn_back_detail);

        // Fungsi tombol Kembali
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                getParentFragmentManager().popBackStack();
            });
        }

        // Fungsi tombol Masuk Mode Sewa
        if (btnModeSewa != null) {
            btnModeSewa.setOnClickListener(v -> {
                // 1. Simpan Status: SEWA AKTIF menggunakan requireActivity() agar lebih aman
                SharedPreferences prefs = requireActivity().getSharedPreferences("PasDerPrefs", Context.MODE_PRIVATE);
                prefs.edit().putBoolean("is_sewa_active", true).apply();

                // 2. Notifikasi ke driver
                Toast.makeText(getContext(), "Masuk Mode Sewa: Menampilkan Rute", Toast.LENGTH_SHORT).show();

                // 3. Pindah ke fragment Mode Sewa
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ModeSewaFragment())
                        .addToBackStack(null) // Menambahkan ke backstack agar navigasi lebih baik
                        .commit();
            });
        }

        return view;
    }
}

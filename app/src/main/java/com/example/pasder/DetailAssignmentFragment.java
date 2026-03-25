package com.example.pasder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailAssignmentFragment extends Fragment {

    private Button btnModeSewa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // PENTING: Pastikan nama layout XML-nya benar (fragment_detail_assignment)
        View view = inflater.inflate(R.layout.fragment_detail_assignment, container, false);

        // Inisialisasi tombol (Sesuaikan ID-nya dengan yang ada di XML lo)
        btnModeSewa = view.findViewById(R.id.btn_masuk_mode_sewa);

        btnModeSewa.setOnClickListener(v -> {
            // 1. Simpan Status: SEWA AKTIF
            SharedPreferences prefs = getActivity().getSharedPreferences("PasDerPrefs", Context.MODE_PRIVATE);
            prefs.edit().putBoolean("is_sewa_active", true).apply();

            // 2. Kasih tau user
            Toast.makeText(getContext(), "Masuk Mode Sewa: Menampilkan Rute", Toast.LENGTH_SHORT).show();

            // 3. Pindah balik ke Home (Maps) otomatis
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });

        return view;
    }
}
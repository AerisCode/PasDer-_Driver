package com.example.pasder;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate layout fragment_home
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 2. Inisialisasi Spinner
        Spinner spinner = view.findViewById(R.id.spinner_keluhan);

        // 3. Data Keluhan (Sesuai Usecase Diagram kamu)
        String[] listKeluhan = {"Pilih Keluhan", "Ban Bocor", "Mesin Mogok", "Bensin Habis", "Lainnya"};

        // 4. Set Adapter dengan trick agar teks di tengah
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

        spinner.setAdapter(adapter);

        return view;
    }
}
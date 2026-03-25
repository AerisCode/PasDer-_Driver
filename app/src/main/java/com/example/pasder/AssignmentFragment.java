package com.example.pasder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs = requireActivity().getSharedPreferences("PasDerPrefs", Context.MODE_PRIVATE);
        boolean isSewaActive = prefs.getBoolean("is_sewa_active", false);

        if (isSewaActive) {
            View view = inflater.inflate(R.layout.fragment_detail_assignment, container, false);

            LinearLayout btnBack = view.findViewById(R.id.btn_back_detail);
            AppCompatButton btnModeSewa = view.findViewById(R.id.btn_mode_sewa);

            if (btnBack != null) {
                btnBack.setOnClickListener(v -> {
                    // Turn off Sewa Mode when going back
                    prefs.edit().putBoolean("is_sewa_active", false).apply();
                    // Reload this fragment to show the list
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AssignmentFragment())
                            .commit();
                });
            }

            if (btnModeSewa != null) {
                btnModeSewa.setText("Keluar Mode Sewa");
                btnModeSewa.setOnClickListener(v -> {
                    prefs.edit().putBoolean("is_sewa_active", false).apply();
                    Toast.makeText(getContext(), "Mode Sewa Dinonaktifkan", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AssignmentFragment())
                            .commit();
                });
            }

            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_assignment_list, container, false);

            RecyclerView rv = view.findViewById(R.id.recycler_assignment);
            
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getContext()));

                List<Assignment> list = new ArrayList<>();
                list.add(new Assignment("Paket Wisata Kuliner", "Arif Purnomo", "28/02/2026"));
                list.add(new Assignment("Paket Wisata Religi", "Aris Hasanudin", "10/03/2026"));
                list.add(new Assignment("Paket Wisata Sejarah", "Yudha Pratama", "27/03/2026"));

                AssignmentAdapter adapter = new AssignmentAdapter(list);
                rv.setAdapter(adapter);
            }

            return view;
        }
    }
}

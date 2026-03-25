package com.example.pasder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatButton;

/**
 * Fragment that displays the details of a specific assignment.
 * It allows users to go back to the previous fragment or enter "Sewa" mode.
 */
public class DetailAssignmentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_assigment, container, false);

        // Initialize components
        LinearLayout btnBack = view.findViewById(R.id.btn_back_detail);
        AppCompatButton btnModeSewa = view.findViewById(R.id.btn_mode_sewa);

        // Set Click Listeners
        btnBack.setOnClickListener(v -> {
            // Navigate back to the previous fragment
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        btnModeSewa.setOnClickListener(v -> {
            // Action for entering "Sewa" mode
            // For now, this could be a toast or navigation to another screen
        });

        return view;
    }
}
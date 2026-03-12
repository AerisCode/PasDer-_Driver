package com.example.pasder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;
    private List<Assignment> listTugas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_assignment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listTugas = new ArrayList<>();
        listTugas.add(new Assignment("Paket Wisata Kuliner", "Arif Purnomo", "28/02/2026"));
        listTugas.add(new Assignment("Paket Wisata Religi", "Aris Hasanudin", "10/03/2026"));
        listTugas.add(new Assignment("Paket Wisata Sejarah", "Yudha Pratama", "27/03/2026"));

        adapter = new AssignmentAdapter(listTugas);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
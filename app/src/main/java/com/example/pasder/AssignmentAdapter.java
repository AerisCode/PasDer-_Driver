package com.example.pasder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private List<Assignment> listTugas;

    public AssignmentAdapter(List<Assignment> listTugas) {
        this.listTugas = listTugas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment data = listTugas.get(position);

        holder.tvTitle.setText(data.getNamaPaket());
        holder.tvDetail.setText("Penyewa: " + data.getPenyewa() + "\n" + data.getTanggal());
    }

    @Override
    public int getItemCount() {
        return listTugas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_item);
            tvDetail = itemView.findViewById(R.id.tv_detail_item);
        }
    }
}
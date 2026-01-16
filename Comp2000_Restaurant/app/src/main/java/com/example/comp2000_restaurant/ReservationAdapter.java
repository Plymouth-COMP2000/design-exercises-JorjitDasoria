package com.example.comp2000_restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private final List<Reservation> reservations;
    private final boolean isPast;

    public ReservationAdapter(List<Reservation> reservations, boolean isPast) {
        this.reservations = reservations;
        this.isPast = isPast;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = isPast ? R.layout.item_reservation_past : R.layout.item_reservation;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.tableSize.setText("Table for " + reservation.getTableSize());
        holder.details.setText(reservation.getDateTime() + "\nTable " + reservation.getTableNumber());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tableSize;
        TextView details;
        Button editButton; // Only in item_reservation

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableSize = itemView.findViewById(R.id.tv_table_size);
            details = itemView.findViewById(R.id.tv_table_details);
            editButton = itemView.findViewById(R.id.btn_edit);
        }
    }
}
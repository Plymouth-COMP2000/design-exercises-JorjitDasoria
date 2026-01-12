package com.example.comp2000_restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StaffReservationAdapter extends RecyclerView.Adapter<StaffReservationAdapter.ViewHolder> {

    private final List<Reservation> reservations;

    public StaffReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.customerName.setText("Customer: " + reservation.getCustomerName());
        holder.tableSize.setText("Table for " + reservation.getTableSize());
        holder.details.setText(reservation.getDateTime() + "\nTable " + reservation.getTableNumber());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerName;
        TextView tableSize;
        TextView details;
        Button cancelButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.tv_staff_res_customer_name);
            tableSize = itemView.findViewById(R.id.tv_staff_res_table_size);
            details = itemView.findViewById(R.id.tv_staff_res_details);
            cancelButton = itemView.findViewById(R.id.btn_cancel_reservation);
        }
    }
}

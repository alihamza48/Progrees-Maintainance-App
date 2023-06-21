package com.example.hifzstudents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.ViewHolder> {

    private List<StudentRecord> recordList;

    public StudentRecordAdapter(List<StudentRecord> recordList) {
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemstudentrecord, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentRecord record = recordList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDate;
        private TextView textViewSabaq;
        private TextView textViewSabaqi;
        private TextView textViewManzil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.ViewName);
            textViewDate = itemView.findViewById(R.id.ViewDate);
            textViewSabaq = itemView.findViewById(R.id.ViewSabaq);
            textViewSabaqi = itemView.findViewById(R.id.ViewSabaqi);
            textViewManzil = itemView.findViewById(R.id.ViewManzil);
        }

        public void bind(StudentRecord record) {
            textViewName.setText("Name: " + record.getName());
            textViewDate.setText("Date: " + record.getDate());
            textViewSabaq.setText("Sabaq: " + record.getSabaq());
            textViewSabaqi.setText("Sabaqi: " + record.getSabaqi());
            textViewManzil.setText("Manzil: " + record.getManzil());
        }
    }
}

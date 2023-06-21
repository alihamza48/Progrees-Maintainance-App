package com.example.hifzstudents;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class searchStudent extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editTextSearch;
    private dbHandler databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchstudent);

        databaseHelper = new dbHandler(this);

        recyclerView = findViewById(R.id.recycler);
        editTextSearch = findViewById(R.id.editText);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button searchButton = findViewById(R.id.Button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String searchCriteria = editTextSearch.getText().toString().trim();

        List<StudentRecord> recordList = databaseHelper.searchStudentRecords(searchCriteria);

        StudentRecordAdapter adapter = new StudentRecordAdapter(recordList);
        recyclerView.setAdapter(adapter);
    }

}

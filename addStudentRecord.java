package com.example.hifzstudents;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addStudentRecord extends AppCompatActivity {

    private EditText idEditText, nameEditText, sabaqEditText, sabaqiEditText, manzilEditText,Editdate;
    private dbHandler databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudentsrecord);

        databaseHelper = new dbHandler(this);

        idEditText = findViewById(R.id.idEditText);
        nameEditText = findViewById(R.id.nameEditText);
        Editdate = findViewById(R.id.Editdate);
        sabaqEditText = findViewById(R.id.sabaqEditText);
        sabaqiEditText = findViewById(R.id.sabaqiEditText);
        manzilEditText = findViewById(R.id.manzilEditText);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String date = Editdate.getText().toString();
                String sabaq = sabaqEditText.getText().toString();
                String sabaqi = sabaqiEditText.getText().toString();
                String manzil = manzilEditText.getText().toString();

                // Check if the student exists in the first database
                if (databaseHelper.checkStudentExistence(id, name)) {
                    // Insert the student record into the second database
                    StudentRecord record = new StudentRecord(id, name,date, sabaq, sabaqi, manzil);
                    boolean isRecordInserted = databaseHelper.insertStudentRecord(record);

                    if (isRecordInserted) {
                        Toast.makeText(addStudentRecord.this, "Student record added successfully", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(addStudentRecord.this, "Failed to add student record", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addStudentRecord.this, "Student not found in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearFields() {
        idEditText.setText("");
        nameEditText.setText("");
        Editdate.setText("");
        sabaqEditText.setText("");
        sabaqiEditText.setText("");
        manzilEditText.setText("");
    }
}

package com.example.hifzstudents;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "student";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CLASS = "class";

    private static final String TABLE_STUDENT_RECORD = "student_record";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_SABAQ = "sabaq";
    private static final String COLUMN_SABAQI = "sabaqi";
    private static final String COLUMN_MANZIL = "manzil";


    public dbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStudentTableQuery = "CREATE TABLE " + TABLE_STUDENT + " ("
                + COLUMN_ID + " TEXT PRIMARY KEY, "
                + COLUMN_NAME + " TEXT , "
                + COLUMN_AGE + " TEXT, "
                + COLUMN_CLASS + " TEXT)";
        db.execSQL(createStudentTableQuery);

        String createStudentRecordTableQuery = "CREATE TABLE " + TABLE_STUDENT_RECORD + " ("
                + COLUMN_ID + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_SABAQ + " TEXT, "
                + COLUMN_SABAQI + " TEXT, "
                + COLUMN_MANZIL + " TEXT)";
        db.execSQL(createStudentRecordTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_RECORD);
        onCreate(db);
    }

    public boolean insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_AGE, student.getAge());
        values.put(COLUMN_CLASS, student.getClassName());

        long result = db.insert(TABLE_STUDENT, null, values);
        return result != -1;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE));
                @SuppressLint("Range") String className = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));

                Student student = new Student(id, name, age, className);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentList;
    }

    public boolean checkStudentExistence(String id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + "=? AND " + COLUMN_NAME + "=?";
        String[] selectionArgs = { id, name };
        Cursor cursor = db.query(TABLE_STUDENT, null, selection, selectionArgs, null, null, null);

        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean insertStudentRecord(StudentRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, record.getId());
        values.put(COLUMN_NAME, record.getName());
        values.put(COLUMN_DATE, record.getDate());
        values.put(COLUMN_SABAQ, record.getSabaq());
        values.put(COLUMN_SABAQI, record.getSabaqi());
        values.put(COLUMN_MANZIL, record.getManzil());

        long result = db.insert(TABLE_STUDENT_RECORD, null, values);
        return result != -1;
    }
    public List<StudentRecord> searchStudentRecords(String criteria) {
        List<StudentRecord> recordList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT_RECORD +
                " WHERE " + COLUMN_NAME + " LIKE '%" + criteria + "%'", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                @SuppressLint("Range") String sabaq = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQ));
                @SuppressLint("Range") String sabaqi = cursor.getString(cursor.getColumnIndex(COLUMN_SABAQI));
                @SuppressLint("Range") String manzil = cursor.getString(cursor.getColumnIndex(COLUMN_MANZIL));

                StudentRecord record = new StudentRecord(id, name, date, sabaq, sabaqi, manzil);
                recordList.add(record);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return recordList;
    }


}

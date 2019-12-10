package com.androidaura.hw3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "hw3.db";
    public static final String TABLE_NAME = "student";
    public static final String CWID = "cwid";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + CWID + " TEXT,"+ FNAME + " TEXT,"
                + LNAME + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void deleteRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }
    // code to add the new contact
    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CWID, student.getCWID());
        values.put(FNAME, student.getFname());
        values.put(LNAME, student.getLname());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }



    // code to get all contacts in a list view
    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setCWID(cursor.getString(1));
                student.setFName(cursor.getString(2));
                student.setLName(cursor.getString(3));

                // Adding contact to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return contact list
        return studentList;
    }



    // Getting contacts Count
    public int getStudentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}

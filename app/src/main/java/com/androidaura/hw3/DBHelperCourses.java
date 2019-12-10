package com.androidaura.hw3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelperCourses extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "hw.db";
    public static final String TABLE_NAME = "courses";
    private static final String ID = "id";
    public static final String CWID = "cwid";
    public static final String COURSEID = "courseid";
    public static final String COURSENAME = "coursename";


    public DBHelperCourses(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                 + CWID + " TEXT,"+ COURSEID + " TEXT,"
                + COURSENAME + " TEXT" + ")";
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
    void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ID, course.getID());
        values.put(CWID, course.getCWID());
        values.put(COURSEID, course.getCOURSEID());
        values.put(COURSENAME, course.getCOURSENAME());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCWID(cursor.getString(1));
                course.setCOURSEID(cursor.getString(2));
                course.setCOURSENAME(cursor.getString(3));


                courseList.add(course);
            } while (cursor.moveToNext());
        }

        return courseList;
    }



    // Getting contacts Count
    public int getCourseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
       // cursor.close();

        // return count
        return cursor.getCount();
    }
}

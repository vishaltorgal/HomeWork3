package com.androidaura.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomList adapter;
    ListView listview;
    ArrayList<Student> array_list;
    DBHelper db;
    DBHelperCourses dbcourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        dbcourse = new DBHelperCourses(this);


        listview = (ListView) findViewById(R.id.list);

        if(Appconstant.flag) {

            if(db.getRecordsCount() >0 ){
                db.deleteRows();
            }
            if(dbcourse.getRecordsCount() >0 ){
                dbcourse.deleteRows();
            }

            Log.d("vt", "Inserting Data ...");
            db.addStudent(new Student("888261294", "Vishal", "Torgal"));
            db.addStudent(new Student("888261299", "Rohit", "Shah"));

            dbcourse.addCourse(new Course("888261294", "545", "Software Management"));
            dbcourse.addCourse(new Course("888261294", "552", "Mobile Development"));

            dbcourse.addCourse(new Course("888261299", "411", "Cyber Forensic"));
            dbcourse.addCourse(new Course("888261299", "522", "Seminar"));


            Appconstant.flag=false;
        }
        array_list = db.getAllStudent();
        adapter = new CustomList(array_list, getApplicationContext());
        listview.setAdapter(adapter);

    }
}
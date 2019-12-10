package com.androidaura.hw3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class StudentAdd extends AppCompatActivity {


    DBHelper db;
    DBHelperCourses dbcourse;

    ImageView img_add;

    EditText sd_add_fname,sd_add_lname,sd_add_cwid,sd_add_courseid,
            sd_add_coursename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_studentadd);

        img_add = (ImageView) findViewById(R.id.img_add);
        sd_add_fname = (EditText)findViewById(R.id.sd_add_fname);
        sd_add_lname = (EditText)findViewById(R.id.sd_add_lname);

        sd_add_cwid = (EditText)findViewById(R.id.sd_add_cwid);
        sd_add_courseid = (EditText)findViewById(R.id.sd_add_courseid);
        sd_add_coursename = (EditText)findViewById(R.id.sd_add_coursename);

        db = new DBHelper(this);
        dbcourse = new DBHelperCourses(this);

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveCourse();

            }
        });

    }

    public void saveCourse(){

        dbcourse.addCourse(new Course(sd_add_cwid.getText().toString(),
                sd_add_courseid.getText().toString(), sd_add_coursename.getText().toString()));

        sd_add_cwid.setEnabled(false);
        sd_add_fname.setEnabled(false);
        sd_add_lname.setEnabled(false);

        sd_add_courseid.getText().clear();
        sd_add_coursename.getText().clear();

    }

    public void saveStudent(){

        db.addStudent(new Student(sd_add_cwid.getText().toString(),
                sd_add_fname.getText().toString(), sd_add_lname.getText().toString()));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        menu.findItem(R.id.add).setTitle("Done");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                //perform any action;



             if(sd_add_courseid.getText().toString() != null){

                 saveStudent();
                 saveCourse();

             }else{

                 saveStudent();
             }

                Intent i = new Intent(StudentAdd.this,MainActivity.class);
                startActivity(i);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
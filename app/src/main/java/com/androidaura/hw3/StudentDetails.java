package com.androidaura.hw3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetails extends AppCompatActivity {

    TextView sd_cwid,sd_courseId,sd_courseName;
    EditText sd_fname,sd_lname;
    String id;
    MainActivity m;
    ArrayList<Course> array_list_course;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                //perform any action;
                Intent i = new Intent(StudentDetails.this,StudentAdd.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentdetails);

        sd_courseId = (TextView) findViewById(R.id.sd_courseId);
        sd_courseName = (TextView) findViewById(R.id.sd_courseName);
        sd_cwid = (TextView) findViewById(R.id.sd_cwid);
        sd_fname = (EditText) findViewById(R.id.sd_fname);
        sd_lname = (EditText) findViewById(R.id.sd_lname);

        sd_fname.setEnabled(false);
        sd_lname.setEnabled(false);


        DBHelperCourses dbcourse = new DBHelperCourses(this);

        array_list_course = dbcourse.getAllCourse();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                id = extras.getString("cwid");
                sd_cwid.setText(id);
                sd_fname.setText(extras.getString("fname"));
                sd_lname.setText(extras.getString("lname"));

                Log.d("vt", "count" +dbcourse.getCourseCount() );
                for (int i = 0; i < array_list_course.size(); i++) {

                    if (id.equals(array_list_course.get(i).getCWID())) {
                        sd_courseId.append(array_list_course.get(i).getCOURSEID() + "\n" + "\n");
                        sd_courseName.append(array_list_course.get(i).getCOURSENAME() + "\n" + "\n");
                    }

                    //    Log.d("vt", "cname" + array_list_course.get(i).getCOURSENAME());
                }
                }
            }

        }
    }

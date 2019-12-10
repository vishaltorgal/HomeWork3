package com.androidaura.hw3;

public class Course {



    String cwid;
    String courseid;
    String coursename;


    public Course(){   }


    public Course(String cwid, String courseid, String coursename){

        this.cwid = cwid;
        this.courseid =  courseid;
        this.coursename = coursename;
    }



    public String getCWID(){
        return this.cwid;
    }

    public String getCOURSEID(){
        return this.courseid;
    }

    public String getCOURSENAME(){
        return this.coursename;
    }




    public void setCWID(String cwid){
        this.cwid = cwid;
    }

    public void setCOURSEID(String courseid){
        this.courseid = courseid;
    }

    public void setCOURSENAME(String coursename){
        this.coursename = coursename;
    }


}

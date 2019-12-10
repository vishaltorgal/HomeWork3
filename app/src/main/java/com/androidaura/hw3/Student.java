package com.androidaura.hw3;

public class Student {


    String cwid;
    String fname;
    String lname;

    public Student(){   }


    public Student(String cwid, String fname, String lname){

        this.cwid = cwid;
        this.fname = fname;
        this.lname = lname;
    }



    public String getCWID(){
        return this.cwid;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }


    public void setCWID(String cwid){
        this.cwid = cwid;
    }

    public void setFName(String fname){
        this.fname = fname;
    }

    public void setLName(String lname){
        this.lname = lname;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leecottrell.guiclient;
import java.util.*;

/**
 *
 * @author User
 */
public class Student {
    private int studentID;
    private String fname;
    private String lname;

   @Override
    public String toString() {
        return getFname() + " " + getLname() + " ID " + getStudentID();
    }
    
    
    public Student() {
    }

    public Student(int studentID, String fname, String lname) {
        this.studentID = studentID;
        this.fname = fname;
        this.lname = lname;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Student studentID(int studentID) {
        setStudentID(studentID);
        return this;
    }

    public Student fname(String fname) {
        setFname(fname);
        return this;
    }

    public Student lname(String lname) {
        setLname(lname);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return studentID == student.studentID && Objects.equals(fname, student.fname) && Objects.equals(lname, student.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, fname, lname);
    }

 

}

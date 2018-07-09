// -----------------------------------------------------
// Assignment 4
// Question: 1
// Written by: Huaying Zhou 40037751
// -----------------------------------------------------
/**
 * Huaying Zhou 40037751
 * COMP249
 * Assignment # 4
 * Due Date: 13 April
 */

import jdk.nashorn.internal.ir.IfNode;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Course implements DirectlyRelatable{
    private String courseID;
    private String courseName;
    private double credit;
    private String preReqID;
    private String coReqID;

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setPreReqID(String preReqID) {
        this.preReqID = preReqID;
    }

    public void setCoReqID(String coReqID) {
        this.coReqID = coReqID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCredit() {
        return credit;
    }

    public String getPreReqID() {
        return preReqID;
    }

    public String getCoReqID() {
        return coReqID;
    }

    public Course(String ID, String name, double cre, String pre, String co){
        setCourseID(ID);
        setCourseName(name);
        setCredit(cre);
        setPreReqID(pre);
        setCoReqID(co);
    }

    public Course(Course course, String newID){
        courseID=newID;
        courseName=course.getCourseName();
        credit=course.getCredit();
        preReqID=course.getPreReqID();
        coReqID=course.getCoReqID();
    }

    public Course clone() {
        Scanner kb = new Scanner(System.in);
        String newID = "N/A";
        Boolean enterValid = false;
        System.out.print("Please enter a new courseID: ");
        while (!enterValid) {
            try {
                newID = kb.next();
                enterValid = true;
            } catch (InputMismatchException e) {
                kb.nextLine();
                System.out.println("That's not a valid String, please enter again");
            }
        }
        Course c1= new Course(this,newID);
        return c1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null||obj.getClass()!=this.getClass())
            return false;
        Course c1=(Course) obj;
        return (courseName.equals(c1.getCourseName())&&credit==c1.getCredit()
                &&preReqID.equals(getPreReqID())&&coReqID.equals(getCoReqID()));
    }

    public String toString(){
        return ("courseID: "+courseID+"\t courseName: "+courseName+
        "\t credit: "+credit+"\t preReqID: "+preReqID+"\t coReqID: "+coReqID);
    }

    @Override
    public boolean isDirectlyRelated(Course C) {
        return (this.courseID==C.getPreReqID()||this.courseID==C.getCoReqID()||
                this.coReqID==C.getCourseID()||this.preReqID==C.getCourseID());
    }

    /* This is a wrong code but I dont want delete it because I tried so hard :(
    @Override
    public boolean isDirectlyRelated(Course C) {
        Scanner sc = null;
        String s1 = "C:\\Users\\hu_zho\\IdeaProjects\\Asg4\\src\\Syllabus.txt";
        String ID1 = courseID;
        String ID2 = C.getCourseID();
        String testCourseName = "N/A";
        String testP = "N/A";
        String testC = "N/A";
        try {
            sc = new Scanner(new FileReader(s1));
        } catch (FileNotFoundException e) {
            System.out.println("File not founded");
        }
        Boolean found = false;
        while ((!found) && (sc.hasNext())) {
            testCourseName = sc.next();
            if ((testCourseName.equals(ID1)) || (testCourseName.equals(ID2))) {
                sc.nextLine();  //jump out of course name
                sc.next();  //jump out of P/C
                testP = sc.next();
                sc.next();
                testC = sc.next();
                Boolean realated = testP.equals(ID1) || testP.equals(ID2) ||
                        testC.equals(ID1) || testC.equals(ID2);
                if (realated) {
                    return true;    //return true, jump out of the whole method
                }
            }
        }
        }
        return false;//if method reach here, it sesarched whole file and no match, so return false
    */
}

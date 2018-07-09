// -----------------------------------------------------
// Assignment 4
// Question: 3
// Written by: Huaying Zhou 40037751
// -----------------------------------------------------
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.ir.IfNode;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Huaying Zhou 40037751
 * COMP249
 * Assignment # 4
 * Due Date: 13 April
 */
public class EnrolmentResults {
    public static void main(String[] args) {
        System.out.println("==============Welcome to course-enroll system=================");
        CourseList list1=new CourseList();  //This is the list that store syllabus information
        CourseList list2=new CourseList(list1);
        CourseList list3=new CourseList(list1);

        BufferedReader bf=null;
        try {
            bf=new BufferedReader(new FileReader("C:\\Users\\hu_zho\\IdeaProjects\\ASG4\\src\\Syllabus.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }

        String newID="N/A";
        String cName="N/A";
        String trCredit="N/A";
        double credit=0;
        String pre="N/A";
        String co="N/A";
        String st1="N/A";
        String judge="N/A";
        StringTokenizer stz=null;

        /**
         * The while method get a LinkedList list1 recording all courses and it's related pre/co courses from Syllabus
         */
            while (judge!=null){
                try {
                    st1=bf.readLine();
                    stz=new StringTokenizer(st1);
                    newID=stz.nextToken();
                    cName=stz.nextToken();
                    trCredit=stz.nextToken();
                    credit=Double.parseDouble(trCredit);    //get information from 1st line

                    st1=bf.readLine();
                    stz=new StringTokenizer(st1);
                    stz.nextToken();
                    try {
                        pre=stz.nextToken();
                    }
                    catch (NoSuchElementException e1){
                        pre="Null";
                    }       //get information from pre line

                    st1=bf.readLine();
                    stz=new StringTokenizer(st1);
                    stz.nextToken();
                    try {
                        co=stz.nextToken();
                    }
                    catch (NoSuchElementException e1){
                        co="Null";
                    }       //get information from co line

                    list1.addToStart(new Course(newID,cName,credit,pre,co));    //add courses
                    judge=bf.readLine();    //just empty line between courses
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
            try {
                bf.close();
            }
            catch (IOException e){
                System.out.println(e);
            }
            list1.deleteDupli();    //delete duplicate courses from syllabus, method from CourseList class as we cannot access Head method from outside


        Scanner kb=new Scanner(System.in);
        BufferedReader br=null;
        ArrayList<String> taken=new ArrayList<String>();
        ArrayList<String> request=new ArrayList<String>();
        Boolean valid=false;
        //make sure input filename is always valid
        do {
            try {
                System.out.println("\nPlease input the request file that need to be processed: ");
                String fileName=kb.next();
                br=new BufferedReader(new FileReader(fileName));    //enter file path
                valid=true;
            }
            catch (FileNotFoundException e){
                kb.nextLine();
                System.out.println("File is not founded, Please try again");
            }
        }
        while (!valid);


        /**
         * following try block get a taken courses and request courses list from file user input
         */
        try {
            br.readLine();
            String nextCourse="N/A";
            String nextRe="N/A";
            while (!nextCourse.equals("Requested")){
                nextCourse=br.readLine();
                if (!nextCourse.equals("Requested")){
                    taken.add(nextCourse);
                }
            }
            while (nextRe!=null){
                nextRe=br.readLine();
                if (nextRe!=null)
                request.add(nextRe);
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

        /**
         * check if request courses meets requirement
         * First, for every course from request (a courseID string ArrayList), find the course in syllabus
         * Comparing class he/she taken in the past, check if it's co/pre for request courses.
         * Comparing he/she taking now, check if it's co for request courses.
         * If two condition are right, printout enroll information, otherwise, print out information.
         */

        System.out.println("============Here is your course-enroll result:================");
        if (request.size()==0)
            System.out.println("Student can’t enrol as he/she doesn’t have request course.");
        else
        for (String s: request){
            Boolean preTaken=false;
            Boolean coTaking=false;
            Boolean dontNeedPre=false;
            Boolean needPre=false;
            Boolean dontNeedCo=false;
            Boolean needCo=false;
            String preCourse="Null";
            String coCourse="Null";
            CourseList.CourseNode pointer=list1.find(s);
            if (pointer!=null){
                for (String x: taken){
                    dontNeedPre=pointer.getCr().getPreReqID().equals("Null");
                    if(pointer.getCr().getPreReqID().equals(x)){
                        needPre=true;
                        preCourse=x;
                    }
                    if(pointer.getCr().getCoReqID().equals(x)){
                        needCo=true;
                        coCourse=x;
                    }
                    preTaken=dontNeedPre||needPre;
                }
                for (String y: request){
                    dontNeedCo=pointer.getCr().getCoReqID().equals("Null");
                    if (pointer.getCr().getCoReqID().equals(y)){
                        needCo=true;
                        coCourse=y;
                    }
                    coTaking=dontNeedCo||needCo;
                }
            }

            if (preTaken&&coTaking){
                if (dontNeedPre&&dontNeedCo)
                    System.out.println("Student can enrol in "+s+" course as he/she doesn’t have pre-requisite and co-requisite.");
                else if (dontNeedPre&&needCo)
                    System.out.println("Student can enrol in "+s+" course as he/she is enroling for the co-requisite "+coCourse+".");
                else if ((dontNeedCo&&needPre))
                    System.out.println("Student can enrol in "+s+" course as he/she is enroling for the pre-requisite "+preCourse+".");
                else if (needCo&&needPre)
                    System.out.println("Student can enrol in "+s+" course as he/she is enroling for the co-requisite "+coCourse+". and pre-requisite "+preCourse+".");
            }
            else if (pointer==null){
                    System.out.println("Student cannot enrol in "+s+" course as he/she'a enroling course is not exist.");
            }
            else
        System.out.println("Student can’t enrol in "+s+" course as he/she doesn’t have sufficient background needed.");
        }

        /**
         * The following test search certain courses.
         */
        ArrayList<String> searchID=new ArrayList<String>();
        System.out.println("\n============Now test search courses====================");
        Boolean finished=false;
        do {
            System.out.println("Please enter some courseIDs you want to search(exist enter N): ");
            String newSearID="N/A";
                newSearID=kb.next();
                finished=newSearID.equals("N");
                if (!finished)
                    searchID.add(newSearID);
            }
        while (!finished);

        for(String s: searchID){
            int i=1;
            CourseList.CourseNode pt=list1.find(s);
            if (pt==null)
                System.out.println("Cannot find the element.");
            else {
                System.out.println("The information for your "+1+" courses is:");
                System.out.println(pt.getCr());
            }
        }

        System.out.println("=====Now test the LinkedList we created above using copy constructor========");
        System.out.println("LinkedList 1 size is: "+list1.getSize());
        System.out.println("LinkedList 2 size is: "+list2.getSize());
        System.out.println("=====The copy constructor from linkedlist using deep copy======");
    }

}



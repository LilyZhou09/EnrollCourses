// -----------------------------------------------------
// Assignment 4
// Question: 4
// Written by: Huaying Zhou 40037751
// -----------------------------------------------------
import java.sql.SQLOutput;
/**
 * Huaying Zhou 40037751
 * COMP249
 * Assignment # 4
 * Due Date: 13 April
 */

public class testDriver {
    public static void main(String[] args) {
        Course c1=new Course("COMP233","COMP",3,"NA","NA");
        Course c2=new Course("COMP244","COMP",3,"NA","NA");
        Course c3=new Course("COMP255","COMP",3,"NA","NA");
        Course c4=new Course("COMP266","COMP",3,"NA","NA");
        Course c5=new Course("COMP277","COMPX",3,"NA","NA");
        CourseList crList=new CourseList();
        CourseList crList2=new CourseList();
        crList.addToStart(c4);
        crList.addToStart(c3);
        crList.addToStart(c2);
        crList.addToStart(c1);

        System.out.println("=====Test isDirectlyRelated method========");
        Course c6=new Course("COMP248","COMP",3,"NA","NA");
        Course c7=new Course("MATH204","COMP",3,"NA","NA");
        System.out.println("The course "+c6.getCourseID()+" and the course "+c7.getCourseID()+" is realated?");
        System.out.println(c6.isDirectlyRelated(c7));
        Course c8=new Course("COMP208","COMP",3,"NA","NA");
        Course c9=new Course("COMP108","COMP",3,"NA","NA");
        System.out.println("The course "+c8.getCourseID()+" and the course "+c9.getCourseID()+" is realated?");
        System.out.println(c8.isDirectlyRelated(c9));
        System.out.println("=====Finishing test======\n");

        System.out.println("=====Test equals method from course class===========");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.equals(c2));
        System.out.println("=====It compares all elements other than courseID=====");

        System.out.println("\n=====Test LinkedList equals method==================");
        crList2.addToStart(c5);
        System.out.println(crList.equals(crList2)); //because it compare other elements other than courseID, so it's differernt
        crList2.addToStart(c3);
        System.out.println(crList.equals(crList2)); //now they have similar courses, so equal

        System.out.println("\n=====Test methods from CourseList=======");

        System.out.println("This is the original list:");
        crList.showContent();
        System.out.println("=====add the start======");
        crList.insertAtIndex(c5,0);
        crList.showContent();
        System.out.println("=====add the end======");
        crList.insertAtIndex(c5,crList.getSize()-1);
        crList.showContent();

        System.out.println("=====Test clone() method from courseNote=====");
        System.out.println(crList.find("COMP233").clone().getCr());

        System.out.println("=====Test delete&replace method=====");
        crList.deleteFromIndex(0);
        System.out.println("====delete from start=====");
        crList.showContent();
        System.out.println("====delete from the end===");
        crList.deleteFromIndex(crList.getSize()-1);
        crList.showContent();

    }
}

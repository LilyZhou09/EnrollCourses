// -----------------------------------------------------
// Assignment 4
// Question: 2
// Written by: Huaying Zhou 40037751
// -----------------------------------------------------

/**
 * Huaying Zhou 40037751
 * COMP249
 * Assignment # 4
 * Due Date: 13 April
 */

import java.util.NoSuchElementException;

public class CourseList {
    public class CourseNode{
        private Course cr;
        private CourseNode next;

        public Course getCr() {
            return cr;
        }

        public CourseNode getNext() {
            return next;
        }

        public void setCr(Course cr1) {
            cr=new Course(cr1,cr1.getCourseID());
        }

        public void setNext(CourseNode next) {
            this.next = next;
        }

        public CourseNode(){
            setNext(null);
            setCr(null);
            size++;
        }
        public CourseNode(Course c, CourseNode cn){
            Course c1=new Course(c,c.getCourseID());
            setCr(c1);
            setNext(cn);
            size++;
        }
        public CourseNode(CourseNode cn){
            Course c1=new Course(cn.getCr(),cn.getCr().getCourseID());
            setCr(c1);
            setNext(cn);
            size++;
        }
        public CourseNode clone(){
            size++;
            return new CourseNode(cr.clone(),this);
        }
    }

    private int size=0;

    public int getSize() {
        int size=0;
        CourseNode temp=head;
        while (temp!=null){
            temp=temp.next;
            size++;
        }
        return size;
    }

    private CourseNode head;
    public CourseList(){
        head=null;
    }
    public CourseList(CourseList lst){
        if (lst.head==null)
            head=null;
        else {
            head=null;
            CourseNode t1, t2, t3;
            t1=lst.head;
            t2=t3=null;
            while (t1!=null){
                if (head==null){
                    t2=new CourseNode(t1.getCr(),null);
                    head=t2;
                }
                else {
                    t3=new CourseNode(t1.getCr(),null);
                    t2.next=t3;
                    t2=t3;
                }
                t1=t1.next;
            }
            t2=t3=null;
        }
    }

    public void addToStart(Course c1){
        CourseNode n=new CourseNode(c1,head);
        head=n;
        n=null;
    }

    public void insertAtIndex(Course c1, int index){
        try {
            if (index<0||index>(size-1)){
                throw new NoSuchElementException();
            }
            else {
                if (index==0){  //if insert at 0,then add to start
                    addToStart(c1);
                }
                else if (index==1){
                    CourseNode temp=new CourseNode();
                    temp=head;
                    CourseNode newHead=new CourseNode(c1,temp.next);
                    head.next=newHead;
                }
                else {
                    CourseNode temp=head;
                    for (int i = 0; i < (index-1); i++) {
                        temp=temp.next;
                    }
                    CourseNode newIndex=new CourseNode(c1,temp.next);
                    temp.next=newIndex;
                }
            }
        }
        catch (NoSuchElementException e1){
            System.out.println(e1);
            System.exit(0);
        }
    }

    public void deleteFromIndex(int index){
        try {
            if (index<0||index>(size-1)){
                throw new NoSuchElementException();
            }
            else {
                if (index==0){
                    head=head.next;
                    size--;
                }
                else {
                    CourseNode temp1=head;
                    for (int i = 0; i < index-1; i++) {
                        temp1=temp1.next;
                    }
                    CourseNode temp2=temp1.next;
                    temp1.next=temp2.next;
                    size--;
                }
            }
        }
        catch (NoSuchElementException e1){
            System.out.println(e1);
            System.exit(0);
        }
    }

    public void deleteFromStart(){
        deleteFromIndex(0);
    }

    public boolean replaceAtIndex(Course c1, int index){
        if (index<0||index>(size-1)){
            return false;
        }
        else {
            if (index==0){  //if insert at 0,then add to start
                head.setCr(c1);
            }
            else {
                CourseNode temp=head;
                for (int i = 0; i < index; i++) {
                    temp=temp.next;
                }
                temp.setCr(c1);
            }
        }
        return true;
    }

    /**
    the method may result in privacy leak, as it return a pointer to CourseNote
    User from outside class can access to the List's pointer through find method and change
    it's attributes through public get and set method
     **/
    public CourseNode find(String ID){
        int iteration=0;
        CourseNode temp=head;
        CourseNode findNode=null;
        String findID="N/A";
        boolean find=false;
        while (temp!=null&&!find){
            findID=temp.getCr().getCourseID();
            if (findID.equals(ID)){
                find=true;
                findNode=temp;
            }
            temp=temp.next;
            iteration++;
        }
        if (iteration!=0&&find==true)
            System.out.println("We find the course and it takes "+iteration+" iterations to find it.");
        else
            System.out.println("The course is not in the list");
        return findNode;
    }

    public Boolean contains(String ID){
        if (find(ID)==null)
            return false;
        return true;
    }

    public Boolean equals(CourseList c2){
        CourseNode t1=head;
        CourseNode t2=c2.head;
        Boolean compare=false;
        while (t1!=null&&!compare){
            t2=c2.head;
            while (t2!=null&&!compare){
                compare=t1.getCr().equals(t2.getCr());
                t2=t2.next;
            }
            t1=t1.next;
        }
        if (compare==true)
            return true;
        else
            return false;
    }

    public void deleteDupli(){
        CourseNode temp=head;
        CourseNode t2=head.next;
        int index=1;
        while (temp!=null){
            t2=temp.next;
            index=1;
            while (t2!=null){
                if (temp.getCr().equals(t2.getCr())){
                    deleteFromIndex(index);
                }
                t2=t2.next;
                index++;
            }
            temp=temp.next;
        }
    }
    public void showContent(){
        int i=0;
        CourseNode temp=head;
        if (temp==null)
            System.out.println("Nothing to show");
        else
            while (temp!=null){
                System.out.println(temp.getCr());
                temp=temp.next;
            }
    }
}

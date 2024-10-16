/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART=========
//===========================================================================
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xSource, int xPrice, int xType) {
        //You should write here appropriate statements to complete this function.
        Node node = new Node(new Watermelon(xSource, xPrice, xType));
        if (xSource.charAt(0) == 'D') return;
        if (this.isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        //----------------------------------------------------------------------
    }
    void addFirst(String xSource, int xPrice, int xType) {
        //You should write here appropriate statements to complete this function.
        Node node = new Node(new Watermelon(xSource, xPrice, xType));
        if (xSource.charAt(0) == 'D') return;
        if (this.isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        //----------------------------------------------------------------------
    }

    void deleteLast() {
        if (this.isEmpty()) return;
        if (head == tail) head = tail = null;
        else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        }
    }
    void deleteFirst() {
        if (this.isEmpty()) return;
        if (head == tail) head = tail = null;
        else {
            head = head.next;
        }
    }
    void insertNodeat(Node x, int k) {
        if (this.isEmpty()) {
            head = tail = x;
        } if (k < 2) {
            x.next = head;
            head = x;
            return;
        }
        Node p = head;
        int count=1;
        while (count < k - 1 && p != null) {
            p = p.next;
            count++;
        }
        if (p==null || p.next ==null) {
            tail.next = x;
            tail = x;
        }
        x.next = p.next;
        p.next=x;
    }
    void delete(int k) {
        if (this.isEmpty()) {
            return;
        } if (k < 2) {
            head = head.next;
            return;
        }
        Node p = head;
        int count = 1;
        while (p != null && count < k-1) {
            p = p.next;
            count++;
        }
        if (p==null) {
            return;
        }
        p.next = p.next.next;
    }
    void swap(int x, int y) {
        if (isEmpty() || x == y) {
            return;
        }
        Node  X = head;
        Node  Y = head;
        
        for (int i = 1; i < x; i++){
            X =X.next;
        }
        for (int i = 1; i < y; i++){
            Y = Y.next;
        }
        if (X == null || Y == null) {
            return;
        }
        Watermelon temp = X.info;
        X.info=Y.info;
        Y.info=temp;
    }

    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Watermelon g;
        Watermelon h;
        g = new Watermelon("G", 2, 3);
        h = new Watermelon("H", 5, 6);
        Node x = new Node(g);
        Node y = new Node(h);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
          Your task is to insert statements here, just after this comment,
          to complete the question in the exam paper.*/
        /*y.next = x;
        x.next = head.next;
        head.next = y;*/
        insertNodeat(y, 2);
        insertNodeat(x, 3);
        
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        /*int count = 1;
        Node p = head;
        while (p != null) {
            if (count == 2) {
                p.next = p.next.next;
                p.next = p.next.next;
            } 
            count++;
            p = p.next;
        } */
        delete(3);
        delete(3);
        
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
           Your task is to insert statements here, just after this comment,
           to complete the question in the exam paper.*/
        int n = 4;
        for (int i = 1; i <=n; i++) {
            Node p= head;
            for (int j = 1; j < n; j++) {
                if (p != null && p.next != null) {
                    if (p.info.type > p.next.info.type) {
                        swap(j, j+1);
                    }
                    p = p.next; 
                }
            }
        }
        
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    //==================================================================
    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
           Your task is to insert statements here, just after this comment,
           to complete the question in the exam paper.*/
        deleteLast();
       
        
        
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    //==================================================================
    void f6() throws Exception {
        clear();
        loadData(21);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
           Your task is to insert statements here, just after this comment,
           to complete the question in the exam paper.*/
        deleteFirst();
       
        
        

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    void f7() throws Exception {
        clear();
        loadData(25);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
           Your task is to insert statements here, just after this comment,
           to complete the question in the exam paper.*/
        swap(3,6);
       
        
        
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    

    
}

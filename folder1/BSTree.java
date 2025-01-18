/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }
    void rnl(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        rnl(p.right, f);
        fvisit(p, f);
        rnl(p.left, f);   
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }
    

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xSource, int xPrice, int xType) {
        //You should insert here statements to complete this function
        /*if (xSource.charAt(0) == 'B') return;
        Node x = new Node(new Watermelon(xSource, xPrice, xType));
        if (root == null) { //this.isEmpty
            root = x;
            return;
        }
        Node f, p;
        p = root;
        f= null;
        while (p!=null) {
            if (x.info.price == p.info.price) return;
            f=p;
            if (x.info.price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.info.price < f.info.price) {
            f.left = x;
        } else {
            f.right = x;
        } */
        if (xSource.charAt(0)=='B') return;
        Node x = new Node(new Watermelon(xSource, xPrice, xType));
        if (this.isEmpty()) {
            root = x;
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p!=null) {
            if (x.info.price == p.info.price) return;
            f = p;
            if (x.info.price < p.info.price) p = p.left;
            else p = p.right;
        }
        if (x.info.price < f.info.price) f.left = x;
        else f.right = x;
    } 
    Node rightmost(Node p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }
    
    
    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder2(p.left, f);
        postOrder2(p.right, f);
        if (p.info.type < 4) fvisit(p, f);
    }
    void findleaf(Node root, RandomAccessFile f) throws Exception {
        if (root == null) {
            return;
        }
        findleaf(root.left, f);
        findleaf(root.right, f);
        if (root.left == null && root.right == null) {
            fvisit(root, f);
        }
    }
    void internal(Node root, RandomAccessFile f) throws Exception {
        if (root == null) {
            return;
        }
        if (root.left != null || root.right != null) {
            fvisit(root, f);
        } 
        internal(root.left, f);
        internal(root.right, f);
    }
    int height(Node root) throws IOException {
        int height = 0;
        if (root == null) {
            return 0;
        }
        height = Integer.max(height(root.left), height(root.right)) + 1;
        return height;
    }
    void addHeight(Node root, RandomAccessFile f, int height) throws Exception {
        if (this.isEmpty()) {
            return;
        }
        if (root.left == null && root.right == null) {
            root.info.type = root.info.type + height;
            fvisit(root, f);
            return;
        }
        if (root.left != null) {
            addHeight(root.left, f, height);
        }
        if (root.right != null) {
            addHeight(root.right, f, height);
        }  
        
    }
    int largestType(Node p, int largest) {
        if (p == null) {
            return largest;
        }
        if (p.info.type > largest) largest = p.info.type;
        return Integer.max(largestType(p.left, largest), largestType(p.right, largest));
    }
    void searchLargest(Node p, int x, RandomAccessFile f) throws Exception {
        if (p==null) return;
        if (p.info.type == x) fvisit(p, f);
        searchLargest(p.left, x, f);
        searchLargest(p.right, x, f);
    }
    Node nthPreOrder(Node root, int N, int a) {
        int flag = a;
        if (root == null) return null;
        if (flag <= N ) {
            flag++;
            if (flag == N) return root;
            Node left = nthPreOrder(root.left, N, flag);
            if (left != null) return left;
            return nthPreOrder(root.right, N, flag);
        }
        return null;
    }
    Node findFather(Node node, int price, Node parent) {
        if (node == null) return null;
        if (node.info.price == price) return parent;
        else {
            Node left = findFather(node.left, price, node);
            if (left != null) return left;
            return findFather(node.right, price, node);
        }
    }
    void rightRotation(Node cur){
        if (cur.left == null) return;
        
        Node parent = findFather(root, cur.info.price, null);
        Node son = cur.left;
        
        if (parent.left == cur) parent.left = son;
        else parent.right = son;
        
        cur.left = son.right;
        son.right = cur;
    }
    void leftRotation(Node cur) {
        Node parent = findFather(root, cur.info.price, null);
        Node son = cur.right;
        
        if (parent.left == cur) parent.left = son;
        else parent.right = son;
        
        cur.right = son.left;
        son.left = cur;
    }
    Node deleteCopying(Node root, int x) {
        if (root == null) return root;
        if (root.info.price > x) root.left = deleteCopying(root.left, x);
        else if (root.info.price < x) root.right = deleteCopying(root.right, x);
        else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node rightmost = rightmost(root.left);
            root.info = rightmost.info;
            root.left = deleteCopying(root.left, rightmost.info.price);
        }
       return root; 
    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f2() – Perform post-order traversal from the root but display to file f2.txt
        //nodes with type<4 only.
        
        postOrder2(root, f);

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f3() – Suppose p is the 3rd node when performing the pre-order traversal of the tree.
        //Delete the node p  by copying. 
        root = deleteCopying(root, nthPreOrder(root, 3, 0).info.price);
        
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //void f4() - rightmost node.

        fvisit(rightmost(root),f);
        
        
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f5() - Leaf nodes
        findleaf(root, f);
     

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void f6() throws Exception {
        clear();
        loadData(21);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f6() - Internal nodes

        internal(root, f);
        
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================

    void f7() throws Exception {
        clear();
        loadData(25);
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //void f7() - height of the tree
        
        f.writeBytes(Integer.toString(height(root)));

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================
    
    void f8() throws Exception {
        clear();
        loadData(29);
        String fname = "f8.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        //void f8() - tim nut la, cong type voi chieu cao cua cay
        addHeight(root, f, height(root));
        

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
//=============================================================
    
    void f9() throws Exception {
        clear();
        loadData(33);
        String fname = "f9.txt";
        File g123 = new File(fname);
        if(g123.exists()) g123.delete();
        RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
        preOrder(root,f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
          Your task is to insert statements here, just after this comment,
          to complete the question in the exam paper.*/

        rightRotation(nthPreOrder(root, 3, 0));


        //------------------------------------------------------------------------------------
        preOrder(root,f);
        f.writeBytes("\r\n");
        f.close();
   }

//=============================================================
    
    void f10() throws Exception {
        clear();
        loadData(37);
        String fname = "f10.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //------------------------------------------------------------------------------------
        //void f4() - rightmost node.

        searchLargest(root, largestType(root, root.info.type), f);
        
        
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    } 

    

    

    

    
}

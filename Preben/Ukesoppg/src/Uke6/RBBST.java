import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

/*
Oppg. 3.3.1
           S
     (EO)     U
   A (IN) Q  T Y

Oppg. 3.3.2
           P
     (CL)       X
   A (EH) M   (RS)  Y

*/

public class RBBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int n;
        private int h;
        private boolean color;

        public Node(Key key, Value val, int n, int h, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.h = h;
            this.color = color;
        }
    }
    private boolean isR(Node x){
        if (x==null) return false;
        return x.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        h.h = 1 + Math.max(height(h.left), height(h.right));
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        h.h = 1 + Math.max(height(h.left), height(h.right));
        return x;
    }
    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int size(){ return size(root); }
    private int size(Node x){
        if (x == null) return 0;
        else           return x.n;
    }
    public Value get( Key key){ return get(root, key); }
    private Value get(Node x, Key key){
        if( x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put( Node x, Key key, Value val){
        if(x == null) return new Node(key, val, 1, 1, RED);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isR(x.right) && !isR(x.left)) x = rotateLeft(x);
        if (isR(x.left) && isR(x.left.left)) x = rotateRight(x);
        if (isR(x.left) && isR(x.right)) flipColors(x);

        x.n = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException();
        Node x = min(root);
        return x.key;
    }

    private  Node min (Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }
    //finner det største elementet som er mindre enn key.
    private Node floor (Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }
    public Key max(){
        if (isEmpty()) throw new NoSuchElementException();
        Node x = max(root);
        return x.key;
    }
    //Finner elementet som er lengst til høyre.
    private  Node max (Node x){
        if (x.right == null) return x;
        return max(x.left);
    }

    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }
    //Finner det minste elementet som er større enn key
    private Node ceiling (Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return floor(x.right, key);
        Node t = floor(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key select(int k){
        if (k<0 || k>= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }
    //select returns the key to a given number in the tree. f. eks nummer 4 i et tre av A-B-C-D blir D.
    private Node select(Node x, int k){
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else            return x;
    }

    public int rank(Key key){ return rank (key, root); }
    //gir rangeringen av
    private int rank(Key key, Node x){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return rank (key, x.left);
        else if (cmp > 0)   return 1 + size(x.left) + rank(key, x.right);
        else                return size(x.left);
    }

    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete (Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if          (cmp < 0) x.left = delete(x.left, key);
        else if     (cmp > 0) x.right = delete(x.right,key);
        else{
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) +1;
        return x;
    }
    //enqueuer alle elementene i treet.
    public Iterable<Key> keys(){ return keys(min(), max()); }
    public Iterable<Key> keys (Key lo, Key hi){
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo<0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int height () { return height(root); }
    private int height (Node x){
        if (x == null) return 0;
        else           return x.h;
    }
    public int heightR () { return heightR (root); }
    private int heightR (Node x){
        if (x == null) return 0;
        else return (1 + Math.max(heightR(x.right), heightR(x.left)));
    }

    public static void main(String[] args){
        RBBST<String, Integer> st;
        st = new RBBST<String, Integer>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys())
            System.out.println(s + " " );
    }
    public RBBST valSort(RBBST st){
        return null;
    }



}

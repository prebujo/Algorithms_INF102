import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

/*
Oppg. 3.2.1
No of compareTo's: 28. End tree:
        E
      A     S
         Q     Y
      I      U
        O  T
      N

Oppg. 3.2.4
a), b), c), e) works
d) is not a valid order as 8 is greater than 7 and would be put right instead of left of 7 where 3 was put. 4 and 5 would be put
on the right below 3 thereafter.

Oppg. 3.2.6
method height:lagde en height metode hvor jeg har lagt til en int h slik som int n og øker denne kun med max av høyden til høyre og venstre
gren. Metoden returnerer tallet h for enhvert node med konstant tid og tar lineært med plass.
method heightR: recursive method that takes max of the height of node left and right + 1; returns 0 if x == null. linear proportionate to
height.

Oppg. 3.2.13
see line 210 and 222 for solutions

Oppg 3.2.18
Will remove A from tree first. then E resulting in the subtree below S, then I will be removed resulting in following:
        S
      Q   Y
     O   U
    N   T
Now N will be removed then O then Q then S and left with following subtree:
        Y
       U
      T
Then T then U then finally Y.

Oppg 3.2.24
Since the depth of any tree will correspond to log n or higher (log n being a balanced tree) and you will have to insert n elements with log n compares (depth). the
best compare based algorithm will have to compare until it hits the bottom of the tree i. e. n times. corresponding to n log n.

Oppg. 3.2.27
BST:
32 bytes for stack, 8 for ref to root, per Node: 108 bytes (16 overhead, 8 extra, 2x8 Object ref, 2xNode ref, 4 for n (and 4 for height not counted), (also not counted: 2x24 for each Object)
= 40 + 60*n
Tale of two cities has 10,679 distict words. assuming each word has an average of 5 char,
usage of memory will be:
40 + 60*10679 + (56 + 2 * 5)*10679 + 24*10679 = 1,601,890 bytes.

BinarySearchST:
32 + 48 + 16n

 */

public class BinST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int n;
        private int h;

        public Node(Key key, Value val, int n, int h) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.h = h;
        }
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

    public void put(Key key, Value val) { root = put(root, key, val);}
    private Node put( Node x, Key key, Value val){
        if(x == null) return new Node(key, val, 1, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
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

    //solution of get from 3.2.13
    public Value gett(Key key){
        Node x = root;
        while(x != null){
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    //solution to 3.2.13
    public void putt (Key key, Value val){
        Node x = root;
        Node p = null;
        while (x != null){
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.val = val;
                return;
            }
            else if (cmp < 0){
                p = x;
                x = x.left;
            }
            else if (cmp > 0){
                p = x;
                x = x.right;
            }
        }
        //if we reached this point the key does not exist already, starting over assuming updates of Nodes.
        x=root;
        //checking if we have a height increase and if true incrementing height in each step.
        boolean heightInc = false;
        if (p.right == null && p.left ==null)
            heightInc = true;

        while(x != null){
            int cmp = key.compareTo(x.key);
            x.n += 1;
            if (heightInc)
                x.h += 1;

            if (cmp < 0) {
                if (x.left != null)
                    x = x.left;
                else {
                    x.left = new Node(key, val, 1, 1);
                    return;
                }
            }
            else if (cmp > 0) {
                if (x.right != null)
                    x = x.right;
                else {
                    x.right = new Node(key, val, 1, 1);
                    return;
                }

            }
        }

        //if we have come this far the root must be null so we make a new node
        root = new Node(key, val, 1, 1);
    }
    public static void main(String[] args){
        BinST<String, Integer> st;
        st = new BinST<String, Integer>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys())
            System.out.println(s + " ");
        }


}

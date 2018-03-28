import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

import static edu.princeton.cs.algs4.StdIn.isEmpty;

public class BinSearchST <Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int n;
    private static final int INIT_CAPACITY = 100000;

    public BinSearchST(int capacity){
        //Alg. 1.1
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public BinSearchST() {
        this(INIT_CAPACITY);
    }

    public int size(){
        return n;
    }

    public Value get(Key key){
        if(isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        else                                      return null;
    }

    public int rank(Key key){
        int lo = 0, hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            vals[i] = val ; return;
        }
        for (int j = n; j > i; j--){
            keys[j] = keys[j-1]; vals[j] = vals[j-1];
        }
        keys[i] = key; vals[i] = val;
        n++;
    }

    public void delete(Key key){

    }

    public Key min(){ return keys[0]; }
    public Key max(){ return keys[n-1]; }
    public Key select(int k){ return keys[k]; }
    public Key ceiling (Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){
    return key;
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++ )
            queue.enqueue(keys[i]);
        if(contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }


    public static void main(String[] args){
        BinSearchST<String, Integer> st;
        st = new BinSearchST<String, Integer>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys())
            System.out.println(s + " " + st.get(s));
    }

}





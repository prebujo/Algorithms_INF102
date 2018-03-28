

import edu.princeton.cs.algs4.Queue;

import java.util.Scanner;

/*
Oppg. 3.1.31

 */
public class PerformanceDriver<Key, Value> {
    private Node first;
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public Value get(Key key){
        for (Node x = first; x!= null; x=x.next)
            if (key.equals(x.key)) return x.val;
        return null;
    }

    public void put (Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
    public boolean contains(Key key){
        return get(key) != null;
    }


    public static void main(String[] args){
        PerformanceDriver<String, Integer> st;
        st = new PerformanceDriver<>();
        Scanner reader = new Scanner(System.in);
        for (int i = 0; i<15; i++) {
            String key = reader.next();
            st.put(key, i);
        }
        for(String s: st.keys())
            System.out.println(s + " " + st.get(s));
    }

}


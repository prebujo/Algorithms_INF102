

import edu.princeton.cs.algs4.Queue;

import java.util.Scanner;

/*
Oppg. 3.1.13 og 14
Sequence Search put: Gjennomgår hele tabellen en gang og setter inn for key ny val eller oppretter en ny  tuppell -> O(n). For å
sette inn n forskjellige keys i tabellen bruker den n²/2 compares.
Binary search put: Gjennomgår færre elementer men må etter å ha funnet ny rank for ord flytte alle elementer etter
elementet nedover i rankingen. Denne prosessen er for BinarySearch også O(n) men bruker n² compares

Sequence Search get: går igjennom hele tabellen i værste fall og sammenligner som en normal for loop gjennomgang -> O(n)
BinarySearch get: Halverer Streng og leter på riktig side, halverer og søker igjen. gjør at vi får O(log n) sammenligninger. Ville
Av denne grunn brukt BinarySearch når antall get er vesentlig større enn put.

10³ put og 10⁶ get -> (10³)² + 10⁶*(log(10³/2))= 10⁶ + 10⁶*9 for Bin search og (10³)²/2 + 10⁶*(10³/2) = 5*10⁵ + 5*10⁸ for Seq. ->
Bin Search requires in total much less operations because of the lower get operations.
10⁶ put og 10³ get -> (10⁶)² + 10³*log(10⁶/2) for Binary Search og (10⁶)²/2 + 10³*(10⁶/2) for Sequencial Search. -> Seq Search vinner da
det blir a * 10^(11) mens Bin search blir b * 10^(12).

 */
public class SeqSearchST<Key, Value> {
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
        SeqSearchST<String, Integer> st;
        st = new SeqSearchST<>();
        Scanner reader = new Scanner(System.in);
        for (int i = 0; i<15; i++) {
            String key = reader.next();
            st.put(key, i);
        }
        for(String s: st.keys())
            System.out.println(s + " " + st.get(s));
    }

}


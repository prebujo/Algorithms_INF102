package Uke8;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graf {
    private final int V;
    private int E = 0;
    private Bag<Integer>[] adj;
    public Graf (int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v<V; v++)
            adj[v] = new Bag<Integer>();
    }
    public Graf (In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i<E;i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }
    public int V() {return V;}
    public int E() {return E;}

    private void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v);
    E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}

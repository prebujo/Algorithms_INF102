package Uke9;

import edu.princeton.cs.algs4.Stack;

public class DirectDFS {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public DirectDFS(DiGraf G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        ddfs(G,s);
    }
    private void ddfs (DiGraf G, int v){
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                ddfs(G,w);
            }
        }
    }
    public boolean hasPathTo(int v){
        return (marked[v]);
    }
    public Iterable<Integer> pathTo (int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}

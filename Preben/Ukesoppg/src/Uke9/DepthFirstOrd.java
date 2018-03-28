package Uke9;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrd {
    private boolean[] marked;
    private Stack<Integer> revPostOrd;
    public DepthFirstOrd(DiGraf G){
        revPostOrd = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V();v++){
            if (!marked[v]) dfo(G,v);
        }
    }
    private void dfo (DiGraf G, int v){
        marked[v] = true;
        for (int w:G.adj(v))
            if (!marked[w])
                dfo(G,w);
        revPostOrd.push(v);
    }
    public Iterable<Integer> revPostOrd(){
        return revPostOrd;
    }
}

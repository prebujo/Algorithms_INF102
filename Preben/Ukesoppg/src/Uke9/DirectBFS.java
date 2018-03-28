package Uke9;


import edu.princeton.cs.algs4.Queue;

public class DirectBFS {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DirectBFS(DiGraf G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s= s;
        dbfs(G,s);
    }

    private void dbfs (DiGraf G, int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for (int w:G.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    edgeTo[w] = v;
                    marked[w] = true;
                }
            }
        }

    }

}

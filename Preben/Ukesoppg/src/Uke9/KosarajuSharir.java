package Uke9;

public class KosarajuSharir {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSharir(DiGraf G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrd order = new DepthFirstOrd(G.reverse());
        for (int s: order.revPostOrd()){
            if (!marked[s]) {dfs(G, s); count++;}
        }
    }
    private void dfs (DiGraf G, int v){
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
    }
    public boolean stronglyConnect(int v, int w){
        return id[v] == id [w];
    }
    public int id(int v){
        return id[v];
    }
    public int count(){
        return count;
    }

}

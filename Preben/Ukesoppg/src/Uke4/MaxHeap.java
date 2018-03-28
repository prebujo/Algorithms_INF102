public class MaxHeap<Key extends Comparable<Key>> {
    /*
    Oppg 2.4.4 En Max oriented heap er definert som en liste hvor for enhvert k er barna til elementet, altså 2*k og 2*k + 1,  mindre
    enn eller lik elementet selv. i den liste som er sortert etter synkende rekkefølge vil dette alltid være tilfellet og listen er
    dermed per definisjon heap.

    Oppg 2.4.7
    k=2 -> i = 2,3 og ii = p>3
    k=3 -> i = 2,3 og ii = p>3
    k=4 -> i = 4,5,6,7 og ii = 7>p, p<4

    Oppg 2.4.8
    k=2,3,4 -> i = 15<p<=31 og ii = p<15

     */
    private Key[] pq;
    private int n = 0;
    public MaxHeap(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public int size(){
        return n;
    }
    public void insert(Key v){
        pq[++n] = v;
        swim (n);
    }
    public Key delMax(){
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);
        return max;
    }
    public Key value(int n){
        Key value = pq[n];
        return value;
    }
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j){
        Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }
    private void swim (int k){
        while (k>1 && less (k/2, k)){
            exch(k/2, k);
            k= k/2;
        }

    }
    private void sink (int k){
        while (2*k <= n){
            int j = 2*k;
            if (j < n && less(j, j+1))
                j++;
            if (!less(k, j)) break;
            exch(k, j);
            k=j;
        }
    }
    //Oppg. 2.4.18 Main som løser oppgaven. Når man oppretter en ny Heap må man passe på å lage den stor nok (derfor n+2).
    //Vi ser at Arrayen blir lik før og etter for både en og to insertions fulgt av delete.
    public static void main(String[] args){
        int n = 15;
        MaxHeap<Integer> pq = new MaxHeap<>(n+2);

        for (int i = 0; i<n-1;i++){
            pq.insert(i);
            for (int j = 1; j<pq.size();j++)
                System.out.print(pq.value(j) +" ");
            System.out.println();
        }

        System.out.println("insert 14: ");pq.insert(14);
        for (int j = 1; j<pq.size();j++)
            System.out.print(pq.value(j) + " ");
        System.out.println();
        System.out.println("insert 15: ");pq.insert(15);
        for (int j = 1; j<pq.size();j++)
            System.out.print(pq.value(j) + " ");
        System.out.println();
        System.out.println("Delete: "+ pq.delMax());
        for (int j = 1; j<pq.size();j++)
            System.out.print(pq.value(j) + " ");
        System.out.println();
        System.out.println("Delete: "+ pq.delMax());
        for (int j = 1; j<pq.size();j++)
            System.out.print(pq.value(j) + " ");

    }


}

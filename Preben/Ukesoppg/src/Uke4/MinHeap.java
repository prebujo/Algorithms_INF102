public class MinHeap<Key extends Comparable<Key>> {
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

    2.5.1
    CompareTo som returnerer 0 hvis de to variablene som sjekkes er like gjør at man sparer n sammenligninger da for loopen som følger
    sjekker for alle ulike verdier og returnerer kun verdier når this og that er ulike.
     */
    private Key[] pq;
    private int n = 0;
    public MinHeap(int maxN){
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
    public Key delMin(){
        Key min = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);
        return min;
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
        while (k>1 && less (k, k/2)){
            exch(k, k/2);
            k = k/2;
        }

    }
    private void sink (int k){
        while (2*k <= n){
            int j = 2*k;
            if (j < n && less(j +1 , j))
                j++;
            if (less(k, j)) break;
            exch(k, j);
            k=j;
        }
    }
    //Oppg. 2.4.18 Main som løser oppgaven. Når man oppretter en ny Heap må man passe på å lage den stor nok (derfor n+2).
    //Vi ser at Arrayen blir lik før og etter for både en og to insertions fulgt av delete.
    public static void main(String[] args){
        int n = 15;
        MinHeap<Integer> pq = new MinHeap<>(n+2);
        MaxHeap<Integer> mpq = new MaxHeap<>(n+2);

        for (int i = 2; i<n;++i){
            pq.insert(i);
            mpq.insert(i);
            System.out.println("MinHeap: ");
            for (int j = 0; j<=pq.size();j++)
                System.out.print(pq.value(j) +" ");
            System.out.println();
            System.out.println("MaxHeap: ");
            for (int j = 0; j<=mpq.size();j++)
                System.out.print(mpq.value(j) +" ");
            System.out.println();
        }

//        System.out.println("insert 2: ");pq.insert(2);
//        for (int j = 1; j<pq.size();j++)
//            System.out.print(pq.value(j) + " ");
//        System.out.println();
//        System.out.println("insert 1: ");pq.insert(1);
//        for (int j = 1; j<pq.size();j++)
//            System.out.print(pq.value(j) + " ");
//        System.out.println();
//        System.out.println("Delete: "+ pq.delMin());
//        for (int j = 1; j<pq.size();j++)
//            System.out.print(pq.value(j) + " ");
//        System.out.println();
//        System.out.println("Delete: "+ pq.delMin());
//        for (int j = 1; j<pq.size();j++)
//            System.out.print(pq.value(j) + " ");

    }


}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickInsert {
    /* Oppg. 2.3.25


     */
    public static int countComp = 0;
    public static int m = 30;
    public static void main(String[] args){
        int n = 100000;
        Comparable[] b = compN(n);
        Stopwatch timer = new Stopwatch();
        sort(b);
        System.out.println("Time used: " + timer.elapsedTime());
        double twonlnn = 2*n*Math.log(n);
        System.out.println("Compares: " + countComp);
        System.out.println("2(n)ln(n): " + twonlnn);
//        for (int i = 0; i < b.length; i++)
//            System.out.println(b[i]);
    }
    public static Comparable[] compN(int tablelength){
        Comparable[] b = new Comparable[tablelength];
        for(int i = 0; i<tablelength;i++)
            b[i] = i+1;
        return b;
    }
    public static void sort (Comparable [] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length -1);
    }
    private static void sort (Comparable[] a, int lo, int hi){
        if (hi <= lo) return;

        if (hi-lo <= m) InsertionCut.sort(a, hi, lo);
        else {
            int j = partition(a, lo, hi);
            if ((j-1)-lo > m) sort(a, lo, j - 1);
            else InsertionCut.sort(a, lo, j-1);
            if (hi - (j+1) > m) sort(a, j + 1, hi);
            else InsertionCut.sort(a, j+1, hi);
        }
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j= hi+1;
        Comparable v = a[lo];
        while (true){
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private static boolean less(Comparable v, Comparable w){
        countComp += 1;
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

import edu.princeton.cs.algs4.StdRandom;

public class QuickS {
    /* Oppg. 2.3.2:
    E A S Y Q U E S T I O N ville blitt sortert på følgende måte uten randomisering:

    E A S Y Q U E S T I O N
    v = K, a[i] = S, a[j] = A -> break -> bytter j med lo
    A E S Y Q U E S T I O N
    returenrer j = 1
    bruker sort på venstre del 0 og j-1 = 0 som bare returneres
    bruker så sort på høyre del lo = 2 og hi = 11:
        S Y Q U E S T I O N
    v = S, a[i] = Y, a[j] = N -> bytter i og j:
        S N Q U E S T I O Y
    v = S, a[i] = U, a[j] = O -> bytter i og j:
        S N Q O E S T I U Y
    v = S, a[i] = T, a[j] = I -> bytter i og j:
        S N Q O E S I T U Y
    v = S, a[i] = T, a[j] = I -> i>j -> break: bytter lo og j og returnerer j=8:
    A E I N Q O E S S T U Y
    sort på venstre del: lo = 2  j - 1 = 7
        I N Q O E S
    v = I, a[i] = N, a[j] = E -> bytter i og j:
        I E Q O N S
    v = I, a[i] = Q, a[j] = E -> i>j -> break -> bytter lo og j = 4:
        E I Q O N S
    sort venstre avsluttes da hi = lo = 3
    sort høyre for lo = j+1 = 5 og hi = 7 gir:
            Q O N S
    v = Q, a[i] = S, a[j] = N -> i > j -> break, bytter lo og j og returnerer j = 7:
            N O Q S
    sort venstre gjør ingenting da N O er sortert. sort høyre har lo > hi -> break

    sort på høyre del fra tidligere: lo = j +1 = 9 hi = 11
                      T U Y
    v = T, a[i] = U, a[j] = T -> i>j -> break -> byttet j med seg selv og resten blir avsluttet da det er sortert.
    A E E I N O Q S S T U Y
    Dermed er tabellen sortert.
     */

    /* Oppg. 2.3.4
    10 9 8 7 6 5 4 3 2 1
     1 2 3 4 5 6 7 8 9 10

    ??
     */

    /* Oppg. 2.3.6
    Cn for n = 100 ga 764 2nlogn er 921 -> 82%
    Cn for n = 1000 ga 12099 2nlogn er 13815 -> 87%
    Cn for n = 10000 ga 169670 2nlogn er 184206 -> 92%

    vi ser at for økt n går Cn mot 100% av 2nlnn.

     */
    /* Oppg 2.3.15
    Man kjører en runde med partition på nuts og bruker den samme paritionen på acorns etterpå. Dette krever en hviss endring
    av partisjon koden som da tar med partisjonen av nuts som argument (altså v).

     */
    public static int countComp = 0;
    public static void main(String[] args){
        int n = 10000;
        Comparable[] b = compN(n);
        sort(b);
        double twonlnn = 2*n*Math.log(n);
        System.out.println("Compares: " + countComp);
        System.out.println("2(n)ln(n): " + twonlnn);
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
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
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

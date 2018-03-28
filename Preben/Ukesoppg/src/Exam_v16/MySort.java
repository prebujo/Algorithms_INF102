package Exam_v16;

// Oppg 2. a)
public class MySort {
    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }
    //ikke nødvendig for oppg men grei øvelse
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }

    private static void exchange(int[] a, int i, int j){
        int v = a[i];
        a[i] = a[j];
        a[j] = v;
    }
}

/*Oppg 2. b)
    Insertionsort kjører i linær tid når listen allerede er sortert f. eks. [1,2,3,4,5,6,7]
    dette gjør at det kun blir gjort sammenligninger og ingen exchanger.
 */
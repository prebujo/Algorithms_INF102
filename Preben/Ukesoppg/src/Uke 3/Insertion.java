import java.util.Scanner;


public class Insertion {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; ++i) { // n²/2
            for (int j = i; j > 0 && less(a[j],  a[j - 1]); j--)
                exchange(a, j, j - 1);
        }

    }

    //returnerer True hvis compareTo returnerer mindre enn 0 (-1)
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //bytter om verdien i index i og j i tabellen a
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //printer ut en tabell
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    //sjekker om en tabell er sortert
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Comparable[] a = new Comparable[10];
        for(int i = 0; i<10;i++){
            a[i] = sc.nextInt();
        }
        sort(a);
        assert isSorted(a);
        show(a);

    }
}

public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort (a,0,a.length -1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi<=lo) return;
        int mid = lo +(hi-lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }


    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo; int j = mid+1;
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for(int k = lo; k <= hi; k++){
            if (i>mid)                          a[k] = aux[j++];
            else if (j>hi)                      a[k] = aux[i++];
            else if (less(aux[j], aux[i]))      a[k] = aux[j++];
            else                                a[k] = aux[i++];
        }

    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args){
        System.out.println("Sorterer en tabell: ");
        Double[] a = {4.5,1.3,8.2,6.3, 3.1, 9.9, 4.2, 1.1};
        for (int i = 0; i<a.length-1;i++)
            System.out.print(a[i] +" ");
        System.out.println();
        sort(a);
        System.out.println("Streng etter sortering");
        for (int j = 0; j<a.length-1;j++)
            System.out.print(a[j] + " ");



    }

}

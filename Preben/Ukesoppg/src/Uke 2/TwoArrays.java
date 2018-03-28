public class TwoArrays {
    public static void main(String[] args){
        int[] a = {4,8,9,13,16,19,25,28};
        int[] b = {2,3,5,10,11,12,20};
        int[] c = merge(a, b);
        for (int i : c) System.out.println(i);
    }
    public static int[] merge(int[] a, int[] b){
        int[] c = new int[a.length + b.length];

        int m = 0; //teller for array a
        int n = 0; //teller for array b
        int k = 0;
        while(m < a.length && n < b.length){
            if (a[m] <= b[n]) {
                    c[k] = a[m];
                    m++;
                } else {
                    c[k] = b[n];
                    n++;
                }
                k++;
            }
        while(m < a.length) {
            c[k] = a[m];
            m++;
            k++;
        }
        while(n < b.length) {
            c[k] = b[n];
            n++;
            k++;
        }
        return c;
    }
}

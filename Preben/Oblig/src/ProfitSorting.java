import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Random;

public class ProfitSorting {
    public static void main(String[] args){ //main method that declares a s and n and a list of n random numbers between 1 and n
                                            //After that it calls on the method binSearchT and linSearchT and prints each methods time
        int s = 80;
        int n = (int) Math.pow(10, 7);
        Integer[] a = new Integer[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            a[i] = (int) (n*rand.nextDouble());
        }
        double binTime = binSearchT(s, a);
        double linTime = linSearchT(s, a);
        System.out.println("binTime: "+ binTime);
        System.out.println("linTime: "+ linTime);

    }
    public static double binSearchT(int s, Integer[] a){    // method uses a random generator to generate s amounts of random variables
                                                            // and searches for that variable with binary search
        Random rand = new Random();
        int searchVar;
        Stopwatch timer = new Stopwatch();
        Arrays.sort(a);
        for (int i = 0; i < s; i++){
            searchVar = (int)(rand.nextDouble()*a.length);
            Arrays.binarySearch(a, searchVar);
        }
        return timer.elapsedTime();                         // the method returns the elapsed time
    }

    public static double linSearchT(int s, Integer[] a){    // method uses a random generator to generate s amounts of random variables
                                                            // and searches for that variable with a simple linear search
        Random rand = new Random();
        int searchVar;
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < s; i++){
            searchVar = (int)(rand.nextDouble()*a.length);
            for (int j = 0; j < a.length; j++){
                if (a[j] == searchVar)
                    break;
            }
        }
        return timer.elapsedTime();                         // the method returns the elapsed time
    }
}

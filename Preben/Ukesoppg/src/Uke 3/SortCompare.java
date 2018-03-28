import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class SortCompare {
    public static double time(String alg, Double[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("InsertionMvRight")) InsertionMvRight.sort(a);
        if (alg.equals("Quick")) QuickS.sort(a);
        if (alg.equals("QuickInsert")) QuickInsert.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int n, int trials){
        double total = 0.0;
        Double[] a = new Double[n];
        for(int t = 0; t <trials; t++){
            for(int i = 0; i < n; i++)
                a[i] = RandomDoub();
            total += time(alg, a);
        }
        return total;
    }
    public static double RandomDoub(){
        Random randGen = new Random();
        double d = randGen.nextDouble();
        return d;
    }

    public static void main(String[] args){
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int trials = Integer.parseInt(args[3]);
        double time1 = timeRandomInput(alg1, n, trials);
        double time2 = timeRandomInput(alg2, n, trials);
        double ratio = time2/time1;
        System.out.printf("For %d random Doubles\n     %s is", n, alg1);
        System.out.printf(" %.2f times faster than %s\n", ratio, alg2);
        System.out.println(alg1+": "+time1 );
        System.out.println(alg2+": "+time2 );
    }

}

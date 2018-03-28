package minimiseDisks;

import edu.princeton.cs.algs4.MaxPQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MinimiseDisks {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter file or 'Enter' for Random input.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Integer> files = new ArrayList<>();

        if (input.length() == 0){
            int n;
            int lo = 2;
            int hi = 1;
            System.out.println("No input file given, how many random files should be generated?");
            n = sc.nextInt();
            while (hi<lo) {
                System.out.println("Please give the lower limit of MB (between 0 and 1000 MB)");
                lo = sc.nextInt();
                System.out.println("Please give the upper limit of MB (between 0 and 1000 MB)");
                hi = sc.nextInt();
                if (hi<lo)
                    System.out.println("Please give a valid lo/hi interval. ");
            }
            System.out.println("Generating " + n + " random files between " + lo +" & " + hi +"MB.");
            for (int i = 0; i < n; i++)
                files.add(randNum(lo,hi));
        }
        else {
            File fil = new File(input);
            sc = new Scanner(fil);
            while (sc.hasNext()) {
                files.add(sc.nextInt());
            }
        }
        //lager en maxPQ og legger filene til kronologisk
        MaxPQ chronoPQ = saveFiles(files);

        //sorterer filene etter størrelse, med største først.
        Collections.sort(files, (a,b) -> -a.compareTo(b));

        //legger til de sorterte filene i en MaxPQ
        MaxPQ sortPQ = saveFiles(files);

        //printer disker til kronologisk rekkefølge
        System.out.println();
        System.out.println("Chronological order add");
        printDisks(chronoPQ);

        //printer disker til sortert rekkefølge
        System.out.println();
        System.out.println("Sorted order add");
        printDisks(sortPQ);
    }
    private static int randNum(int minN, int maxN) {
        return (int) Math.round(Math.random() * (maxN - minN) + minN);
    }

    //metode som printer diskene ut i riktig rekkefølge
    public static void printDisks( MaxPQ<Disk> pq){
        System.out.println("Amount of disks used: " + pq.size());
        int limit = pq.size();
        for (int c = 1; c <= limit; c++) {
            String print = getDiskI(c, pq).toString();
            System.out.println("Disk " + c + " : " + print);
        }
    }
    //Metode som lagrer filer til en disk og oppdaterer PQ.
    public static MaxPQ<Disk> saveFiles(ArrayList<Integer> files) {
        MaxPQ<Disk> pq = new MaxPQ<>();
        Disk a = new Disk();
        a.index = 1;
        pq.insert(a);
        int i = 2;

        for (Integer file : files) {
            if (file > pq.max().restcap()) {
                Disk b = new Disk();
                b.addFile(file);
                b.index = i;
                pq.insert(b);
                i++;
            } else {
                Disk b = pq.delMax();
                b.addFile(file);
                pq.insert(b);
            }
        }
        return pq;
    }
    //returmerer disken til en disk index og tar denne ut av PQ'en
    public static Disk getDiskI(int i, MaxPQ<Disk> pq){
        MaxPQ<Disk> pqInd = new MaxPQ<>();
        int limit = pq.size();
        for(int j = 0; j< limit; j++) {
            int ind = pq.max().index;
            if (ind == i){
                Disk a = pq.delMax();
                int restsz = pqInd.size();
                for (int k = 0; k < restsz; k++) //setter inn igjen de andre diskene i PQ'en
                    pq.insert(pqInd.delMax());
                return a;
            }
            pqInd.insert(pq.delMax());
        }

        return null;
    }
}


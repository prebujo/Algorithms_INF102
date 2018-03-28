package minimiseDisks;
import java.util.ArrayList;

public class Disk implements Comparable<Disk> {
    private final int disk_Cap = 1_000;
    public int cap_rest = disk_Cap;
    public int index = 0;
    private ArrayList<Integer> files = new ArrayList<>();

    //metode som returnerer resterende kapasitet.
    public int restcap(){ return cap_rest; }

    //metode som legger til en fil.
    public void addFile(int file){
        files.add(file);
        cap_rest -= file;
    }

    //sammenligner to disks og returnerer et integer
    public int compareTo(Disk disk2){
        return Integer.compare(cap_rest, disk2.cap_rest);
    }

    //lager en egen toString som overrider Object toString
    @Override
    public String toString() {
        String mbstring = "[";
        for (int i = 0; i < files.size(); i++) {
            mbstring += files.get(i);
            if (i < files.size()-1) {
                mbstring += " MB, ";
            } else {
                mbstring += "MB]";
            }
        }
        return mbstring;
    }





}

//
//package sorting.priorityQueues;
//
//        import edu.princeton.cs.algs4.In; import edu.princeton.cs.algs4.StdOut;
//        import edu.princeton.cs.algs4.MinPQ;
//        import oddsAndEnds.PolyPair;
//
//public class MultiwayMerge {// uses MinPQ, remembers stream number, no IndexMinPQ
//
//    public static void merge(PolyPair<String,Integer> disks) {
//        int N = streams.length;
//        MinPQ<disks> pq = new MinPQ<>(N);
//        for (int i=0; i<N; i++)
//            if (!streams[i].isEmpty()) pq.insert(new PolyPair(streams[i].readString(), i));
//        while (!pq.isEmpty()){
//            PolyPair<String,Integer> x = pq.delMin();
//            String s = x.getFst();
//            Integer i = x.getSnd();
//            StdOut.print(s + " ");
//            if (!streams[i].isEmpty()) pq.insert(new PolyPair(streams[i].readString(), i));
//        }
//        StdOut.println();
//    }
//
//    public static void main(String[] args){
//        int N = args.length;
//        In[] streams = new In[N];
//        for (int i=0; i<N; i++) streams[i] = new In(args[i]);
//        merge(streams);
//    }//End of main
//}//End of MultiwayMerge, a variation of Multiway, Algorithms - 4th Edition, p. 322
//

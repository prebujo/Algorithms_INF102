import edu.princeton.cs.algs4.StdIn;

public class FrequencyCount {
    public static void main (String[] args){
        int minlen = Integer.parseInt(args[0]);
        SeqSearchST<String, Integer> st = new SeqSearchST<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if(word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word)+1);
        }
        String max = "";
        st.put(max, 0);
        for (String word: st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        System.out.println(max + " " + st.get(max));
    }
}

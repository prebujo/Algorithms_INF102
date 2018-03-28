import java.util.Arrays;

public class ExactTriplicates {
    public static void main(String[] args){  // Main class mainly for testing purposes.
        String[] l1 = {"Preben", "Morten", "Rudi", "Mikael", "Marianne", "Cecilie"};
        String[] l2 = {"Preben", "Morten", "Rudi", "Mikael", "Marianne", "Cecilie", "Alfa"};
        String[] l3 = {"Preben", "Morten", "Rudi", "Mikael", "Marianne", "Cecilie", "Alfa"};
        String[] l4 = {"Preben", "Morten", "Rudi", "Mikael", "Marianne", "Cecilie", "Beta", "Quatro", "Alfa"};
        String result = checkList(l1, l2, l3,l4);
        if (result == "")
            System.out.println("No triplet");
        else
            System.out.println(result);
    }
    public static String checkList(String[] l1, String[] l2, String[] l3, String[] l4){ //method that takes 4 Lists of strings and returns a String

        //sorting all 4 lists with javas array sort assumed to be O(log(N))
        Arrays.sort(l1);
        Arrays.sort(l2);
        Arrays.sort(l3);
        Arrays.sort(l4);
        //current runtime O(4log(N)) ~ O(log(N))

        String lexo = "";

        int count;

        //running through all elements of the first list O(N)
        for (int i = 0; i < l1.length; i++) {
            count = 1;
            if (Arrays.binarySearch(l2, l1[i]) >= 0)
                count += 1;
            if (Arrays.binarySearch(l3, l1[i]) >= 0)
                count += 1;
            if (Arrays.binarySearch(l4, l1[i]) >= 0)
                count += 1;
            if (count == 3){
                lexo = l1[i];
                break;
            }
        }
        //running through elements of the last list O(N)
        for (int j = 0; j < l2.length; j++) {
            count = 1;
            if (Arrays.binarySearch(l1, l2[j]) >= 0)
                count += 1;
            if (Arrays.binarySearch(l3, l2[j]) >= 0)
                count += 1;
            if (Arrays.binarySearch(l4, l2[j]) >= 0)
                count += 1;
            if (count == 3 && (l2[j].compareTo(lexo)<0 || lexo == "")){
                lexo = l2[j];
                break;
            }
        }
        //Total running time in a worst case scenario of no triplets is now 2N(4*log(N)) ~ O(N*log(N))
        return lexo;


    }
}

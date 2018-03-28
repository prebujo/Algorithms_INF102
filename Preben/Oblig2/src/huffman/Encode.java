package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

public class Encode {
    private final static int wordLength = 4;
    private final static int freqLimit = 10;
    private static int asciicount = 32;//to not mix ascii values with other text we start out with double ascii characters, see
    public static void encode(String text, String file) throws FileNotFoundException {
        //doing a frequency count and sorting after frequency
        Map<String, Integer> wordFreq = wordFrequency(text);
        Map<String, Integer> sortedWords = sortByValue (wordFreq);

        //Assigning symbols to most frequent words
        Map<String, String> symbolDict = assignSymboles (sortedWords);

        //translating file with Symbol table and writing result to file.
        translateToFile(text, file, symbolDict);
    }

    private static void translateToFile(String text, String file, Map<String, String> symbolDict) throws FileNotFoundException {
        String translation = "";
        for (String key: symbolDict.keySet())
            translation += (key + " " + symbolDict.get(key) + "\n");
        translation += "\n****\n";

        for (String line : text.split("\n")) {
            translation += "\n";
            Scanner sc = new Scanner(line);

            while (sc.hasNext()){
                String word = sc.next();
                if (symbolDict.containsKey(word)) {
                    translation += (symbolDict.get(word))+ " ";
                } else
                    translation += word + " ";

            }
        }
        PrintWriter saveFile = new PrintWriter(file + ".cmp");
        saveFile.println(translation);
        saveFile.close();


    }

    private static Map<String,String> assignSymboles(Map<String, Integer> sortedWords) {
        Map<String, String> symbolDict = new HashMap<>();
        for (String key: sortedWords.keySet()){
            if (sortedWords.get(key) >= freqLimit && key.length() >= wordLength) {
                symbolDict.put(key, nextASCII());
            }
            if (sortedWords.get(key) < freqLimit)
                break;
        }
        return symbolDict;
    }

    private static String nextASCII() { //starts at !" and moves on from there. We assume that text doesnt contain two symbols
        String symbl = "";              //after eachother in this interval.

        // We are using all ASCII-characters from [33, 64]. Meaning our modulo value is 64-32 = 32.
        int modV = 32;
        Character[] ascii = new Character[modV+1];

        //we add each symbol to a table
        for (int i = 0; i < modV; i++) {
            ascii[i] = (char) (i+33) ;
        }

        // setting current value and iterating.
        int n = asciicount++;
        //setting symbol
        do {
            symbl+=(ascii[n % modV].toString());
            n/= modV;
        }   while (n != 0);
        return symbl;
    }

    //Method to help sort elements in Hashmap after value
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static Map<String,Integer> wordFrequency(String text) {
        Scanner sc = new Scanner(text);
        Map<String, Integer> wordFreq = new HashMap<>();
        while(sc.hasNext()){
            String word = sc.next();
            if(wordFreq.containsKey(word))
                wordFreq.put(word, wordFreq.get(word)+1);
            else wordFreq.put(word, 1);
        }
        return wordFreq;
    }

}


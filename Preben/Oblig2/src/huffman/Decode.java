package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Decode {
    public static String decode(String text) throws FileNotFoundException {
        Scanner sc = new Scanner(text);
        Map<String, String> symbDict = readST(sc);
        return cleanText(sc, symbDict);
    }

    private static String cleanText(Scanner sc, Map<String, String> symbDict) {
        String decoded = "";
        while (sc.hasNext()){
            String line = sc.nextLine();
            Scanner lsc = new Scanner(line);
            while (lsc.hasNext()) {
                String nextS = lsc.next();
                String space = " ";
                if (!lsc.hasNext())
                    space = "";
                decoded += symbDict.getOrDefault(nextS,nextS) + space;
            }
            if (sc.hasNext())
                decoded += "\n";
        }
        return decoded;
    }
    //method for reading the Symbol table.
    private static Map<String,String> readST(Scanner sc) {
        Map<String, String> symbDict = new HashMap<>();
        while (sc.hasNext()) {
            String word = sc.next();
            if (word.equals("****")){
                sc.nextLine();
                sc.nextLine();//removing space between **** and text...
                break;
            }
            String symbl = sc.next();
            sc.nextLine();
            symbDict.put(symbl, word);
        }
        return symbDict;
        
        
        
    }
}

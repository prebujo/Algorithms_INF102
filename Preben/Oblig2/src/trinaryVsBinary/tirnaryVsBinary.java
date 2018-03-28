package trinaryVsBinary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tirnaryVsBinary {
    public static void main(String[] args) throws FileNotFoundException {
        UBST<String, Integer> binaryTree = new UBST<>();
        UTST<String> ternaryTree = new UTST<>();
        File file = new File("files/alphabetical.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()){
            String dict = sc.next();
            binaryTree.put(dict, 1);
            ternaryTree.put(dict);
        }

        System.out.println("     Results from alphabetical.txt  ");
        System.out.println();
        System.out.println("Binary Tree compares: " + binaryTree.compares);
        System.out.println("Ternary Tree compares: " + ternaryTree.compares);

    }
}

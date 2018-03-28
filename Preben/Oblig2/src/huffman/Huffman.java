package huffman;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static huffman.Decode.decode;
import static huffman.Encode.encode;


public class Huffman {
    public static void main(String[] args) throws FileNotFoundException{

        Scanner sc = new Scanner(System.in);
        boolean enc = true;
        boolean both = false;
        String inpFile;
        while (true) {
            System.out.println("what do you wish to do, dec file (write 'd') encode file (write 'e') or both ('b')?");
            String answr = sc.nextLine();
            if (answr.toLowerCase().equals("d")) {
                enc = false;
                break;
            }
            else if (answr.toLowerCase().equals("e")) {
                break;
            }
            else if (answr.toLowerCase().equals("b")) {
                both = true;
                break;
            }
            System.out.println("please enter a valid answer d, e or b..");
        }
        while (true) {
            System.out.println("Please enter file you wish to process, example 'file.txt' or ('file.txt.cmp' for decode only)..");
            inpFile = sc.nextLine();

            if (true) //add statement to test if file is valid
                break;
            System.out.println("File not valid or without content. Please try again.");
        }
        String text = new In(inpFile).readAll();
        if (both){ //her decoder vi og encoder samme filen. resultatet for enc blir skrevet til en .cmp fil od decode kan brukeren velge
            encodeOnly(text, inpFile);
            String decodeTxt = new In(inpFile + ".cmp").readAll();
            decodeOnly(decodeTxt, inpFile);

        }
        else if (enc){ //her skal det kun encodes til filen .cmp
            encodeOnly(text, inpFile);
        }
        else{   //kun decode, brukeren velger hvordan output skal skrives
            decodeOnly(text, inpFile);
        }
    }
    //brukervennlig metode som kaller på decode og skriver ut eller lager en fil med teksten.
    public static void decodeOnly(String text, String inpFile) throws FileNotFoundException{
        System.out.println("Decoding text...");
        String decoded = decode((text));
        System.out.println("..decoding done! ");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Do you want to save string in file or print to console? (s/p) ");
            String answr = sc.nextLine();
            if (answr.toLowerCase().equals("s")) {
                PrintWriter out = new PrintWriter(inpFile + ".dec");
                out.println(decoded);
                out.close();
                System.out.println("File saved as <<" + inpFile +".dec>>");
                break;
            } else if (answr.toLowerCase().equals("p")) {
                System.out.println(decoded);
                break;
            }
            else
                System.out.println("Please enter valid answer s or p..");
        }

    }
    public static void encodeOnly(String text, String inpFile) throws FileNotFoundException{
        System.out.println("Encoding text...");
        encode(text, inpFile);
        System.out.println("..encoding done! Please see file " + inpFile +".cmp");
    }
}

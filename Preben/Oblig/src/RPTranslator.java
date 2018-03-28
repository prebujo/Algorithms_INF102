import java.util.Scanner;
import java.util.Stack;

public class RPTranslator {

    public static void main(String[] args){     //main class that takes input in the form of polish reverse from arguments and prints the
                                                //resulting readable calculation in the correct form.
        String input = new String();

        for(int i = 0; i < args.length; i++)    //Loop reads a sequence of strings (f.eks: "1" "3" osv) as input and puts them in one String separated by " "
            input += args[i] + " ";

        String output = expr(input);            //setting a string to the return statement of expr and printing it to the terminal
        System.out.println(output);

    }

    public static String expr(String input){    // method that takes a String argument input and returns a String output

        Scanner reader = new Scanner(input);    // Importing Scanner tool
        Stack <String> stack = new Stack<>();   // Declaring new Stack.

        while(reader.hasNext()){                // Reading while string has next argument.
            String element = reader.next();     // setting a String variable to next element

            if (element.equals("*") || element.equals("+")){                            // if next item for the stack is a operator
                String topItem = stack.pop();                                           // popping top item on stack and saving it
                String secondItem = stack.pop();                                        // popping second item on stack and saving it
                String pushItem = "("+ secondItem +" " + element + " " + topItem + ")"; // generating new item and
                stack.push(pushItem);                                                   // pushing the new item to stack
            }
            else{
                stack.push(element);                                                    // pushing element to stack if its not an operator
            }

        }
        return stack.pop();                     //returning the last String item on stack
    }
}

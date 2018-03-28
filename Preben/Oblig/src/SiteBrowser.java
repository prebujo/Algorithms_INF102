import java.util.Scanner;
import java.util.Stack;

public class SiteBrowser {

    public static void main(String[] args){             // main class that reads amout of arguments to follow and then reads arguments

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();                      // reading amount of arguments N
        scanner.nextLine();
        for (int i = 0; i < N; i++){                    // reading arguments N times
            String command = scanner.nextLine();        // setting command to the next input from user separated by nextline
            System.out.println(currBrowser(command));   // printing the return argument of currentbrowser
        }

        System.out.println();
    }

    private static Stack <String> stack = new Stack<>();    // generating a private Stack to store the websites
    private static Stack  <String> stack2 = new Stack<>();  // generating a private Stack to store history websites

    public static String currBrowser(String a){             // method that returns the current website as a String

        switch (a) {                        //switch for each case either *back*, *forward* or new website command
            case "*back*":
                if (stack.size() <= 1)      // if on first page or no page is navigated return a warning message
                    System.out.println("[Warning: no/too few websites]");
                else {                      // if not saving last page to stack2 and poping top element from stack
                    stack2.push(stack.pop());
                }
                break;

            case "*forward*":
                if (stack2.size() > 0)      // if a history exists pop item from stack2 and push that element in stack
                    stack.push(stack2.pop());
                else                        // if not return warning message
                    System.out.println("[Warning: Last Site]");
                break;
            default:
                stack.push(a);              // if no navigation command is given navigate to given website
                stack2.clear();             // clear history if navigating to new site
        }
        return stack.peek();                // returning the top of the stack, i.e. current website
    }


}

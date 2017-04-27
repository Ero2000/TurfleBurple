import java.util.ArrayList;
import java.util.Scanner;

public class HelpDesk {
    //Variables
    private ArrayPriorityQueue data;
    private ArrayList services;

    private Scanner sc;

    private static int lastID = 0;


    //Constructor
    public HelpDesk() {
	    data = new ArrayPriorityQueue();
	    services = new ArrayList();
	    sc = new Scanner(System.in);
        services.add("passwordReset");
	    services.add("dispatchTech");
	    services.add("rebootOpSystem");
    }

    public Ticket createTicket(String username, double priority, String problemDescription) {
        Ticket retTicket = new Ticket(username, priority, lastID, problemDescription);
	    lastID++;
        return retTicket;
    }

       private String inputString(String prompt) {
        System.out.print(prompt + " ");
        return sc.next(); 
    }
    
    private int inputInt(String prompt) {
        System.out.print(prompt + " ");
        return sc.nextInt(); 
    }

    // Returns true/false for a yes/no prompt
    private boolean inputYN(String prompt) {
        System.out.print(prompt + " y/n: ");
        return (sc.next().equals("y"));
    }

    private int inputOptions(String prompt, String options...) {
        int result = -1;
        while (result != -1) {
            System.out.println(prompt "(Pick an option from 1 to " + options.length + ": ");
            for(int i = 0; i < options.length; i++) {
                System.out.println(options[i] + " (" + (i+1) + ")");
            }
            result = sc.nextInt();
            if (result == -1) System.out.println("Please pick a valid number");
        }
        return result;
    }

    // TODO: Fill in
    private String callService(String service) {
        return "";
    }

    // Gos through questions. Returns the final problem
    private String prompt() {
        String name = inputString("What is your name?");
        String problem = inputString("Describe your problem");
        if (!inputYN("Is your device plugged in?")) {
            return "Plug it in";
        }
        if (!inputYN("Did you turn it on?")) {
            return "Turn it on. Push the on button dummy";
        }
        if ( inputOptions("What is your OS?", "Linux", "Windows", "Mac OSX", "Other") != 1) {
            return "Get a better OS";
        }
        if (!inputYN("Are you able to log in?") {
            if (!inputYN("Do you have a user account setup?")) {
                return callService("Acccount Setup"); // TODO: Make this mean something
            }
            if (!inputYN("Do you know your password?")) {
                return callService("Password Reset"); // TODO: Make this mean something
            }
            return "
        }
        
    }

    public static void main(String[] args) {
        

    }
}

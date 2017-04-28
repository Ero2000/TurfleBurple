import java.util.ArrayList;
import java.util.Scanner;

public class HelpDesk {
    enum SERVICE {
        PASSWORD_RESET,DISPATCH_TECH,REBOOT_OS
    }

    //Variables
    private ArrayPriorityQueue data;
    //private HashMap<String, SERVICE> services;

    private Scanner sc;

    private static int lastID = 0;


    //Constructor
    public HelpDesk() {
	    data = new ArrayPriorityQueue();
	    //services = new HashMap<String, SERVICE>();
	    sc = new Scanner(System.in);
        //services.put("passwordReset", SERVICE.PASSWORD_RESET);
	    //services.put("dispatchTech", SERVICE.DISPATCH_TECH);
	    //services.put("rebootOpSystem", SERVICE.REBOOT_OS);
    }

    public Ticket createTicket(String username, double priority, String problemDescription) {
        Ticket retTicket = new Ticket(username, priority, lastID, problemDescription);
	    lastID++;
        return retTicket;
    }

       private String inputString(String prompt) {
        System.out.print("\n" + prompt + " ");
        return sc.next(); 
    }
    
    private int inputInt(String prompt) {
        System.out.print("\n" + prompt + " ");
        return sc.nextInt(); 
    }

    // Returns true/false for a yes/no prompt
    private boolean inputYN(String prompt) {
        //System.out.print(prompt + " y/n: ");
        
        return (inputString(prompt + " y/n: ").equals("y"));
    }

    private int inputOptions(String prompt, String... options) {
        System.out.println("\n" + prompt + ": ");
        int result = -1;
        while (result == -1) {
            System.out.println("(Pick an option from 1 to " + options.length + ": ");
            for(int i = 0; i < options.length; i++) {
                System.out.println(options[i] + " (" + (i+1) + ")");
            }
            result = sc.nextInt();
            if (result == -1) System.out.println("Please pick a valid number");
        }
        return result;
    }

    private void callService(SERVICE service) {
        switch (service) {
            case DISPATCH_TECH:
                System.out.println("Dispatching tech. "
                        + "\n\n Please wait while we put you on hold forever");
                while ((true)) {}
            case PASSWORD_RESET:
                System.out.println("Sending password reset crew"
                        + "\n\nPlease send all of your private information "
                        + "including passwords, your social security number, "
                        + "your credit card number, its pin and drivers license."
                        );
                break;
            case REBOOT_OS:
                System.out.println("Please wait while we destroy your OS and replace it with a new one: "
                        + "\n\n This message will self destruct in 5 seconds");
                long startTime = System.currentTimeMillis();
                int deltaSeconds = 0;
                int lastDeltaSeconds = 0;
                while (deltaSeconds < 5) {
                    if (deltaSeconds != lastDeltaSeconds) {
                        System.out.println( (5 - deltaSeconds) + "...");
                        lastDeltaSeconds = deltaSeconds;
                    }
                    deltaSeconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
                }
                System.out.println("*boom*");
                System.exit(0);
                break;
        }        
    }

    // Gos through questions. Returns the final problem
    private String prompt() {
        String name = inputString("What is your name?");
        String problem = inputString("Describe your problem:");
        if (!inputYN("Is your device plugged in?")) {
            return "Plug it in";
        }
        if (!inputYN("Did you turn it on?")) {
            return "Turn it on. Push the on button dummy";
        }
        if ( inputOptions("What is your OS?", "Linux", "Windows", "Mac OSX", "Other") != 1) {
            return "Get a better OS";
        }
        if (!inputYN("Are you able to log in?")) {
            if (!inputYN("Do you have a user account setup?")) {
                callService(SERVICE.DISPATCH_TECH); 
                return "";
            }
            if (!inputYN("Do you know your password?")) {
                callService(SERVICE.PASSWORD_RESET);
                return "";
            }
            return "Welp you're screwed";
        }
        return "We couldn't find anything wrong. Geeet ouuta heere";    
    }

    public static void main(String[] args) {
        HelpDesk desk = new HelpDesk();
        System.out.println("Final Diagnosis: " + desk.prompt()); 

    }
}

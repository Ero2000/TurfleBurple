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

    public static Ticket createTicket(String username, double priority, String problemDescription) {
        Ticket retTicket = new Ticket(username, priority, lastID, problemDescription);
	    lastID++;
        return retTicket;
    }

    private String inputString(String prompt) {
        System.out.print("\n" + prompt + " ");
        return sc.nextLine(); 
    }
    
    private int inputInt(String prompt) {
        System.out.print("\n" + prompt + " ");
        int result = sc.nextInt(); 
        sc.nextLine(); // consume newline leftover
        return result;
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
            sc.nextLine(); // consume newline leftover
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

    // Gos through questions. Returns the priority
    private double prompt() {
        if (!inputYN("Is your device plugged in?")) {
            System.out.println("Plug it in");
            return 0.01;
        }
        if (!inputYN("Did you turn it on?")) {
            System.out.println("Turn it on. Push the on button dummy");
            return 0.01;
        }
        if ( inputOptions("What is your OS?", "Linux", "Windows", "Mac OSX", "Other") != 1) {
            System.out.println("Get a better OS");
            return 0.1;
        }
        if (!inputYN("Are you able to log in?")) {
            if (!inputYN("Do you have a user account setup?")) {
                callService(SERVICE.DISPATCH_TECH); 
                return 10;
            }
            if (!inputYN("Do you know your password?")) {
                callService(SERVICE.PASSWORD_RESET);
                return 8;
            }
            System.out.println("Welp you're screwed");
            return 0.1;
        }
        System.out.println("\nWe couldn't find anything wrong. Geeet ouuta heere");    
        return 0;
    }

    public static void main(String[] args) {
        ArrayPriorityQueue ticketQueue = new ArrayPriorityQueue();
        HelpDesk desk = new HelpDesk();
        
        boolean running = true;
        while (running) {

            String name = desk.inputString("What is your name?");
            String problem = desk.inputString("Describe your problem:");
            double priority = desk.prompt();
            
            Ticket ticket = createTicket(name, priority, problem);
            ticketQueue.add(ticket);
            if (!desk.inputYN("Would you like to add another problem?")) {
                running = false;
            }
        }
        System.out.println("FULL TICKET LIST: " );
        while(!ticketQueue.isEmpty()) {
            Ticket current = (Ticket)ticketQueue.removeMin();
            System.out.println("\n######TICKET " + current.getID() + "#####");
            System.out.println("name: " + current.getUsername());
            System.out.println("problem description: " + current.getDesc());
            System.out.println("priority: " + current.getPriority());
            System.out.println("##################");
        }
        System.out.println("\n\n\n Thank you for using HelpDesk!");
    }
}

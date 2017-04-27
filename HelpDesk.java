import java.util.ArrayList;
import java.util.Scanner;

public class HelpDesk {
    //Variables
    private ArrayPriorityQueue data;
    private ArrayList services;

    private Scanner sc;

    private static int last_id;

    //Constructor
    public HelpDesk() {
	    data = new ArrayPriorityQueue();
	    services = new ArrayList();
	    sc = new Scanner(System.in);
        services.add("passwordReset");
	    services.add("dispatchTech");
	    services.add("rebootOpSystem");
    }

    public Ticket createTicket(String username, double priority, int ID, String problemDescription) {
        Ticket retTicket = new Ticket(username, priority, ID, problemDescription);
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

    private boolean inputYN(String prompt) {
        System.out.print(prompt + " y/n: ");
        return (sc.next().equals("y"));
    }

    private void prompt() {
        String name = inputString("What is your name?");
        String problem = inputString("Describe your problem");
        
    }

    public static void main(String[] args) {
        

    }
}

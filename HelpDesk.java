import java.util.ArrayList;

public class HelpDesk {
    //Variables
    private ArrayPriorityQueue data;
    private ArrayList services;

    //Constructor
    public HelpDesk(){
	data = new ArrayPriorityQueue();
	services = new ArrayList();
	services.add("passwordReset");
	services.add("dispatchTech");
	services.add("rebootOpSystem");
    }

    public Ticket createTicket(String username, double priority, int ID, String problemDescription) {
        Ticket retTicket = new Ticket(username, priority, ID, problemDescription);
	return retTicket;
    }
}

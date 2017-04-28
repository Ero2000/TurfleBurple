
/** Ticket.java
  * A "problem" ticket that holds user data from a submitted issue.
  * Holds:
  *     - A description of the user's problem
  *     - A priority for their problem
  *     - A username to show we care (not really)
  *     - An ID (why?)
  */

public class Ticket implements Comparable {

    private String username, problemDescription;
    private double priority;
    private int ID;

    public Ticket(String username, double priority, int ID, String problemDescription) {
        this.username = username;
	this.problemDescription = problemDescription;
        this.priority = priority;
        this.ID = ID;
    }


    //Accessors
    public int getID(){
	return ID;
    }
    public double getPriority(){
	return priority;
    }
    public String getUsername(){
	return username;
    }
    public String getDesc(){
	return problemDescription;
    }

    //mutators
    public void setID(int x){
	ID = x;
    }

    public void setPriority(double x){
	priority = x;
    }

    public void setUsername(String x){
	username = x;
    }

    public void setDesc(String x){
	problemDescription = x;
    }

    //compareTo() compares priorities
    public int compareTo(Object x){
	if (this.getPriority() > ((Ticket)x).getPriority())
	    return 1;
	return -1;
    }
}

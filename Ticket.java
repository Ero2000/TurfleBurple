
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

    public Ticket(String username, String priority, int ID) {
        this.username = username;
        this.priority = priority;
        this.ID = ID;
    }
}

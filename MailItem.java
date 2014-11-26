
/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    private String from;
    
    private String to;
    
    private String message;
    
    public MailItem(String from, String to, String message)
    {
        this.message=message;
        this.to=to;
        this.from=from;
    }
    
    public String getFrom()
    {
        return from;
    }
    
       public String getMessage()
    {
        return message;
    }
    
       public String getTo()
    {
        return to;
    }
    
    public void print()
    {
        System.out.println("from: " + from);
        System.out.println("to: " + to);
        System.out.println("message: " + message);
    }
    
}

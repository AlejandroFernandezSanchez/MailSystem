

public class MailClient
{  
    private MailServer server;
    
    private String user;
        
    public MailClient(MailServer servidor, String usuario)
    {
       user=usuario;
       server=servidor;
    }

   
    public MailItem getNextMailItem()
    {
        MailItem item;
        item = server.getNextMailItem(user);
        return item;
    }
    
    public void printNextMailItem()
    {
        server.getNextMailItem(user).print();
    }
    
    public void sendMailItem(String para, String mensaje)
    {
        MailItem emilio;
        emilio = new MailItem(user, para, mensaje);
        server.post(emilio);
        
        
        
    }
    
}
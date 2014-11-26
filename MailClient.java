

public class MailClient
{  
    private MailServer server;
    
    private String user;
        
    public MailClient(MailServer server, String user)
    {
       this.user=user;
       this.server=server;
    }
    
       
    public MailItem getNextMailItem()
        {
        MailItem item;
        item = server.getNextMailItem(user);
        
        if (item==null)
        {
            System.out.println("No hay mensajes nuevos");
        }       
        return item;                
    }
    
    public int howManyMails()
    {
        return server.howManyMailItems(user);
    }
    
    public void printNextMailItem()
        {
            MailItem item;
            item = server.getNextMailItem(user);
            
            if (item==null)
            {
                System.out.println("No hay mensajes nuevos");
            }
            else 
            {                
                item.print();
            }        
        }
        
    public void sendMailItem(String para, String subject, String message)
        {
            MailItem emilio;
            emilio = new MailItem(user, para, subject, message);
            server.post(emilio);                        
        }            
    }

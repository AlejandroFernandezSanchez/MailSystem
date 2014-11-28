

public class MailClient
{  
    private MailServer server;
    
    private String user;
    
    private MailItem savedMail;
    
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
    
    public void howManyMails()
    {
        System.out.println("Tienes "+ server.howManyMailItems(user) + " mensaje/s nuevo/s" );  
    }
    
    public void getNextMailItemAndAutorespond()
    {
        MailItem item;      
        item = server.getNextMailItem(user);
        savedMail= item;
        if (item!=null){            
            String newSubject = "RE: " + item.getSubject();
            String newMessage = ""+ item.getMessage() + "\n" +
            "###########AUTORESPUESTA###########" +
            "\n###########ESTAMOS DE VACACIONES###########";
            MailItem newMail = new MailItem(item.getTo(), item.getFrom(), newSubject, newMessage) ;
            server.post(newMail);            
        }
        else
        {
            System.out.println("No hay mensajes nuevos");
        }
        
    }
    
    public void getLastMail()
    {
       if (savedMail!=null)
       {
           savedMail.print();
        }
        else
        {
            System.out.println("No hay ningún mensaje");
        }
    }
    
    public void printNextMailItem()
        {
            MailItem item;
            item = server.getNextMailItem(user);
            savedMail= item;
            
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



public class MailClient
{  
    private MailServer server;
    
    private String user;
    
    private MailItem savedMail;
    
    private MailItem savedSpam;
    
    private int sendCount;
    
    private int receivedCount;
    
    private int spamCount;
    
    private int longestInt;
    
    private String longestUser;
    
    
    public MailClient(MailServer server, String user)
    {
       this.user=user;
       this.server=server;
    }
    
       
    public MailItem getNextMailItem()
        {
        MailItem item;
        item = server.getNextMailItem(user);
        
        String spam1 = "viagra";
        String spam2 = "oferta";  
        String noSpam = "proyecto";
        boolean found = false ;
        boolean found2 = false ;
        
        if (item.getMessage().contains(spam1) || item.getMessage().contains(spam2))
        {
            found = true;
        }
        
        if (item.getMessage().contains(noSpam))
        {
            found2 = true;
        }
        
        if ((found==true)&&(found2==false))
        {
            savedSpam = item;
            item=null;
            spamCount = spamCount +1;
            receivedCount = receivedCount +1;
              if (item.getMessage().length()>longestInt)
            {
                longestInt = item.getMessage().length();
                longestUser = item.getFrom();
            }
        }
        else
        {
            savedMail= item;
            receivedCount = receivedCount +1;
              if (item.getMessage().length()>longestInt)
            {
                longestInt = item.getMessage().length();
                longestUser = item.getFrom();
            }
        }                          
        
        return item;                
    }
    
    public void tester(int sendCount, int receivedCount, int spamCount, int longestInt, String longestUser)
    {
        this.sendCount= sendCount;
        this.receivedCount = receivedCount;
        this.spamCount = spamCount;
        this.longestInt = longestInt;
        this.longestUser = longestUser;
    }
    
    public void howManyMails()
    {
        System.out.println("Tienes "+ server.howManyMailItems(user) + " mensaje/s nuevo/s" );  
    }
    
    public void statics()
    {
        System.out.println("ESTADÍSTICAS");
        System.out.println("Mensajes recibidos en total: " + receivedCount + "." );
        System.out.println("Mensajes recibidos descartados por spam: "+ spamCount + ".");
        
        if (spamCount>0)
        {
            System.out.println(spamCount*100/receivedCount + "% de los correos son spam");
        }        
        
        System.out.println("Mensajes enviados: " +sendCount);
        
        if (longestInt>0)
        {
            System.out.println("El mensaje más largo recibido hasta ahora es de " +longestUser+ ", con " +longestInt+ " carácteres.");
        }
        
    
    
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
            receivedCount = receivedCount +1;
              if (item.getMessage().length()>longestInt)
            {
                longestInt = item.getMessage().length();
                longestUser = item.getFrom();
            }
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
    
    public void getLastSpam()
    {
       if (savedSpam!=null)
       {
           savedSpam.print();
        }
        else
        {
            System.out.println("No hay ningún mensaje de spam");
        }
    }
    
    public void printNextMailItem()
    {
        MailItem item;
        item = server.getNextMailItem(user);                
        String spam1 = "viagra";
        String spam2 = "oferta";  
        String noSpam = "proyecto";
        boolean found = false ;
        boolean found2 = false ;
        
        if (item.getMessage().contains(spam1) || item.getMessage().contains(spam2))
        {
            found = true;
        }
              
        if (item.getMessage().contains(noSpam))
        {
            found2 = true;
        }
        
        if (item==null)
        {
            System.out.println("No hay mensajes nuevos");
        }
        else if ((found==true)&&(found2==false))
        {
            savedSpam = item;
            System.out.println("Este mensaje contenía spam");
            spamCount = spamCount +1;
            receivedCount = receivedCount +1;
              if (item.getMessage().length()>longestInt)
            {
                longestInt = item.getMessage().length();
                longestUser = item.getFrom();
            }           
        }
        else
        {                
            savedMail= item;
            item.print();
            receivedCount = receivedCount +1;
            if (item.getMessage().length()>longestInt)
            {
                longestInt = item.getMessage().length();
                longestUser = item.getFrom();
            }
            
        }            
    }
        
    public void sendMailItem(String para, String subject, String message)
        {
            MailItem emilio;
            emilio = new MailItem(user, para, subject, message);
            server.post(emilio);
            sendCount = sendCount +1;
        }            
    }

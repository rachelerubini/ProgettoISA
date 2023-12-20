package it.isa.progetto;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;


import org.junit.Test;

//TESTIAMO ISCRIZIONEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

public class IscrizioneDAOTest 
{
    //Della create non testo la duplicated perchè abbiamo deciso di non lanciarla ma di mettere deleted a Y
    @Test 
    public void testriiscrizione() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(2);
            corsoprova=daocc.findCorsoByID_CO(2);
            dao.createIscrizione(corsoprova,clienteprova);
        
            
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());
            dao.createIscrizione(corsoprova,clienteprova);

            assertEquals(dao.countrighe(clienteprova.getID_CL(),corsoprova.getID_CO()), 1);
            assertEquals("N", dao.findIscrizioneByID(clienteprova.getID_CL(),corsoprova.getID_CO()).isDELETED());
           
        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal corso prova creato sopra
        finally         
        {
            try
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(2);
            corsoprova=daocc.findCorsoByID_CO(2);
            
            //verifico che la findCorsoCliente mi dia il corso   giusto 

            //elimino l'iscrizione fatta
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

  
    @Test 
    public void testcreateIscrizioneSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();
            
            dao.createIscrizione(new Corso(), new Cliente());
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }

    }

    @Test 
    public void testdelete() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(1);
            corsoprova=daocc.findCorsoByID_CO(1);
            dao.createIscrizione(corsoprova,clienteprova);

            int iscritto=dao.findCorsoCliente(clienteprova.getID_CL(),corsoprova.getID_CO());
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());
            assertEquals(0, dao.findCorsoCliente(clienteprova.getID_CL(),corsoprova.getID_CO()));
            //verifico che non  ci sia piu l'iscrizione
            

        }
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }     
    }



    // Test che prova a generare un errore sql disiscrivendomi al corso subito dopo aver chiuso la connessione
    @Test 
    public void testdeleteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();
            
            dao.disiscrivi(1,2);
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }



    @Test 
    public void testcountiscritti() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Cliente clienteprova1 = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);

            daocc.create("prova","prova","prova");
            
            clienteprova=daocl.findClienteByID(1);
            clienteprova1=daocl.findClienteByID(2);
            corsoprova=daocc.findByNomeCorso("prova");
            dao.createIscrizione(corsoprova,clienteprova);
            dao.createIscrizione(corsoprova,clienteprova1);
            
            int iscritti=dao.countiscritti(corsoprova.getID_CO());
            assertEquals(iscritti, 2);
            //verifico che la findCorsoCliente mi dia il corso   giusto 

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal corso prova creato sopra
        finally         
        {
            try
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Cliente clienteprova1 = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            corsoprova=daocc.findByNomeCorso("prova");
            daocc.delete(corsoprova);

            clienteprova=daocl.findClienteByID(1);

            //elimino l'iscrizione fatta
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());

            clienteprova1=daocl.findClienteByID(2);
            dao.disiscrivi(clienteprova1.getID_CL(),corsoprova.getID_CO());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Test che prova a generare un errore sql 
    @Test 
    public void testcountiscrittiSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();
            
            dao.countiscritti(2);
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test 
    public void testcountrighe() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);

            daocc.create("prova","prova","prova");
            
            clienteprova=daocl.findClienteByID(1);
            
            corsoprova=daocc.findByNomeCorso("prova");
            dao.createIscrizione(corsoprova,clienteprova);
            
            
            int righe=dao.countrighe(clienteprova.getID_CL(),corsoprova.getID_CO());
            assertEquals(righe, 1);
            //verifico che la findCorsoCliente mi dia il corso   giusto 

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal corso prova creato sopra
        finally         
        {
            try
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            corsoprova=daocc.findByNomeCorso("prova");
            daocc.delete(corsoprova);

            clienteprova=daocl.findClienteByID(1);

            //elimino l'iscrizione fatta
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());

           
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

     // Test che prova a generare un errore sql 
    @Test 
    public void testcountrigheSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();
            
            dao.countrighe(1,1);
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void testfindCorsoCliente() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(1);
            corsoprova=daocc.findCorsoByID_CO(1);
            dao.createIscrizione(corsoprova,clienteprova);
            int iscritto=dao.findCorsoCliente(clienteprova.getID_CL(),corsoprova.getID_CO());
            assertEquals(iscritto, 1);
          

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dall'iscrizione di prova creato sopra
        finally         
        {
            try
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(1);
            corsoprova=daocc.findCorsoByID_CO(1);
            
            

            //elimino l'iscrizione fatta
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test 
    public void testfindCorsoClienteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();

            dao.findCorsoCliente(1,2);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }
    }

    @Test 
    public void testfindIscrizioneByID() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(1);
            corsoprova=daocc.findCorsoByID_CO(1);
            dao.createIscrizione(corsoprova,clienteprova);
            Iscrizione iscrizione=dao.findIscrizioneByID(clienteprova.getID_CL(),corsoprova.getID_CO());

            assertEquals(iscrizione.getCliente().getID_CL(),1);
            assertEquals(iscrizione.getCorso().getID_CO(),1);
            assertEquals(iscrizione.isDELETED(),"N");
            

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dall'iscrizione di prova creato sopra
        finally         
        {
            try
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();
            IscrizioneDAO dao =new IscrizioneDAO(con);
            
            ClienteDAO daocl =new ClienteDAO(con);
            CorsoDAO daocc =new CorsoDAO(con);
            clienteprova=daocl.findClienteByID(1);
            corsoprova=daocc.findCorsoByID_CO(1);
            
           

            //elimino l'iscrizione fatta
            dao.disiscrivi(clienteprova.getID_CL(),corsoprova.getID_CO());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test 
    public void testfindIscrizioneByIDSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            IscrizioneDAO dao =new IscrizioneDAO(con);
            con.close();

            dao.findIscrizioneByID(1, 2);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }
    }

}




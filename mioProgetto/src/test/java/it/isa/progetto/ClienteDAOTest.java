
package it.isa.progetto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;


//TESTIAMO CLIENTEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

public class ClienteDAOTest 
{

  

    // testo la create verificando che la duplicated controlli che io non possa creare un cliente uguale ad un altro
    @Test 
    public void testcreateEsistente() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
            
            dao.create("prova", "prova","prova","prova","prova", "2012-12-12");
            assertThrows(DuplicatedObjectException.class, () -> {dao.create("prova", "prova","prova","prova","prova","2012-12-12");});
            //verifico che la create mi dia una eccezione duplicated (se ce la dà allora funziona)

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal cliente prova creato sopra
        finally         
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
                ClienteDAO dao =new ClienteDAO(con);
            
                Cliente cliente1 = new Cliente();
                cliente1 = dao.findByMAILCliente("prova");
                dao.delete(cliente1);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


    @Test 
    public void testcreateEsistenteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
            con.close();
        
            dao.create("prova", "prova","prova","prova","prova","prova");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }


    //testo la delete verificando che elimini davvero il cliente 
    @Test 
    public void testdelete() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
            
            dao.create("prova", "prova","prova","prova","prova", "2012-12-12");
            Cliente cl1 = new Cliente();
            cl1=dao.findByMAILCliente("prova");
            dao.delete(cl1);
            assertEquals(null, dao.findByMAILCliente("prova"));
            //verifico che non  ci sia piu il cliente con mail "prova"

            

        }
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        
        
    }


    // Test che prova a generare un errore sql eliminando il clientee subito dopo aver chiuso la connessione
    @Test 
    public void testdeleteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
            con.close();
            
            dao.delete(new Cliente());
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    //test per vedere se funnziona il findbymail
    @Test 
    public void testfindByMAILCliente() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
            Cliente cl= new Cliente();
            cl=dao.create("prova", "prova","prova","prova","prova", "2012-12-12");
            String SSNcl= cl.getSSN();
            Cliente cl1= new Cliente();
            cl1=dao.findByMAILCliente("prova");
            String SSNcl1=cl1.getSSN();
            assertEquals(SSNcl, SSNcl1);
            //verifico che la findbymail mi dia il cliente   giusto 

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal cliente prova creato sopra
        finally         
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
                ClienteDAO dao =new ClienteDAO(con);
            
                Cliente cliente1 = new Cliente();
                cliente1 = dao.findByMAILCliente("prova");
                dao.delete(cliente1);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


    @Test 
    public void testfindByMAILClienteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
            con.close();
            
            dao.findByMAILCliente("prova");   
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }


    //test per vedere se funnziona il findbyID
    @Test 
    public void testfindClienteByID() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
            
            dao.create("prova", "prova","prova","prova","prova", "2012-12-12");
            
            Cliente cl1= new Cliente();
            cl1=dao.findByMAILCliente("prova");
            
            Cliente cl2=new Cliente();
            cl2=dao.findClienteByID(cl1.getID_CL());
            
            assertEquals(cl1.getID_CL(), cl2.getID_CL());
            //verifico che la findbyid mi dia il cliente   giusto 

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dal cliente prova creato sopra
        finally         
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
                ClienteDAO dao =new ClienteDAO(con);
            
                Cliente cliente1 = new Cliente();
                cliente1 = dao.findByMAILCliente("prova");
                dao.delete(cliente1);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


    @Test 
    public void testfindClienteByIDSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
            con.close();

            dao.findClienteByID(0);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }


    // testo il numero di elementi che la findAll restituisce (se unaa chiamata sql che conta i clienti dà lo stesso numero di clienti restituiti dalla  finAllClienti)
    @Test
    public void findAllClientiTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");

            Statement st = con.createStatement();

            String sql = "SELECT COUNT(*)"
                        +" FROM cliente"
                        +" WHERE DELETED='N'";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int numero = rs.getInt(1);   // numero varrà il numro totale di clienti (es.29)
            ClienteDAO dao = new ClienteDAO(con);
            List<Cliente> clienti= new ArrayList<>();
            clienti = dao.findAllClienti();
            assertEquals(clienti.size(), numero);

        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void findAllClientiSQLExceptionTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
            con.close();
            dao.findAllClienti();
            
        }

        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

 
}
    
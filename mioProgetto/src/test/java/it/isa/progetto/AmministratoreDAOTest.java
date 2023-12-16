package it.isa.progetto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

//TESTIAMO AMMINISTRATOREDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

public class AmministratoreDAOTest 
{
    //test il findbymail
@Test 
    public void testfindByMAILAmministratore() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            AmministratoreDAO dao =new AmministratoreDAO(con);
            Amministratore am=dao.findByMAILAmministratore("martino@gmail.com");
            
            assertEquals(am.getID_A(), 1);
            //verifico che la findbymail mi dia amministratore   giusto 

            

        }
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

       
           
    }


    @Test 
    public void testfindByMAILAmministratoreSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            AmministratoreDAO dao = new AmministratoreDAO(con);
            con.close();
            
            dao.findByMAILAmministratore("prova");   
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void testfindAmministratoreByID() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            AmministratoreDAO dao =new AmministratoreDAO(con);
            Amministratore am=dao.findAmministratoreByID(1);
            
            assertEquals(am.getMAIL(), "martino@gmail.com");
            //verifico che il findbyid mi dia amministratore   giusto 

            

        }
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

       
           
    }

    @Test 
    public void testfindAmministratoreByIDSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            AmministratoreDAO dao = new AmministratoreDAO(con);
            con.close();

            dao.findAmministratoreByID(0);
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
}





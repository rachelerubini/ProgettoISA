package it.isa.progetto;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

//TESTIAMO AMMINISTRATOREDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

public class AmministratoreDAOTest 
{
    
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





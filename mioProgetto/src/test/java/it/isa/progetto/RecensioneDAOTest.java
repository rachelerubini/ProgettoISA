package it.isa.progetto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.HashMap;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

//TESTIAMO RECENSIONEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

public class RecensioneDAOTest 
{
    // testo la create verificando che la duplicated controlli che io non possa creare una recensione uguale ad un altra
    @Test 
    public void testcreateEsistente() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao =new RecensioneDAO(con, clienteprova);
            Corso corsoprova = new Corso();
            dao.create(5, "2012-12-12",corsoprova,clienteprova);
            assertThrows(DuplicatedObjectException.class, () -> {dao.create(5, "2012-12-12",corsoprova,clienteprova);});
            //verifico che la create mi dia una eccezione duplicated (se ce la dà allora funziona)

            

        }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
        catch(Exception e)
        {  
            System.out.println(e.getMessage());
        }  

        //questo blocco ci serve per pulire (deleted:Y ) il DB Mysql dala recensione prova creata sopra
        finally         
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
                Cliente clienteprova = new Cliente();
                RecensioneDAO dao =new RecensioneDAO(con, clienteprova);
            
                Recensione recensione1 = new Recensione();
                recensione1 = dao.findRecensioneByDATA("2012-12-12");
                dao.delete(recensione1);
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
            Cliente clienteprova = new Cliente();
            Corso corsoprova = new Corso();

            RecensioneDAO dao =new RecensioneDAO(con, clienteprova);
            con.close();
        
            dao.create(5, "2012-12-12",corsoprova,clienteprova);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }

    }


    @Test 
    public void testfindRecensioneByIDSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao = new RecensioneDAO(con, clienteprova);
            con.close();

            dao.findRecensioneByID(0);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }


    @Test 
    public void testfindRecensioneByDATASQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao = new RecensioneDAO(con, clienteprova);
            con.close();

            dao.findRecensioneByDATA("2021-03-20");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }


    // Test che prova a generare un errore sql eliminando una recensione subito dopo aver chiuso la connessione
    @Test 
    public void testdeleteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao = new RecensioneDAO(con, clienteprova);
            con.close();
            
            dao.delete(new Recensione());
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    // testo il numero di elementi che la findAll restituisce (se una chiamata sql che conta le recensioni dà lo stesso numero di recensioni restituiti dalla  finAllRecensioni)
    @Test
    public void findAllRecensioniTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");

            Statement st = con.createStatement();

            String sql = "SELECT COUNT(*)"
                        +" FROM recensione"
                        +" WHERE DELETED='N'";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int numero = rs.getInt(1);   // numero varrà il numro totale di recensioni (es.29)
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao = new RecensioneDAO(con, clienteprova);
            List<Recensione> recensioni= new ArrayList<>();
            recensioni = dao.findAllRecensioni();
            assertEquals(recensioni.size(), numero);

        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void findAllRecensioniSQLExceptionTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            Cliente clienteprova = new Cliente();
            RecensioneDAO dao = new RecensioneDAO(con, clienteprova);
            con.close();
            dao.findAllRecensioni();
            
        }

        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}



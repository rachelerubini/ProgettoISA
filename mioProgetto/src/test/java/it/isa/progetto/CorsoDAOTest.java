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


//TESTIAMO CORSODAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO

//NO EXCEPTIION MA si SQL E AGGIUNGI I TEST DELLLE FUNZIONI

public class CorsoDAOTest 
{
    // testo la create verificando che la duplicated controlli che io non possa creare un corso uguale ad un altro
    @Test 
    public void testcreateEsistente() 
    {
        

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            CorsoDAO dao =new CorsoDAO(con);
            
            dao.create("prova","prova","prova");
            assertThrows(DuplicatedObjectException.class, () -> {dao.create("prova","prova","prova");});
            //verifico che la create mi dia una eccezione duplicated (se ce la dà allora funziona)

            

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
                CorsoDAO dao =new CorsoDAO(con);
            
                Corso corso1 = new Corso();
                corso1 = dao.findByNomeCorso("prova");
                dao.delete(corso1);
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
            CorsoDAO dao =new CorsoDAO(con);
            con.close();
        
            dao.create("prova","prova","prova");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }

    }

    // Test che prova a generare un errore sql eliminando il corso subito dopo aver chiuso la connessione
    @Test 
    public void testdeleteSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            CorsoDAO dao = new CorsoDAO(con);
            con.close();
            
            dao.delete(new Corso());
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }



    // testo il numero di elementi che la findAll restituisce (se unaa chiamata sql che conta i corsi dà lo stesso numero di corsi restituiti dalla finAllCorsi)
    @Test
    public void findAllCorsiTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");

            Statement st = con.createStatement();

            String sql = "SELECT COUNT(*)"
                        +" FROM corso"
                        +" WHERE DELETED='N'";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int numero = rs.getInt(1);   // numero varrà il numro totale di clienti (es.29)
            CorsoDAO dao = new CorsoDAO(con);
            List<Corso> corsi= new ArrayList<>();
            corsi = dao.findAllCorsi();
            assertEquals(corsi.size(), numero);

        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void findAllCorsiSQLExceptionTest()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            CorsoDAO dao = new CorsoDAO(con);
            con.close();
            dao.findAllCorsi();
            
        }

        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    @Test 
    public void testfindCorsoByID_COSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            CorsoDAO dao = new CorsoDAO(con);
            con.close();

            dao.findCorsoByID_CO(0);
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }
    }

    @Test 
    public void testfindByNomeCorsoSQLException()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            CorsoDAO dao = new CorsoDAO(con);
            con.close();

            dao.findByNomeCorso("prova");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }
}



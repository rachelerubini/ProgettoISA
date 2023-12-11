package it.isa.progetto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

//TESTIAMO ISCRIZIONEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO
//NO EXCEPTIION MA si SQL E AGGIUNGI I TEST DELLLE FUNZIONI

public class IscrizioneDAOTest 
{
    //Della create non testo la duplicated perchè abbiamo deciso di non lanciarla ma di mettere deleted a Y

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




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




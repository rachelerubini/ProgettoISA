
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


//TESTIAMO CLIENTEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO
//Non abbiamo fatto i  testdeleteNotFoundException() ovvero il Test che mi copre il missing object (Non abbiamo gesstito qiesta coosa nel codice quindi non la testimo)




public class ClienteDAOTest {

   /*
    //QUESTA NON LA FACCIAMO
    @Test 
    public void testcreateNonEsistente()
    {

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");

            
            
        
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        //Brano brano = new Brano();                            //Noi dobbiamo fare qualcosa al posto delle operazioni che fa lui  con brano?
        //brano.setId(1);

       ClienteDAO dao =new ClienteDAO(con);
      
        
       cliente1=dao.create("prova", "prova","prova","prova","prova","prova");
       cliente2.setID_CL(0);
       cliente2.setSSN("prova");
       cliente2.setNOME("prova");
       cliente2.setCOGNOME("prova");
       cliente2.setMAIL("prova");
       cliente2.setPASSWORD("prova");
       cliente2.setNASCITA("prova");
       cliente2.setDELETED("P"); // va  messa?????
       int id=cliente1.getID_CL();
       cliente2.setID_CL(id);
       cliente1 = dao.findClienteByID(id);

       
       //dao.creaAscolto(brano, utente1);
      //dao.creaAscolto(brano, utente1);
        
      // utente1 = dao.findById(id);
      //  HashMap<Brano, Integer> mappa = new HashMap<Brano, Integer>();
      //  Integer integer = new Integer(2);
      // mappa.put(brano, integer);
      //  utente2.setHaAscoltato(mappa);
       

        
        assertEquals(cliente1.getID_CL(), cliente2.getID_CL());
        assertEquals(cliente1.getSSN(), cliente2.getSSN());
        assertEquals(cliente1.getNOME(), cliente2.getNOME());
        assertEquals(cliente1.getMAIL(), cliente2.getMAILL());
        assertEquals(cliente1.getPASSWORD(), cliente2.getPASSWORD());
        assertEquals(cliente1.getCOGNOME(), cliente2.getCOGNOME());
        assertEquals(cliente1.getNASCITA(), cliente2.getNASCITA());
        //assertEquals(cliente1.isDELETED(), cliente2.isDELETED());      //va messo?????????????
        
        

        dao.delete(cliente1);
        
    }

    catch(Exception ex)
    {
        System.out.println(ex.getMessage());
    }


        

    
 }
*/

// testo la create verificando che la duplicated controlli che io non possa creare un cliente uguale ad un altro
 @Test 
    public void testcreateEsistente() 
    {
        

    try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
        
       dao.create("prova", "prova","prova","prova","prova", "2012-12-12");
       assertThrows(DuplicatedObjectException.class, () -> {dao.create("prova", "prova","prova","prova","prova","2012-12-12");});
       //verifico che la create mi dia una eccezione duplicated (se ce la dà allora funziona)

        

    }// se il test ha funzionato senza darci problemi salto il catch e vado al finally
    catch(Exception e){  System.out.println(e.getMessage());}  

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
        try{
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


/*
//Test che mi copre se  provoo a cancellare  un utente che non esiste (Non abbiamo gesstito qiesta coosa nel codice quindi non la testimo)
@Test
public void testdeleteNotFoundException()
{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
        Cliente cliente = new Cliente();
        cliente.setMAIL("0");   

        assertThrows(MissingObjectException.class, () -> {dao.delete(cliente);});
       
    }

    catch(Exception e){
        System.out.println(e.getMessage());
    }
}*/


// Test che prova a generare un errore sql eliminando il clientee subito dopo aver chiuso la connessione
@Test 
    public void testdeleteSQLException()
    {
        try{
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


/*
@Test

public void testfindByMAILClienteNotFoundException()
{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
        ClienteDAO dao = new ClienteDAO(con);
        
        
        assertThrows(MissingObjectException.class, () -> {dao.findByMAILCliente("0");}); //forse va messo valore mail non numero
        
    }

    catch(Exception e){
        System.out.println(e.getMessage());
    }

}
*/
@Test 
    public void testfindByMAILClienteSQLException()
    {
        try{
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


/*


 @Test
public void testfindClienteByIDNotFoundException()
{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
        
        
        assertThrows(MissingObjectException.class, () -> {dao.findClienteByID("0");});
        
       
    }

    catch(Exception e){
        System.out.println(e.getMessage());
    }
}



*/


    @Test 
    public void testfindClienteByIDSQLException()
    {
        try{
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
        try{
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
    try{
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
    
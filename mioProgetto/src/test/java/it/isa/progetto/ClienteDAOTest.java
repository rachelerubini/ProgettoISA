/*
package it.isa.progetto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;


import org.junit.Test;


//TESTIAMO CLIENTEDAO.JAVA QUINDI TUTTE LE FUNZIONI CHE ABBIAMO FATTO AL SUO INTERNO


//molto confuse ssu questa funzione, facciamocela spiegare
public class ClienteDAOTest {

   
    //DA MODIFICARE crea utente poi findby id e se utente trovato uguale  al creato alloora  test passa
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


// testo eccezione per vvederee se  cliente esiste già
 @Test 
    public void testcreateEsistente()
    {
        

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
        
       dao.create("prova", "prova","prova","prova","prova","prova");
       assertThrows(DuplicatedObjectException.class, () -> {dao.create("prova", "prova","prova","prova","prova","prova");});
       
       

       

        


        

    }
    catch(Exception e){ System.out.println(e.getMessage());}  

    finally
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao =new ClienteDAO(con);
           
            Cliente cliente1 = new Cliente();
            cliente = dao.findByMAILCliente("prova");
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



@Test
public void testdeleteNotFoundException()
{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
        Cliente cliente = new Cliente();
        cliente.setMAIL("0");     //perchè? lo dobbiamo fare anche noi? va bene con MAIL? (llui usava Username)

        assertThrows(MissingObjectException.class, () -> {dao.delete(cliente);});
       
    }

    catch(Exception e){
        System.out.println(e.getMessage());
    }
}

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

@Test 
    public void testfindByMAILClienteSQLException()
    {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
        ClienteDAO dao = new ClienteDAO(con);
        con.close();
        
       
        
        dao.findByMAILCliente("prova");   //forse va messo valore mail esistente non numero
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }





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






    @Test 
    public void testfindClienteByIDSQLException()
    {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
        ClienteDAO dao = new ClienteDAO(con);
        con.close();
        
       
        
        dao.findClienteByID("prova");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }



@Test
public void testfindClienteByIDNotFoundException()
{
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
            ClienteDAO dao = new ClienteDAO(con);
        
        // forzo errore (non c'è id con vallore 0)
        assertThrows(MissingObjectException.class, () -> {dao.findClienteByID("0");});
        
       
    }

    catch(Exception e){
        System.out.println(e.getMessage());
    }
}






    @Test 
    public void testfindClienteByIDSQLException()
    {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");
        ClienteDAO dao = new ClienteDAO(con);
        con.close();
        
       
        //inserisco un errore per forzare l'eccezione (chiudo connesssione  prima del find)
        dao.findClienteByID("prova");
        
       }
       catch(Exception e)
       {
        System.out.println(e.getMessage());
       }


    }
    
    
    //mollto confuuse su cosaa fa questa funzione: perche quella ssql  e a che serve 'numero'?
    
  @Test
    public void findAllClientiTest()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "GlisCols123");

            Statement st = con.createStatement();

            String sql = "SELECT COUNT(*)"
                        +" FROM Cliente";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int numero = rs.getInt(1);
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
    */
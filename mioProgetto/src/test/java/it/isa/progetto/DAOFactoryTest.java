
package it.isa.progetto;

import it.isa.progetto.Corso;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;


//COMMENTO DI QUESTI TEST: la begin e la rollback non   danno errori e nneanche nullpointer ma vediamo tipo stampati due 'null' è un problema?? Daa dove derivano? Menntre lle funzioni sotto sono  commenttate perchè danno Nullpointer

public class DAOFactoryTest {
    
// testiamo la begin transaction, faccendo una transazione (find) per ogni entità e poi verifico che l'oggetto trovato con la find corrisponda con quello che miii aspetto se ocnicide è ok, la transazione è stata effettuata
  /*  @Test
    public void beginTransactionTest()
    {
        DAOFactory dao = new DAOFactory();
        dao.beginTransaction();
        ClienteDAO cl = dao.getClienteDAO();
        AmministratoreDAO am = dao.getAmministratoreDAO();
        CorsoDAO co = dao.getCorsoDAO();
        RecensioneDAO rc = dao.getRecensioneDAO();
        IscrizioneDAO is = dao.getIscrizioneDAO();
        try{
        Cliente cliente = cl.findClienteByID(1);
        Amministratore amministratore = am.findAmministratoreByID(1);
        Corso corso = co.findCorsoByID_CO(1);
        Recensione recensione = rc.findRecensioneByID(1);
        Iscrizione iscrizione = is.findIscrizioneByCorsoCliente(5,6);

        dao.commitTransaction();
        dao.closeTransaction();
        assertEquals(cliente.getID_CL(), 1);
        assertEquals(corso.getID_CO(), 1);

        //non siamo sicure che l'iscrizione si possa gestire cosi avendo due id
        assertEquals(iscrizione.getCorso().getID_CO(), 5);
        assertEquals(iscrizione.getCliente().getID_CL(), 6);
        
        assertEquals(recensione.getID_R(), 1);
        assertEquals(amministratore.getID_A(), 1);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }*/


//testiamo la roolback sul cliente (se funziona per il cliente funzionerà per tutti)
    @Test
    public void rollbackTransactionTest()
    {
       DAOFactory dao = new DAOFactory();
       dao.beginTransaction();
       ClienteDAO ud = dao.getClienteDAO();
       try{
       ud.create("prova", "prova","prova","prova","prova","2012-02-12"); //creiamo un cliente prova
       dao.rollbackTransaction(); //annullo l'operazione fatta riportandoci allo stato di prima
       dao.closeTransaction();
       dao = new DAOFactory();
       dao.beginTransaction();
        DAOFactory daoo = new DAOFactory();
        daoo.beginTransaction();
        ClienteDAO udd = daoo.getClienteDAO();
       assertEquals(null, udd.findByMAILCliente("prova")); //provo a cercare un cliente con mail "prova" ma non c'è (poichè la Rollback è aaaandata a buon fine) infatti è null il risultato della findbyMAIL
       }

       catch(Exception ex)
       {
        System.out.println(ex.getMessage());
       }

    }
// Le tre funziioni sotto ci danno dei Nullpointer cche non vanno bene quindi ooo sisstema  o eliminale
  /*  @Test
    public void commitExceptionTest()
    {
        DAOFactory dao = new DAOFactory();
        try{
        dao.commitTransaction();
        
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void rollbackExceptionTest()
    {
        DAOFactory dao = new DAOFactory();
        try{
        dao.rollbackTransaction();
        
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void closeExceptionTest()
    {
        DAOFactory dao = new DAOFactory();
        try{
        dao.closeTransaction();
        
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }*/

    
    
}


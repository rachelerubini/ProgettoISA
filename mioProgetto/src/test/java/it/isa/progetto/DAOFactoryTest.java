package it.isa.progetto;

import it.isa.progetto.Corso;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;


//FACCIAMOCI SPIEGARE TUTTI I BLOCCHEETTI COSA FANNO A CCOSA SERVONO !!!!!!!!!!!!


public class DAOFactoryTest {

    @Test
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
        Iscrizione iscrizione = is.findCorsoCliente(2,1);

        dao.commitTransaction();
        dao.closeTransaction();
        assertEquals(cliente.getID_CL(), 1);
        assertEquals(corso.getID_CO(), 1);

        //non siamo sicure che l'iscrizione si possa gestire cosi avendo due id
        assertEquals(iscrizione.getCorso(), 2);
        assertEquals(iscrizione.getCliente(), 1);
        
        assertEquals(recensione.getID_R(), 1);
        assertEquals(amministratore.getID_A(), 1);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


//per chi va fatta? solo utente o anche altri? iscrrizione tipo?
    @Test
    public void rollbackTransactionTest()
    {
       DAOFactory dao = new DAOFactory();
       dao.beginTransaction();
       ClienteDAO ud = dao.getClienteDAO();
       try{
       ud.create("prova", "prova");
       dao.rollbackTransaction();
       dao.closeTransaction();
       dao = new DAOFactory();
       dao.beginTransaction();
        
       assertThrows(MissingObjectException.class, () -> {
        DAOFactory daoo = new DAOFactory();
        daoo.beginTransaction();
        ClienteDAO udd = daoo.getClienteDAO();
        udd.findByUsername("prova");
    });
       }

       catch(Exception ex)
       {
        System.out.println(ex.getMessage());
       }

    }

    @Test
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
        }

    }
    
}
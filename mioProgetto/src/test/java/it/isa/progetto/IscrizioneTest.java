package it.isa.progetto;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;
import it.isa.progetto.Corso;
import it.isa.progetto.Cliente;
import it.isa.progetto.Iscrizione;


import java.lang.reflect.Field;
import java.util.HashMap;

//TESTIAMO ISCRIZIONE.JAVA QUINDI TUTTI I SET E GET DEI SUOI CAMPI

@TestInstance(Lifecycle.PER_CLASS)
public class IscrizioneTest 
{
    
    @Test
    public void testSetDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Iscrizione iscrizione = new Iscrizione();

        //when
        iscrizione.setDELETED("testsetDELETED");

        //then
        final Field field = iscrizione.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(iscrizione), "testsetDELETED");

    }

    @Test
    public void testisDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Iscrizione iscrizione = new Iscrizione();
        final Field field = iscrizione.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        field.set(iscrizione, "testisDELETED");

        //when
        final String result = iscrizione.isDELETED();

        //then
        assertEquals("field wasn't retrieved properly", result, "testisDELETED");
    }
/*
   @Test
    public void testGetCorso() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        IscrizioneDAO dao= df.getIscrizioneDAO();
        
        Iscrizione iscrizione=dao.findIscrizioneByID(2,5);
        df.commitTransaction();
        df.closeTransaction();
        

        //then
        
        assertEquals("Fields didn't match", iscrizione.getCorso().getID_CO(), 5);

    }*/
    @Test
    public void testSetCorso() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        CorsoDAO dao= df.getCorsoDAO();
        
        Corso corso=dao.findCorsoByID_CO(1);
        df.commitTransaction();
        df.closeTransaction();
        
       

        final Iscrizione iscrizione = new Iscrizione();

        //when
        iscrizione.setCorso(corso);

        //then
        
        assertEquals("Fields didn't match", iscrizione.getCorso().getID_CO(), corso.getID_CO());

    }
/*
    @Test
    public void testGetCliente() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        IscrizioneDAO dao= df.getIscrizioneDAO();
        
        Iscrizione iscrizione=dao.findIscrizioneByID(2,5);
        df.commitTransaction();
        df.closeTransaction();
        

        //then
        
        assertEquals("Fields didn't match", iscrizione.getCliente().getID_CL(), 2);

    }*/
    @Test
    public void testSetCliente() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        ClienteDAO dao= df.getClienteDAO();
        
        Cliente cliente=dao.findClienteByID(1);
        df.commitTransaction();
        df.closeTransaction();
        
       

        final Iscrizione iscrizione = new Iscrizione();

        //when
        iscrizione.setCliente(cliente);

        //then
        
        assertEquals("Fields didn't match", iscrizione.getCliente().getID_CL(), cliente.getID_CL());

    
    }
}
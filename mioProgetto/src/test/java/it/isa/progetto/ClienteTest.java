package it.isa.progetto;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashMap;


//TESTIAMO CLIENTE.JAVA QUINDI TUTTI I SET E GET DEI SUOI CAMPI


@TestInstance(Lifecycle.PER_CLASS)
public class ClienteTest {


    @Test
    public void testSetID_CL() throws NoSuchFieldException, IllegalAccessException {
        //given
        final int id=3;
        final Cliente cliente = new Cliente();

        //when
        cliente.setID_CL(id);

        //then
        final Field field = cliente.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), id);

    }

    @Test
    public void testGetID_CL() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final int id=10;
        final Field field = cliente.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(cliente, id);

        //when
        final int result = cliente.getID_CL();

        //then
        assertEquals("field wasn't retrieved properly", result, id);
    }

//proossimi test per tutti i campi dii cliente?


    @Test
    public void testSetSSN() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setCliente("testsetSSN");

        //then
        final Field field = cliente.getClass().getDeclaredField("SSN");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetSSN");

    }

    @Test
    public void testGetSSN() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("SSN");
        field.setAccessible(true);
        field.set(cliente, "testgetSSN");

        //when
        final String result = cliente.getSSN();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetSNN");
    }

    @Test
    public void testSetNOME() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setNOME("testsetNOME");

        //then
        final Field field = cliente.getClass().getDeclaredField("NOME");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetNOME");

    }

    @Test
    public void testGetNOME() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("NOME");
        field.setAccessible(true);
        field.set(cliente, "testgetNOME");

        //when
        final String result = cliente.getNOME();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetNOME");
    }

    @Test
    public void testSetPASSWORD() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setPASSWORD("testsetPASSWORD");

        //then
        final Field field = cliente.getClass().getDeclaredField("PASSWORD");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetPASSWORD");

    }

    @Test
    public void testGetPASSWORD() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("PASSWORD");
        field.setAccessible(true);
        field.set(cliente, "testgetPASSWORD");

        //when
        final String result = cliente.getPASSWORD();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetPASSWORD");
    }   
    
    
     @Test
    public void testSetCOGNOME() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setCOGNOME("testsetCOGNOME");

        //then
        final Field field = cliente.getClass().getDeclaredField("COGNOME");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetCOGNOME");

    }

    @Test
    public void testGetCOGNOME() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("COGNOME");
        field.setAccessible(true);
        field.set(cliente, "testgetCOGNOME");

        //when
        final String result = cliente.getCOGNOME();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetCOGNOME");
    }

        @Test
    public void testSetMAIL() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setMAIL("testsetMAIL");

        //then
        final Field field = cliente.getClass().getDeclaredField("MAIL");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetMAIL");

    }

    @Test
    public void testGetMAIL() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("MAIL");
        field.setAccessible(true);
        field.set(cliente, "testgetMAIL");

        //when
        final String result = cliente.getMAIL();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetMAIL");
    }



        @Test
    public void testSetNASCITA() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setNASCITA("testsetNASCITA");

        //then
        final Field field = cliente.getClass().getDeclaredField("NASCITA");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetNASCITA");

    }

    @Test
    public void testGetNASCITA() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("NASCITA");
        field.setAccessible(true);
        field.set(cliente, "testgetNASCITA");

        //when
        final String result = cliente.getNASCITA();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetNASCITA");
    }

        @Test
    public void testSetDELETED() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();

        //when
        cliente.setDELETED("testsetDELETED");

        //then
        final Field field = cliente.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(cliente), "testsetDELETED");

    }

    @Test
    public void testisDELETED() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Cliente cliente = new Cliente();
        final Field field = cliente.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        field.set(cliente, "testisDELETED");

        //when
        final String result = cliente.isDELETED();

        //then
        assertEquals("field wasn't retrieved properly", result, "testisDELETED");
    }



//COME VA FATTO GET E SET RECENSIONI?





//NOI NON  ABBIAAAAAMO GLI HASHMAPP QUINDI IL PROSSIMO BLOCCO È COMMENTATO E ABBIAAMO FATTO I TEST PER TUTTI I CAMPI COME QUELLI SOPRA

//   DOVEVAMMMMO STARE ATTENTE A QUAAAALCOSA? COME I CAMPI ESTERNI? SE SI COME VA FATTO?

/*
    @Test
    public void testSetHaAscoltato() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Utente utente = new Utente();
        Brano brano = new Brano();
        Integer integer = new Integer(6);
        HashMap<Brano, Integer> mappa = new HashMap<Brano, Integer>();
        HashMap<Brano, Integer> mappa2 = new HashMap<Brano, Integer>();
        mappa.put(brano, integer);
        mappa2.put(brano, integer);




        //when
        utente.setHaAscoltato(mappa);

        //then
        final Field field = utente.getClass().getDeclaredField("haAscoltato");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(utente), mappa2);

    }

    @Test
    public void testGetHaAscoltato() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Utente utente = new Utente();
        Brano brano = new Brano();
        Integer integer = new Integer(6);
        HashMap<Brano, Integer> mappa = new HashMap<Brano, Integer>();
        HashMap<Brano, Integer> mappa2 = new HashMap<Brano, Integer>();
        mappa.put(brano, integer);
        mappa2.put(brano, integer);
        final Field field = utente.getClass().getDeclaredField("haAscoltato");
        field.setAccessible(true);
        field.set(utente, mappa);

        //when
        final HashMap<Brano, Integer> result = utente.getHaAscoltato();

        //then
        assertEquals("field wasn't retrieved properly", result, mappa2);
    }

    @Test
    public void testSetHaAscoltato2() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Utente utente = new Utente();
        Brano brano = new Brano();
        Integer ascolti = new Integer(6);
        
        HashMap<Brano, Integer> mappa2 = new HashMap<Brano, Integer>();
        mappa2.put(brano, ascolti);




        //when
        utente.setHaAscoltato(brano, ascolti);

        //then
        final Field field = utente.getClass().getDeclaredField("haAscoltato");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(utente), mappa2);

    }

    @Test
    public void testGetHaAscoltato2() throws NoSuchFieldException, IllegalAccessException {
        //given
        final Utente utente = new Utente();
        Brano brano = new Brano();
        Integer integer = new Integer(6);
        HashMap<Brano, Integer> mappa = new HashMap<Brano, Integer>();
        
        mappa.put(brano, integer);
        
        final Field field = utente.getClass().getDeclaredField("haAscoltato");
        field.setAccessible(true);
        field.set(utente, mappa);

        //when
        final Integer result = utente.getHaAscoltato(brano);

        //then
        assertEquals("field wasn't retrieved properly", result, integer);
    }
   */ 
    
}
package it.isa.progetto;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashMap;

//TESTIAMO CORSO.JAVA QUINDI TUTTI I SET E GET DEI SUOI CAMPI

@TestInstance(Lifecycle.PER_CLASS)
public class CorsoTest 
{
    @Test
    public void testSetID_CO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final int id=3;
        final Corso corso = new Corso();

        //when
        corso.setID_CO(id);

        //then
        final Field field = corso.getClass().getDeclaredField("ID_CO");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(corso), id);

    }

    @Test
    public void testGetID_CO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();
        final int id=10;
        final Field field = corso.getClass().getDeclaredField("ID_CO");
        field.setAccessible(true);
        field.set(corso, id);

        //when
        final int result = corso.getID_CO();

        //then
        assertEquals("field wasn't retrieved properly", result, id);
    }


    @Test
    public void testSetNOME() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();

        //when
        corso.setNOME("testsetNOME");

        //then
        final Field field = corso.getClass().getDeclaredField("NOME");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(corso), "testsetNOME");

    }

    @Test
    public void testGetNOME() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();
        final Field field = corso.getClass().getDeclaredField("NOME");
        field.setAccessible(true);
        field.set(corso, "testgetNOME");

        //when
        final String result = corso.getNOME();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetNOME");
    }



    @Test
    public void testSetTIPO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();

        //when
        corso.setTIPO("testsetTIPO");

        //then
        final Field field = corso.getClass().getDeclaredField("TIPO");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(corso), "testsetTIPO");

    }

    @Test
    public void testGetTIPO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();
        final Field field = corso.getClass().getDeclaredField("TIPO");
        field.setAccessible(true);
        field.set(corso, "testgetTIPO");

        //when
        final String result = corso.getTIPO();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetTIPO");
    }


    @Test
    public void testSetLIVELLO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();

        //when
        corso.setLIVELLO("testsetLIVELLO");

        //then
        final Field field = corso.getClass().getDeclaredField("LIVELLO");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(corso), "testsetLIVELLO");

    }

    @Test
    public void testGetLIVELLO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();
        final Field field = corso.getClass().getDeclaredField("LIVELLO");
        field.setAccessible(true);
        field.set(corso, "testgetLIVELLO");

        //when
        final String result = corso.getLIVELLO();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetLIVELLO");
    }


    @Test
    public void testSetDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();

        //when
        corso.setDELETED("testsetDELETED");

        //then
        final Field field = corso.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(corso), "testsetDELETED");

    }

    @Test
    public void testisDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Corso corso = new Corso();
        final Field field = corso.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        field.set(corso, "testisDELETED");

        //when
        final String result = corso.isDELETED();

        //then
        assertEquals("field wasn't retrieved properly", result, "testisDELETED");
    }



    //Non testiamo get e set di recensione perchè non li abbiamo mai utilizzati
}




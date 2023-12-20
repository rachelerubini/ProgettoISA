package it.isa.progetto;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;


//TESTIAMO AMMINISTRATORE.JAVA QUINDI TUTTI I SET E GET DEI SUOI CAMPI

@TestInstance(Lifecycle.PER_CLASS)
public class AmministratoreTest 
{

    @Test
    public void testSetID_A() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final int id=3;
        final Amministratore amministratore = new Amministratore();

        //when
        amministratore.setID_A(id);

        //then
        final Field field = amministratore.getClass().getDeclaredField("ID_A");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(amministratore), id);

    }

    @Test
    public void testGetID_A() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Amministratore amministratore = new Amministratore();
        final int id=10;
        final Field field = amministratore.getClass().getDeclaredField("ID_A");
        field.setAccessible(true);
        field.set(amministratore, id);

        //when
        final int result = amministratore.getID_A();

        //then
        assertEquals("field wasn't retrieved properly", result, id);
    }


    @Test
    public void testSetPASSWORD() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Amministratore amministratore = new Amministratore();

        //when
        amministratore.setPASSWORD("testsetPASSWORD");

        //then
        final Field field = amministratore.getClass().getDeclaredField("PASSWORD");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(amministratore), "testsetPASSWORD");

    }



    @Test
    public void testGetPASSWORD() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Amministratore amministratore = new Amministratore();
        final Field field = amministratore.getClass().getDeclaredField("PASSWORD");
        field.setAccessible(true);
        field.set(amministratore, "testgetPASSWORD");

        //when
        final String result = amministratore.getPASSWORD();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetPASSWORD");
    }   
    

    @Test
    public void testSetMAIL() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Amministratore amministratore = new Amministratore();

        //when
        amministratore.setMAIL("testsetMAIL");

        //then
        final Field field = amministratore.getClass().getDeclaredField("MAIL");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(amministratore), "testsetMAIL");

    }

    

    @Test
    public void testGetMAIL() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Amministratore amministratore = new Amministratore();
        final Field field = amministratore.getClass().getDeclaredField("MAIL");
        field.setAccessible(true);
        field.set(amministratore, "testgetMAIL");

        //when
        final String result = amministratore.getMAIL();

        //then
        assertEquals("field wasn't retrieved properly", result, "testgetMAIL");
    }

}
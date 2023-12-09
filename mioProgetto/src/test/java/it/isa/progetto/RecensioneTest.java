/*package it.isa.progetto;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashMap;

//TESTIAMO RECENSIONE.JAVA QUINDI TUTTI I SET E GET DEI SUOI CAMPI

@TestInstance(Lifecycle.PER_CLASS)
public class RecensioneTest 
{
    @Test
    public void testSetID_R() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final int id=3;
        final Recensione recensione = new Recensione();

        //when
        recensione.setID_R(id);

        //then
        final Field field = recensione.getClass().getDeclaredField("ID_R");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(recensione), id);

    }

    @Test
    public void testGetID_R() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Recensione recensione = new Recensione();
        final int id=10;
        final Field field = recensione.getClass().getDeclaredField("ID_R");
        field.setAccessible(true);
        field.set(recensione, id);

        //when
        final int result = recensione.getID_R();

        //then
        assertEquals("field wasn't retrieved properly", result, id);
    }


    @Test
    public void testSetVOTO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final int voto=3;
        final Recensione recensione = new Recensione();

        //when
        recensione.setVOTO(voto);

        //then
        final Field field = recensione.getClass().getDeclaredField("VOTO");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(recensione), voto);

    }

    @Test
    public void testGetVOTO() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Recensione recensione = new Recensione();
        final int voto=10;
        final Field field = recensione.getClass().getDeclaredField("VOTO");
        field.setAccessible(true);
        field.set(recensione, voto);

        //when
        final int result = recensione.getVOTO();

        //then
        assertEquals("field wasn't retrieved properly", result, voto);
    }



    @Test
    public void testSetDATA() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        //NON SONO SICURA CHE VADA BENE!!!!!!
        final String data="2021-03-20";
        final Recensione recensione = new Recensione();

        //when
        recensione.setDATA(data);

        //then
        final Field field = recensione.getClass().getDeclaredField("DATA");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(recensione), data);

    }

    @Test
    public void testGetDATA() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Recensione recensione = new Recensione();
        //NON SONO SICURA CHE VADA BENE!!!!!!
        final String data="2021-03-20";
        final Field field = recensione.getClass().getDeclaredField("DATA");
        field.setAccessible(true);
        field.set(recensione, data);

        //when
        final int result = recensione.getID_R();

        //then
        assertEquals("field wasn't retrieved properly", result, data);
    }

    @Test
    public void testSetDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Recensione recensione = new Recensione();

        //when
        recensione.setDELETED("testsetDELETED");

        //then
        final Field field = recensione.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(recensione), "testsetDELETED");

    }

    @Test
    public void testisDELETED() throws NoSuchFieldException, IllegalAccessException 
    {
        //given
        final Recensione recensione = new Recensione();
        final Field field = recensione.getClass().getDeclaredField("DELETED");
        field.setAccessible(true);
        field.set(recensione, "testisDELETED");

        //when
        final String result = recensione.isDELETED();

        //then
        assertEquals("field wasn't retrieved properly", result, "testisDELETED");
    }


    //Non testiamo get e set di corso e cliente perchè non li abbiamo mai utilizzati
}

*/

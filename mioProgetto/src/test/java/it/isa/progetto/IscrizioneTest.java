package it.isa.progetto;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.Assert.assertEquals;

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

//Non testiamo get e set di corso e cliente perchè non li abbiamo mai utilizzati

}




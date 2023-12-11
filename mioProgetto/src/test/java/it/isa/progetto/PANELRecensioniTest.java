package it.isa.progetto;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

public class PANELRecensioniTest 
{

    //definiamo findAllCorsi perchè ci serve per testare il makeButtonText
     public List<Recensione> findAllRecensioni() 
    {
        DAOFactory df= new DAOFactory();
        List<Recensione> recensioni = new ArrayList<Recensione>();
        df.beginTransaction();
        RecensioneDAO dao= df.getRecensioneDAO();
        recensioni  = dao.findAllRecensioni();
        df.commitTransaction();
        df.closeTransaction();
        return recensioni;
    }
    

    //Testiamo il makeButtonText: creiamo un corso di prova e verifichiamo che l'etichetta assegnata al corso con il makebutton sia corretta
    @Test 
    public void makeButtonTextTest()
    {
        Cliente cliente= new Cliente();
        PANELRecensioni pr = new PANELRecensioni(cliente, findAllRecensioni());
        
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        RecensioneDAO dao= df.getRecensioneDAO();
        //QUA VA MESSO COME PARAMETRO UNA DATA DI UNA RECENSIONE
        Recensione recensione  = dao.findRecensioneByDATA();
        df.commitTransaction();
        df.closeTransaction();

        String etichetta1= pr.makeButtonText(recensione);
        //QUA COME SECONDO PARAMETRO CONVIENE SCRIVERE NOI LA STRINGA CHE DEVE USCIRE (GUARDA IL DB)
        assertEquals(etichetta1,"CORSO: "+nome+"\t VOTO: "+recensione.getVOTO()+"\t CLIENTE: "+nomec +"\t DATA: "+recensione.getDATA());
    } 
    
}


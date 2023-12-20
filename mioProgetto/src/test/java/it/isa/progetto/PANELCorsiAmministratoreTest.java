package it.isa.progetto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;


public class PANELCorsiAmministratoreTest 
{

    //definiamo findAllCorsi perchè ci serve per testare il makeButtonText
     public List<Corso> findAllCorsi() 
    {
        DAOFactory df= new DAOFactory();
        List<Corso> corsi = new ArrayList<Corso>();
        df.beginTransaction();
        CorsoDAO dao= df.getCorsoDAO();
        corsi  = dao.findAllCorsi();
        df.commitTransaction();
        df.closeTransaction();
        return corsi;
    }

    //Testiamo il makeButtonText: creiamo un corso di prova e verifichiamo che l'etichetta assegnata al corso con il makebutton sia corretta
     @Test 
    public void makeButtonTextTest()
    {
        PANELCorsiAmministratore pca = new PANELCorsiAmministratore(findAllCorsi());
        Corso corso = new Corso();
        corso.setID_CO(100);
        corso.setNOME("prova");
        corso.setTIPO("prova");
        corso.setLIVELLO("prova");
        String etichetta1= pca.makeButtonText(corso);
        assertEquals(etichetta1,"NOME: "+corso.getNOME()+"\t TIPO: "+corso.getTIPO()+"\t LIVELLO: "+corso.getLIVELLO());
    }   
}

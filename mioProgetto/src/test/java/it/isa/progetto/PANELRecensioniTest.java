package it.isa.progetto;

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
    

    //Testiamo il makeButtonText: creiamo una recensione di prova e verifichiamo che l'etichetta assegnata alla recensiione con il makebutton sia corretta
    @Test 
    public void makeButtonTextTest()
    {
        Cliente cliente= new Cliente();
        PANELRecensioni pr = new PANELRecensioni(cliente, findAllRecensioni());
        
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        RecensioneDAO dao= df.getRecensioneDAO();
        CorsoDAO daocr= df.getCorsoDAO();
        ClienteDAO daocl=df.getClienteDAO();
        Recensione recensione  = dao.findRecensioneByDATA("2021-07-26");
        String nomeco=  daocr.findCorsoByID_CO(recensione.getCorso().getID_CO()).getNOME();
        String nomec= daocl.findClienteByID(recensione.getCliente().getID_CL()).getNOME();
        df.commitTransaction();
        df.closeTransaction();

        String etichetta1= pr.makeButtonText(recensione);
        assertEquals(etichetta1,"CORSO: "+nomeco+"\t VOTO: "+recensione.getVOTO()+"\t CLIENTE: "+nomec +"\t DATA: "+recensione.getDATA());
    }
    
}

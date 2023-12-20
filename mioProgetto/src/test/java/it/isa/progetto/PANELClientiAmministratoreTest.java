
package it.isa.progetto;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;


public class PANELClientiAmministratoreTest
{
    //definiamo findAllClienti perchè ci serve per testare il makeButtonText
     public List<Cliente> findAllClienti() 
    {
        DAOFactory df= new DAOFactory();
        List<Cliente> clienti = new ArrayList<Cliente>();
        df.beginTransaction();
        ClienteDAO dao= df.getClienteDAO();
        clienti  = dao.findAllClienti();
        df.commitTransaction();
        df.closeTransaction();
        return clienti;
    }
    //Testiamo il makeButtonText: creiamo un cliente di prova e verifichiamo che l'etichetta assegnata al cliente con il makebutton sia corretta
     @Test 
    public void makeButtonTextTest()
    {
        
        PANELClientiAmministratore pca = new PANELClientiAmministratore(findAllClienti());
        Cliente cliente = new Cliente();
        cliente.setID_CL(100);
        cliente.setNOME("prova");
        cliente.setSSN("prova");
        cliente.setCOGNOME("prova");
        String etichetta1= pca.makeButtonText(cliente);
        assertEquals(etichetta1,"SSN: "+cliente.getSSN()+"\t Nome: "+cliente.getNOME()+"\t Cognome: "+cliente.getCOGNOME());
    }   


}
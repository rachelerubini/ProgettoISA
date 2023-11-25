package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IscrizioneDAO 
{
    private static Connection conn;

    public IscrizioneDAO(Connection conn) 
    {
        IscrizioneDAO.conn = conn;
    }

      
    //per disiscriversi a un corso (N.B essendo N:M passo per Iscrizione)
    public void disiscrivi(int ID_corso, int ID_cliente)
    {
        
        PreparedStatement ps;
    
        try 
        {
    
          String sql
                  = " UPDATE iscrizione "
                  + " SET DELETED='Y' "
                  + " WHERE "
                  + " ID_CO=?"
                  + " AND "
                  + " ID_CL=?";
    
                  

          ps = conn.prepareStatement(sql);
          ps.setInt(1, ID_corso);  
          ps.setInt(2, ID_cliente); 
          ps.executeUpdate();
          ps.close();
    
        } 
        catch (SQLException e) 
        {  
          throw new RuntimeException(e);
        }
        

    }



    //funzione che mi dice se quel cliente è iscritto a quel corso (se è iscritto 1, altrimenti 0)
    public int findCorsoCliente( int ID_corso, int ID_cliente)
    {
        PreparedStatement ps;
         
        Iscrizione iscrizione = null;

        try 
        {

            String sql
                    = " SELECT * "
                    + " FROM iscrizione "
                    + " WHERE "
                    + " ID_CO=?"
                    + " AND "
                    + " ID_CL=?"
                    + " AND "
                    + " DELETED='N' " ;

            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, ID_corso); 
            ps.setInt(2, ID_cliente); 
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) 
            {
                iscrizione = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }
    
        if(iscrizione==null){return 0;} else{return 1;}

    }


      
    //funzione che mi crea una nuova iscrizione
    public Iscrizione createIscrizione(
        //boolean DELETED,
        Corso corso,
        Cliente cliente) //throws DuplicatedObjectException 
    {
  
        PreparedStatement ps;
        Iscrizione iscrizione = new Iscrizione();
        iscrizione.setCorso(corso);
        iscrizione.setCliente(cliente);

        try 
        { //qua modifichiamo 
            //probabilmente il blocco commentato sotto si puuo togliere perche si controllava di non iscriversi ad un corso a cuiiii si era già iscrittta ma ora non abbiamoo neanchee piu il bottone per farlo:
            /*
            String sql
                    = " SELECT ID_CO, ID_CL "
                    + " FROM iscrizione "
                    + " WHERE "
                    + " ID_CO=?"
                    + " AND "
                    + " ID_CL=?"
                    + " AND "
                    + " DELETED = 'N' ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, iscrizione.getCorso().getID_CO());
            ps.setInt(i++, iscrizione.getCliente().getID_CL());


            ResultSet resultSet = ps.executeQuery();
            
            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if (exist)
            {
                throw new DuplicatedObjectException("Tentativo di iscrizione ad un corso a cui sei già iscritto");
            }
            */


            String sql
                    = " INSERT INTO iscrizione "
                    + "   ( DELETED,"
                    + "     ID_CO,"
                    + "     ID_CL" 
                    + ")"
                    + " VALUES ('N',?,?)";

            ps = conn.prepareStatement(sql);
            int j = 1;
            ps.setInt(j++, iscrizione.getCorso().getID_CO());
            ps.setInt(j++, iscrizione.getCliente().getID_CL());


            ps.executeUpdate();
            
        } 
        catch (SQLException e) 
        {  
            throw new RuntimeException(e);
        }
            
        return iscrizione;

    }



    Iscrizione read(ResultSet rs) 
    {
        Iscrizione iscrizione = new Iscrizione();
        Corso corso = new Corso();
        Cliente cliente = new Cliente();

        iscrizione.setCorso(corso);
        iscrizione.setCliente(cliente);

        try 
        {
            iscrizione.setDELETED(rs.getString("DELETED"));
        } 
        catch (SQLException sqle) {}
        return iscrizione;
    }

    
}

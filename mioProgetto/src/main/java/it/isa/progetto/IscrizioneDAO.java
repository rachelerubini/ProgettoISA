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
        { 
        
            
            String sql
                    = " SELECT ID_CO, ID_CL "
                    + " FROM iscrizione "
                    + " WHERE "
                    + " ID_CO=?"
                    + " AND "
                    + " ID_CL=?"
                    + " AND "
                    + " DELETED = 'Y' ";

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
                //se esiste già dobbiamo modificare il camppo delited da N a Y così torna l'iscrizione per quel cliente a quel corso
                try 
                {
            
                    sql
                        = " UPDATE iscrizione "
                        + " SET DELETED='N' "
                        + " WHERE "
                        + " ID_CO=?"
                        + " AND "
                        + " ID_CL=?";
            
                        

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
            }
            
            else
            {
                try
                {    

                    sql
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

            }    
        }
        catch (SQLException e) 
        {  
            throw new RuntimeException(e);
        }
        return iscrizione;

    }

    //funzione che mi conta gli iscritti ad un corso
    public int countiscritti(int ID_CO){
        PreparedStatement ps;
        int iscritti = 0;
        ArrayList<Iscrizione> TOT = new ArrayList<Iscrizione>();
        Iscrizione EL;
        try 
        {

            String sql
                    = " SELECT *"
                    + "   FROM iscrizione "
                    + " WHERE "
                    + "   ID_CO = ?"
                    + " AND "
                    + " DELETED='N'";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_CO);

            ResultSet resultSet = ps.executeQuery();

            
            while (resultSet.next()) 
            {
                EL = read(resultSet);
                TOT.add(EL);
                iscritti=iscritti+1;
            }

            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }
        
        return iscritti;
    }

    //mi conta le righe che hanno un certo id cliente associato ad un certo id corso
    public int countrighe(int ID_CL, int ID_CO){
        PreparedStatement ps;
        int iscritti = 0;
        ArrayList<Iscrizione> TOT = new ArrayList<Iscrizione>();
        Iscrizione EL;
        try 
        {

            String sql
                    = " SELECT *"
                    + "   FROM iscrizione "
                    + " WHERE "
                    + "   ID_CL = ?"
                    + " AND "
                    + "   ID_CO = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_CL);
            ps.setInt(2, ID_CO);

            ResultSet resultSet = ps.executeQuery();

            
            while (resultSet.next()) 
            {
                EL = read(resultSet);
                TOT.add(EL);
                iscritti=iscritti+1;
            }

            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }
        
        return iscritti;
    }


    

//forse non la usiamo

    public Iscrizione findIscrizioneByID(int ID_CL, int ID_CO) 
    {
        
        PreparedStatement ps;
        Iscrizione iscrizione = null;

        try 
        {

            String sql
                    = " SELECT * "
                    + "   FROM iscrizione "
                    + " WHERE "
                    + "   ID_CL = ?"
                    + " AND "
                    + "   ID_CO = ?"
                    + " AND "
                    + " DELETED = 'N' ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_CL);
            ps.setInt(2, ID_CO);

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
            iscrizione.getCorso().setID_CO(rs.getInt("ID_CO"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            iscrizione.getCliente().setID_CL(rs.getInt("ID_CL"));
        } 
        catch (SQLException sqle) {}
        try 
        {
            iscrizione.setDELETED(rs.getString("DELETED"));
        } 
        catch (SQLException sqle) {}
        return iscrizione;
    }

    
}

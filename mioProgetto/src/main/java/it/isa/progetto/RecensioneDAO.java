package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO
{
    Connection conn;
 
    public RecensioneDAO(Connection conn, Cliente cliente) 
    {
        this.conn = conn;
    }

    //creo una nuova recensione
    public Recensione create(
            //int ID_R,
            int VOTO,
            String DATA,
            //boolean DELETED,
            Corso corso,
            Cliente cliente) throws DuplicatedObjectException 
    {
                
        PreparedStatement ps;
        Recensione recensione = new Recensione();
        recensione.setVOTO(VOTO);
        recensione.setDATA(DATA);
        recensione.setCorso(corso);
        recensione.setCliente(cliente);

        try 
        {

            String sql
                    = " SELECT ID_R "
                    + " FROM recensione "
                    + " WHERE "
                    + " DATA = ? AND"
                    + " ID_COR = ? AND"
                    + " ID_CLR = ? AND"
                    + " DELETED ='N'";


            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, recensione.getDATA());
            ps.setInt(i++, recensione.getCorso().getID_CO());
            ps.setInt(i++, recensione.getCliente().getID_CL());

            ResultSet resultSet = ps.executeQuery();

            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if (exist) 
            {
                throw new DuplicatedObjectException("RecensioneDAO.create: Tentativo di inserimento di un recensione già esistente.");
            }


            sql
                    = " INSERT INTO recensione "
                    + "   ( ID_R,"
                    + "     VOTO,"
                    + "     DATA,"
                    + "     DELETED,"
                    + "     ID_COR,"
                    + "     ID_CLR "
                    + "   ) "
                    + " VALUES (NULL,?,?,'N',?,?)";

            ps = conn.prepareStatement(sql);
            int j = 1;
            ps.setInt(j++, recensione.getVOTO());
            ps.setString(j++, recensione.getDATA());
            //j++;
            ps.setInt(j++, recensione.getCorso().getID_CO());
            ps.setInt(j++, recensione.getCliente().getID_CL());

            ps.executeUpdate();

        } 
        catch (SQLException e) 
        {   
            throw new RuntimeException(e);
        }
        
        return recensione;

    }

    //funzione che restituisce una recensione a partire dal suo ID
    public Recensione findRecensioneByID(int ID_R) 
    {
        
        PreparedStatement ps;
        Recensione recensione = null;

        try 
        {

            String sql
                    = " SELECT * "
                    + "   FROM recensione "
                    + " WHERE "
                    + "   ID_R = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_R);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) 
            {
                recensione = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {   
            throw new RuntimeException(e);
        }
        
        return recensione;

    }


    //funzione che restituisce una recensione a partire dal suo ID
    public Recensione findRecensioneByDATA(String DATA) 
    {
        
        PreparedStatement ps;
        Recensione recensione = null;

        try 
        {

            String sql
                    = " SELECT * "
                    + "   FROM recensione "
                    + " WHERE "
                    + "   DATA = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, DATA);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) 
            {
                recensione = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {   
            throw new RuntimeException(e);
        }
        
        return recensione;

    }


    //elimino una recensione
    public void delete(Recensione recensione) 
    {
         
        PreparedStatement ps;

        try 
        {

            String sql
                    = " UPDATE recensione "
                    + " SET DELETED='Y' "
                    + " WHERE "
                    + " ID_R=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, recensione.getID_R());
            ps.executeUpdate();
            ps.close();

        } 
        catch (SQLException e) 
        {    
            throw new RuntimeException(e);
        }
        
    }


    //funzione che restituisce tutte le mie recensioni
    public List<Recensione> findAllRecensioni() 
    {
        PreparedStatement ps;
        Recensione oggetto;
        ArrayList<Recensione> lista = new ArrayList<Recensione>();

        try 
        {
            String sql
                    = " SELECT *"
                    + " FROM recensione"
                    + " WHERE DELETED='N'"
                    + " ORDER BY recensione.DATA DESC";

            ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) 
            {
                oggetto = read(resultSet);
                lista.add(oggetto);
            }

            resultSet.close();
            ps.close();

        } 
        catch (SQLException e) 
        {    
            throw new RuntimeException(e);
        }
    
        return lista;
    }




    Recensione read(ResultSet rs) 
    {
        Recensione recensione = new Recensione();
        Corso corso = new Corso();
        Cliente cliente = new Cliente();

        recensione.setCorso(corso);
        recensione.setCliente(cliente);

        try 
        {
            recensione.getCorso().setID_CO(rs.getInt("ID_COR"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            recensione.getCliente().setID_CL(rs.getInt("ID_CLR"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            recensione.setID_R(rs.getInt("ID_R"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            recensione.setVOTO(rs.getInt("VOTO"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            recensione.setDATA(rs.getString("DATA"));
        } 
        catch (SQLException sqle) {}

        try 
        {
            recensione.setDELETED(rs.getString("DELETED"));
        } 
        catch (SQLException sqle) {}
        
        return recensione;
    }
}

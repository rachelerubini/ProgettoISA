package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmministratoreDAO 
{
    
  private static Connection conn;

  public AmministratoreDAO(Connection conn) 
  {
    this.conn = conn;
  }

  public static Amministratore findByMAILAmministratore(String MAIL) 
  {

    PreparedStatement ps;
    Amministratore amministratore = null;
    
    try 
    {

      String sql
                = " SELECT * "
                + "   FROM amministratore "
                + " WHERE MAIL = ?";

      ps = conn.prepareStatement(sql);
      ps.setString(1, MAIL);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) 
      {
        amministratore = read(resultSet);
      }
        
      resultSet.close();
      ps.close();

    } 
    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }

    
    return amministratore;

  }


  //funzione che restituisce l'amminiistratore a partire dal suo ID
  public Amministratore findAmministratoreByID(int ID_A) 
  {
        
    PreparedStatement ps;
    Amministratore amministratore = null;
    
    try 
    {
      String sql
                = " SELECT * "
                + "   FROM amministratore "
                + " WHERE "
                + "   ID_A = ?";

      ps = conn.prepareStatement(sql);
      ps.setInt(1, ID_A);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) 
      {
        amministratore = read(resultSet);
      }
        
      resultSet.close();
      ps.close();

    } 
    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }
  
    return amministratore;
  }


  static Amministratore read(ResultSet rs) 
  {
    Amministratore amministratore = new Amministratore();

    try 
    {
      amministratore.setID_A(rs.getInt("ID_A"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      amministratore.setMAIL(rs.getString("MAIL"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      amministratore.setPASSWORD(rs.getString("PASSWORD"));
    } 
    catch (SQLException sqle) {}
    
    return amministratore;
  }

}

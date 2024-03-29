package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO 
{

  private static Connection conn;
   

  public ClienteDAO(Connection conn) 
  {
    this.conn = conn;
  }



  //creazione di un nuovo cliente
  public Cliente create(
          //int ID_CL,
          String SSN,
          String NOME,
          String COGNOME,
          String MAIL,
          String PASSWORD,
          String NASCITA
          //boolean DELETED,
          ) throws DuplicatedObjectException
  {
  
    
    PreparedStatement ps;
    Cliente cliente = new Cliente();
    cliente.setSSN(SSN);
    cliente.setNOME(NOME);
    cliente.setCOGNOME(COGNOME);
    cliente.setMAIL(MAIL);
    cliente.setPASSWORD(PASSWORD);
    cliente.setNASCITA(NASCITA);


    try 
    {

      String sql
              = " SELECT ID_CL "
              + " FROM cliente "
              + " WHERE "
              + " ( SSN = ? OR"
              + " MAIL = ? ) AND"
              + " DELETED ='N' ";


      ps = conn.prepareStatement(sql);
      int j = 1;
      ps.setString(j++, cliente.getSSN());
      ps.setString(j++, cliente.getMAIL());

      ResultSet resultSet = ps.executeQuery();

      boolean exist;
      exist = resultSet.next();
      resultSet.close();

      if (exist) 
      {
        throw new DuplicatedObjectException("ClienteDAO.create: Tentativo di inserimento di un cliente già esistente.");
      }
    
      sql
              = " INSERT INTO cliente "
              + "   ( ID_CL,"
              + "     SSN,"
              + "     NOME,"
              + "     COGNOME,"
              + "     MAIL,"
              + "     PASSWORD,"
              + "     NASCITA,"
              + "     DELETED )"
              + " VALUES (NULL,?,?,?,?,?,?,'N')";

      ps = conn.prepareStatement(sql);
      int i = 1;
      ps.setString(i++, cliente.getSSN());
      ps.setString(i++, cliente.getNOME());
      ps.setString(i++, cliente.getCOGNOME());
      ps.setString(i++, cliente.getMAIL());
      ps.setString(i++, cliente.getPASSWORD());
      ps.setString(i++, cliente.getNASCITA());
      ps.executeUpdate();

    } 
    catch (SQLException e) 
    {  
      throw new RuntimeException(e);
    }
    
    return cliente;

  }




  //elimino un cliente
  public void delete (Cliente cliente) 
  {

    PreparedStatement ps;
     
    try 
    {

      String sql
              = " UPDATE cliente "
              + " SET DELETED='Y' "
              + " WHERE "
              + " ID_CL=?";

      ps = conn.prepareStatement(sql);
      ps.setInt(1, cliente.getID_CL());
      ps.executeUpdate();
      ps.close();

    } 
    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }
  
  }




  //Trova il cliente (tutti i campi) a partire dalla mail, lo usiamo nel login
  public static Cliente findByMAILCliente(String MAIL) 
  {

    PreparedStatement ps;
    Cliente cliente = null;
  
    try 
    {
      String sql
              = " SELECT * "
              + "   FROM cliente "
              + " WHERE "
              + "   MAIL = ?"
              + "   AND DELETED = 'N' ";

      ps = conn.prepareStatement(sql);
      ps.setString(1, MAIL);

      ResultSet resultSet = ps.executeQuery();
      
      if(resultSet.next()) 
      {
        cliente = read(resultSet);
       
      }

       resultSet.close();
       ps.close();
    
    } 
    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }

    return cliente;
    
  }



  //funzione che restituisce il cliente a partire dal suo ID
  public Cliente findClienteByID(int ID_CL) 
  {
        
    PreparedStatement ps;
    Cliente cliente = null;
    
    try 
    {
      String sql
                = " SELECT * "
                + "   FROM cliente "
                + " WHERE "
                + "   ID_CL = ?";
               // + "   AND DELETED = 'N' ";

      ps = conn.prepareStatement(sql);
      ps.setInt(1, ID_CL);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) 
      {
        cliente = read(resultSet);
      }
        
      resultSet.close();
      ps.close();

    } 
    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }
  
    return cliente;
  }



  //funzione che mi restituisce tutti i clienti del db
  public List<Cliente> findAllClienti() 
  {
  
    PreparedStatement ps;
    Cliente EL;
    ArrayList<Cliente> TOT = new ArrayList<Cliente>();

    try 
    {

      String sql
              = " SELECT * "
              + " FROM cliente"
              + " WHERE DELETED='N'"
              +" ORDER BY cliente.NOME";

      ps = conn.prepareStatement(sql);

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) 
      {
        EL = read(resultSet);
        TOT.add(EL);
      }

      resultSet.close();
      ps.close();

    }

    catch (SQLException e) 
    {
      throw new RuntimeException(e);
    }
  
    return TOT;
  }



  static Cliente read(ResultSet rs) 
  {
    Cliente cliente = new Cliente();

    try 
    {
      cliente.setID_CL(rs.getInt("ID_CL"));
    } 
    catch (SQLException sqle) {}
    
    try 
    {
      cliente.setSSN(rs.getString("SSN"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setNOME(rs.getString("NOME"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setCOGNOME(rs.getString("COGNOME"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setMAIL(rs.getString("MAIL"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setPASSWORD(rs.getString("PASSWORD"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setNASCITA(rs.getString("NASCITA"));
    } 
    catch (SQLException sqle) {}

    try 
    {
      cliente.setDELETED(rs.getString("DELETED"));
    } 
    catch (SQLException sqle) {}
    
    return cliente;
  }

}
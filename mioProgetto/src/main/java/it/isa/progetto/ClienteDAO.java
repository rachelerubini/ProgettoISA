package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {

  Connection conn;

  public ClienteDAO(Connection conn) {
    this.conn = conn;
  }
  
  public Cliente create(
          int ID_CL,
          String SSN,
          String NOME,
          String COGNOME,
          String MAIL,
          String PASSWORD,
          String NASCITA
          //boolean DELETED,
          ) throws DuplicatedObjectException {

    PreparedStatement ps;
    Cliente cliente = new Cliente();
    cliente.setSSN(SSN);
    cliente.setNOME(NOME);
    cliente.setCOGNOME(COGNOME);
    cliente.setMAIL(MAIL);
    cliente.setPASSWORD(PASSWORD);
    cliente.setNASCITA(NASCITA);


    try {

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

      if (exist) {
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
              + "     DELETED,"
              + "   ) "
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

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return cliente;

  }





  public void delete (Cliente cliente) {

    PreparedStatement ps;

    try {

      String sql
              = " UPDATE cliente "
              + " SET DELETED='Y' "
              + " WHERE "
              + " ID_CL=?";

      ps = conn.prepareStatement(sql);
      ps.setInt(1, cliente.getID_CL());
      ps.executeUpdate();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
}
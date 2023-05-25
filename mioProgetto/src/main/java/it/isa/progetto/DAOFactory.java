package it.isa.progetto;

import java.sql.DriverManager;
import java.sql.Connection;


public class DAOFactory {


  private Connection connection;

  
  public void beginTransaction() {

    try {
      Class.forName(Configuration.DATABASE_DRIVER);
      this.connection = DriverManager.getConnection(Configuration.DATABASE_URL);
      this.connection.setAutoCommit(false);
    }
    catch(Exception ex)
    {
      System.out.println(ex.getMessage());
    }

  }

  
  public void commitTransaction() {
    try {
      this.connection.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  
  public void rollbackTransaction() {

    try {
      this.connection.rollback();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public void closeTransaction() {
    try {
      this.connection.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  
  public AmministratoreDAO getAmministratoreDAO() {
    return new AmministratoreDAO(connection);
  }

  public ClienteDAO getClienteDAO() {
    return new ClienteDAO(connection);
  }

  private Cliente cliente= new Cliente();
  public RecensioneDAO getRecensioneDAO() {
    return new RecensioneDAO(connection, cliente);
  }

  public CorsoDAO getCorsoDAO() {
    return new CorsoDAO(connection);
  }

  public IscrizioneDAO getIscrizioneDAO() {
    return new IscrizioneDAO(connection);
  }

  
}
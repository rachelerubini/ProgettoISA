package it.isa.progetto;
import java.sql.*;

public class App 
{
  public static void main( String[] args )
  {

    //PER FARE IL TEST DELLA CONNESSIONE 
    /* 
      Connection connection;

      try 
      {
        
        connection = MyConnection.getConnection();
        System.out.println(connection);

      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
    */
    
    App.showFrame();
        
  }

  public static Frame showFrame()
  {
    Frame Frame = new Frame();
    return Frame;
  }
   
}

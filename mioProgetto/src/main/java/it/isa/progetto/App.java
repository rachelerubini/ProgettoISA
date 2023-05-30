package it.isa.progetto;
import java.sql.*;

public class App 
{
   public static void main( String[] args )
    {

        //DA QUI FINO ALLA PARTE COMMENTATA L'HO AGGIUNTO IO PER FARE IL TEST DELLA CONNESSIONE SENZA FAR PARTIRE IL RESTO

        Connection connection;

        try {
        
            connection = MyConnection.getConnection();
            System.out.println(connection);

          }
          catch(Exception ex)
          {
            System.out.println(ex);
          }

        //App.showFrame();
        
     }

     /*public static Frame showFrame()
        {
            Frame Frame = new Frame();
            return Frame;
        }*/
   
 }

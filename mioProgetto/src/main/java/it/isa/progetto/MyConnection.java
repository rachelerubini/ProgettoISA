package it.isa.progetto;
import java.sql.*;
/**
 *
 * @author Martino Maniero & Alessandro Bergantin
 */
public class MyConnection {
     public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/isa-palestra", "root", "Glis.Cols1!2!3!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
    
}   
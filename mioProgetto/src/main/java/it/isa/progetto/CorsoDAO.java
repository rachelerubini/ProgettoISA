package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CorsoDAO {

    private static Connection conn;

    /*public*/ CorsoDAO(Connection conn) {
        this.conn = conn;
    }

    public Corso create(
            //int ID_CO,
            String NOME,
            String TIPO,
            String LIVELLO) throws DuplicatedObjectException {

        PreparedStatement ps;
        Corso corso = new Corso();
        corso.setNOME(NOME);
        corso.setTIPO(TIPO);
        corso.setLIVELLO(LIVELLO);

        try {

            String sql
                    = " SELECT ID_CO "
                    + " FROM corso "
                    + " WHERE "
                    + " NOME = ? AND"
                    + " TIPO = ? AND"
                    + " LIVELLO = ? AND"
                    + " DELETED = 'N' ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, corso.getNOME());
            ps.setString(i++, corso.getTIPO());
            ps.setString(i++, corso.getLIVELLO());


            ResultSet resultSet = ps.executeQuery();

            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if (exist)
            {
                    throw new DuplicatedObjectException("CorsoDAO.create: Tentativo di inserimento di un corso già esistente.");
                }



      sql
                    = " INSERT INTO corso "
                    + "   ( ID_CO,"
                    + "     NOME,"
                    + "     TIPO,"
                    + "     LIVELLO,"
                    + "     DELETED," 
                    + ")"
                    + " VALUES (NULL,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int j = 1;
            ps.setString(j++, corso.getNOME());
            ps.setString(j++, corso.getTIPO());
            ps.setString(j++, corso.getLIVELLO());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return corso;

    }

    public void delete (Corso corso) {

        PreparedStatement ps;
    
        try {
    
          String sql
                  = " UPDATE corso "
                  + " SET DELETED='Y' "
                  + " WHERE "
                  + " ID_CO=?";
    
          ps = conn.prepareStatement(sql);
          ps.setInt(1, corso.getID_CO());
          ps.executeUpdate();
          ps.close();
    
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
    
      }




      public List<Corso> findAllCorsi(){

        //PreparedStatement ps;
        //Statement st;
        Corso corso;
        ArrayList<Corso> corsi = new ArrayList<Corso>();
        
        try {

            /*String sql
                    = " SELECT * "
                    + " FROM corso ";*/

                    //st= conn.createStatement();
                    //ResultSet resultSet=st.executeQuery(sql);

                    //ps = conn.prepareStatement(sql);

           //Connection c = MyConnection.getConnection();
           String query = "SELECT * FROM corso";
           //PreparedStatement ps= c.prepareStatement(query);
           PreparedStatement ps = conn.prepareStatement(query);
                    
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                corso = read(resultSet);
                corsi.add(corso);
            }

            //resultSet.close();
            //ps.close();
            //st.close();

        } catch(SQLException e)
        {
            System.out.println(e.getMessage());        
        }

        return corsi;
    }

    public Corso findCorsoByID_CO(int ID_CO) {

        PreparedStatement ps;
        Corso corso = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM corso "
                    + " WHERE "
                    + "   ID_CO = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID_CO);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                corso = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return corso;

    }

      public static Corso findByNomeCorso(String NOME) {

        PreparedStatement ps;
        Corso corso = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM corso "
                    + " WHERE "
                    + "   NOME = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, NOME);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                corso = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return corso;

    }

    

      static Corso read(ResultSet rs) {
        Corso corso = new Corso();


        try {
            corso.setID_CO(rs.getInt("ID_CO"));
        } catch (SQLException sqle) {
        }
        try {
            corso.setNOME(rs.getString("NOME"));
        } catch (SQLException sqle) {
        }
        try {
            corso.setTIPO(rs.getString("TIPO"));
        } catch (SQLException sqle) {
        }
        try {
            corso.setLIVELLO(rs.getString("LIVELLO"));
        } catch (SQLException sqle) {
        }
        try {
            corso.setDELETED(rs.getString("DELETED"));
          } catch (SQLException sqle) {
          }

        return corso;
    }
}

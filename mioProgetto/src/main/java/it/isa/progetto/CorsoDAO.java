package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorsoDAO {

    Connection conn;

    public CorsoDAO(Connection conn) {
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

    

      Corso read(ResultSet rs) {
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

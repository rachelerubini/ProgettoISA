package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorsoDAO {

    private static Connection conn;

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



















      //LE PROSSIME 3 LE LASCIO QUI O CREO ISCRIZIONEDAO????????????????????????????????????

      //da controllareeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
      //per disiscriversi a un corso (N.B essendo N:M passo per Iscrizione)
      public void disiscrivi(int ID_corso, int ID_cliente){
        
        PreparedStatement ps;
    
        try {
    
          String sql
                  = " UPDATE iscrizione "
                  + " SET DELETED='Y' "
                  + " WHERE "
                  + " ID_CO=?"
                  + " AND "
                  + " ID_CL=?";
    
                  //controlla sql

          ps = conn.prepareStatement(sql);
          //ps.setInt(1, iscrizione.getID?());              o cosi o le due sotto:
          ps.setInt(1,iscrizione.getCorso().getID_CO());  //non so se va bene e non capisco bene a cosa serve
          ps.setInt(1, iscrizione.getCliente().getID_CL()); //non so se va bene e non capisco bene a cosa serve
          ps.executeUpdate();
          ps.close();
    
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }


      }





      //da controllareeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
      //funzione che mi dice se quel cliente è iscritto a quel corso
     public int findCorsoCliente( int ID_corso, int ID_cliente){
        PreparedStatement ps;
    Iscrizione iscrizione = null;

    try {

        String sql
                = " SELECT * "
                + " FROM iscrizione "
                + " WHERE "
                + " ID_CO=?"
                + " AND "
                + " ID_CL=?";

        ps = conn.prepareStatement(sql);
         //ps.setInt(1, iscrizione.getID?());              o cosi o le due sotto:
        ps.setInt(1, ID_corso); //non so se va bene e non capisco bene a cosa serve
        ps.setInt(1, ID_cliente); //non so se va bene e non capisco bene a cosa serve
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            iscrizione = read(resultSet);
        }
        resultSet.close();
        ps.close();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return 1;

     }






      //da controllareeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
      //funzione che mi crea una nuova iscrizione
      public Iscrizione createIscrizione(
        //int ID_CO,
        //boolean DELETED,
        Corso corso,
        Cliente cliente) throws DuplicatedObjectException {

    PreparedStatement ps;
    Iscrizione iscrizione = new Iscrizione();
    iscrizione.setCorso(corso);
    iscrizione.setCliente(cliente);

    try {

        String sql
                = " SELECT ID_CO "
                + " FROM iscrizione "
                + " WHERE "
                + " ID_CO=?"
                + " AND "
                + " ID_CL=?"
                + " DELETED = 'N' ";

        ps = conn.prepareStatement(sql);
        int i = 1;
        ps.setInt(i++, iscrizione.getCorso().getID_CO());
        ps.setInt(i++, iscrizione.getCliente().getID_CL());


        ResultSet resultSet = ps.executeQuery();

        boolean exist;
        exist = resultSet.next();
        resultSet.close();

        if (exist)
        {
                throw new DuplicatedObjectException("Tentativo di iscrizione ad un corso a cui sei già iscritto");
            }



  sql
                = " INSERT INTO iscrizione "
                + "   ( DELETED,"
                + "     ID_CO,"
                + "     ID_CL" 
                + ")"
                + " VALUES ('N',?,?)";

        ps = conn.prepareStatement(sql);
        int j = 1;
        ps.setInt(j++, iscrizione.getCorso().getID_CO());
        ps.setInt(j++, iscrizione.getCliente().getID_CL());


        ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return iscrizione;

}


























      public List<Corso> findAllCorsi() {

        PreparedStatement ps;
        Corso corso;
        ArrayList<Corso> corsi = new ArrayList<Corso>();

        try {

            String sql
                    = " SELECT * "
                    + " FROM corso ";

            ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                corso = read(resultSet);
                corsi.add(corso);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {
    Connection conn;

    public RecensioneDAO(Connection conn) {
        this.conn = conn;
    }

    public Recensione create(
            //int ID_R,
            int VOTO,
            String DATA,
            //boolean DELETED,
            Corso corso,
            Cliente cliente) throws DuplicatedObjectException {

        PreparedStatement ps;
        Recensione recensione = new Recensione();
        recensione.setVOTO(VOTO);
        recensione.setDATA(DATA);
        recensione.setCorso(corso);
        recensione.setCliente(cliente);

        try {

            String sql
                    = " SELECT ID_R "
                    + " FROM recensione "
                    + " WHERE "
                    + " DATA = ? AND"
                    + " ID_COR = ? AND"
                    + " ID_CLR = ? AND"
                    + " DELETED ='N'";


            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, recensione.getDATA());
            ps.setInt(i++, recensione.getCorso().getID_CO());
            ps.setInt(i++, recensione.getCliente().getID_CL());

            ResultSet resultSet = ps.executeQuery();

            boolean exist;
            exist = resultSet.next();
            resultSet.close();

            if (exist) {
                throw new DuplicatedObjectException("RecensioneDAO.create: Tentativo di inserimento di un recensione già esistente.");
            }


            sql
                    = " INSERT INTO recensione "
                    + "   ( ID_R,"
                    + "     VOTO,"
                    + "     DATA,"
                    + "     DELETED,"
                    + "     ID_COR,"
                    + "     ID_CLR "
                    + "   ) "
                    + " VALUES (NULL,?,?,'N',?,?)";

            ps = conn.prepareStatement(sql);
            int j = 1;
            ps.setInt(j++, recensione.getVOTO());
            ps.setString(j++, recensione.getDATA());
            //i++;
            ps.setInt(j++, recensione.getCorso().getID_CO());
            ps.setInt(j++, recensione.getCliente().getID_CL());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recensione;

    }


    public void delete(Recensione recensione) {

        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE recensione "
                    + " SET DELETED='Y' "
                    + " WHERE "
                    + " ID_R=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, recensione.getID_R());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

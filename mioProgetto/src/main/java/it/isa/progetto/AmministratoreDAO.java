package it.isa.progetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmministratoreDAO {
    
    Connection conn;

    public AmministratoreDAO(Connection conn) {
    this.conn = conn;
  }

  public Amministratore findByMAILAmministratore(String MAIL) {

    PreparedStatement ps;
    Amministratore amministratore = null;

    try {

        String sql
                = " SELECT * "
                + "   FROM amministratore "
                + " WHERE MAIL = ?";

        ps = conn.prepareStatement(sql);
        ps.setString(1, MAIL);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            amministratore = read(resultSet);
        }
        resultSet.close();
        ps.close();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return amministratore;

}


//IMPLEMENTA READ

}

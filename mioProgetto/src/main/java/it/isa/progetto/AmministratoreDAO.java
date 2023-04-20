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
}

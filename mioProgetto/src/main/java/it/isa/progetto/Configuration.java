package it.isa.progetto;

import java.util.Calendar;
//alcuni DB vogliono la data attuale quindi uso Calendar anche se con Mysql non serve


public class Configuration 
{
  
  /* Database Configruation */
 
  public static final String DATABASE_DRIVER="com.mysql.cj.jdbc.Driver";
  public static final String SERVER_TIMEZONE=Calendar.getInstance().getTimeZone().getID();
  public static final String 
    DATABASE_URL="jdbc:mysql://localhost/isa-palestra?user=root&password=GlisCols123";
 
}

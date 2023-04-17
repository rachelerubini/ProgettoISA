package it.isa.progetto;

import java.util.Calendar;
//alcuni DB vogliono la data attuale quindi uso Calendar anche se con Mysql non serve



public class Configuration {
  
  /* Database Configruation */
 
  public static final String DATABASE_DRIVER="com.mysql.cj.jdbc.Driver";
  public static final String SERVER_TIMEZONE=Calendar.getInstance().getTimeZone().getID();
  public static final String 
    DATABASE_URL="jdbc:mysql://localhost:3306/isa-palestra?user=root&password=Glis.Cols1!2!3!&useSSL=false&serverTimezone="+SERVER_TIMEZONE;
  
}
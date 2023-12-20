package it.isa.progetto;

public class Cliente 
{
  private int ID_CL;
  private String SSN;
  private String NOME;
  private String COGNOME;
  private String MAIL;
  private String PASSWORD;
  private String NASCITA;
  private String DELETED;
  
  private Recensione[] recensioni;


  public int getID_CL() 
  {
    return ID_CL;
  }

  public void setID_CL(int ID_CL) 
  { 
    this.ID_CL = ID_CL; 
  }

  public String getSSN() 
  {
    return SSN;
  }

  public void setSSN(String SSN) 
  {
    this.SSN = SSN;
  }

  public String getNOME() 
  {
    return NOME;
  }

  public void setNOME(String NOME) 
  {
    this.NOME = NOME;
  }

  public String getPASSWORD() 
  {
    return PASSWORD;
  }

  public void setPASSWORD(String PASSWORD) 
  {
    this.PASSWORD = PASSWORD;
  }

  public String getCOGNOME() 
  {
    return COGNOME;
  }

  public void setCOGNOME(String COGNOME) 
  {
    this.COGNOME = COGNOME;
  }

  public String getMAIL() 
  {
    return this.MAIL;
  }

  public void setMAIL(String MAIL) 
  {
    this.MAIL = MAIL;
  }

  public String getNASCITA() 
  {
    return NASCITA;
  }

  public void setNASCITA(String NASCITA) 
  {
    this.NASCITA = NASCITA;
  }

  public String isDELETED() 
  {
    return DELETED;
  }

  public void setDELETED(String DELETED) 
  {
    this.DELETED = DELETED;
  }

  public Recensione[] getrecensioni() 
  {
    return recensioni;
  }

  public void setrecensioni(Recensione[] recensioni) 
  {
    this.recensioni = recensioni;
  }

  public Recensione getrecensioni(int index) 
  {
    return this.recensioni[index];
  }

  public void setrecensioni(int index, Recensione recensioni) 
  {
    this.recensioni[index] = recensioni;
  }

}

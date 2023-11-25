package it.isa.progetto;

public class Amministratore 
{
    private int ID_A;
    private String MAIL;
    private String PASSWORD;

    public int getID_A() 
    {
        return ID_A;
    }

    public void setID_A(int ID_A) 
    {
        this.ID_A = ID_A;
    }

    public String getPASSWORD() 
    {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) 
    {
        this.PASSWORD = PASSWORD;
    }

    public String getMAIL() 
    {
        return MAIL;
    }

    public void setMAIL(String MAIL) 
    {
        this.MAIL = MAIL;
    }

}

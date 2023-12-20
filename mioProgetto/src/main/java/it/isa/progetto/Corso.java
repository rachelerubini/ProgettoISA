package it.isa.progetto;

public class Corso 
{
    
    private int ID_CO;
    private String NOME;
    private String TIPO;
    private String LIVELLO;
    private String DELETED;

    private Recensione[] recensioni;


    public int getID_CO() 
    {
        return ID_CO;
    }

    public void setID_CO(int ID_CO) 
    {
        this.ID_CO = ID_CO;
    }

    public String getNOME() 
    {
        return NOME;
    }

    public void setNOME(String NOME) 
    {
        this.NOME = NOME;
    }

    public String getTIPO() 
    {
        return TIPO;
    }

    public void setTIPO(String TIPO) 
    {
        this.TIPO = TIPO;
    }

    public String getLIVELLO() 
    {
        return LIVELLO;
    }

    public void setLIVELLO(String LIVELLO) 
    {
        this.LIVELLO = LIVELLO;
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

    public String isDELETED() 
    {
        return DELETED;
    }
    
    public void setDELETED(String DELETED) 
    {
        this.DELETED = DELETED;
    }


}


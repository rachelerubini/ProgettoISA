package it.isa.progetto;

public class Iscrizione {
    private String DELETED;
     /* N:M */
     private Corso corso;
     private Cliente cliente;

     public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String isDELETED() {
        return DELETED;
    }

    public void setDELETED(String DELETED) {
        this.DELETED = DELETED;
    }

}

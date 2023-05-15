package it.isa.progetto;
/*package model.mo;*/

public class Recensione {

    private int ID_R;
    private int VOTO;
    private String DATA;
    private String DELETED;
    /* N:1 */
    private Corso corso;
    /* N:1 */
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

    public int getID_R() {
        return ID_R;
    }

    public void setID_R(int ID_R) {
        this.ID_R = ID_R;
    }

    public int getVOTO() {
        return VOTO;
    }

    public void setVOTO(int VOTO) {
        this.VOTO = VOTO;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String isDELETED() {
        return DELETED;
    }

    public void setDELETED(String DELETED) {
        this.DELETED = DELETED;
    }

}

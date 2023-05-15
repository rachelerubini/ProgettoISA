package it.isa.progetto;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//com

public class Frame extends JFrame {

    private JPanel container = new JPanel();

    private PANELHomepage hp = new PANELHomepage();
    private PANELHomepageCliente hcp = new PANELHomepageCliente();
    private PANELHomepageAmministratore hap = new PANELHomepageAmministratore();
    private PANELIscrizione ip = new PANELIscrizione();
    private PANELCorsiClienti ccp = new PANELCorsiClienti();
    private PANELRecensioni rp = new PANELRecensioni();
    private PANELNuovaRecenesione nrp = new PANELNuovaRecensione();
    private PANELCorsiAmministratore cap = new PANELCorsiAmministratore();
    private PANELNuovoCorso ncp = new PANELNuovoCorso();

   
   
    public List<Cliente> findAllClienti() 
    {
    DAOFactory df= new DAOFactory();
    List<Cliente> clienti = new ArrayList<Cliente>();
    df.beginTransaction();
    ClienteDAO dao= df.getClienteDAO();
    clienti  = dao.findAllClienti();
    df.commitTransaction();
    df.closeTransaction();
    return clienti;
}
    private PANELClientiAmministratore clap = new PANELClientiAmministratore(findAllClienti());  //da implementare findClienti!!!!!!!!
  


    private CardLayout cl = new CardLayout();

    //private PANELHomepageController hpc = new PANELHomepageController();
    //private PANELHomepageClienteController hcpc = new PANELHomepageClienteController();
    //private PANELHomepageAmministratoreController hapc = new PANELHomepageAmministratoreController();
    //private PANELIscrizioneController ipc = new PANELIscrizioneController();
    //private PANELCorsiClientiController ccpc = new PANELCorsiClientiController();
    //private PANELRecensioniController rpc = new PANELRecensioniController();
    //private PANELNuovaRecenesioneController nrpc = new PANELNuovaRecensioneController();
    //private PANELCorsiAmministratoreController capc = new PANELCorsiAmministratoreController();
    //private PANELNuovoCorsoController ncpc = new PANELNuovoCorsoController();
    //private PANELClientiAmministratoreController clapc = new PANELClientiAmministratoreController();


    //private PANELClienti cliente= new PANELClienti();
    //private MainPanel mp = new MainPanel(mpc.findAllBrani(), utente);


    public Frame() 
    {

        super("G&C");        
        
        container.setLayout(cl);

        container.add(hp, "1");
        container.add(hcp, "2");
        container.add(hap, "3");
        container.add(ip, "4");
        container.add(ccp, "5");
        container.add(rp, "6");
        container.add(nrp, "7");
        container.add(cap, "8");
        container.add(ncp, "9");
        container.add(clap, "10");

        //parto dall hompegae che è la schermata container 1
        cl.show(container, "1");

        //qua metto tutti i pulsanti della mia applicazione

        //registrati
        hp.jButton7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "4");
                
            }
            
        });

        //NOTA: il login di brina è diverso ma non lo capiamo e forse non va bene per il nostro
        //login posso andare se sono cliente in 2 se sono amministratore nella 3
        hp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) 
            {

                    // le due funzioni finByMAIL sono da implementare nei dao, gli passo la mil e mi restituiscono l'utente con quella mail, se non c'è null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    Cliente cliente = ClienteDAO.findByMAILCliente(hp.jTextField1.getMAIL());
                    Amministratore amministratore = AmministratoreDAO.findByMAILAmministratore(hp.jTextField1.getMAIL());


                    if (cliente == null && amministratore==null)
                    {
                        JOptionPane.showMessageDialog(hp, "Mail errata!");
                        cl.show(container, "1");
                    }
                    else if (cliente != null && amministratore==null)
                    {
                        if (!cliente.getPASSWORD().equals(hp.jTextField2.getPASSWORD()))
                        {
                            JOptionPane.showMessageDialog(hp, "Password errata!");
                            cl.show(container, "1");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(hp, "Login avvenuto con successo!");
                            cl.show(container, "2");
                        }
                    }
                    else 
                    {
                        if (!amministratore.getPASSWORD().equals(hp.jTextField2.getPASSWORD()))
                        {
                            JOptionPane.showMessageDialog(hp, "Password errata!");
                            cl.show(container, "1");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(hp, "Login avvenuto con successo!");
                            cl.show(container, "3");
                        }
                    }
                
            }
            
        });


        //vado dalla homepage del cliente ai corsi per il cliente
         hcp.jButton3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "5");
                
            }
            
        });

             //vado dalla homepage del cliente alle recensioni per il cliente
        hcp.jButton4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "6");
                
            }
            
        });


        //dalla homepage degli amministartori vado alla pagina dei clienti per gli amministratori
        hap.jButton5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "10");
                
            }
            
        });

        //dalla homepage degli amministartori vado alla pagina dei corsi per gli amministratori
        hap.jButton6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "8");
                
            }
            
        });


        //dalla pagina clienti per l'amministratore, l'amministratore va alla sua homepage
        clap.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "3");
                
            }
            
        });

        //dalla pagina corsi per l'amministratore, l'amministratore va alla sua homepage
        cap.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "3");
                
            }
            
        });

        //dalla pagina corsi per l'amministratore, vado sulla pagina per creare un nuovo corso
        cap.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "9");
                
            }
            
        });

        
        //dalla pagina corsi per i clienti, vado sulla loro homepage
        ccp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "2");
                
            }
            
        });

        //dalla pagina recensioni per i clienti, vado sulla loro homepage
        rp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "2");
                
            }
            
        });

        //dalla pagina recensioni per i clienti, vado sulla nuova recensione
        rp.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "7");
                
            }
            
        });


        //dalla pagina iscrizione vado nella homepeage generica
        //ATTENZIONE: BRINA AVEVA SCRITTO: String.valueOf(rp.jTextField2.getPASSWORD()) MA SERVE QUESTO String.valueof????
        ip.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                DAOFactory df = new DAOFactory();
                df.beginTransaction();
                ClienteDAO dao = df.getClienteDAO();
                dao.create(ip.jTextField1.getSSN(),ip.jTextField2.getNOME(),ip.jTextField3.getCOGNOME(), ip.jTextField4.getMAIL(), ip.jTextField5.getPASSWORD(), ip.jTextField6.getNASCITA());
                df.commitTransaction();
                df.closeTransaction();
                JOptionPane.showMessageDialog(ip, "Registrazione avvenuta con successo!");
                cl.show(container, "1");
                }
                catch(DuplicatedObjectException ex)
                {
                    JOptionPane.showMessageDialog(ip, "L'utente esiste già!");
                }
            }
        });


        //dalla pagina nuova recensione vado nella pagina recensioni
        //ATTENZIONE,non siamo sicure vada bene il controllo per vedere se il corso esiste!!!
        nrp.jButton1.addActionListener(new ActionListener(){
            int i=0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try{ nrp.jTextField3.getCorso();} catch(MissingObjectException ex)
                {     i=1;
                    JOptionPane.showMessageDialog(nrp, "Il corso inserito non esiste!");
                }
                if(i==0){

                DAOFactory df = new DAOFactory();
                df.beginTransaction();
                RecensioneDAO dao = df.getRecensioneDAO();
                dao.create(nrp.jTextField1.getVOTO(), nrp.jTextField2.getDATA(),nrp.jTextField3.getCorso());
                df.commitTransaction();
                df.closeTransaction();
                JOptionPane.showMessageDialog(nrp, "Creazione recensione avvenuta con successo!");
                cl.show(container, "6");
                }
                
            }
            
        });


        //dalla pagina nuovo corso vado nella pagina dei corsi per amministratori
        ncp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                DAOFactory df = new DAOFactory();
                df.beginTransaction();
                CorsoDAO dao = df.getCorsoDAO();
                dao.registerCorso( ncp.jTextField1.getNOME(), ncp.jTextField2.getTIPO(), ncp.jTextField3.getLIVELLO());
                df.commitTransaction();
                df.closeTransaction();
                JOptionPane.showMessageDialog(ncp, "Creazione corso avvenuta con successo!");
                cl.show(container, "8");
                }
                catch(DuplicatedObjectException ex)
                {
                    JOptionPane.showMessageDialog(ncp, "Il corso esiste già!");
                }
            }
        });

        
        add(container);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    

    
}
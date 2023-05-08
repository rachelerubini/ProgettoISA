package it.isa.progetto;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private PANELClientiAmministratore clap = new PANELClientiAmministratore();

    private CardLayout cl = new CardLayout();

    private PANELHomepageController hpc = new PANELHomepageController();
    private PANELHomepageClienteController hcpc = new PANELHomepageClienteController();
    private PANELHomepageAmministratoreController hapc = new PANELHomepageAmministratoreController();
    private PANELIscrizioneController ipc = new PANELIscrizioneController();
    private PANELCorsiClientiController ccpc = new PANELCorsiClientiController();
    private PANELRecensioniController rpc = new PANELRecensioniController();
    private PANELNuovaRecenesioneController nrpc = new PANELNuovaRecensioneController();
    private PANELCorsiAmministratoreController capc = new PANELCorsiAmministratoreController();
    private PANELNuovoCorsoController ncpc = new PANELNuovoCorsoController();
    private PANELClientiAmministratoreController clapc = new PANELClientiAmministratoreController();


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


        //DA FINIREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        //login posso andare se sono cliente in 2 se sono amministratore nella 3
        hp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "");
                
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

                ipc.register(ip.jTextField1.getSSN(), ip.jTextField2.getNOME(), ip.jTextField5.getPASSWORD(), ip.jTextField4.getMAIL(), ip.jTextField3.getCOGNOME(), ip.jTextField6.getNASCITA());
                JOptionPane.showMessageDialog(lp, "Registrazione avvenuta con successo!");
                cl.show(container, "1");
                }
                catch(DuplicatedObjectException ex)
                {
                    JOptionPane.showMessageDialog(ip, "L'utente esiste già!");
                }
            }
        });


        //dalla pagina nuova recensione vado nella pagina recensioni
        //ATTENZIONE, SERVE UN CONTROLLO PER DIRE CHE IL CORSO INSERITO DEVE ESISTERE, BBIAMO PENSATO AL MISSING OBJECT EXCEPTION MA NON SAPPIAMO COME LEGARLO AL CAMPO CORSO!!!!!!!!!!!!!!!!!!!!!!
        nrp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                nrpc.register(nrp.jTextField1.getVOTO(), nrp.jTextField2.getDATA(), nrp.jTextField3.getCorso());
                JOptionPane.showMessageDialog(lp, "Creazione recensione avvenuta con successo!");
                cl.show(container, "6");
                }
                catch(MissingObjectException ex)
                {
                    JOptionPane.showMessageDialog(nrp, "Il corso inserito non esiste!");
                }
            }
            
        });


        //dalla pagina nuovo corso vado nella pagina dei corsi per amministratori
        ncp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                ncpc.register(ncp.jTextField2.getTIPO(), ncp.jTextField1.getNOME(), ncp.jTextField3.getLIVELLO());
                JOptionPane.showMessageDialog(lp, "Creazione corso avvenuta con successo!");
                cl.show(container, "8");
                }
                catch(DuplicatedObjectException ex)
                {
                    JOptionPane.showMessageDialog(ncp, "Il corso esiste già!");
                }
            }
        });




//poi si stema login e attenta all aregistrazione e poi guarda la parte finale di brina di questo file
//modifica i campi da inserire in nuova recensione, nuovo corso e iscrizione


















//da qui in poi di brina

        
//login sistema il nostro!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        lp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Utente utente = lpc.login(lp.jTextField1.getText(), String.valueOf(lp.jTextField2.getPassword()));
                    JOptionPane.showMessageDialog(lp, "Login avvenuto con successo!");
                
                    
                    mp= new MainPanel(mpc.findAllBrani(), utente);
                    mp.jButton4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            cl.show(container, "1");
                            if (mp.jButton3.isEnabled())
                            mpc.stop();
                            
                        }
                    }); 
                    
                
                    mp.jButton1.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                           try{ 

                            if (mp.jButton3.isEnabled())
                            mpc.stop();
                           
                           mp = new MainPanel((mpc.findByString(mp.jTextField1.getText())), mpc.aggiornaUtente(utente));
                           mp.jButton1.addActionListener(this);
                           mp.jButton4.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                cl.show(container, "1");
                                if (mp.jButton3.isEnabled())
                            mpc.stop();
                            }
                        }); 
                           
                           
                           
                           container.add(mp, "7");
            
                           cl.show(container, "7");
            
            
                           }
            
                           catch(MissingObjectException ex){
            
                            JOptionPane.showMessageDialog( mp, "Nessun Brano!");
            
                           }
                          
                            
                                
            
                               
                                
            
                             
                             
                        }
                        
                        
                        
                    });


                    container.add(mp, "7");
                    cl.show(container, "7");
                }
                catch(MissingObjectException ex)
                {
                    JOptionPane.showMessageDialog(lp, "L'utente non esiste!");
                }

                catch(WrongPasswordException ex)
                {
                    JOptionPane.showMessageDialog(lp, "Password Errata!");
                }
                
            }
            
        });


        

        


       

        

        //DA GUARDARE COSA SONO E SE CI SERVONOOOOOOOOOOOOOOOOOOOOOOOOOO

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                
                file.delete();
                
                }
                
            });
        


        

        
        add(container);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    


    

    
}
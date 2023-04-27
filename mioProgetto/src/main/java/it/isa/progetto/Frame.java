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
        fp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "2");
                
            }
            
        });

        fp.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "3");
                
            }
            
        });

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

        lp.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "1");
                
            }
            
        });

        rp.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                rpc.register(rp.jTextField1.getText(), String.valueOf(rp.jTextField2.getPassword()));
                JOptionPane.showMessageDialog(lp, "Registrazione avvenuta con successo!");
                cl.show(container, "1");
                }
                catch(DuplicatedObjectException ex)
                {
                    JOptionPane.showMessageDialog(lp, "L'utente esiste già!");
                }
            }
            
        });

        rp.jButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "1");
                
            }
            
        });

        


       

        

        

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
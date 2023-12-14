package it.isa.progetto;

import javax.swing.JFrame;
import javax.swing.JOptionPane; 
import javax.swing.JPanel;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;





public class Frame extends JFrame 
{

    private JPanel container = new JPanel();

    private PANELHomepage hp = new PANELHomepage();

    private Cliente cliente= new Cliente(); 
    // ma ai panel passiamo questo cliente vuoto creato qui? oppure llo inzializziamo da qualche parte tipo nel login, 
    //ed è lo stesso  cliente che vorreemo usare nel bottone nuova recensione (cosi fa riferimento al cliente loggato) ->frame riga  403
    
    private PANELHomepageCliente hcp = new PANELHomepageCliente(cliente);
    private Amministratore amministratore= new Amministratore();
    private PANELHomepageAmministratore hap = new PANELHomepageAmministratore(amministratore);
    private PANELIscrizione ip = new PANELIscrizione();
    private PANELNuovaRecensione nrp = new PANELNuovaRecensione(cliente);
    private PANELNuovoCorso ncp = new PANELNuovoCorso();

   
    public List<Corso> findAllCorsi() 
    {
        DAOFactory df= new DAOFactory();
        List<Corso> corsi = new ArrayList<Corso>();
        df.beginTransaction();
        CorsoDAO dao= df.getCorsoDAO();
        corsi  = dao.findAllCorsi();
        df.commitTransaction();
        df.closeTransaction();
        return corsi;
    }

    private PANELCorsiClienti ccp = new PANELCorsiClienti(cliente,findAllCorsi());

    private PANELCorsiAmministratore cap = new PANELCorsiAmministratore(findAllCorsi());

    public List<Recensione> findAllRecensioni() 
    {
        DAOFactory df= new DAOFactory();
        List<Recensione> recensioni = new ArrayList<Recensione>();
        df.beginTransaction();
        RecensioneDAO dao= df.getRecensioneDAO();
        recensioni  = dao.findAllRecensioni();
        df.commitTransaction();
        df.closeTransaction();
        return recensioni;
    }

    
    private PANELRecensioni rp = new PANELRecensioni(cliente, findAllRecensioni());
   
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
    private PANELClientiAmministratore clap = new PANELClientiAmministratore(findAllClienti());  
  


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

        //bottoone che dalla home page ti porta neella pagina di registrazione (per  creare nuovo  cliente)
        hp.jButton7.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
    
                cl.show(container, "4");
                
            }
            
        });


       
        //LOGIN 
        //posso andare se sono cliente in 2 se sono amministratore nella 3
        hp.jButton1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            { 
                DAOFactory df=new DAOFactory();
                df.beginTransaction();
                ClienteDAO dao=df.getClienteDAO();
                AmministratoreDAO dao2= df.getAmministratoreDAO();
            

                Cliente cliente = dao.findByMAILCliente(hp.jTextField1.getText());
                Amministratore amministratore = dao2.findByMAILAmministratore(hp.jTextField1.getText());

                if (cliente == null && amministratore==null)
                {
                    JOptionPane.showMessageDialog(hp, "Mail errata!");
                    cl.show(container, "1");
                }
                else if (cliente != null && amministratore==null)
                {
                    if (!cliente.getPASSWORD().equals(hp.jTextField2.getText()))
                    {
                        JOptionPane.showMessageDialog(hp, "Password errata!");
                        cl.show(container, "1");
                    }
                    else
                    {   
                        JOptionPane.showMessageDialog(hp, "Login avvenuto con successo!");

                        hcp = new PANELHomepageCliente(cliente);
                        
                        //prova per paassare clliente:
                        container.add(hcp, "2");

                        ccp = new PANELCorsiClienti(cliente,findAllCorsi());
                          
                        //da homepage cliente vado dai coorsi
                        hcp.jButton3.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "5");
                
                            }   
            
                        });
                                
                        container.add(ccp, "5");

                        //dalla pagina corsi per i clienti, vado sulla loro homepage
                        ccp.jButton1.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "2");
                
                            }
            
                        });


                        //vado dalla homepage del cliente alle recensioni per il cliente
                        rp = new PANELRecensioni(cliente, findAllRecensioni());                      
                        hcp.jButton4.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "6");
                
                            }
            
                        });

                            
                        container.add(rp, "6");


                        //dalla pagina recensioni per i clienti, vado sulla loro homepage
                        rp.jButton1.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "2");
                
                            }
            
                        });

                        
                        //logout cliente
                        hcp.jButton1.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {   
                                hp.jTextField1.setText("");
                                hp.jTextField2.setText("");
                                cl.show(container, "1");
                
                            }
            
                        }); 
                            


                        //dalla pagina recensioni per i clienti, vado sulla nuova recensione

                        nrp = new PANELNuovaRecensione(cliente);
        
                        rp.jButton2.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "7");
                            }
            
                        });
                        
                        container.add(nrp, "7");

                        //da pagina di nuova recensione vado alla home del cliente
                        nrp.jButton2.addActionListener(new ActionListener()
                        {

                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
                                cl.show(container, "2");
                
                            }
            
                        });


    
                        //dalla pagina nuova recensione, creo nuova recensione e vado nella pagina recensioni
                        //ATTENZIONE,non siamo sicure vada bene il controllo per vedere se il corso esiste!!!
                        nrp.jButton1.addActionListener(new ActionListener()
                        {
                            // int i=0;

                            @Override
                            public void actionPerformed(ActionEvent e) 
                            {
              
                                try
                                {
                

                                    DAOFactory df = new DAOFactory();
                                    df.beginTransaction();
                                    RecensioneDAO dao = df.getRecensioneDAO();

                
                                    CorsoDAO daoc=df.getCorsoDAO();
                                    Corso corso = daoc.findByNomeCorso(nrp.jTextField3.getText());
                                    
                                    
                                    if (corso == null)
                                    {
                                        JOptionPane.showMessageDialog(nrp, "Il corso inserito non esiste");
                                        cl.show(container, "7");  //rimango nella pagina di creazione recensione 
                                    }

                                    dao.create(Integer.valueOf(nrp.jTextField1.getText()), nrp.jTextField2.getText(), corso , cliente  ); 
                                    df.commitTransaction();
                                    df.closeTransaction();
                                    JOptionPane.showMessageDialog(nrp, "Creazione recensione avvenuta con successo!");
                                    cl.show(container, "6");
               
                                }
                                catch(DuplicatedObjectException ex)
                                {  
                                    JOptionPane.showMessageDialog(ncp, "Recensione già scritta!");
                                }
           
                            }
            
                        });




                  
                        
                        //per andare alla homeepage cliente dalla home
                        cl.show(container, "2");


                    }

                        
                }
                
                else 
                {
                    if (!amministratore.getPASSWORD().equals(hp.jTextField2.getText()))
                    {
                        JOptionPane.showMessageDialog(hp, "Password errata!");
                        cl.show(container, "1");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(hp, "Login avvenuto con successo!");
                        hap= new PANELHomepageAmministratore(amministratore);
                                    
                        //per andare alla homeepage amministratore dalla home
                        cl.show(container, "3");
                    }
                }
                df.closeTransaction();
            }
            
        });



        //logout
        hap.jButton1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                hp.jTextField1.setText("");
                hp.jTextField2.setText("");
                cl.show(container, "1");
            }  
        }); 



    
        //dalla pagina iscrizione vado nella homepeage generica
        ip.jButton2.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "1");
            }
            
        });


            
        //dalla iscrizione quando creo nuovo utente vado alla homepage generica
        ip.jButton1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    DAOFactory df = new DAOFactory();
                    df.beginTransaction();
                    ClienteDAO dao = df.getClienteDAO();
                    dao.create(ip.jTextField1.getText(),ip.jTextField2.getText(),ip.jTextField3.getText(), ip.jTextField4.getText(), ip.jTextField5.getText(), ip.jTextField6.getText());
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


        


        //dalla homepage degli amministartori vado alla pagina dei clienti per gli amministratori
        hap.jButton5.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "10"); 
            }
            
        });




        //dalla homepage degli amministartori vado alla pagina dei corsi per gli amministratori
        hap.jButton6.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "8");    
            }
            
        });


        //dalla pagina clienti per l'amministratore, l'amministratore va alla sua homepage
        clap.jButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "3");
            }
            
        });


        
        //dalla pagina corsi per l'amministratore, l'amministratore va alla sua homepage
        cap.jButton2.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "3");
                
            }
            
        });



        //dalla pagina corsi per l'amministratore, vado sulla pagina per creare un nuovo corso
        cap.jButton1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "9");
                
            }
            
        });




        //da pagina di nuovo corso vado alla home dell'amministratore
        ncp.jButton2.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cl.show(container, "3");
            }
            
        });





        //dalla pagina nuovo corso, creo un nuovo corso e vado nella pagina dei corsi per amministratori
        ncp.jButton1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    DAOFactory df = new DAOFactory();
                    df.beginTransaction();
                    CorsoDAO dao = df.getCorsoDAO();
                    dao.create( ncp.jTextField1.getText(), ncp.jTextField2.getText(), ncp.jTextField3.getText());
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
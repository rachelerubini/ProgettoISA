package it.isa.progetto;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.lang.Integer;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.List;



public class PANELCorsiClienti extends javax.swing.JPanel 
{

    //Creates new form PANELCorsiClienti
    public PANELCorsiClienti(Cliente cliente, List<Corso> corsi) 
    {
        initComponents(cliente,corsi);
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(Cliente cliente, List<Corso> corsi) 
    {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pannello=new TestPane();
        pannello1=new TestPane();

        //brr:   this.cliente= cliente;

        setPreferredSize(new java.awt.Dimension(400, 300));
        setBackground(new java.awt.Color(243, 226, 243));

        jLabel1.setText("Tutti i corsi a cui ti puoi iscrivere:");

        jLabel2.setText("Corsi a cui sei iscritto:");

        jButton1.setText("Home");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           // .addGap(0, 226, Short.MAX_VALUE)
           .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //.addGap(0, 255, Short.MAX_VALUE)
            .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //.addGap(0, 249, Short.MAX_VALUE)
            .addComponent(pannello1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           // .addGap(0, 0, Short.MAX_VALUE)
           .addComponent(pannello1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
           );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(107, 107, 107)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

       

        //tutti i corsi a cui puoi iscriverti (ovvero non quelli a cui sei gia iscritto)
        for(int i=0; i<corsi.size(); i++)
        { 
            DAOFactory df= new DAOFactory();
            df.beginTransaction();
            IscrizioneDAO dao= df.getIscrizioneDAO();

            JPanel panel = new JPanel();
            if(corsi.get(i).isDELETED().equals("N")  &&  (dao.findCorsoCliente( corsi.get(i).getID_CO(),  cliente.getID_CL())!=1)  )
            {
         
                JButton button = new JButton(makeButtonText(corsi.get(i)));
                button.setName(Integer.toString(corsi.get(i).getID_CO()));
                panel.add(button);
                df.commitTransaction();
                df.closeTransaction();
            
  
                // le prossime righe servono perchè quando clicco il bottone mi iscrivo a quel corso
                button.addActionListener(new ActionListener()
                {
                    //tutti i corsi:
                    @Override
                    public void actionPerformed(ActionEvent e ) 
                    {
                        DAOFactory df= new DAOFactory();
                        df.beginTransaction();
                        IscrizioneDAO dao= df.getIscrizioneDAO();
                        CorsoDAO daoc= df.getCorsoDAO();
       
                        int ID_corso= (Integer.parseInt(((JButton)e.getSource()).getName()));
                        //com'era:

                        /*
                        //try{
                        dao.createIscrizione(daoc.findCorsoByID_CO(ID_corso), cliente);
                        Corso corso=daoc.findCorsoByID_CO(ID_corso); 
                        JOptionPane.showMessageDialog(null, "Ti sei iscritto al corso: "+corso.getNOME());
                        //} catch(DuplicatedObjectException ex){}*/ 

                        //prova:     DA PROVARE
                        int iscritti= dao.countiscritti(ID_corso);
                        Corso corso=daoc.findCorsoByID_CO(ID_corso); 

                        if (iscritti<10){
                        dao.createIscrizione(daoc.findCorsoByID_CO(ID_corso), cliente);
                       
                        JOptionPane.showMessageDialog(null, "Ti sei iscritto al corso: "+corso.getNOME());
                        }

                        else{ JOptionPane.showMessageDialog(null, "Non puoi iscriverti al corso "+corso.getNOME()+ " perchè è stato raggiunto il numero massimo");  }


                        //ffinee prova        
                        df.commitTransaction();
                        df.closeTransaction();
                    }
                });
        
                //panel.setBorder(new MBorder(0, 0, 1, 0, Color.GRAY));       serve?
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pannello.mainList.add(panel, gbc, 0);      
            }
        
        }




        //i corsi a cui il cliente è iscritto
        for(int i=0; i<corsi.size(); i++)
        {   
            DAOFactory df= new DAOFactory();
            df.beginTransaction();
            IscrizioneDAO dao= df.getIscrizioneDAO();
         
            JPanel panel= new JPanel();
            if((corsi.get(i).isDELETED().equals("N")) && (dao.findCorsoCliente( corsi.get(i).getID_CO(),  cliente.getID_CL())==1))
            {
                JButton button2 = new JButton(makeButtonText(corsi.get(i)));
                button2.setName(Integer.toString(corsi.get(i).getID_CO()));
    
                panel.add(button2);
        
                button2.addActionListener(new ActionListener()
                {
                    // va fatto un action Permormed diverso per il secondo pannello? si puo? come?????
                    @Override
                    public void actionPerformed(ActionEvent e ) 
                    {
                        DAOFactory df= new DAOFactory();
                        df.beginTransaction();
                        IscrizioneDAO dao= df.getIscrizioneDAO();
                        int ID_corso= (Integer.parseInt(((JButton)e.getSource()).getName()));

                        //disiscrivi se clicca sul bottone
                        dao.disiscrivi(ID_corso,  cliente.getID_CL());  
                        CorsoDAO daoc=df.getCorsoDAO();
                        Corso corso=daoc.findCorsoByID_CO(ID_corso); 
                        JOptionPane.showMessageDialog(null, "Ti sei disiscritto al corso: "+corso.getNOME());
                        df.commitTransaction();
                        df.closeTransaction();
                            
                    }
                });
            
                //panel.setBorder(new MBorder(0, 0, 1, 0, Color.GRAY));       serve?
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL; 
                pannello1.mainList.add(panel, gbc, 0);   
            
              
            }
            //df.commitTransaction();
            //df.closeTransaction();
        }
    
        validate();
        repaint();
    
    }// </editor-fold>                           
    



    //cosa mostro nel bottone
    public String makeButtonText(Corso corsi)
    { 
        return ("NOME: "+corsi.getNOME()+"\t TIPO: "+corsi.getTIPO()+"\t LIVELLO: "+corsi.getLIVELLO());
          
    }
    
      
    public class TestPane extends JPanel 
    {
    
        private JPanel mainList;
    
        public TestPane() 
        {
            setLayout(new BorderLayout());
    
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
    
            add(new JScrollPane(mainList));
    
        }
          
        @Override
        public Dimension getPreferredSize() 
        {
            return new Dimension(200, 200);
        }
    }
                           


    // Variables declaration                     
    javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private TestPane pannello;
    private TestPane pannello1;
    Cliente cliente;
    // End of variables declaration                   
}


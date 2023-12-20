package it.isa.progetto;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.lang.Integer;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;



public class PANELCorsiAmministratore extends javax.swing.JPanel implements ActionListener
{

    //Creates new form PANELCorsiAmministratore
    public PANELCorsiAmministratore(List<Corso> corsi) 
    {
        initComponents(corsi);
    }
                        
    private void initComponents(List<Corso> corsi) 
    {
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pannello=new TestPane();

        setBackground(new java.awt.Color(243, 226, 243));
        setForeground(new java.awt.Color(153, 0, 204));

        jLabel1.setText("Corsi:");

        jButton1.setText("Nuovo corso");

        jButton2.setText("Home");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            
            .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            
            .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 513, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 253, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );

        for(int i=0; i<corsi.size(); i++)
        {
    
            JPanel panel = new JPanel();
            if(corsi.get(i).isDELETED().equals("N"))
            {
                JButton button = new JButton(makeButtonText(corsi.get(i)));
                button.setName(Integer.toString(corsi.get(i).getID_CO()));
                panel.add(button);
        
                // le prossime righe servono perchè quando clicco il bottone voglio che si elimini quel corso
                button.addActionListener((ActionListener) this);
            
                //panel.setBorder(new MBorder(0, 0, 1, 0, Color.GRAY));       serve?
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pannello.mainList.add(panel, gbc, 0);      
            }
        }
    
        validate();
        repaint();
    }                       



    public void actionPerformed(ActionEvent e ) 
    {
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        CorsoDAO dao= df.getCorsoDAO();
        Corso cr= dao.findCorsoByID_CO(Integer.parseInt(((JButton)e.getSource()).getName()));
        dao.delete(cr);
        JOptionPane.showMessageDialog(null, "Hai eliminato il corso:  "+cr.getNOME());
        df.commitTransaction();
        df.closeTransaction();
            
    }
    
    //aggiunto  per il for:  per il i-esimo corso con delted = N , creo il bottone per eliminarlo
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
    javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private TestPane pannello;
    // End of variables declaration                   
}

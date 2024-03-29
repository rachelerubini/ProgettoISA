package it.isa.progetto;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.List;


public class PANELClientiAmministratore extends javax.swing.JPanel implements ActionListener
{

    //Creates new form PANELClientiAmministratore

    public PANELClientiAmministratore(List<Cliente> clienti) 
    {
        initComponents(clienti);
    }

    private void initComponents(List<Cliente> clienti)
    {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pannello=new TestPane();

        setPreferredSize(new java.awt.Dimension(400, 300));
        setBackground(new java.awt.Color(243, 226, 243));
    
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Scegli il cliente da eliminare:");
    
        jButton1.setText("Home");
    
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 550, Short.MAX_VALUE)
                .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 700, Short.MAX_VALUE)
                .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
            );
    
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(303, 303, 303))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
       
           
        
        //Il for lo aggiungiamo per mostrare tutti i clienti e poterli cancellare
        for(int i=0; i<clienti.size(); i++)
        {
    
            JPanel panel = new JPanel();
            if(clienti.get(i).isDELETED().equals("N"))
            {
                JButton button = new JButton(makeButtonText(clienti.get(i)));
                button.setName(Integer.toString(clienti.get(i).getID_CL()));
                panel.add(button);

                // le prossime righe servono perchè quando clicco il bottone voglio che si elimini quel cliente
                button.addActionListener((ActionListener) this);

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
    
    //aggiunto per il for:  per il i-esimo cliente con delted = N , creo il bottone per eliminarlo
    public String makeButtonText(Cliente cliente)
    {
        return ("SSN: "+cliente.getSSN()+"\t Nome: "+cliente.getNOME()+"\t Cognome: "+cliente.getCOGNOME());
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
   

    
    public void actionPerformed(ActionEvent e) 
    {
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        ClienteDAO dao= df.getClienteDAO();
        Cliente cl= dao.findClienteByID(Integer.parseInt(((JButton)e.getSource()).getName()));
        dao.delete(cl);
        JOptionPane.showMessageDialog(null, "Hai eliminato il cliente:  "+cl.getNOME()+"  "+cl.getCOGNOME());
        df.commitTransaction();
        df.closeTransaction();
    }

    // Variables declaration                  
    javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private TestPane pannello;
    // End of variables declaration                   
}


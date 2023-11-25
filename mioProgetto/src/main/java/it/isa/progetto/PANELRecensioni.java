package it.isa.progetto;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.lang.Integer;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.List;


public class PANELRecensioni extends javax.swing.JPanel implements ActionListener
{

    //Creates new form PANELRecensioni
    public PANELRecensioni(Cliente cliente, List<Recensione> recensioni) 
    {
        initComponents(cliente, recensioni);
    }

    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(Cliente cliente, List<Recensione> recensioni) 
    {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pannello=new TestPane();

        setPreferredSize(new java.awt.Dimension(400, 300));
        setBackground(new java.awt.Color(243, 226, 243));
    
        jLabel1.setText("Recensioni:");

        jButton1.setText("Home");

        jButton2.setText("Nuova recensione");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //c'era:        .addGap(0, 495, Short.MAX_VALUE)
            //brr:
            .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //c'era:         .addGap(0, 0, Short.MAX_VALUE)
             //brr:
             .addComponent(pannello, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 281, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );


   
        //Il for serve per mostrare tutte le recensioni e poterle cancellare
        for(int i=0; i<recensioni.size(); i++)
        {
            JPanel panel = new JPanel();
        
            //se la recensione non è  eliminata e l'ho scritta io vedrò un pulsante con la recensione e cliccandoci sopra la elimino
            if((recensioni.get(i).isDELETED().equals("N")) && (recensioni.get(i).getCliente().getID_CL() ==  cliente.getID_CL()))
            {
                JButton button = new JButton(makeButtonText(recensioni.get(i)));
                button.setName(Integer.toString(recensioni.get(i).getID_R()));
                panel.add(button);

                // le prossime righe servono perchè quando clicco il bottone voglio che si elimini per quel cliente
                button.addActionListener((ActionListener) this);
            
                //panel.setBorder(new MBorder(0, 0, 1, 0, Color.GRAY));       serve?
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pannello.mainList.add(panel, gbc, 0);      
            } 
            else if (recensioni.get(i).isDELETED().equals("N"))
            { 
                //se non è  una tua recensione la vedi scritta (e non a botttone) perchè non puoi eliminarla
                JLabel jLabel = new javax.swing.JLabel();
            
                DAOFactory df= new DAOFactory();
                df.beginTransaction();
                CorsoDAO dao= df.getCorsoDAO();
                ClienteDAO daoc=df.getClienteDAO();
                String nome=  dao.findCorsoByID_CO(recensioni.get(i).getCorso().getID_CO()).getNOME() ;
                String nomec= daoc.findClienteByID(recensioni.get(i).getCliente().getID_CL()).getNOME();
                df.commitTransaction();
                df.closeTransaction();
                jLabel.setText("CORSO: "+nome+"\t VOTO: "+recensioni.get(i).getVOTO()+"\t CLIENTE: "+nomec +"\t DATA: "+recensioni.get(i).getDATA());
                panel.add(jLabel);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pannello.mainList.add(panel, gbc, 0);  

            }
        }
        validate();
        repaint();

    }// </editor-fold>                           


    public void actionPerformed(ActionEvent e) 
    {
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        RecensioneDAO dao= df.getRecensioneDAO();
        Recensione rec= dao.findRecensioneByID(Integer.parseInt(((JButton)e.getSource()).getName()));
        dao.delete(rec);
        JOptionPane.showMessageDialog(null, "Hai eliminato la tua recensione");
        df.commitTransaction();
        df.closeTransaction();
            
    } 

    // per il for:  per il i-esimo cliente con delted = N , creo il bottone per eliminarlo
    public String makeButtonText(Recensione recensione)
    {
        DAOFactory df= new DAOFactory();
        df.beginTransaction();
        CorsoDAO dao= df.getCorsoDAO();
        ClienteDAO daoc=df.getClienteDAO();
        String nome=  dao.findCorsoByID_CO(recensione.getCorso().getID_CO()).getNOME() ;
        String nomec= daoc.findClienteByID(recensione.getCliente().getID_CL()).getNOME();
        df.commitTransaction();
        df.closeTransaction();
        return ("CORSO: "+nome+"\t VOTO: "+recensione.getVOTO()+"\t CLIENTE: "+nomec +"\t DATA: "+recensione.getDATA());

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


    // Variables declaration - do not modify                     
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private TestPane pannello;
    Cliente cliente;
    // End of variables declaration                   
}


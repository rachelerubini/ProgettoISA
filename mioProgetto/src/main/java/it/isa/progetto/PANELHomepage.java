package it.isa.progetto;


public class PANELHomepage extends javax.swing.JPanel 
{

    //Creates new form PanelHomepage
    public PANELHomepage() 
    {
        initComponents();
    }

    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() 
    {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 300));
        setBackground(new java.awt.Color(243, 226, 243));
        setForeground(new java.awt.Color(153, 0, 204));

        jLabel1.setText("Mail:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Password:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Login");

        jTextArea1.setBackground(new java.awt.Color(102, 102, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("Benvenuto!\nAllenati da noi, G&C è tra le migliori \npalestre nel mondo.\nVienici a trovare nella nostra sede\nVia Sabbioni 24\n+39 320 337 2135\n ");
        jScrollPane1.setViewportView(jTextArea1);

        jButton7.setText("Registrati");

       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)

                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1,
                javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                javax.swing.GroupLayout.PREFERRED_SIZE)))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                false)
                            .addComponent(jTextField1,
                javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(31, 31, 31))

                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                109, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1,
                javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton7,
                javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(134, Short.MAX_VALUE))
            );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(28, 28, 28)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField2,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jScrollPane1,
                javax.swing.GroupLayout.PREFERRED_SIZE, 125,
                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(67, Short.MAX_VALUE))
            );

	

    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) 
    {                                            
        // TODO add your handling code here:
    }                                           


    // Variables declaration                  
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    javax.swing.JTextField jTextField1;
    javax.swing.JTextField jTextField2;
    // End of variables declaration                   
}


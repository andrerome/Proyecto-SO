package rss.gui;

import java.net.MalformedURLException;
import java.util.LinkedList;
import javax.swing.AbstractListModel;
import rss.logic.FeedFetcher;
import rss.logic.RSSData;
import rss.logic.RSSProvider;
import rss.logic.UserFeedUpdater;

public class RSSReaderFrame extends javax.swing.JFrame {
    private final LinkedList <RSSData> userFeed;
    public final LinkedList <RSSProvider> providers = new LinkedList <RSSProvider> ();
    public final LinkedList <FeedFetcher> fetchers = new LinkedList <FeedFetcher> ();
    private UserFeedUpdater updater;
    
    public RSSReaderFrame(LinkedList <RSSData> feed) throws MalformedURLException {
        this.userFeed = feed;

        initComponents();
        providers.add(new RSSProvider("Ecuador ", "http://www.eluniverso.com/rss/ecuador.xml"));
        providers.add(new RSSProvider("Internacional", "http://www.eluniverso.com/rss/internacional.xml"));
        providers.add(new RSSProvider("Tecnologia", "http://www.microsiervos.com/index.xml"));
        providers.add(new RSSProvider("ESPOL - Trabajos", "http://www.espol.edu.ec/trabajo.aspx"));
        providers.add(new RSSProvider("Vida y estilo", "http://www.eluniverso.com/rss/vida-y-estilo.xml"));
        providers.add(new RSSProvider("Deportes", "http://www.eluniverso.com/rss/deportes.xml"));
        
        try {
            jCheckBox1.setText(providers.get(0).getTema());
            jCheckBox2.setText(providers.get(1).getTema());
            jCheckBox3.setText(providers.get(2).getTema());
            jCheckBox4.setText(providers.get(3).getTema());
            jCheckBox5.setText(providers.get(4).getTema());
            jCheckBox6.setText(providers.get(5).getTema());
        } catch (NullPointerException e) {}
    }
    
    public void refreshFeed() {
        jList1.setModel(new AbstractListModel() {       
            @Override
            public int getSize() {
                return userFeed.size();
            }

            @Override
            public Object getElementAt(int i) {
                return userFeed.get(i);
            }
        });
    }
    
    public void forcePull() {
        for(FeedFetcher fetcher : fetchers)
            fetcher.forcePull();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RSSReader");

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCellRenderer(new RSSDataRenderer());
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Force pull");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("RSS");

        jCheckBox1.setText("Tema1");

        jCheckBox2.setText("Tema2");

        jCheckBox3.setText("Tema3");

        jCheckBox4.setText("Tema4");

        jButton2.setText("Comenzar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox5.setText("Tema5");

        jCheckBox6.setText("Tema6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox6)
                .addGap(40, 40, 40)
                .addComponent(jButton2)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        forcePull();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO: Optimizar esto
        if (jCheckBox1.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(0).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        if (jCheckBox2.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(1).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        if (jCheckBox3.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(2).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        if (jCheckBox4.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(3).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        if (jCheckBox5.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(4).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        if (jCheckBox6.isSelected()) {
            FeedFetcher fetcher = new FeedFetcher(providers.get(5).getUrl().toString());
            fetcher.start();
            fetchers.add(fetcher);
        }
        
        updater = new UserFeedUpdater(userFeed, this);
        updater.start();
        
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox4.setEnabled(false);
        jCheckBox5.setEnabled(false);
        jCheckBox6.setEnabled(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
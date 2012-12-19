/*
 * DesktopApplication1View.java
 */

package desktopapplication1;

import desktopapplication1.sixpack.MainThread;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRootPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * The application's main frame.
 */
public class DesktopApplication1View extends FrameView {

    
    public Boolean status=false;
    public String response=null;
    public int starttimeMilli;
    public int hour;
    public int minute;
    public int second;
     int k=0;
    Calendar calendar ;
     Date today = new Date();
     Date today2=new Date();
        long time1;
        
    int noOfRequests;
     private static final Object lock= new Object();  
    public DesktopApplication1View(SingleFrameApplication app) {
        super(app);
        
        initComponents();
        getFrame().setResizable(false);
       
       // jDesktopPane1.setSize(new Dimension(400,500));
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DesktopApplication1.getApplication().getMainFrame();
            mainFrame.setResizable(false);
            aboutBox = new DesktopApplication1AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DesktopApplication1.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(desktopapplication1.DesktopApplication1.class).getContext().getResourceMap(DesktopApplication1View.class);
        jDesktopPane1.setBackground(resourceMap.getColor("jDesktopPane1.background")); // NOI18N
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setBackground(resourceMap.getColor("jTable1.background")); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No of Attempts", "Time(Sec)", "No of Requests", "Avg. Response time(MilliSeconds)"
            }
        ));
        jTable1.setGridColor(resourceMap.getColor("jTable1.gridColor")); // NOI18N
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setRowHeight(27);
        jTable1.setSelectionBackground(resourceMap.getColor("jTable1.selectionBackground")); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        jScrollPane1.setBounds(10, 60, 590, 110);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setForeground(resourceMap.getColor("jTextArea1.foreground")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(resourceMap.getString("jTextArea1.text")); // NOI18N
        jTextArea1.setToolTipText(resourceMap.getString("jTextArea1.toolTipText")); // NOI18N
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jTextArea1.setSelectionColor(resourceMap.getColor("jTextArea1.selectionColor")); // NOI18N
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea1MouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane2.setBounds(10, 210, 590, 430);
        jDesktopPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea2.setBackground(resourceMap.getColor("jTextArea2.background")); // NOI18N
        jTextArea2.setColumns(20);
        jTextArea2.setFont(resourceMap.getFont("jTextArea2.font")); // NOI18N
        jTextArea2.setForeground(resourceMap.getColor("jTextArea2.foreground")); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setToolTipText(resourceMap.getString("jTextArea2.toolTipText")); // NOI18N
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jTextArea2.setName("jTextArea2"); // NOI18N
        jTextArea2.setSelectionColor(resourceMap.getColor("jTextArea2.selectionColor")); // NOI18N
        jTextArea2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea2MouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea2);

        jScrollPane3.setBounds(640, 30, 550, 610);
        jDesktopPane1.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setForeground(resourceMap.getColor("jLabel3.foreground")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(20, 30, 30, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField3.setBackground(resourceMap.getColor("jTextField3.background")); // NOI18N
        jTextField3.setFont(resourceMap.getFont("jTextField3.font")); // NOI18N
        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(resourceMap.getString("jTextField3.toolTipText")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N
        jTextField3.setBounds(60, 30, 540, 22);
        jDesktopPane1.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jToolBar1.add(jLabel1);

        jTextField1.setBackground(resourceMap.getColor("jTextField1.background")); // NOI18N
        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(resourceMap.getString("jTextField1.toolTipText")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jToolBar1.add(jTextField1);

        jButton1.setBackground(resourceMap.getColor("jButton1.background")); // NOI18N
        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1MouseMoved(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jToolBar1.setBounds(10, 170, 590, 40);
        jDesktopPane1.add(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        menuBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(desktopapplication1.DesktopApplication1.class).getContext().getActionMap(DesktopApplication1View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1033, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
//JOptionPane.showMessageDialog(aboutBox,"Dark Knight comming soon");
      
   
    
   // response=jTextArea1.getText();
   
   // MainThread mainThread=new MainThread(jTextField3.getText(),jTextArea1.getText());
    //jTextArea1.setText(response);
    
            try {
               if(!jTextField1.getText().equalsIgnoreCase("")){
                       
                jTextArea2.setText("");
                calendar =Calendar.getInstance();
                starttimeMilli = calendar.get(Calendar.MILLISECOND);    
                hour = calendar.get(Calendar.HOUR);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                time1=calendar.getTimeInMillis();
               
               // String response =DesktopApplication1View.goPost(jTextField3.getText(), jTextArea1.getText());
              
                
              /*
                for(int i=0;i<Integer.parseInt(jTextField2.getText());i++)
                {  */  
                    noOfRequests=0;
          
               // new progressBar();    
                
                progressBar.setVisible(true);
                progressBar.setMaximum(Integer.parseInt(jTextField1 .getText()));
                progressBar.setMinimum(0);
  
 
              String request=jTextArea1.getText();
            new postingThread(jTextField3.getText(), request);
               //String response=goPost(jTextField3.getText(), request);
                    //jTextArea2.setText(response);
                
                    //noOfRequests++;
                
                  
               }else{
                   JOptionPane.showMessageDialog(new JDialog(),"Number Of Requests should not be NULL","Use the Force, Luke",0);
                  
               }
            } catch (Exception ex) {
                Logger.getLogger(DesktopApplication1View.class.getName()).log(Level.SEVERE, null, ex);
                    progressBar.setVisible(false);
                    progressBar.setIndeterminate(false);
            }
      
                 
                 // progressBar.setVisible(false);
    
}//GEN-LAST:event_jButton1ActionPerformed

private void jTextArea1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseEntered
// TODO add your handling code here:
    if(jTextArea1.getText().equalsIgnoreCase(""))
    {
    jTextArea1.setText("Request Paste Here");
    }
}//GEN-LAST:event_jTextArea1MouseEntered

private void jTextArea1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseExited
// TODO add your handling code here:
     if(jTextArea1.getText().equalsIgnoreCase("Request Paste Here"))
    {
      jTextArea1.setText("");
    }
}//GEN-LAST:event_jTextArea1MouseExited

private void jTextArea2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea2MouseEntered
// TODO add your handling code here:
    if(jTextArea2.getText().equalsIgnoreCase(""))
    {
    jTextArea2.setText("Response Get Displayed Here");
    }
}//GEN-LAST:event_jTextArea2MouseEntered

private void jTextArea2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea2MouseExited
// TODO add your handling code here:
    if(jTextArea2.getText().equalsIgnoreCase("Response Get Displayed Here"))
    {
    jTextArea2.setText("");
    }
}//GEN-LAST:event_jTextArea2MouseExited

private void jButton1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseMoved
// TODO add your handling code here:
    
}//GEN-LAST:event_jButton1MouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    
    public static String goPost(String urlPath, String reqStr) throws Exception {
		String rspStr = null;
		OutputStreamWriter out = null;
		InputStream in = null;
		try {
                    
			URL url = new URL(urlPath.trim());
			URLConnection connection = url.openConnection();
			// connection.setReadTimeout(60000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			out = new OutputStreamWriter(connection.getOutputStream());
			out.write(reqStr);
			out.flush();
			out.close();

			int i = -1;
			in = connection.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((i = in.read()) != -1) {
				sb.append((char) i);
			}
			rspStr = sb.toString();
			// logger.debug("RspStr->" + rspStr);
			in.close();

		} catch (IOException e) {
                    
                        JOptionPane.showMessageDialog(new JDialog(),"Connection Problem");
                        System.out.println("IOException");
			throw e;

		} catch (Exception e) {
			System.out.println("1");
			throw e;
		} finally {
			if (out != null)
				out.close();
			out = null;
			if (in != null)
				in.close();
			in = null;
		}
		return rspStr;
	}

    
    public class progressBar extends Thread
    {

        public progressBar() {
            this.start();
        }
        
        public void run()
        {
               progressBar.setVisible(true);
               progressBar.setIndeterminate(true);
               
        }
    }
    
    public class postingThread extends Thread
    {
        String url;
        String request;
        postingThread(String url,String request)
        {
            this.request=request;
            this.url=url;
            start();
        }
        public void run()
        {
            try {
                
                  Random randomNumbers = new Random(); 
                Integer r=randomNumbers.nextInt(99999999);
                Long sysdate = System.currentTimeMillis();
               request= request.replaceAll("%SysDate%",sysdate.toString());
               request= request.replaceAll("%Random%",sysdate.toString());
                
                System.out.println("posting thread "+request);
                  System.out.println("noofReq"+noOfRequests);
                  
                  long starttime=System.currentTimeMillis();
                  
                while(noOfRequests<Integer.parseInt(jTextField1 .getText()))
                {
                response=goPost(url,request);
                 System.out.println(request);
                progressBar.setValue(noOfRequests);
                
                 noOfRequests=noOfRequests+1;
                   jTextArea2.setText(response);
            
                 
         System.out.println("testing "+calendar.get(Calendar.MILLISECOND));
    
          Calendar c=Calendar.getInstance();
          long test2=c.getTimeInMillis();
          String time=hour+":"+minute+":"+second+":"+starttimeMilli;
       
     
       long time2=test2-time1;
      if(noOfRequests<100)
      {try{
        jTable1.setValueAt(k,k,0);
        jTable1.setValueAt(time,k,1);
        jTable1.setValueAt("1",k,2);
        jTable1.setValueAt(time2,k,3);  
      }catch(Exception e)
      {
          continue;
      }
      } 
         k++;
       
                   
                }
                
                long endtime=System.currentTimeMillis();
                
                long totaltime=endtime-starttime;
                float sec=totaltime/1000;
                float TPS=noOfRequests/sec;
                
                
                //increment no of request
               
               if(!jTextField1.getText().equalsIgnoreCase("1")){
                    JOptionPane.showMessageDialog(new JDialog(),"Completed!\n"+"TPS="+TPS+"\n"+"StartTime="+starttime+"\nEndTime="+endtime+"\n"+"TotalTimeTaken="+totaltime);
               }
            
                 
            } catch (Exception ex) {
                 progressBar.setVisible(false);
                 progressBar.setIndeterminate(false);
                Logger.getLogger(DesktopApplication1View.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
                    {
                      
                            progressBar.setVisible(false);
                    }
        }
        }
    
}

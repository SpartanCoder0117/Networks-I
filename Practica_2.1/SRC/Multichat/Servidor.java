
package Multichat;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Servidor extends javax.swing.JFrame {
    
    public static String nombre = "Servidor";
    public static int cont = 0;
    public static List<String> listaConectados;
    public static DefaultListModel modelo;
    
    public Servidor() {
        initComponents();
        listaConectados = new ArrayList<>();
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText("");
        setTitle("Servidor");
        modelo = new DefaultListModel();
        listConectados.setModel(modelo);
        //modelo.addElement(name); //Agregue esta linea
        this.jTextField1.setVisible(false); //Hace invisible el textfield
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        listConectados = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTextField1.setBackground(new java.awt.Color(102, 102, 102));
        jTextField1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mensaje: ");

        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Conectados");

        jEditorPane1.setEditable(false);
        jEditorPane1.setBackground(new java.awt.Color(204, 204, 204));
        jEditorPane1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jEditorPane1.setForeground(new java.awt.Color(255, 255, 255));
        jEditorPane1.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jEditorPane1);

        listConectados.setBackground(new java.awt.Color(0, 0, 0));
        listConectados.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        listConectados.setForeground(new java.awt.Color(0, 153, 0));
        jScrollPane4.setViewportView(listConectados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Chat", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        
        /*int code = evt.getKeyCode();
        DatagramPacket p;
        DatagramPacket p1;
        if(code == KeyEvent.VK_ENTER)
        {
            InetAddress gpo = null;
            try
            {
                MulticastSocket s = new MulticastSocket(4000); //se envia el paquete en el puerto 4000
                s.setReuseAddress(true);
                s.setTimeToLive(1);
                String msj = jTextField1.getText();
                byte[] b = msj.getBytes();
                byte[] b1 = nombre.getBytes();
                gpo = InetAddress.getByName("230.1.1.1");
                s.joinGroup(gpo);                               //Se une al grupo
                p1 = new DatagramPacket(b1, b1.length, gpo,4000);
                p = new DatagramPacket(b, b.length, gpo,4000); // se recibbe el paquete en puerto 9999                 
                s.send(p1);
                s.send(p);                                      //se envian paquetes
                jTextField1.setText("");
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }*/

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {
        new Servidor().setVisible(true);
        String cadena = "";
        InetAddress gpo = null;
        DatagramPacket p;
        DatagramPacket p1;
       // DatagramPacket p2;
        //int cantidad = 2;
        //String aux[] = new String[200];
        String aux2 = new String();
        //int i = 0;
        try
        {
            MulticastSocket cl = new MulticastSocket(4000); //se envia el paquete en el puerto 4000
            cl.setReuseAddress(true);
            try
            {
                gpo = InetAddress.getByName("230.1.1.1");
            }catch(UnknownHostException e){
                JOptionPane.showMessageDialog(null, "Direccion no válida");
            }
            cl.joinGroup(gpo);
            //jEditorPane1.setText(name + " se unio a la conversacion");
            for(;;)
            {
                p1 = new DatagramPacket(new byte[1000], 1000);
                cl.receive(p1);        //Recibe el nombre
                String msj1 = new String(p1.getData());
                p = new DatagramPacket(new byte[1000], 1000); 
                cl.receive(p);  //se envian paquetes
                String msj = new String(p.getData());
                msj = msj.replaceAll("jajaja", "<img width=\"32\" height=\"32\" src=\"file:./jajaja.gif\"></img>");
                msj = msj.replaceAll("Adios", "<img width=\"80\" height=\"50\" src=\"file:./bye.gif\"></img>");
                //msj = msj.replaceAll("Hola", "<img width=\"80\" height=\"50\" src=\"file:./hola.gif\"></img>");
                msj = msj.replaceAll(":D", "<img width=\"32\" height=\"32\" src=\"file:./sonreir2.gif\"></img>");
                msj = msj.replaceAll("<3", "<img width=\"80\" height=\"50\" src=\"file:./corazon.gif\"></img>");
                msj = msj.replaceAll(":/", "<img width=\"80\" height=\"50\" src=\"file:./triste.gif\"></img>");
                cadena += msj1 + " : " + msj + "<br/>";
                jEditorPane1.setText(cadena);
                aux2 += msj1;
                Pattern regex = Pattern.compile("\\b" + Pattern.quote(msj1) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher match = regex.matcher(aux2);
                if(!match.find())
                    modelo.addElement(msj1);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JEditorPane jEditorPane1;
    public javax.swing.JList<String> listConectados;
/*    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listConectados;
    // End of variables declaration//GEN-END:variables
*/}

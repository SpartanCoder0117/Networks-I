
package Multichat;

import static Multichat.Servidor.jEditorPane1;
import java.util.List;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;

public class Cliente extends javax.swing.JFrame {

    public static JFrame frame;
    public static String name;
    public static String conectados;
    public static List<String> listaConectados;
    public static DefaultListModel modelo;
    public static String mensaje_privado = "";
    public static int[] puerto_privado;
    
    
    
    public Cliente() throws IOException 
    {
        initComponents();
        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.setText("");
        frame = new JFrame("Frame");
        name = JOptionPane.showInputDialog(frame, "Ingresa tu nombre:");    
        setTitle(name);
        listaConectados = new ArrayList<>();
        modelo = new DefaultListModel();
        jList1.setModel(modelo); 
        //modelo.addElement(name); //Agregue esta linea
        jList1.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                JList list =(JList)evt.getSource();
                if (evt.getClickCount() == 2) 
                {
                    int index = list.locationToIndex(evt.getPoint());
                    mensaje_privado = (String)modelo.getElementAt(index);
                    String texto = JOptionPane.showInputDialog("Escriba su mensaje privado: " + mensaje_privado);
                    if(texto != null)
                    { 
                        texto = "Mensaje privado de " + name + " : " + texto; 
                        DatagramPacket p1;
                        DatagramPacket p;
                        InetAddress gpo = null;
                        try
                        {
                            MulticastSocket cl2 = new MulticastSocket(4000);
                            cl2.setReuseAddress(true);
                            cl2.setTimeToLive(1);
                            byte[] b = texto.getBytes();
                            byte[] b1 = mensaje_privado.getBytes();
                            gpo = InetAddress.getByName("230.1.1.1");
                            cl2.joinGroup(gpo);                           //Se une al grupo
                            p1 = new DatagramPacket(b1, b1.length, gpo, 4000);
                            p = new DatagramPacket(b, b.length, gpo, 4000); //             
                            cl2.send(p1);
                            cl2.send(p);  
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }   
                    } 
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setText("Mensaje: ");

        jLabel2.setText("Conectados");

        jScrollPane4.setViewportView(jEditorPane1);

        jScrollPane5.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chat", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        int code = evt.getKeyCode();
        DatagramPacket p1;
        DatagramPacket p;
        //Graphics gr;
        //String cn;
            
        if(code == KeyEvent.VK_ENTER)
        {
            InetAddress gpo = null;
            try
            {
                MulticastSocket s = new MulticastSocket(4000);
                s.setReuseAddress(true);
                s.setTimeToLive(1);
                String msj = jTextField1.getText();
                byte[] b = msj.getBytes(); //mensaje
                byte[] b1 = name.getBytes(); //nombre
                gpo = InetAddress.getByName("230.1.1.1");
                s.joinGroup(gpo); //Se une al grupo
                
                //jEditorPane1.setText(name + " se unio a la conversacion");
                p1 = new DatagramPacket(b1, b1.length, gpo, 4000); //Envia el nombre
                p = new DatagramPacket(b, b.length, gpo, 4000); // Envia el mensaje            
                s.send(p1);
                s.send(p);  
                jTextField1.setText("");
                if("Adios".equals(msj))
                {
                    //modelo.remove(name);
                    System.exit(0);
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    public static void main(String args[]) throws IOException {
        new Cliente().setVisible(true);
        InetAddress gpo = null;
        String cadena = "";
        //String cadena2 = "";
        DatagramPacket p;
        DatagramPacket p1;
        DatagramPacket p2;
        String[] aux = new String[100];
        String aux2 = new String();
        int i = 0;
        try
        {
            MulticastSocket cl = new MulticastSocket(4000); //se envia el paquete en el puerto 9876
            cl.setReuseAddress(true);
            try
            {
                gpo = InetAddress.getByName("230.1.1.1");
            }catch(UnknownHostException e){
                JOptionPane.showMessageDialog(null, "Direccion no válida");
            }
            cl.joinGroup(gpo);
            for(;;)
            {
                p1 = new DatagramPacket(new byte[1000], 1000);
                cl.receive(p1);
                String msj1 = new String(p1.getData());
                p = new DatagramPacket(new byte[1000], 1000); 
                cl.receive(p);  //se envian paquetes
                String msj = new String(p.getData());
                //Emoticones
                msj = msj.replaceAll("jajaja", "<img width=\"32\" height=\"32\" src=\"file:./jajaja.gif\"></img>");
                msj = msj.replaceAll("Adios", "<img width=\"80\" height=\"50\" src=\"file:./bye.gif\"></img>");
                //msj = msj.replaceAll(":)", "<img width=\"80\" height=\"50\" src=\"file:./sonreir3.gif\"></img>");
                msj = msj.replaceAll(":D", "<img width=\"32\" height=\"32\" src=\"file:./sonreir2.gif\"></img>");
                msj = msj.replaceAll("<3", "<img width=\"80\" height=\"50\" src=\"file:./corazon.gif\"></img>");
                msj = msj.replaceAll(":/", "<img width=\"80\" height=\"50\" src=\"file:./triste.gif\"></img>");
                aux2 += msj1;
                Pattern regex = Pattern.compile("\\b" + Pattern.quote(msj1) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher match = regex.matcher(aux2);
                if(!match.find())
                {
                    if(!msj1.contains(name)) //Esta linea no deja ver al usuario su conexion
                    {
                        aux[i] = msj1;
                        modelo.addElement(aux[i]);
                        i += 1; 
                    }
                    
                }
                Pattern regex1 = Pattern.compile("\\b" + Pattern.quote("Mensaje privado") + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher match2 = regex1.matcher(msj);
                if(match2.find() && !msj1.contains(name))
                {
                    int tamano = cadena.length();
                    cadena += msj1 + "<br/>";
                    cadena = cadena.substring(0, tamano);
                    jEditorPane1.setText(cadena);
                }
                else
                {                   
                    cadena += msj1 + " : " + msj + "<br/>";
                    jEditorPane1.setText(cadena);  
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
   
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea3;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JEditorPane jEditorPane1;
    public static javax.swing.JList<String> jList1;
    
/*
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
*/}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package multicast;
import java.net.*;
import java.io.*;
/**
 *
 * @author alumno
 */
public class CMulticast 
{
    public static void main(String[] args)
    {
        InetAddress gpo = null;
        try
        {
            MulticastSocket cl = new MulticastSocket(9999); //Se define el socket de multicast
            System.out.println("Cliente escuchando puerto " + cl.getLocalPort()); //
            cl.setReuseAddress(true); //Puerto reutilizable
            ////////////////////
            //cl.setTimeToLive(1);
            ////////////////////
            try //Se mete en un try porque algunas direcciones son restringidas esto sirve para validarla
            {
                gpo = InetAddress.getByName("228.1.1.1");                
            }catch(UnknownHostException u) //Envia mensaje de error
            {
                System.err.println("Direccion no valida");
            }
            ////////////////////////////////////
            //String msjS = "Hola Servidor";
            String msjS = "Hola Servidor 2";
            byte[] bs = msjS.getBytes();
            ////////////////////////////////////
            cl.joinGroup(gpo); //Se une al grupo
            System.out.println("Unido al grupo");
            
            for(;;) //Este for es para recibir los mensajes del servidor
            {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                DatagramPacket q = new DatagramPacket(bs,bs.length,gpo,9999); //9876
                cl.send(q); //Se envia 
                System.out.println("Enviando mensaje: " + msjS); //+ " con un TTL: " + cl.getTimeToLive()); //Muestra el TTL
                try
                {
                    Thread.sleep(4000); //Cada 4 segundos se envia el mensaje (Usando hilos)
                }catch(InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////*/
                DatagramPacket p = new DatagramPacket(new byte[1024],1024);
                cl.receive(p); //Se recibe el mensaje
                String msj = new String(p.getData()); //Convertimos los bytes recibidos en string
                System.out.println("Datagrama recibido... " + msj);
                System.out.println("Servidor descubierto: " + p.getAddress() + ":" + p.getPort());
                
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

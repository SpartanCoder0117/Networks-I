/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package multicast;
import java.io.*;
import java.net.*;
/**
 *
 * @author alumno
 */
public class SMulticast 
{
    public static void main(String[] args)
    {
        InetAddress gpo = null; //Inicializar un InetAddress
        try
        {
            MulticastSocket s = new MulticastSocket(9999); //Socket multicast, el numero de puerto es para escuchar 9876
            s.setReuseAddress(true); //Socket reutilizable (varios pueden conectarse)
            s.setTimeToLive(1); //Datagramas solo soportan un salto (TTL)
            
            String msj = "Hola Cliente";
            byte[] b = msj.getBytes(); //Arreglo de byte para contruir el datagrampacket
            
            gpo = InetAddress.getByName("228.1.1.1"); //Canal multicast (ip clase D)
            s.joinGroup(gpo); //Se une al grupo (Canal multicast)
            
            for(;;)
            {
                DatagramPacket p = new DatagramPacket(b,b.length,gpo,9999); //El numero de puerto para enviar los archivos
                s.send(p); //Se envia 
                System.out.println("Enviando mensaje: " + msj + " con un TTL: " + s.getTimeToLive()); //Muestra el TTL
                try
                {
                    Thread.sleep(3000); //Cada 3 segundos se envia el mensaje (Usando hilos)
                }catch(InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                DatagramPacket q = new DatagramPacket(new byte[1024],1024);
                s.receive(q);
                String msj2 = new String(q.getData());
                System.out.println("Datagrama recibido... " + msj2);
            }
              /*
                DatagramPacket q = new DatagramPacket(new byte[1000],1000);
                s.receive(q); //Se recibe el mensaje
                String msj2 = new String(p.getData()); //Convertimos los bytes recibidos en string
                System.out.println("Datagrama recibido... " + msj2);
                System.out.println("Servidor descubierto: " + q.getAddress() + ":" + q.getPort());
                */
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

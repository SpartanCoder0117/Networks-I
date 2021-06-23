import java.net.*;
import java.io.*;
public class ServidorUDP 
{
    public static void main(String[] args) 
    {
        try
        {
            //Se crea el socket con el puerto
            DatagramSocket s = new DatagramSocket(2000);
            //Mensaje de espera
            System.out.println("Servidor iniciado,esperando cliente...");
            
            //Ciclo infinito para que el servidor no cierre
            for(;;)
            {
                String auxiliar = null;
                
                //Bandera para saber cuantas subcadenas hay
                DatagramPacket p1 = new DatagramPacket(new byte[3],3);
                s.receive(p1);
                String mensaje = new String(p1.getData(),0,p1.getLength());
                int cantidad = Integer.parseInt(mensaje);
                //Para evitar errores en la lectura de subcadenas
                if(cantidad > 0)
                    cantidad -= 1;
                                             
                //Ciclo para concatenacion de subcadenas
                for(int i = 0; i < cantidad + 1; i++) 
                {
                    DatagramPacket p2 = new DatagramPacket(new byte[20],20); 
                    s.receive(p2);
                    auxiliar = new String(p2.getData(),0,p2.getLength());
                    mensaje += auxiliar;
                    auxiliar = "";
                }
                
                DatagramPacket p3 = new DatagramPacket(new byte[3],3);
                s.receive(p3);                
                String mensaje2 = new String(p3.getData(),0,p3.getLength());                
                int resultado = Integer.parseInt(mensaje2);
                
                //Residuo por si falta una cadena
                if(resultado > 0)
                {
                    DatagramPacket p4 = new DatagramPacket(new byte[resultado],resultado); //6,6
                    s.receive(p4);
                    String mensaje3 = new String(p4.getData(),0,p4.getLength());
                    mensaje += mensaje3;
                    mensaje3 = "";
                }
                //--------------------------------------------------------------------------------------------
                //Este fragmento de codigo reenvia el mensaje al cliente
                String dst2 = "127.0.0.1";
                int port2 = 2001;
                //String cantidad2;
                //long tam;
                //int residuo1;
                //DatagramSocket cl = new DatagramSocket();
                byte[] v = mensaje.getBytes();
                int longitud2 = v.length;
                DatagramPacket c = new DatagramPacket(v,longitud2,InetAddress.getByName(dst2),port2);
                s.send(c);
                /*if(longitud2 <= 20)
                //{
                    
                    cantidad2 = "0";
                    byte[] tam = cantidad2.getBytes();
                    DatagramPacket p8 = new DatagramPacket(tam,tam.length,InetAddress.getByName(dst2),port2);
                    cl.send(p8);
                    DatagramPacket p = new DatagramPacket(v,v.length,InetAddress.getByName(dst2),port2);
                    cl.send(p);
                    residuo1 = 0;
                    String mensaje6 = Integer.toString(residuo1);
                    byte[] n = mensaje2.getBytes();
                    DatagramPacket p2 = new DatagramPacket(n,n.length,InetAddress.getByName(dst2),port2);
                    cl.send(p2);
                }*/
                
                //--------------------------------------------------------------------------------------------
                //Imprime el string
                System.out.println("El cliente envio el siguiente mensaje:\t" + mensaje);
                mensaje = "";
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
}

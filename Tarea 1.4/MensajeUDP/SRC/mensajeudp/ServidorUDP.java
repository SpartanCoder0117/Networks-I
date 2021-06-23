import java.net.*;
import java.io.*;
public class ServidorUDP 
{
    public static void main(String[] args) 
    {
        try
        {
            //Se crea el socket con el puerto
            DatagramSocket s = new DatagramSocket(3000);
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
                byte[] v = mensaje.getBytes();
                String dst2 = "127.0.0.1";
                int port2 = 2001;
                DatagramPacket c = new DatagramPacket(v,v.length,InetAddress.getByName(dst2),port2);
                s.send(c);
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

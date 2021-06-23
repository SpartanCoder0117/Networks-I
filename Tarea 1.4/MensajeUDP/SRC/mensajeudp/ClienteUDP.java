
import java.net.*;
import java.io.*;
public class ClienteUDP 
{
    public static void main(String[] args) 
    {
        try
        {
            //Variables
            String cantidad;
            String texto;
            int inicio = 0;
            int fin = 20;
            int residuo;
            int division;
            
            //Direccion del servidor
            String dist = "127.0.0.1";
            //Puerto del servidor
            int port = 2000;
            //Se crea el socket de datagrama
            DatagramSocket cl = new DatagramSocket();
            //Mensaje a pantalla
            System.out.println("Cliente iniciado. Escriba mensaje: ");
            //Esperando entrada de teclado
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Se lee el mensaje ingresado
            String mensaje = br.readLine();
          
            
            //Obtienes bytes del mensaje
            byte[] b = mensaje.getBytes();
            //Se obtiene la longitud de la cadena
            int longitud  = b.length;
            
            if(longitud <= 20)
            {
                cantidad = "0";
                byte[] tam = cantidad.getBytes();
                DatagramPacket pl = new DatagramPacket(tam,tam.length,InetAddress.getByName(dist),port);
                cl.send(pl);
                DatagramPacket p = new DatagramPacket(b,b.length,InetAddress.getByName(dist),port);
                cl.send(p);
                residuo = 0;
                String mensaje2 = Integer.toString(residuo);
                byte[] n = mensaje2.getBytes();
                DatagramPacket p2 = new DatagramPacket(n,n.length,InetAddress.getByName(dist),port);
                cl.send(p2);
            }else 
            {
                division = b.length / 20;
                residuo = b.length - (division * 20);
                String mensaje2  = Integer.toString(residuo);
                
                cantidad = Integer.toString(division);
                byte[] tam = cantidad.getBytes();
                DatagramPacket pl = new DatagramPacket(tam,tam.length,InetAddress.getByName(dist),port);
                cl.send(pl);
                
                //Envio consecutivo del mensaje al servidor
                for(int i = 0; i < division; i++)
                {
                    texto = mensaje.substring(inicio,fin);
                    byte[] c = texto.getBytes();
                    DatagramPacket p = new DatagramPacket(c,c.length,InetAddress.getByName(dist),port);
                    cl.send(p);
                    inicio = fin; 
                    fin += 20; 
                }
                //Tamaño del  residuo 
                byte[] n = mensaje2.getBytes();
                DatagramPacket p3 = new DatagramPacket(n,n.length,InetAddress.getByName(dist),port);
                cl.send(p3);
                
                //Si queda un residuo del mensaje tambien se envia
                if(residuo > 0)
                {
                    texto = mensaje.substring(longitud - residuo, longitud);
                    byte[] a = texto.getBytes();
                    DatagramPacket p4 = new DatagramPacket(a,a.length,InetAddress.getByName(dist),port);
                    cl.send(p4);
                }
            }
            //Se cierra el socket
            cl.close();
            //-------------------------------------------------------------------------------------------
            //Este fragmento de codigo recibe el mensaje del servidor
            DatagramSocket s2 = new DatagramSocket(3001);
            DatagramPacket r = new DatagramPacket(new byte[2000],2000);
            s2.receive(r);
            String mensa = new String(r.getData(),0,r.getLength());
            System.out.println("Mensaje recibido del servidor: " + mensa);
            //-------------------------------------------------------------------------------------------
        }catch(Exception e)
        {
            e.printStackTrace();
        }            
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;
import java.net.*;
import java.io.*;
/**
 *
 * @author alumno
 */
public class Servidor 
{
    public static void main(String[] args)
    {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try
        {
            ServerSocket s = new ServerSocket(9999);
            System.out.println("Servidor iniciado...");
            for(;;)
            {
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde: " + cl.getInetAddress() + cl.getPort());
                
                oos = new ObjectOutputStream(cl.getOutputStream());
                ois = new ObjectInputStream(cl.getInputStream());
                
                Usuario u = (Usuario)ois.readObject();
                
                System.out.println("Objeto recibido... Extrayendo informacion");
                System.out.println("Nombre: " + u.getNombre());
                System.out.println("Apellido paterno: " + u.getApaterno());
                System.out.println("Apallido materno: " + u.getAmaterno());
                System.out.println("Password: " + u.getPwd());
                System.out.println("Edad: " + u.getEdad());
                System.out.println("Devolviendo objeto...");
                
                oos.writeObject(u);
                oos.flush();                
            }            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

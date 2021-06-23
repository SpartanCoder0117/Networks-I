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
public class Cliente 
{
    public static void main(String[] args)
    {
        String host = "127.0.0.1";
        int pto = 9999;
        
        //Flujos para manejo de objectos de entrada y salida
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try
        {
            Socket cl = new Socket(host,pto);
            System.out.println("Conexion establecida...");
            //Flujos de entrada y salida
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            
            Usuario u = new Usuario("Pepito","Perez","Juarez","1234",23);
            System.out.println("Enviado objeto");
            
            oos.writeObject(u);
            oos.flush();
            
            System.out.println("Preparando para recibir objeto...");
            //Lectura de objeto recibido
            Usuario u2 = (Usuario)ois.readObject();
            
            System.out.println("Nombre: " + u2.getNombre());
            System.out.println("Apellido paterno: " + u2.getApaterno());
            System.out.println("Apellido materno: " + u2.getAmaterno());
            System.out.println("Password: " + u2.getPwd());
            System.out.println("Edad: " + u2.getEdad());
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}

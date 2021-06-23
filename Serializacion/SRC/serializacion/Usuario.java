/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;
import java.io.Serializable;
/**
 *
 * @author alumno
 */
public class Usuario implements Serializable
{
    String nombre;
    String apaterno;
    String amaterno;
    
    transient String pwd; //No se envia el valor
    int edad;
    
    public Usuario(String nombre, String apaterno, String amaterno, String pwd, int edad)
    {
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.pwd = pwd;
        this.edad = edad;          
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getApaterno()
    {
        return apaterno;
    }
    
    public String getAmaterno()
    {
        return amaterno;
    }
    
    public String getPwd()
    {
        return pwd;
    }
    
    public int getEdad()
    {
        return edad;
    }
            
    
}

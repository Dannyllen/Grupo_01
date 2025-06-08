/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.grupo_01.Modelo;

//imports para la clase
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;



/**
 *
 * @author Grupo 1
 */
public class Usuario implements Serializable {
    private String nomUsuario;
    private String contraseña;
    private String tipoDeUsuario;
    private LinkedListDobleCircular<Contacto> contactos;
    
    
    //Constructor de la clase
    public Usuario(String nombreUsuario, String contraseña, String tipoUsuario) {
        this.nomUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoDeUsuario = tipoUsuario;
        this.contactos = new LinkedListDobleCircular<>();
    }
    
    
    //Getters
    public String getNomUsuario() {
        return nomUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public LinkedListDobleCircular<Contacto> getContactos() {
        return contactos;
    }
    
    
    //Setters
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public void setContactos(LinkedListDobleCircular<Contacto> contactos) {
        this.contactos = contactos;
    }

    
    //Metodo que serializa una arraylist de tipo usuario en un archivo ser
    public static void saveListToFileSerUsuarios(LinkedListDobleCircular<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Usuarios.ser"))) {
            oos.writeObject(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //Metodo que deserializa el archivo ser 
    public static LinkedListDobleCircular<Usuario> readListFromFileSerUsuarios() {
        LinkedListDobleCircular<Usuario> usuarios = new LinkedListDobleCircular<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Usuarios.ser"))) {
            usuarios = (LinkedListDobleCircular<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    
    /* Genera un código que representa este objeto, 
    para que se pueda buscar y comparar fácilmente en listas o conjuntos.
     */
    @Override  
    public int hashCode() {
        return Objects.hash(nomUsuario, contraseña);
    }
    
    
    //Metodo que compara dos objetos para ver si son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nomUsuario, other.nomUsuario)) {
            return false;
        }
        return Objects.equals(this.contraseña, other.contraseña);
    }
    
    
    //formato para mostrar los valores, con el toString
    @Override
    public String toString() {
        return "User{" + "nombreUsuario='" + nomUsuario + '\'' + ", contraseña='" + contraseña + '\'' + ", tipoUsuario='" + tipoDeUsuario + '\'' + '}';
    }
}

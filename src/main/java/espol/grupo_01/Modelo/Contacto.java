package espol.grupo_01.Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.image.Image;

public abstract class Contacto implements Serializable {
    private String nombre;
    private LinkedListSimpleCircular<String> direcciones;
    private LinkedListSimpleCircular<String> emails;
    private LinkedListSimpleCircular<String> numTelefonicos;
    private LinkedListSimpleCircular<String> identRedesSociales;
    private LinkedListDobleCircular<Image> rutasFotos; 
    private LinkedListSimpleCircular<String> fechasInteres;

    public Contacto(String nombre, 
            LinkedListSimpleCircular<String>direcciones,
            LinkedListSimpleCircular<String> emails,
            LinkedListSimpleCircular<String> numTelefonicos,
            LinkedListSimpleCircular<String> identRedesSociales,
            LinkedListDobleCircular<Image> rutasFotos,
            LinkedListSimpleCircular<String> fechasInteres){
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.numTelefonicos = numTelefonicos;
        this.identRedesSociales = identRedesSociales;
        this.rutasFotos = rutasFotos;
        this.fechasInteres = fechasInteres;
    }
    //Getters de los atibutos
    public String getNombre() {
        return nombre;
    }

    public LinkedListSimpleCircular<String> getDirecciones() {
        return direcciones;
    }
    
    public abstract String getTipoDeContacto();

    public LinkedListSimpleCircular<String> getEmails() {
        return emails;
    }

    public LinkedListSimpleCircular<String> getNumTelefonicos() {
        return numTelefonicos;
    }

    public LinkedListSimpleCircular<String> getIdentRedesSociales() {
        return identRedesSociales;
    }

    public LinkedListDobleCircular<Image> getFotos() {
        return rutasFotos;
    }

    public LinkedListSimpleCircular<String> getFechasInteres() {
        return fechasInteres;
    }
    //Setters
    public void setNombre(String nombre) { 
        this.nombre = nombre;
    }
    public void setDirecciones(LinkedListSimpleCircular<String> direcciones) { 
        this.direcciones = direcciones;
    }
    public void setEmails(LinkedListSimpleCircular<String> emails) {
        this.emails = emails; 
    }
    public void setNumTelefonicos(LinkedListSimpleCircular<String> numeros) {
        this.numTelefonicos = numeros;
    }
    public void setIdentRedesSociales(LinkedListSimpleCircular<String> redes) {
        this.identRedesSociales = redes;
    }
    public void setFechasInteres(LinkedListSimpleCircular<String> fechas) { 
        this.fechasInteres = fechas;
    }
    public void setFotos(LinkedListDobleCircular<Image> rutasFotos) {
        this.rutasFotos = rutasFotos;
    }
    
    //Metodo que guarda la lista de contactos en archivo ser
   /* public static void saveListToFileSerContactos(LinkedListDobleCircular<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {e.printStackTrace();}
    }
    
    //Metodo que recupera la lista de contactos desde el archivo ser
    public static LinkedListDobleCircular<Contacto> readListFromFileSerContactos() {
        LinkedListDobleCircular<Contacto> contactos = new LinkedListDobleCircular<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/Contactos.ser"))) {
            contactos = (LinkedListDobleCircular<Contacto>) ois.readObject();
        } catch (IOException e) { e.printStackTrace();}
        catch (ClassNotFoundException e) {e.printStackTrace(); } 
        catch (Exception a) {a.printStackTrace();}
        return contactos;
    }
    */
}

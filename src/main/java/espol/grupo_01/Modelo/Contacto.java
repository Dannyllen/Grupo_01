/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.grupo_01.Modelo;

//Imports para la clase
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
/**
 *
// * @author Grupo 1
 */
public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private String tipoDeContacto;
    private LinkedListDobleCircular<String> direcciones;
    private LinkedListDobleCircular<String> emails;
    private LinkedListDobleCircular<String> numTelefonicos;
    private LinkedListDobleCircular<String> identRedesSociales;
    private LinkedListDobleCircular<Image> fotos;
    private LinkedListDobleCircular<String> fechasInteres;

    
    //Getters de los atibutos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoDeContacto() {
        return tipoDeContacto;
    }

    public LinkedListDobleCircular<String> getDirecciones() {
        return direcciones;
    }

    public LinkedListDobleCircular<String> getEmails() {
        return emails;
    }

    public LinkedListDobleCircular<String> getNumTelefonicos() {
        return numTelefonicos;
    }

    public LinkedListDobleCircular<String> getIdentRedesSociales() {
        return identRedesSociales;
    }

    public LinkedListDobleCircular<Image> getFotos() {
        return fotos;
    }

    public LinkedListDobleCircular<String> getFechasInteres() {
        return fechasInteres;
    }
    
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTipoDeContacto(String tipoDeContacto) {
        this.tipoDeContacto = tipoDeContacto;
    }

    public void setDirecciones(LinkedListDobleCircular<String> direcciones) {
        this.direcciones = direcciones;
    }
    
    
    //Metodos para agregar nuevos valores
    public void addEmail(String email) {
        this.emails.add(email);
    }
    
    public void addNumeroTelefono(String numeroTelefono) {
        this.numTelefonicos.add(numeroTelefono);
    }
    
    public void addIdentificadorRedesSociales(String identificadorRedesSociales) {
        this.identRedesSociales.add(identificadorRedesSociales);
    }
    
    public void addFoto(Image foto){
        this.fotos.add(foto);
    }
    
    public void addFechaInter(String fechaInteres) {
        this.fechasInteres.add(fechaInteres);
    }
    
    
    //Metodos para eliminar datos
    public void removeEmail(String email) {
        this.emails.remove(email);
    }

    public void removeNumeroTelefono(String numeroTelefono) {
        this.numTelefonicos.remove(numeroTelefono);
    }
    
    public void removeIdentificadorRedesSociales(String identificadorRedesSociales) {
        this.identRedesSociales.remove(identificadorRedesSociales);
    }

    public void removeFoto(Image foto) {
        this.fotos.remove(foto);
    }
    
    
    //Metodo que guarda la lista de contactos en archivo ser, serializandolo y atrapa posibles errores con el try y catch
    public static void saveListToFileSerContactos(LinkedListDobleCircular<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {}
    }
    
    //Metodo que recupera la lista de contactos desde el archivo ser, lo deserializa y lo devuelve a arraylist
    public static LinkedListDobleCircular<Contacto> readListFromFileSerContactos() {
        LinkedListDobleCircular<Contacto> contactos = new LinkedListDobleCircular<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            contactos = (LinkedListDobleCircular<Contacto>) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } catch (Exception a) {
        }
        return contactos;
    }
    
    
    //formato para mostrar los valores, con el toString
    @Override
    public String toString() {
        return "Contact{"
                + "nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", numeroTelefono='" + numTelefonicos + '\''
                + ", email='" + emails + '\''
                + ", direccion='" + direcciones + '\''
                + ", tipoContacto='" + tipoDeContacto + '\''
                + '}';
    }
}

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
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;
/**
 *
// * @author Grupo 1
 */
public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private String tipoDeContacto;
    private ArrayList<String> direcciones;
    private ArrayList<String> emails;
    private ArrayList<String> numTelefonicos;
    private ArrayList<String> identRedesSociales;
    private LinkedList<Image> fotos;
    private ArrayList<String> fechasInteres;

    
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

    public ArrayList<String> getDirecciones() {
        return direcciones;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public ArrayList<String> getNumTelefonicos() {
        return numTelefonicos;
    }

    public ArrayList<String> getIdentRedesSociales() {
        return identRedesSociales;
    }

    public LinkedList<Image> getFotos() {
        return fotos;
    }

    public ArrayList<String> getFechasInteres() {
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

    public void setDirecciones(ArrayList<String> direcciones) {
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
    public static void saveListToFileSerContactos(LinkedList<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {}
    }
    
    //Metodo que recupera la lista de contactos desde el archivo ser, lo deserializa y lo devuelve a arraylist
    public static LinkedList<Contacto> readListFromFileSerContactos() {
        LinkedList<Contacto> contactos = new LinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            contactos = (LinkedList<Contacto>) ois.readObject();
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

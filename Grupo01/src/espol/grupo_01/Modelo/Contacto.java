package espol.grupo_01.Modelo;

import java.io.Serializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import java.io.File;

public abstract class Contacto implements Serializable {
    private static final long serialVersionUID = 01L;

    protected String nombre;
    protected String apellido;
    private LinkedListSimpleCircular<Dato> direcciones;
    private LinkedListSimpleCircular<Dato> emails;
    private LinkedListSimpleCircular<Dato> numTelefonicos;
    private LinkedListSimpleCircular<Dato> identRedesSociales;
    private LinkedListDobleCircular<String> rutasFotos; 
    private LinkedListSimpleCircular<Dato> fechasInteres;

    public Contacto(String nombre,String apellido, 
            LinkedListSimpleCircular<Dato>direcciones,
            LinkedListSimpleCircular<Dato> emails,
            LinkedListSimpleCircular<Dato> numTelefonicos,
            LinkedListSimpleCircular<Dato> identRedesSociales,
            LinkedListDobleCircular<String> rutasFotos,
            LinkedListSimpleCircular<Dato> fechasInteres){
        this.nombre = nombre;
        this.apellido=apellido;
        this.direcciones = direcciones;
        this.emails = emails;
        this.numTelefonicos = numTelefonicos;
        this.identRedesSociales = identRedesSociales;
        this.rutasFotos = rutasFotos;
        this.fechasInteres = fechasInteres;
    }
    
    public String getNombre() { return nombre; }
    
    public String getApellido() { return apellido;}
    
    public LinkedListSimpleCircular<Dato> getDirecciones() { return direcciones; }
    
    public LinkedListSimpleCircular<Dato> getEmails() { return emails; }
    
    public LinkedListSimpleCircular<Dato> getNumTelefonicos() { return numTelefonicos;}
    
    public LinkedListSimpleCircular<Dato> getIdentRedesSociales() { return identRedesSociales;}

    public LinkedListDobleCircular<Image> getFotos() {
        LinkedListDobleCircular<Image> listaImagenes = new LinkedListDobleCircular<>();

        for (String rutaRelativa : rutasFotos) {
            try {
                File archivo = new File(rutaRelativa);
                if (archivo.exists()) {
                    Image img = new Image(archivo.toURI().toString());
                    listaImagenes.add(img);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaImagenes;
    }

    public LinkedListDobleCircular<String> getFotosRutas(){ return rutasFotos;}
    
    public LinkedListSimpleCircular<Dato> getFechasInteres() { return fechasInteres; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public void setApellido(String apellido) {this.apellido=apellido;}
    
    public void setDirecciones(LinkedListSimpleCircular<Dato> direcciones) { this.direcciones = direcciones;}
    
    public void setEmails(LinkedListSimpleCircular<Dato> emails) { this.emails = emails; }
    
    public void setNumTelefonicos(LinkedListSimpleCircular<Dato> numeros) { this.numTelefonicos = numeros;}
    
    public void setIdentRedesSociales(LinkedListSimpleCircular<Dato> redes) {this.identRedesSociales = redes;}
    
    public void setFechasInteres(LinkedListSimpleCircular<Dato> fechas) { this.fechasInteres = fechas;}
    
    public void setFotos(LinkedListDobleCircular<String> rutasFotos) {this.rutasFotos = rutasFotos;}
    
    public abstract String getTipoDeContacto();
    
    public abstract FXMLLoader editar();
    
}
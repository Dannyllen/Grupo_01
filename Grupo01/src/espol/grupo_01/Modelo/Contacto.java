package espol.grupo_01.Modelo;

import java.io.Serializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import java.io.File;

public abstract class Contacto implements Serializable {
    private static final long serialVersionUID = 01L;

    protected String nombre;
    protected String pais;
    private LinkedListSimpleCircular<Dato> direcciones;
    private LinkedListSimpleCircular<Dato> emails;
    private LinkedListSimpleCircular<Dato> numTelefonicos;
    private LinkedListSimpleCircular<Dato> identRedesSociales;
    private LinkedListDobleCircular<String> rutasFotos; 
    private LinkedListSimpleCircular<Dato> fechasInteres;
    private LinkedListSimpleCircular<Contacto> contactosAsociados;
    private transient LinkedListDobleCircular<Image> imagenesCache = null;

    public Contacto(String nombre, String pais,
            LinkedListSimpleCircular<Dato>direcciones,
            LinkedListSimpleCircular<Dato> emails,
            LinkedListSimpleCircular<Dato> numTelefonicos,
            LinkedListSimpleCircular<Dato> identRedesSociales,
            LinkedListDobleCircular<String> rutasFotos,
            LinkedListSimpleCircular<Dato> fechasInteres){
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.numTelefonicos = numTelefonicos;
        this.identRedesSociales = identRedesSociales;
        this.rutasFotos = rutasFotos;
        this.fechasInteres = fechasInteres;
        this.pais = pais;
    }
    
    public String getNombre() { return nombre; }
        
    public LinkedListSimpleCircular<Dato> getDirecciones() { return direcciones; }
    
    public LinkedListSimpleCircular<Dato> getEmails() { return emails; }
    
    public LinkedListSimpleCircular<Dato> getNumTelefonicos() { return numTelefonicos;}
    
    public LinkedListSimpleCircular<Dato> getIdentRedesSociales() { return identRedesSociales;}

    public LinkedListDobleCircular<Image> getFotos() {
        if (imagenesCache != null) {
            return imagenesCache;
        }

        imagenesCache = new LinkedListDobleCircular<>();

        for (String rutaRelativa : rutasFotos) {
            try {
                File archivo = new File(rutaRelativa);
                if (archivo.exists()) {
                    Image img = new Image(archivo.toURI().toString());
                    imagenesCache.add(img);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imagenesCache;
    }

    public LinkedListDobleCircular<String> getFotosRutas(){ return rutasFotos;}
    
    public LinkedListSimpleCircular<Dato> getFechasInteres() { return fechasInteres; }

    //metodo que retorna la lista de contactos relacionados con el contacto actual
    public LinkedListSimpleCircular<Contacto> getContactosAsociados() { return contactosAsociados; }
    
    
    //metodo para asociar otro contacto al actual
    public void agregarContactoAsociado(Contacto contacto) {
        if (contacto != null && !this.contactosAsociados.contains(contacto)) {
        contactosAsociados.add(contacto);
        }
    }
    
    //Metodo que imprime en consola los nombres asociados
    public void mostrarContactosAsociados() {
    if (contactosAsociados.isEmpty()) {
        System.out.println("No hay contactos asociados para " + this.nombre);
    } else {
        System.out.println("Contactos asociados a " + this.nombre + ":");
        for (Contacto c : contactosAsociados) {
            System.out.println("- " + c.getNombre());
        }
    }
}
    
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pais){
        this.pais = pais;
    }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
        
    public void setDirecciones(LinkedListSimpleCircular<Dato> direcciones) { this.direcciones = direcciones;}
    
    public void setEmails(LinkedListSimpleCircular<Dato> emails) { this.emails = emails; }
    
    public void setNumTelefonicos(LinkedListSimpleCircular<Dato> numeros) { this.numTelefonicos = numeros;}
    
    public void setIdentRedesSociales(LinkedListSimpleCircular<Dato> redes) {this.identRedesSociales = redes;}
    
    public void setFechasInteres(LinkedListSimpleCircular<Dato> fechas) { this.fechasInteres = fechas;}
    
    public void setFotos(LinkedListDobleCircular<String> rutasFotos) {this.rutasFotos = rutasFotos;}
    
    public abstract String getTipoDeContacto();
    
    public abstract FXMLLoader editar();
    
}
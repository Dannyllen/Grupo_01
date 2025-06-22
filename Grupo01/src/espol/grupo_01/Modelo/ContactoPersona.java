package espol.grupo_01.Modelo;
import javafx.fxml.FXMLLoader;
import java.io.Serializable;

public class ContactoPersona extends Contacto implements Serializable {
    private static final long serialVersionUID = 01L;
    private String apellido;

    public ContactoPersona(String nombre, String apellido,
                           LinkedListSimpleCircular<Dato> direcciones,
                           LinkedListSimpleCircular<Dato> emails,
                           LinkedListSimpleCircular<Dato> numTelefonicos,
                           LinkedListSimpleCircular<Dato> identRedesSociales,
                           LinkedListDobleCircular<String> rutasFotos,
                           LinkedListSimpleCircular<Dato> fechasInteres) {
        super(nombre, direcciones, emails, numTelefonicos, identRedesSociales, rutasFotos, fechasInteres);
        this.apellido = apellido;
    }

    @Override
    public String getTipoDeContacto() { return "Persona";}

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido;}
    
    @Override
    public FXMLLoader editar(){
        return new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/AgregarPersonaController.fxml"));
    }
    
    @Override
    public String toString(){
        return nombre + " " + apellido;
    }
}


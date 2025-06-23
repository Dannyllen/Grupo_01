package espol.grupo_01.Modelo;
import javafx.fxml.FXMLLoader;
import java.io.Serializable;

public class ContactoEmpresa extends Contacto implements Serializable {
    
    private static final long serialVersionUID = 01L;
    

    public ContactoEmpresa(String nombre,String pais,
                           LinkedListSimpleCircular<Dato> direcciones,
                           LinkedListSimpleCircular<Dato> emails,
                           LinkedListSimpleCircular<Dato> numTelefonicos,
                           LinkedListSimpleCircular<Dato> identRedesSociales,
                           LinkedListDobleCircular<String> rutasFotos,
                           LinkedListSimpleCircular<Dato> fechasInteres) {
        super(nombre, pais,direcciones, emails, numTelefonicos, identRedesSociales, rutasFotos, fechasInteres);

    }

    @Override
    
    public String getTipoDeContacto() { return "Empresa"; }
        
    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public FXMLLoader editar(){
        return new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/AgregarEmpresaController.fxml"));
    }
}


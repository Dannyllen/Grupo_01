package espol.grupo_01.Modelo;
import javafx.fxml.FXMLLoader;
import java.io.Serializable;

public class ContactoEmpresa extends Contacto implements Serializable {
    
    private static final long serialVersionUID = 01L;
    
    private String razonSocial;
    private String ruc;

    public ContactoEmpresa(String nombre, String razonSocial, String ruc,
                           LinkedListSimpleCircular<Dato> direcciones,
                           LinkedListSimpleCircular<Dato> emails,
                           LinkedListSimpleCircular<Dato> numTelefonicos,
                           LinkedListSimpleCircular<Dato> identRedesSociales,
                           LinkedListDobleCircular<String> rutasFotos,
                           LinkedListSimpleCircular<Dato> fechasInteres) {
        super(nombre, direcciones, emails, numTelefonicos, identRedesSociales, rutasFotos, fechasInteres);
        this.razonSocial = razonSocial;
        this.ruc = ruc;
    }

    @Override
    public String getTipoDeContacto() { return "Empresa"; }

    public String getRazonSocial() { return razonSocial; }

    public String getRuc() { return ruc; }

    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public void setRuc(String ruc) { this.ruc = ruc; }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public FXMLLoader editar(){
        return new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/AgregarEmpresaController.fxml"));
    }
}


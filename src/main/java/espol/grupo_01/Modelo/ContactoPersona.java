package espol.grupo_01.Modelo;
import javafx.scene.image.Image;

public class ContactoPersona extends Contacto {
    private String razonSocial;
    private String ruc;

    public ContactoPersona(String nombre, String razonSocial, String ruc,
                           LinkedListSimpleCircular<String> direcciones,
                           LinkedListSimpleCircular<String> emails,
                           LinkedListSimpleCircular<String> numTelefonicos,
                           LinkedListSimpleCircular<String> identRedesSociales,
                           LinkedListDobleCircular<Image> rutasFotos,
                           LinkedListSimpleCircular<String> fechasInteres) {
        super(nombre, direcciones, emails, numTelefonicos, identRedesSociales, rutasFotos, fechasInteres);
        this.razonSocial = razonSocial;
        this.ruc = ruc;
    }

    @Override
    public String getTipoDeContacto() {
        return "Empresa";
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}


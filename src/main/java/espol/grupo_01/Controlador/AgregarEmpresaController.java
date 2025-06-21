package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.ContactoEmpresa;
import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.ContactoManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;


public class AgregarEmpresaController extends AgregarBaseController {

    // Representa el contacto actual (nuevo o a editar)
    private ContactoEmpresa empresa;

    @FXML private TextField razonSocial;
    @FXML private TextField ruc;

    /*
     * Se ejecuta al guardar un contacto.
     * Valida los campos obligatorios y guarda o actualiza el objeto ContactoEmpresa.
     */
    @Override
    @FXML
    public void guardarContacto() {
        if (!validarCamposObligatorios()) return;

        // Si es un nuevo contacto
        if (empresa == null) {
            empresa = new ContactoEmpresa(
                nombre.getText().trim(),
                razonSocial.getText().trim(),
                ruc.getText().trim(),
                convertirLista(listaDirecciones),
                convertirLista(listaEmails),
                convertirLista(listaTelefonos),
                convertirLista(listaRedesSociales),
                imagenesSeleccionadas,
                convertirLista(listaFechasInteres)
            );
            ContactoManager.agregarContacto(empresa);
        } else {
            // Si se está editando un contacto existente
            empresa.setNombre(nombre.getText().trim());
            empresa.setRazonSocial(razonSocial.getText().trim());
            empresa.setRuc(ruc.getText().trim());
            empresa.setDirecciones(convertirLista(listaDirecciones));
            empresa.setEmails(convertirLista(listaEmails));
            empresa.setNumTelefonicos(convertirLista(listaTelefonos));
            empresa.setIdentRedesSociales(convertirLista(listaRedesSociales));
            empresa.setFechasInteres(convertirLista(listaFechasInteres));
            empresa.setFotos(imagenesSeleccionadas);
        }

        mostrarAlerta("✅ Contacto guardado exitosamente.");
        cambiarEscenaAlListado(); // Regresa a la vista de listado
    }

    /*
     * Establece los datos en los campos del formulario para editar un contacto existente.
     * Este método es llamado cuando se activa el modo edición.
     */
    @Override
    public void setModo(Contacto contacto) {
        this.contacto = contacto;
        this.empresa = (ContactoEmpresa) contacto;

        // Carga los datos en los campos del formulario
        nombre.setText(empresa.getNombre());
        razonSocial.setText(empresa.getRazonSocial());
        ruc.setText(empresa.getRuc());

        // Carga listas de información (direcciones, emails, etc.)
        listaDirecciones.getItems().clear();
        for (String d : empresa.getDirecciones()) {
            listaDirecciones.getItems().add(d);
        }

        listaEmails.getItems().clear();
        for (String e : empresa.getEmails()) {
            listaEmails.getItems().add(e);
        }

        listaTelefonos.getItems().clear();
        for (String t : empresa.getNumTelefonicos()) {
            listaTelefonos.getItems().add(t);
        }

        listaRedesSociales.getItems().clear();
        for (String r : empresa.getIdentRedesSociales()) {
            listaRedesSociales.getItems().add(r);
        }

        listaFechasInteres.getItems().clear();
        for (String f : empresa.getFechasInteres()) {
            listaFechasInteres.getItems().add(f);
        }

        listaFotos.getItems().clear();
        imagenesSeleccionadas.clear();
        for (Image img : empresa.getFotos()) {
            listaFotos.getItems().add("Imagen cargada");
            imagenesSeleccionadas.add(img);
        }
    }

    /*
     * Método que elimina el contacto actual
     */
    @FXML
    private void eliminarContacto() {
        boolean aux = ContactoManager.eliminarContacto(empresa);
        if (aux) mostrarAlerta("❌ Contacto Eliminado");
        else mostrarAlerta("❌ Contacto No registrado");
        cambiarEscenaAlListado(); // Vuelve a la vista anterior
    }

    /*
     * Verifica que los campos obligatorios (nombre y RUC) estén llenos.
     * Si no lo están, se muestra una alerta
     */
    private boolean validarCamposObligatorios() {
        if (nombre.getText().trim().isEmpty() || ruc.getText().trim().isEmpty()) {
            mostrarAlerta("⚠️ Nombre y RUC son obligatorios.");
            return false;
        }
        return true;
    }
}

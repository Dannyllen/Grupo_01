package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.ContactoManager;
import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.ContactoPersona;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class AgregarPersonaController extends AgregarBaseController {

    private ContactoPersona persona;

    @FXML private TextField apellido;       

    /*
     * Método que se ejecuta cuando el usuario presiona el botón "Guardar".
     * Crea o actualiza un objeto ContactoPersona y lo guarda en el sistema.
     */
    @FXML
    public void guardarContacto() {
        if (!validarCamposObligatorios()) return;

        // Si es un nuevo contacto
        if (persona == null) {
            persona = new ContactoPersona(
                nombre.getText().trim(),
                apellido.getText().trim(),
                convertirLista(listaDirecciones),
                convertirLista(listaEmails),
                convertirLista(listaTelefonos),
                convertirLista(listaRedesSociales),
                imagenesSeleccionadas,
                convertirLista(listaFechasInteres)
            );
            ContactoManager.agregarContacto(persona);
        } else {
            // Si ya existe, se actualizan sus atributos
            persona.setNombre(nombre.getText().trim());
            persona.setApellido(apellido.getText().trim());
            persona.setDirecciones(convertirLista(listaDirecciones));
            persona.setEmails(convertirLista(listaEmails));
            persona.setNumTelefonicos(convertirLista(listaTelefonos));
            persona.setIdentRedesSociales(convertirLista(listaRedesSociales));
            persona.setFechasInteres(convertirLista(listaFechasInteres));
            persona.setFotos(imagenesSeleccionadas);
        }

        mostrarAlerta("✅ Contacto guardado exitosamente.");
        cambiarEscenaAlListado(); // Cambia a la vista de lista de contactos
    }

    /*
     * Método que se ejecuta al presionar el botón "Eliminar".
     * Intenta eliminar el contacto actual del sistema.
     */
    @FXML
    private void eliminarContacto() {
        boolean aux = ContactoManager.eliminarContacto(persona);
        if (aux) mostrarAlerta("❌ Contacto Eliminado");
        else mostrarAlerta("❌ Contacto No registrado");
        cambiarEscenaAlListado();
    }

    /*
     * Carga los datos del contacto en los campos de la interfaz para permitir su edición.
     * Se ejecuta cuando se quiere modificar un contacto existente.
     */
    @Override
    public void setModo(Contacto contacto) {
        this.contacto = contacto;
        this.persona = (ContactoPersona) contacto;

        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());

        listaDirecciones.getItems().clear();
        for (String d : persona.getDirecciones()) {
            listaDirecciones.getItems().add(d);
        }

        listaEmails.getItems().clear();
        for (String e : persona.getEmails()) {
            listaEmails.getItems().add(e);
        }

        listaTelefonos.getItems().clear();
        for (String t : persona.getNumTelefonicos()) {
            listaTelefonos.getItems().add(t);
        }

        listaRedesSociales.getItems().clear();
        for (String r : persona.getIdentRedesSociales()) {
            listaRedesSociales.getItems().add(r);
        }

        listaFechasInteres.getItems().clear();
        for (String f : persona.getFechasInteres()) {
            listaFechasInteres.getItems().add(f);
        }

        listaFotos.getItems().clear();
        imagenesSeleccionadas.clear();
        for (Image img : persona.getFotos()) {
            listaFotos.getItems().add("Imagen cargada");
            imagenesSeleccionadas.add(img);
        }
    }

    /*
     * Verifica que los campos obligatorios (nombre y apellido) estén completos.
     * Si no lo están, se muestra una alerta
     */
    private boolean validarCamposObligatorios() {
        if (nombre.getText().trim().isEmpty() || apellido.getText().trim().isEmpty()) {
            mostrarAlerta("⚠️ Nombre y apellido son obligatorios.");
            return false;
        }
        return true;
    }
}
package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.ContactoManager;
import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.ContactoPersona;
import espol.grupo_01.Modelo.Dato;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        for (Dato d : persona.getDirecciones()) {
            listaDirecciones.getItems().add(d.toString());
        }

        listaEmails.getItems().clear();
        for (Dato e : persona.getEmails()) {
            listaEmails.getItems().add(e.toString());
        }

        listaTelefonos.getItems().clear();
        for (Dato t : persona.getNumTelefonicos()) {
            listaTelefonos.getItems().add(t.toString());
        }

        listaRedesSociales.getItems().clear();
        for (Dato r : persona.getIdentRedesSociales()) {
            listaRedesSociales.getItems().add(r.toString());
        }

        listaFechasInteres.getItems().clear();
        for (Dato f : persona.getFechasInteres()) {
            listaFechasInteres.getItems().add(f.toString());
        }

        listaFotos.getItems().clear();
        imagenesSeleccionadas.clear();
        for (String img : persona.getFotosRutas()) {
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

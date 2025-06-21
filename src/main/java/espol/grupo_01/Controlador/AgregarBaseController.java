package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.LinkedListSimpleCircular;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import espol.grupo_01.Modelo.LinkedListDobleCircular;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public abstract class AgregarBaseController {

    // Objeto Contacto que representa el contacto que se está creando o editando
    protected Contacto contacto;

    // Lista circular doble para almacenar imágenes seleccionadas por el usuario
    protected final LinkedListDobleCircular<Image> imagenesSeleccionadas = new LinkedListDobleCircular<>();

    @FXML protected TextField nombre;
    @FXML protected ListView<String> listaDirecciones, listaEmails, listaTelefonos;
    @FXML protected ListView<String> listaRedesSociales, listaFechasInteres, listaFotos;
    @FXML protected MenuButton menuDireccion, menuCorreo, menuRedes, menuFechas, menuTelefono;
    @FXML protected TextField direccion, email, telefono, redSocial, fechaInteresField;

    /*
     * Agrega un elemento a la lista gráfica (ListView), si los campos no están vacíos.
     * Se utiliza para direcciones, correos, redes, etc.
     */
    protected void agregarElemento(TextField campo, ListView<String> lista, MenuButton menu) {
        String textoCampo = campo.getText().trim();
        String textoMenu = menu.getText().trim();
        if (!textoCampo.isEmpty() && !textoMenu.isEmpty()) {
            lista.getItems().add(textoMenu + ": " + textoCampo);
            campo.clear();
        } else {
            mostrarAlerta("⚠ Faltan campos por completar");
        }
    }

    /*
     * Elimina el elemento seleccionado de un ListView.
     * Se usa en direcciones, correos, etc.
     */
    protected void eliminarSeleccionado(ListView<String> lista) {
        int selectedIndex = lista.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            lista.getItems().remove(selectedIndex);
        } else {
            mostrarAlerta("⚠️ Selecciona un elemento para eliminar.");
        }
    }

    /*
     * Convierte los elementos de un ListView a una lista enlazada circular simple personalizada.
     * Cada elemento tiene un tipo (clave) y un valor.
     */
    protected LinkedListSimpleCircular<String> convertirLista(ListView<String> listView) {
        LinkedListSimpleCircular<String> lista = new LinkedListSimpleCircular<>();
        for (String item : listView.getItems()) {
            String[] partes = item.split(":");
            lista.add(partes[1], partes[0]); // valor, tipo
        }
        return lista;
    }

    /*
     * Muestra un mensaje en pantalla.
     */
    protected void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Métodos FXML que asignan el texto del ítem seleccionado al botón del menú correspondiente
    @FXML private void asignarTipoDireccion(ActionEvent event) { asignarTextoMenu(event, menuDireccion); }
    @FXML private void asignarTipoCorreo(ActionEvent event) { asignarTextoMenu(event, menuCorreo); }
    @FXML private void asignarTipoRedes(ActionEvent event) { asignarTextoMenu(event, menuRedes); }
    @FXML private void asignarTipoFechas(ActionEvent event) { asignarTextoMenu(event, menuFechas); }
    @FXML private void asignarTipoTelefono(ActionEvent event) { asignarTextoMenu(event, menuTelefono); }

    // Métodos FXML que agregan elementos a la lista respectiva
    @FXML private void agregarDireccion() { agregarElemento(direccion, listaDirecciones, menuDireccion); }
    @FXML private void agregarEmail() { agregarElemento(email, listaEmails, menuCorreo); }
    @FXML private void agregarTelefono() { agregarElemento(telefono, listaTelefonos, menuTelefono); }
    @FXML private void agregarRedSocial() { agregarElemento(redSocial, listaRedesSociales, menuRedes); }

    @FXML private void agregarFechaInteres() {
        String texto = fechaInteresField.getText().trim();
        String textoMenu = menuFechas.getText().trim();
        if (texto.isEmpty() || textoMenu.isEmpty()) {
            mostrarAlerta("Por favor ingresa una fecha.");
            return;
        }
        try {
            LocalDate.parse(texto);
            listaFechasInteres.getItems().add(textoMenu + ": " + texto);
            fechaInteresField.clear();
        } catch (Exception e) {
            mostrarAlerta("Formato de fecha incorrecto. Usa yyyy-MM-dd.");
        }
    }

    /*
     * Abre un FileChooser para seleccionar una imagen del sistema de archivos.
     */
    @FXML private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File archivo = fileChooser.showOpenDialog(null);
        if (archivo != null) {
            listaFotos.getItems().add(archivo.getAbsolutePath());
            imagenesSeleccionadas.add(new Image(archivo.toURI().toString()));
        }
    }

    // Métodos para eliminar elementos de las listas respectivas
    @FXML private void eliminarDireccion() { eliminarSeleccionado(listaDirecciones); }
    @FXML private void eliminarEmail() { eliminarSeleccionado(listaEmails); }
    @FXML private void eliminarTelefono() { eliminarSeleccionado(listaTelefonos); }
    @FXML private void eliminarRedSocial() { eliminarSeleccionado(listaRedesSociales); }
    @FXML private void eliminarFechaInteres() { eliminarSeleccionado(listaFechasInteres); }

    /*
     * Elimina la imagen seleccionada del ListView y de la lista de imágenes.
     */
    @FXML private void eliminarFoto() {
        int selectedIndex = listaFotos.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            listaFotos.getItems().remove(selectedIndex);
            imagenesSeleccionadas.remove(selectedIndex);
        }
    }

    /*
     * Asigna el texto de un MenuItem a un MenuButton cuando se selecciona una opción del menú.
     */
    private void asignarTextoMenu(ActionEvent event, MenuButton btn) {
        MenuItem item = (MenuItem) event.getSource();
        String tipoValor = item.getText();
        btn.setText(tipoValor);
    }

    /*
     * Cambia la escena actual a la vista de MostrarContacto.fxml.
     * Se utiliza para navegar de vuelta al listado de contactos.
     */
    public void cambiarEscenaAlListado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/MostrarContacto.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nombre.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            mostrarAlerta("❌ Error al cambiar de vista.");
            e.printStackTrace();
        }
    }

    /*
     * Método abstracto que debe ser implementado por la subclase para guardar el contacto.
     */
    @FXML
    public abstract void guardarContacto();

    /*
     * Método abstracto para establecer los datos del contacto a editar (modo edición).
     */
    public abstract void setModo(Contacto contacto);
}
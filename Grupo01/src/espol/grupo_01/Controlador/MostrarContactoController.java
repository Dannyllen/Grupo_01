package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.ContactoManager;
import espol.grupo_01.Modelo.LinkedListDobleCircular;
import espol.grupo_01.Modelo.CNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class MostrarContactoController {

    @FXML
    private ListView<Contacto> listaContactos;
    
    // Lista circular de contactos y nodo actualmente mostrado
    private LinkedListDobleCircular<Contacto> contactosCirculares;
    private CNode<Contacto> nodoActual;
    @FXML protected MenuButton menuOrdenar;
    @FXML protected Button btnFiltrar;
    @FXML private MenuItem ordenarNombre; // ← Falta esta línea
    @FXML private MenuItem ordenarAtributo;
    @FXML private MenuItem ordenarTipo;

    public void initialize() {
        contactosCirculares = ContactoManager.getContactos();
        // Muestra el primer contacto si la lista no esta vacia
        if (!contactosCirculares.isEmpty()) {
            nodoActual = contactosCirculares.getPrimero();
            mostrarContactoActual();
        }
        // Define cómo se muestra cada contacto dentro del ListView
        listaContactos.setCellFactory(lv -> {
            ContactoCard cell = new ContactoCard();

            cell.setOnEditarCallback(() -> {
                Contacto contacto = cell.getItem();
                if (contacto != null) {
                    try {
                        FXMLLoader loader = contacto.editar();
                        Parent root = loader.load();

                        AgregarBaseController controller = loader.getController();
                        controller.setModo(contacto);

                        Stage stage = (Stage) listaContactos.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            return cell;
});

    }
    
    @FXML private void ordenarPorNombre() {
        ContactoManager.ordenarPorNombre();
        contactosCirculares = ContactoManager.getContactos(); // Actualiza referencia
        nodoActual = contactosCirculares.getPrimero(); // Reinicia a primero
        menuOrdenar.setText(ordenarNombre.getText());
        mostrarContactoActual();
    }

    
    @FXML private void ordenarPorCantidadFotos(){
        ContactoManager.ordenarPorCantidadDeFotos();
        contactosCirculares = ContactoManager.getContactos(); // Actualiza referencia
        nodoActual = contactosCirculares.getPrimero();
        menuOrdenar.setText(ordenarAtributo.getText());
        mostrarContactoActual();
    }
    
    @FXML private void ordenarPorTipoDeContacto(){
        ContactoManager.ordenarPorTipoDeContacto();
        contactosCirculares = ContactoManager.getContactos(); // Actualiza referencia
        nodoActual = contactosCirculares.getPrimero();
        menuOrdenar.setText(ordenarTipo.getText());
        mostrarContactoActual();
    }
    
    @FXML private void aplicarFiltro(){
        
    }
    
    private void mostrarContactoActual() {
        ObservableList<Contacto> items = listaContactos.getItems();
        if (items.isEmpty()) {
            items.add(nodoActual.contenido);
        } else {
            items.set(0, nodoActual.contenido);
        }
        listaContactos.getSelectionModel().select(0);
    }


    @FXML
    private void mostrarSiguiente() {
        if (nodoActual != null) {
            nodoActual = nodoActual.siguiente;
            mostrarContactoActual();
        }
    }

    @FXML
    private void mostrarAnterior() {
        if (nodoActual != null) {
            nodoActual = nodoActual.anterior;
            mostrarContactoActual();
        }
    }

    @FXML
    private void irAAgregarContacto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/SeleccionarTipoContacto.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
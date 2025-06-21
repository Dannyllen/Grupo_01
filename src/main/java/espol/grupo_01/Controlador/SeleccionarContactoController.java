package espol.grupo_01.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;


public class SeleccionarContactoController {

    /*
     * Método que cambia la escena actual al formulario de agregar contacto tipo persona.
     * Se activa al hacer clic en el botón correspondiente.
     */
    @FXML
    private void cambiarEscenaAgregarPersona(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/AgregarPersonaController.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Cambia la escena actual al formulario de agregar contacto tipo empresa.
     * Se activa al hacer clic en el botón correspondiente.
     */
    @FXML
    private void cambiarEscenaAgregarEmpresa(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/espol/grupo_01/resources/AgregarEmpresaController.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


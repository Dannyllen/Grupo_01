package espol.grupo_01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //ContactoManager.getContactos(); // Fuerza ejecución del bloque static

        Parent root = FXMLLoader.load(getClass().getResource("/espol/grupo_01/resources/MostrarContacto.fxml"));
        Scene scene = new Scene(root, 600, 500); 
        primaryStage.setTitle("Agenda de Contactos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*@Override
    public void stop() {
        Contacto.saveListToFileSerContactos(ContactoManager.getContactos());
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}

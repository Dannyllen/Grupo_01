package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.ContactoManager;
import espol.grupo_01.Modelo.LinkedListDobleCircular;
import espol.grupo_01.Modelo.LinkedListSimpleCircular;
import espol.grupo_01.Modelo.CNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import espol.grupo_01.Modelo.Dato;
import espol.grupo_01.Modelo.Nodo;

public class MostrarContactoController {

    @FXML
    private ListView<Contacto> listaContactos;
    
    // Lista circular de contactos y nodo actualmente mostrado
    private LinkedListDobleCircular<Contacto> contactosCirculares;
    private CNode<Contacto> nodoActual;

    @FXML
    public void initialize() {
        contactosCirculares = ContactoManager.getContactos();
        // Muestra el primer contacto si la lista no esta vacia
        if (!contactosCirculares.isEmpty()) {
            nodoActual = contactosCirculares.getPrimero();
            mostrarContactoActual();
        }
        // Define cómo se muestra cada contacto dentro del ListView
        listaContactos.setCellFactory(lv -> new ListCell<>() {
            private Label nombre = new Label();
            private ImageView fotoView = new ImageView();
            private Button btnAnteriorFoto = new Button("←");
            private Button btnSiguienteFoto = new Button("→");
            private Button botonEditar = new Button("✏️ Editar");

            private HBox navBotones = new HBox(5, btnAnteriorFoto, btnSiguienteFoto);
            private VBox imagenBox = new VBox(5, fotoView, navBotones);
            private VBox contenedorDatos = new VBox(10);

            private VBox infoBox = new VBox(12, nombre, imagenBox, contenedorDatos, botonEditar);

            private LinkedListDobleCircular<Image> fotosActuales;
            private CNode<Image> nodoFotoActual;

            {
                fotoView.setFitWidth(140);
                fotoView.setFitHeight(140);
                fotoView.setPreserveRatio(true);

                botonEditar.setOnAction(e -> editarContacto());
                btnAnteriorFoto.setOnAction(e -> mostrarFotoAnterior());
                btnSiguienteFoto.setOnAction(e -> mostrarFotoSiguiente());
                nombre.getStyleClass().add("nombreContacto");
                fotoView.getStyleClass().add("fotoContacto");
                btnAnteriorFoto.getStyleClass().add("imagen-nav-button");
                btnSiguienteFoto.getStyleClass().add("imagen-nav-button");
                botonEditar.getStyleClass().add("button");
                
            }
            //define cómo se ve cada contacto en la lista
            @Override
            protected void updateItem(Contacto contacto, boolean empty) {
                super.updateItem(contacto, empty);
                if (empty || contacto == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    nombre.setText(contacto.toString());

                    fotosActuales = contacto.getFotos();
                    if (fotosActuales != null && !fotosActuales.isEmpty()) {
                        nodoFotoActual = fotosActuales.getPrimero();
                        fotoView.setImage(nodoFotoActual.contenido);
                    } else {
                        nodoFotoActual = null;
                        fotoView.setImage(new Image("i.png"));
                    }

                    mostrarBloquesDeDatos(contacto);
                    setGraphic(infoBox);
                }
            }

            private void mostrarFotoAnterior() {
                if (nodoFotoActual != null) {
                    nodoFotoActual = nodoFotoActual.anterior;
                    fotoView.setImage(nodoFotoActual.contenido);
                }
            }

            private void mostrarFotoSiguiente() {
                if (nodoFotoActual != null) {
                    nodoFotoActual = nodoFotoActual.siguiente;
                    fotoView.setImage(nodoFotoActual.contenido);
                }
            }
            //Permite que el usuario edite un contacto (persona o empresa) y actualiza la lista
            private void editarContacto() {
                try {
                    FXMLLoader loader;
                    Parent root;

                    Contacto contacto = getItem();
                    //Verifica el tipo de contacto
                    loader = contacto.editar();

                    root = loader.load();
                    AgregarBaseController controller = loader.getController();
                    controller.setModo(contacto);

                    Stage stage = (Stage) botonEditar.getScene().getWindow();
                    stage.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*
            * Completa dinámicamente la interfaz con bloques visuales que muestran los datos del contacto seleccionado
            */
            private void mostrarBloquesDeDatos(Contacto contacto) {
                contenedorDatos.getChildren().clear();

                contenedorDatos.getChildren().addAll(
                    crearBloqueDato("📧 Emails", contacto.getEmails()),
                    crearBloqueDato("📞 Teléfonos", contacto.getNumTelefonicos()),
                    crearBloqueDato("📍 Direcciones", contacto.getDirecciones()),
                    crearBloqueDato("🌐 Redes Sociales", contacto.getIdentRedesSociales()),
                    crearBloqueDato("📅 Fechas de interés", contacto.getFechasInteres()),
                    crearTextoPlano("🖼 Fotos", contacto.getFotos().isEmpty() ? "Sin fotos" : contacto.getFotos().size() + " imagen(es)"),
                    crearTextoPlano("👤 Tipo", contacto.getTipoDeContacto())
                );
            }
            
            /*Mostrar un bloque de información navegable (con botón →) 
            *cuando hay múltiples elementos en una lista circular (por ejemplo: varios correos, teléfonos, direcciones, etc.).
            */
            private VBox crearBloqueDato(String titulo, LinkedListSimpleCircular<Dato> lista) {
                Label tituloLabel = new Label(titulo);
                tituloLabel.getStyleClass().add("card-title");

                Label valorLabel = new Label();
                valorLabel.getStyleClass().add("card-value");

                Button btnNext = new Button("→");
                btnNext.getStyleClass().add("imagen-nav-button");

                HBox navegacion = new HBox(5, btnNext);

                VBox bloque = new VBox(5, tituloLabel, valorLabel, navegacion);
                bloque.getStyleClass().add("info-block");

                if (lista == null || lista.isEmpty()) {
                    valorLabel.setText("Sin datos");
                    btnNext.setDisable(true);
                    return bloque;
                }

                NavegadorDato navegador = new NavegadorDato(lista);
                valorLabel.setText(navegador.actual());

                btnNext.setOnAction(e -> valorLabel.setText(navegador.siguiente()));

                return bloque;
            }
            
            //Permite navegar en la lista direcciones, correos, etc.
            private class NavegadorDato {
                private Nodo<Dato> nodoActual;

                public NavegadorDato(LinkedListSimpleCircular<Dato> lista) {
                    this.nodoActual = lista.getPrimero();
                }

                public String siguiente() {
                    nodoActual = nodoActual.siguiente;
                    return nodoActual.valor.toString();
                }

                public String actual() {
                    return nodoActual.valor.toString();
                }
            }

            //Muestra un bloque de texto, se usa cuando solo se necesite mostar un elemento
            private VBox crearTextoPlano(String titulo, String valor) {
                Label tituloLabel = new Label(titulo);
                tituloLabel.getStyleClass().add("card-title");

                Label valorLabel = new Label(valor);
                valorLabel.getStyleClass().add("card-value");

                VBox bloque = new VBox(5, tituloLabel, valorLabel);
                bloque.getStyleClass().add("info-block");
                return bloque;
            }
        });
    }
    
    private void mostrarContactoActual() {
        listaContactos.getItems().clear();
        listaContactos.getItems().add(nodoActual.contenido);
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
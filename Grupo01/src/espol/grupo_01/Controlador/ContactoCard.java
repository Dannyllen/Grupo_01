package espol.grupo_01.Controlador;


import espol.grupo_01.Modelo.CNode;
import espol.grupo_01.Modelo.Contacto;
import espol.grupo_01.Modelo.Dato;
import espol.grupo_01.Modelo.LinkedListDobleCircular;
import espol.grupo_01.Modelo.LinkedListSimpleCircular;
import espol.grupo_01.Modelo.Nodo;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ContactoCard extends ListCell<Contacto> {

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

    // para delegar la acción de editar 
    private Runnable onEditarCallback;

    public ContactoCard() {
        fotoView.setFitWidth(140);
        fotoView.setFitHeight(140);
        fotoView.setPreserveRatio(true);

        nombre.getStyleClass().add("nombreContacto");
        fotoView.getStyleClass().add("fotoContacto");
        btnAnteriorFoto.getStyleClass().add("imagen-nav-button");
        btnSiguienteFoto.getStyleClass().add("imagen-nav-button");
        botonEditar.getStyleClass().add("button");

        btnAnteriorFoto.setOnAction(e -> mostrarFotoAnterior());
        btnSiguienteFoto.setOnAction(e -> mostrarFotoSiguiente());
        botonEditar.setOnAction(e -> {
            if (onEditarCallback != null) {
                onEditarCallback.run();
            }
        });
    }

    @Override
    protected void updateItem(Contacto contacto, boolean empty) {
        super.updateItem(contacto, empty);

        if (empty || contacto == null) {
            setText(null);
            setGraphic(null);
            fotosActuales = null;
            nodoFotoActual = null;
        } else {
            nombre.setText(contacto.toString());

            fotosActuales = contacto.getFotos();
            if (fotosActuales != null && !fotosActuales.isEmpty()) {
                nodoFotoActual = fotosActuales.getPrimero();
                fotoView.setImage(nodoFotoActual.contenido);
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

    public void setOnEditarCallback(Runnable callback) {
        this.onEditarCallback = callback;
    }

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

    private VBox crearTextoPlano(String titulo, String valor) {
        Label tituloLabel = new Label(titulo);
        tituloLabel.getStyleClass().add("card-title");

        Label valorLabel = new Label(valor);
        valorLabel.getStyleClass().add("card-value");

        VBox bloque = new VBox(5, tituloLabel, valorLabel);
        bloque.getStyleClass().add("info-block");
        return bloque;
    }
}


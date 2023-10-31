package dad.loginmvc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Vista extends Application {

    private Controlador controlador;
    private TextField usuarioField;
    private TextField passwordField;

    private Button boton_acceder;
    private Button boton_cancelar;

    private CheckBox ldapCheckBox;

    public TextField getUsuarioField() {
        return usuarioField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public CheckBox getLdapCheckBox() {
        return ldapCheckBox;
    }

    public Button getBoton_acceder() {
        return boton_acceder;
    }

    public Button getBoton_cancelar() {
        return boton_cancelar;
    }

    public Vista(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarCorrecto() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("AVISO");
        alert.setHeaderText(null);
        alert.setContentText("Contraseña y usuario correctos");
        alert.showAndWait();
    }
    
    public void mostrarIncorrecto() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setHeaderText(null);
    alert.setContentText("Contraseña y/o usuario incorrectos");

    alert.showAndWait();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Elementos de la interfaz
        usuarioField = new TextField();
        usuarioField.setPromptText("Usuario");
        usuarioField.setPrefWidth(250);

        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        passwordField.setPrefWidth(250);

        boton_acceder = new Button("Acceder");
        boton_cancelar = new Button("Cancelar");

        // linkeamos el controlador con la vista al pulsar los botones
        boton_acceder.setOnAction(controlador);
        boton_cancelar.setOnAction(controlador);


        ldapCheckBox = new CheckBox("Usar LDAP"); // ?

        // Contenedor principal
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        // Grupo 1 : Usuario y Contraseña
        HBox hbox1 = new HBox(10); // Espacio horizontal entre elementos
        HBox hbox2 = new HBox(10);
        hbox1.getChildren().addAll(new Label("Usuario:"), usuarioField);
        hbox2.getChildren().addAll(new Label("Contraseña:"), passwordField);

        // Grupo 2: Botones
        HBox hbox3 = new HBox(10);
        hbox3.getChildren().addAll(boton_acceder, boton_cancelar);

        // contenedor principal
        vbox.getChildren().addAll(hbox1, hbox2, ldapCheckBox, hbox3);

        //Alineación de los elementos
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vbox, 340, 240));
        primaryStage.setTitle("Login.fx");
        primaryStage.show();
    }
}

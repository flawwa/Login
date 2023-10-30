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

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Elementos de la interfaz
        TextField userText = new TextField();
        userText.setPromptText("Nombre de usuario");
        userText.setPrefWidth(250);

        PasswordField pinText = new PasswordField();
        pinText.setPromptText("Contraseña del usuario");
        pinText.setPrefWidth(250);

        Button button = new Button("Acceder");
        Button button2 = new Button("Cancelar");

        CheckBox ldapCheckBox = new CheckBox("Usar LDAP");

        // Contenedor principal
        VBox vbox = new VBox(10); // Espacio vertical entre elementos
        vbox.setAlignment(Pos.CENTER);

        // Grupo 1: Usuario y Contraseña
        HBox hbox1 = new HBox(10); // Espacio horizontal entre elementos
        HBox hbox2 = new HBox(10);
        hbox1.getChildren().addAll(new Label("Usuario:"), userText);
        hbox2.getChildren().addAll(new Label("Contraseña:"), pinText);

        // Grupo 2: Botones
        HBox hbox3 = new HBox(10);
        hbox3.getChildren().addAll(button, button2);

        // Agregar elementos al contenedor principal
        vbox.getChildren().addAll(hbox1, hbox2, ldapCheckBox, hbox3);

        // Alineación de los elementos
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);

        // Ventana
        Scene scene = new Scene(vbox, 340, 240);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login.fx");
        primaryStage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener el usuario y la contraseña ingresados por el usuario
                String username = userText.getText();
                String password = pinText.getText();

                // Lógica de autenticación aquí (deberás implementar tu lógica real)
                if (autenticarUsuario(username, password)) {
                    // Autenticación exitosa, mostrar la ventana de la aplicación principal o lo que necesites
                    Autenticacion autenticacion = new Autenticacion();
                    autenticacion.start(new Stage());

                    // Cerrar la ventana de inicio de sesión
                    primaryStage.close();
                } else {
                    // Autenticación fallida, muestra un mensaje de error
                    mostrarMensajeError("Error de autenticación", "Usuario y/o contraseña incorrectos");
                }
            }
        });

        button2.setOnAction(e -> {
            System.exit(0);
        });
    }

    // Método para realizar la autenticación (deberás implementar tu lógica de autenticación real)
    private boolean autenticarUsuario(String username, String password) {
        // Aquí deberías implementar tu lógica de autenticación.
        // Puedes usar la lógica que tenías en la clase Autenticacion.

        // Por ahora, devolveré true siempre para demostrar el flujo.
        return true;
    }

    // Método para mostrar mensajes de error
    private void mostrarMensajeError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




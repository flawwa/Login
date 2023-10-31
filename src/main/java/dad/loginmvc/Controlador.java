package dad.loginmvc;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Controlador extends Application implements EventHandler {
    private Modelo modelo;
    private Vista vista;

    private boolean ldap_bool = false;

    public void actualizarNombre(String nombre) {
        modelo.setNombre(nombre);
    }

    public void actualizarPassword(String password) {
        modelo.setPassword(password);
    }

    // esta función será la que mande los datos al modelo y el modelo se encargará de comprobarlos
    private boolean checkAuth() throws FileNotFoundException {
        modelo.setNombre(vista.getUsuarioField().getText());
        modelo.setPassword(vista.getPasswordField().getText());

        return modelo.login();
    }

    // aquí se implementa la lógica de interacción del usuario con los botones de la interfaz
    @Override
    public void handle(final Event event) {
        final Object source = event.getSource();

        if(source.equals(vista.getBoton_acceder())) {
            if (!ldap_bool) {
                try {
                    if(checkAuth()) {
                        vista.mostrarCorrecto();
                    }
                    else {
                        vista.mostrarIncorrecto();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // implementar lógica con LDAP
            }
        }
        if(source.equals(vista.getBoton_cancelar())) {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        modelo = new Modelo();
        vista = new Vista(this);

        vista.start(primaryStage);
    }


}

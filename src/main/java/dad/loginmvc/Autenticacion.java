package dad.loginmvc;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;


public class Autenticacion extends Application {

    @Override
    public void start(Stage primaryStage) {
        String csvFilePath = Paths.get("src/main/resources/users.csv").toString();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Autenticación");
        dialog.setHeaderText("Ingrese su contraseña:");
        dialog.setContentText("Contraseña:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String passwordToCheck = DigestUtils.md5Hex(result.get()).toUpperCase();

            boolean authenticated = false;

            try {
                CSVParser parser = CSVFormat.DEFAULT.parse(new FileReader(csvFilePath));

                for (CSVRecord record : parser) {
                    String username = record.get(0);
                    String storedPassword = record.get(1);

                    if (result.get().equals(username) && passwordToCheck.equals(storedPassword)) {
                        authenticated = true;
                        break;
                    }
                }

            } catch (IOException e) {
                // Manejo de excepciones al leer el archivo CSV
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al leer el archivo CSV");
                alert.setContentText("Hubo un problema al acceder al archivo CSV. Verifica su existencia y formato.");
                alert.showAndWait();
                e.printStackTrace();
            }

            if (authenticated) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Acceso permitido");
                alert.setHeaderText("Acceso permitido");
                alert.setContentText("¡Bienvenido, " + result.get() + "!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Acceso denegado");
                alert.setHeaderText("Acceso denegado");
                alert.setContentText("El usuario y/o la contraseña no son correctas.");
                alert.showAndWait();
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}





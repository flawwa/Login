package dad.loginmvc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class Modelo {
    private String nombre;
    private String password;
    private final String csvFilePath = Paths.get("src/main/resources/users.csv").toString();

    public String getNombre() { return nombre; };
    public String getPassword() { return password; };

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    };

    public void setPassword(String Password) {
        this.password = Password;
    }

    public boolean login() throws FileNotFoundException {
        // ir a leer al csv
        boolean found = false;
        try {
            CSVParser parser = CSVFormat.DEFAULT.parse(new FileReader(csvFilePath));

            for(CSVRecord record : parser) {
                String storedNombre = record.get(0);
                String storedPassword = record.get(1);
                if(Objects.equals(storedNombre, nombre) && Objects.equals(storedPassword, password)) {
                    found = true;
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return found;
    }
}

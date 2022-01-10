package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    String serveur = "";
    String baseName = "";
    String user = "";
    String password = "";
    String url = "jdbc:postgresql://"+ serveur + "/"+ baseName;
    Connection connexion;

    public Connection Connect() {

        try {
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connexion;
    }
}

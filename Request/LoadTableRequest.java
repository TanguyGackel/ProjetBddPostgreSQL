package Request;

import Connexion.Connexion;
import Entity.LoadEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadTableRequest {

    public Connexion c;
    public Connection connexion;
    public PreparedStatement statement;
    String requete;

    LoadEntity entity = new LoadEntity();

    public LoadTableRequest() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    public void load() {
        requete = entity.destroyTables() + entity.createTables() + entity.insertTables();
        try {
            statement = connexion.prepareStatement(requete);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** RELOADING TABLES *****");
        new LoadTableRequest().load();
    }
}

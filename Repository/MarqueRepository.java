package Repository;

import Connexion.Connexion;
import Entity.Entity;
import Entity.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MarqueRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public MarqueRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> marques = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM marque");

            while (resultSet.next()) {
                Marque marque = new Marque();
                marque.setId(resultSet.getInt("idmarque"));
                marque.setNom(resultSet.getString("nommarque"));
                marques.add(marque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marques;
    }

    public Entity findById(int id) {
        Marque marque = new Marque();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM marque WHERE idmarque="+id);

            while (resultSet.next()) {
                marque.setId(resultSet.getInt("idmarque"));
                marque.setNom(resultSet.getString("nommarque"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return marque;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into marque(nommarque) values (?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Marque)entity).getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update marque set nommarque = ? where idmarque = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Marque)entity).getNom());
            statement.setInt(2,((Marque)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from marque where idmarque = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Marque) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
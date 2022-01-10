package Repository;

import Connexion.Connexion;
import Entity.Entity;
import Entity.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VilleRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public VilleRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> villes = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville");

            while (resultSet.next()) {
                Ville ville = new Ville();
                ville.setId(resultSet.getInt("idville"));
                ville.setNom(resultSet.getString("nomville"));
                ville.setNbHabitant(resultSet.getInt("nombrehabitants"));
                villes.add(ville);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public Entity findById(int id) {
        Ville ville = new Ville();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville WHERE idville="+id);

            while (resultSet.next()) {
                ville.setId(resultSet.getInt("idville"));
                ville.setNom(resultSet.getString("nomville"));
                ville.setNbHabitant(resultSet.getInt("nombrehabitants"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return ville;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into ville(nomville,nombrehabitants) values (?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Ville)entity).getNom());
            statement.setInt(2, ((Ville)entity).getNbHabitant());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update ville set nomville = ?, nombrehabitants = ? where idville = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Ville)entity).getNom());
            statement.setInt(2,((Ville)entity).getNbHabitant());
            statement.setInt(3,((Ville)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from ville where idville = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Ville) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
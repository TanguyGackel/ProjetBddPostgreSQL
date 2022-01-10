package Repository;

import Connexion.Connexion;
import Entity.Categorie;
import Entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public CategorieRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> categories = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie");

            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("idcategorie"));
                categorie.setLibelle(resultSet.getString("libellecategorie"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Entity findById(int id) {
        Categorie categorie = new Categorie();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie WHERE idcategorie="+id);

            while (resultSet.next()) {
                categorie.setId(resultSet.getInt("idcategorie"));
                categorie.setLibelle(resultSet.getString("libellecategorie"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return categorie;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into categorie(libellecategorie) values (?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Categorie)entity).getLibelle());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update categorie set libellecategorie = ? where idcategorie = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Categorie)entity).getLibelle());
            statement.setInt(2,((Categorie)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from categorie where idcategorie = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Categorie) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
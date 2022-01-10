package Repository;

import Connexion.Connexion;
import Entity.Entity;
import Entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TypeRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public TypeRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> types = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type");

            while (resultSet.next()) {
                Type type = new Type();
                type.setId(resultSet.getInt("idtype"));
                type.setLibelle(resultSet.getString("libelletype"));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public Entity findById(int id) {
        Type type = new Type();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type WHERE idtype="+id);

            while (resultSet.next()) {
                type.setId(resultSet.getInt("idtype"));
                type.setLibelle(resultSet.getString("libelletype"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return type;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into type(libelletype) values (?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Type)entity).getLibelle());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update type set libelletype = ? where idtype = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Type)entity).getLibelle());
            statement.setInt(2,((Type)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from type where idtype = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Type) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
package Repository;

import Connexion.Connexion;
import Entity.Agence;
import Entity.Entity;
import Entity.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public AgenceRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> agences = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM agence");

            while (resultSet.next()) {
                Agence agence = new Agence();
                VilleRepository villeRepository = new VilleRepository();
                agence.setId(resultSet.getInt("idagence"));
                agence.setNbEmploye(resultSet.getInt("nbemployes"));
                agence.setVille((Ville)villeRepository.findById(resultSet.getInt("idville")));
                agences.add(agence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agences;
    }

    public Entity findById(int id) {
        Agence agence = new Agence();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM agence WHERE idagence="+id);

            while (resultSet.next()) {
                VilleRepository villeRepository = new VilleRepository();
                agence.setId(resultSet.getInt("idagence"));
                agence.setNbEmploye(resultSet.getInt("nbemployes"));
                agence.setVille((Ville)villeRepository.findById(resultSet.getInt("idville")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return agence;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into agence(nbemployes,idville) values (?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1, ((Agence)entity).getNbEmploye());
            statement.setInt(2, ((Agence)entity).getVille().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update agence set nbemployes = ?, idville = ? where idagence = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Agence)entity).getNbEmploye());
            statement.setInt(2,((Agence)entity).getVille().getId());
            statement.setInt(3,((Agence)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from agence where idagence = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Agence) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
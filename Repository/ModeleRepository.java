package Repository;

import Connexion.Connexion;
import Entity.Entity;
import Entity.Modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public ModeleRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> modeles = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM modele");

            while (resultSet.next()) {
                Modele modele = new Modele();
                modele.setId(resultSet.getInt("idmodele"));
                modele.setDenomination(resultSet.getString("denomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissancefiscale"));
                modeles.add(modele);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeles;
    }

    public Entity findById(int id) {
        Modele modele = new Modele();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM modele WHERE idmodele="+id);

            while (resultSet.next()) {
                modele.setId(resultSet.getInt("idmodele"));
                modele.setDenomination(resultSet.getString("denomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissancefiscale"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return modele;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into modele(denomination,puissancefiscale) values (?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Modele)entity).getDenomination());
            statement.setInt(2, ((Modele)entity).getPuissanceFiscale());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update modele set denomination = ?, puissancefiscale = ? where idmodele = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Modele)entity).getDenomination());
            statement.setInt(2,((Modele)entity).getPuissanceFiscale());
            statement.setInt(3,((Modele)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from modele where idmodele = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Modele) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
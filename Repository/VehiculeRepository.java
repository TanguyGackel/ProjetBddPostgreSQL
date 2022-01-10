package Repository;

import Connexion.Connexion;
import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VehiculeRepository implements Interface2 {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public VehiculeRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> vehicules = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicule");

            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule();
                MarqueRepository marqueRepository = new MarqueRepository();
                ModeleRepository modeleRepository = new ModeleRepository();
                CategorieRepository categorieRepository = new CategorieRepository();
                TypeRepository typeRepository = new TypeRepository();
                AgenceRepository agenceRepository = new AgenceRepository();

                vehicule.setImmatriculation(resultSet.getString("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getDate("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getInt("nbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("prixParJourDeLocation"));
                vehicule.setMarque((Marque)marqueRepository.findById(resultSet.getInt("idMarque")));
                vehicule.setModele((Modele)modeleRepository.findById(resultSet.getInt("idModele")));
                vehicule.setCategorie((Categorie)categorieRepository.findById(resultSet.getInt("idCategorie")));
                vehicule.setType((Type)typeRepository.findById(resultSet.getInt("idType")));
                vehicule.setAgence((Agence)agenceRepository.findById(resultSet.getInt("idAgence")));
                vehicules.add(vehicule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicules;
    }

    @Override
    public Entity findById(String id) {
        Vehicule vehicule = new Vehicule();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicule WHERE immatriculation="+"'"+id+"'");

            while (resultSet.next()) {
                MarqueRepository marqueRepository = new MarqueRepository();
                ModeleRepository modeleRepository = new ModeleRepository();
                CategorieRepository categorieRepository = new CategorieRepository();
                TypeRepository typeRepository = new TypeRepository();
                AgenceRepository agenceRepository = new AgenceRepository();

                vehicule.setImmatriculation(resultSet.getString("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getDate("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getInt("nbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("prixParJourDeLocation"));
                vehicule.setMarque((Marque)marqueRepository.findById(resultSet.getInt("idMarque")));
                vehicule.setModele((Modele)modeleRepository.findById(resultSet.getInt("idModele")));
                vehicule.setCategorie((Categorie)categorieRepository.findById(resultSet.getInt("idCategorie")));
                vehicule.setType((Type)typeRepository.findById(resultSet.getInt("idType")));
                vehicule.setAgence((Agence)agenceRepository.findById(resultSet.getInt("idAgence")));

            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return vehicule;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into vehicule(immatriculation,dateMiseEnCirculation,etat,nbKilometres,prixParJourDeLocation,idMarque,idModele,idCategorie,idType,idAgence) values (?,?,?,?,?,?,?,?,?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Vehicule)entity).getImmatriculation());
            statement.setDate(2, ((Vehicule)entity).getDateMiseEnCirculation());
            statement.setString(3, ((Vehicule)entity).getEtat());
            statement.setInt(4, ((Vehicule)entity).getNbKilometres());
            statement.setFloat(5, ((Vehicule)entity).getPrixParJourDeLocation());
            statement.setInt(6, ((Vehicule)entity).getMarque().getId());
            statement.setInt(7, ((Vehicule)entity).getModele().getId());
            statement.setInt(8, ((Vehicule)entity).getCategorie().getId());
            statement.setInt(9, ((Vehicule)entity).getType().getId());
            statement.setInt(10, ((Vehicule)entity).getAgence().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update vehicule set dateMiseEnCirculation = ?," +
                "etat = ?," +
                "nbKilometres = ?," +
                "prixParJourDeLocation = ?," +
                "idMarque = ?," +
                "idModele = ?," +
                "idCategorie = ?," +
                "idType = ?," +
                "idAgence = ? where immatriculation = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setDate(1, ((Vehicule)entity).getDateMiseEnCirculation());
            statement.setString(2, ((Vehicule)entity).getEtat());
            statement.setInt(3, ((Vehicule)entity).getNbKilometres());
            statement.setFloat(4, ((Vehicule)entity).getPrixParJourDeLocation());
            statement.setInt(5, ((Vehicule)entity).getMarque().getId());
            statement.setInt(6, ((Vehicule)entity).getModele().getId());
            statement.setInt(7, ((Vehicule)entity).getCategorie().getId());
            statement.setInt(8, ((Vehicule)entity).getType().getId());
            statement.setInt(9, ((Vehicule)entity).getAgence().getId());
            statement.setString(10, ((Vehicule)entity).getImmatriculation());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from vehicule where immatriculation = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1,((Vehicule) entity).getImmatriculation());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}

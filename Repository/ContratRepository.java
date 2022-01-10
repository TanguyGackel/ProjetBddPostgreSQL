package Repository;

import Connexion.Connexion;
import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ContratRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public ContratRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> contrats = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contrat");

            while (resultSet.next()) {
                Contrat contrat = new Contrat();
                ClientRepository clientRepository = new ClientRepository();
                VehiculeRepository vehiculeRepository = new VehiculeRepository();
                AgenceRepository agenceRepository = new AgenceRepository();

                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getDate("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getDate("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));
                contrat.setKmRetour(resultSet.getInt("kmRetour"));
                contrat.setClient((Client)clientRepository.findById(resultSet.getInt("idClient")));
                contrat.setVehicule((Vehicule)vehiculeRepository.findById(resultSet.getString("immatriculation")));
                contrat.setAgence((Agence)agenceRepository.findById(resultSet.getInt("idAgenceDeRetour")));
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrats;
    }

    @Override
    public Entity findById(int id) {
        Contrat contrat = new Contrat();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contrat WHERE idContrat="+id);

            while (resultSet.next()) {
                ClientRepository clientRepository = new ClientRepository();
                VehiculeRepository vehiculeRepository = new VehiculeRepository();
                AgenceRepository agenceRepository = new AgenceRepository();

                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getDate("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getDate("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));
                contrat.setKmRetour(resultSet.getInt("kmRetour"));
                contrat.setClient((Client)clientRepository.findById(resultSet.getInt("idClient")));
                contrat.setVehicule((Vehicule)vehiculeRepository.findById(resultSet.getString("immatriculation")));
                contrat.setAgence((Agence)agenceRepository.findById(resultSet.getInt("idAgenceDeRetour")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return contrat;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into contrat(dateDeRetrait,dateDeRetour,kmRetrait,kmRetour,idClient,immatriculation,idAgenceDeRetour) values (?,?,?,?,?,?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setDate(1, ((Contrat)entity).getDateDeRetrait());
            statement.setDate(2, ((Contrat)entity).getDateDeRetour());
            statement.setInt(3, ((Contrat)entity).getKmRetrait());
            statement.setInt(4, ((Contrat)entity).getKmRetour());
            statement.setInt(5, ((Contrat)entity).getClient().getId());
            statement.setString(6, ((Contrat)entity).getVehicule().getImmatriculation());
            statement.setInt(7, ((Contrat)entity).getAgence().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update contrat set dateDeRetrait = ?, dateDeRetour = ?, kmRetrait = ?, kmRetour = ?, idClient = ?, immatriculation = ?, idAgenceDeRetour = ? where idContrat = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setDate(1, ((Contrat)entity).getDateDeRetrait());
            statement.setDate(2, ((Contrat)entity).getDateDeRetour());
            statement.setInt(3, ((Contrat)entity).getKmRetrait());
            statement.setInt(4, ((Contrat)entity).getKmRetour());
            statement.setInt(5, ((Contrat)entity).getClient().getId());
            statement.setString(6, ((Contrat)entity).getVehicule().getImmatriculation());
            statement.setInt(7, ((Contrat)entity).getAgence().getId());
            statement.setInt(8, ((Contrat)entity).getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from contrat where idContrat = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Contrat) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}

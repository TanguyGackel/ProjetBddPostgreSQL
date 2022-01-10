package Repository;

import Connexion.Connexion;
import Entity.Contrat;
import Entity.Entity;
import Entity.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public FactureRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> factures = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM facture");

            while (resultSet.next()) {
                Facture facture = new Facture();
                ContratRepository contratRepository = new ContratRepository();
                facture.setId(resultSet.getInt("idfacture"));
                facture.setMontant(resultSet.getInt("montant"));
                facture.setContrat((Contrat)contratRepository.findById(resultSet.getInt("idContrat")));
                factures.add(facture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    public Entity findById(int id) {
        Facture facture = new Facture();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM facture WHERE idfacture="+id);

            while (resultSet.next()) {
                ContratRepository contratRepository = new ContratRepository();
                facture.setId(resultSet.getInt("idfacture"));
                facture.setMontant(resultSet.getInt("montant"));
                facture.setContrat((Contrat)contratRepository.findById(resultSet.getInt("idContrat")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return facture;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into facture(montant,idContrat) values (?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1, ((Facture)entity).getMontant());
            statement.setInt(2, ((Facture)entity).getContrat().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update facture set montant = ?, idContrat = ? where idfacture = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1, ((Facture)entity).getMontant());
            statement.setInt(2, ((Facture)entity).getContrat().getId());
            statement.setInt(3,((Facture)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from facture where idfacture = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Facture) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
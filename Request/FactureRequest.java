package Request;

import Connexion.Connexion;
import Entity.Contrat;
import Entity.Entity;
import Entity.Facture;
import Repository.ContratRepository;
import Repository.FactureRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class FactureRequest {

    public FactureRepository factureRepository;

    public static int id;
    public static int montant;
    public static Entity contrat;

    public Connexion c;
    public Connection connexion;

    public FactureRequest() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    public void createFacture(int montant, Contrat contrat) throws SQLException {
        factureRepository = new FactureRepository();
        Facture facture = new Facture(id,montant,contrat);
        factureRepository.create(facture);
        Collection<Entity> factures = factureRepository.findAll();
        for (Entity entity : factures) {
            Facture f = (Facture) entity;
            if(f.getMontant() == montant) {
                System.out.println(f.getId() + " | " + f.getMontant() + " | " + f.getContrat().getId() +  " : created");
            } else {
                System.out.println(f.getId() + " | " + f.getMontant() + f.getContrat().getId());
            }
        }
    }

    public void updateFacture(int id, int montant, Contrat contrat) throws SQLException {
        factureRepository = new FactureRepository();
        Facture facture = (Facture) factureRepository.findById(id);
        facture.setMontant(montant);
        facture.setContrat(contrat);
        factureRepository.update(facture);
        Collection<Entity> factures = factureRepository.findAll();
        for (Entity entity : factures) {
            Facture f = (Facture) entity;
            if(f.getId()==id) {
                System.out.println(f.getId() + " | " + f.getMontant() + " | " + f.getContrat().getId() + " : uptaded");
            } else {
                System.out.println(f.getId() + " | " + f.getMontant() + " | " + f.getContrat().getId());
            }
        }
    }

    public void deleteFacture(int id) throws SQLException {
        factureRepository = new FactureRepository();
        Facture facture = (Facture) factureRepository.findById(id);
        factureRepository.delete(facture);
        findAllFacture();
        System.out.println(facture.getId() + " | " + facture.getMontant() + " | " + facture.getContrat().getId());
    }

    public void findAllFacture() throws SQLException {
        factureRepository = new FactureRepository();
        Collection<Entity> factures = factureRepository.findAll();
        for (Entity entity : factures) {
            Facture f = (Facture) entity;
            System.out.println(f.getId() + " | " + f.getMontant() + " | " + f.getContrat().getId());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE FACTURE *****");
        montant = 5000;
        contrat = new ContratRepository().findById(3);
        new FactureRequest().createFacture(montant,(Contrat)contrat);
        System.out.println("");

        System.out.println("***** UPDATE FACTURE *****");
        montant = 3000;
        id = 6;
        contrat = new ContratRepository().findById(2);
        new FactureRequest().updateFacture(id, montant,(Contrat)contrat);
        System.out.println("");

        System.out.println("***** DELETE FACTURE *****");
        new FactureRequest().deleteFacture(id);
    }
}

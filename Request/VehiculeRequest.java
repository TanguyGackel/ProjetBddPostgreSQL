package Request;

import Connexion.Connexion;
import Entity.*;
import Repository.VehiculeRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public class VehiculeRequest {

    public VehiculeRepository vehiculeRepository;

    public static String immatriculation;
    public static Date dateMiseEnCirculation;
    public static String etat;
    public static int nbKilometres;
    public static float prixParJourDeLocation;
    public static Entity marque;
    public static Entity modele;
    public static Entity categorie;
    public static Entity type;
    public static Entity agence;

    public Connexion c;
    public Connection connexion;

    public VehiculeRequest() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    public void createVehicule(Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation,
                             Marque marque, Modele modele, Categorie categorie, Type type, Agence agence) throws SQLException {
        vehiculeRepository = new VehiculeRepository();
        Vehicule vehicule = new Vehicule(immatriculation,dateMiseEnCirculation,etat,nbKilometres,prixParJourDeLocation,
                marque, modele, categorie, type, agence);
        vehiculeRepository.create(vehicule);
        Collection<Entity> vehicules = vehiculeRepository.findAll();
        for (Entity entity : vehicules) {
            Vehicule v = (Vehicule) entity;
            if(v.getDateMiseEnCirculation().equals(dateMiseEnCirculation)) {
                System.out.println(v.getImmatriculation() + " | " + v.getDateMiseEnCirculation() + " | " + v.getEtat() + " | " + v.getNbKilometres()
                        + " | " +v.getPrixParJourDeLocation() + " | " + v.getMarque().getId() + " | " + v.getModele().getId()
                        + " | " + v.getCategorie().getId() + " | " + v.getType().getId() + " | " + v.getAgence().getId() + " : created");
            } else {
                System.out.println(v.getImmatriculation() + " | " + v.getDateMiseEnCirculation() + " | " + v.getEtat() + " | " + v.getNbKilometres()
                        + " | " +v.getPrixParJourDeLocation() + " | " + v.getMarque().getId() + " | " + v.getModele().getId()
                        + " | " + v.getCategorie().getId() + " | " + v.getType().getId() + " | " + v.getAgence().getId());
            }
        }
    }

    public void updateVehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation,
                             Marque marque, Modele modele, Categorie categorie, Type type, Agence agence) throws SQLException {
        vehiculeRepository = new VehiculeRepository();
        Vehicule vehicule = (Vehicule) vehiculeRepository.findById(immatriculation);
        vehicule.setImmatriculation(immatriculation);
        vehicule.setDateMiseEnCirculation(dateMiseEnCirculation);
        vehicule.setEtat(etat);
        vehicule.setNbKilometres(nbKilometres);
        vehicule.setPrixParJourDeLocation(prixParJourDeLocation);
        vehicule.setMarque(marque);
        vehicule.setModele(modele);
        vehicule.setCategorie(categorie);
        vehicule.setType(type);
        vehicule.setAgence(agence);
        vehiculeRepository.update(vehicule);
        Collection<Entity> vehicules = vehiculeRepository.findAll();
        for (Entity entity : vehicules) {
            Vehicule v = (Vehicule) entity;
            if(v.getImmatriculation().equals(immatriculation)) {
                System.out.println(v.getImmatriculation() + " | " + v.getDateMiseEnCirculation() + " | " + v.getEtat() + " | " + v.getNbKilometres()
                        + " | " +v.getPrixParJourDeLocation() + " | " + v.getMarque().getId() + " | " + v.getModele().getId()
                        + " | " + v.getCategorie().getId() + " | " + v.getType().getId() + " | " + v.getAgence().getId() +  " : uptaded");
            } else {
                System.out.println(v.getImmatriculation() + " | " + v.getDateMiseEnCirculation() + " | " + v.getEtat() + " | " + v.getNbKilometres()
                        + " | " +v.getPrixParJourDeLocation() + " | " + v.getMarque().getId() + " | " + v.getModele().getId()
                        + " | " + v.getCategorie().getId() + " | " + v.getType().getId() + " | " + v.getAgence().getId());
            }
        }
    }

    public void deleteVehicule(String immatriculation) throws SQLException {
        vehiculeRepository = new VehiculeRepository();
        Vehicule vehicule = (Vehicule) vehiculeRepository.findById(immatriculation);
        vehiculeRepository.delete(vehicule);
        findAllVehicule();
        System.out.println(vehicule.getImmatriculation() + " | " + vehicule.getDateMiseEnCirculation() + " | " + vehicule.getEtat() + " | " + vehicule.getNbKilometres()
                + " | " +vehicule.getPrixParJourDeLocation() + " | " + vehicule.getMarque().getId() + " | " + vehicule.getModele().getId()
                + " | " + vehicule.getCategorie().getId() + " | " + vehicule.getType().getId() + " | " + vehicule.getAgence().getId() + " : deleted");
    }

    public void findAllVehicule() throws SQLException {
        vehiculeRepository = new VehiculeRepository();
        Collection<Entity> vehicules = vehiculeRepository.findAll();
        for (Entity entity : vehicules) {
            Vehicule v = (Vehicule) entity;
            System.out.println(v.getImmatriculation() + " | " + v.getDateMiseEnCirculation() + " | " + v.getEtat() + " | " + v.getNbKilometres()
                    + " | " +v.getPrixParJourDeLocation() + " | " + v.getMarque().getId() + " | " + v.getModele().getId()
                    + " | " + v.getCategorie().getId() + " | " + v.getType().getId() + " | " + v.getAgence().getId());
        }
    }

    public static void main(String[] args) throws SQLException {

    }
}

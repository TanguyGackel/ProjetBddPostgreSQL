package Request;

import Entity.*;
import Repository.AgenceRepository;
import Repository.ClientRepository;
import Repository.ContratRepository;
import Repository.VehiculeRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public class ContratRequest {

    public ContratRepository contratRepository;

    public static int idcontrat;
    public static Date dateDeRetrait;
    public static Date dateDeRetour;
    public static int kmRetrait;
    public static int kmRetour;
    public static Entity client;
    public static Entity vehicule;
    public static Entity agence;

    public void createContrat(Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour,
                               Client client, Vehicule Vehicule, Agence agence) throws SQLException {
        contratRepository = new ContratRepository();
        Contrat contrat = new Contrat(idcontrat,dateDeRetrait,dateDeRetour, kmRetrait, kmRetour, client, Vehicule, agence);
        contratRepository.create(contrat);
        Collection<Entity> contrats = contratRepository.findAll();
        for (Entity entity : contrats) {
            Contrat c = (Contrat) entity;
            if(c.getDateDeRetrait()== dateDeRetrait) {
                System.out.println(c.getId() + " | " + c.getDateDeRetrait() + " | " + c.getDateDeRetour() + " | " + c.getKmRetrait()
                        + " | " +c.getKmRetour() + " | " + c.getClient().getId() + " | " + c.getVehicule().getImmatriculation()
                        + " | " + c.getAgence().getId() + " : created");
            } else {
                System.out.println(c.getId() + " | " + c.getDateDeRetrait() + " | " + c.getDateDeRetour() + " | " + c.getKmRetrait()
                        + " | " +c.getKmRetour() + " | " + c.getClient().getId() + " | " + c.getVehicule().getImmatriculation()
                        + " | " + c.getAgence().getId());
            }
        }
    }

    public void updateContrat(int idcontrat,Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour,
                              Client client, Vehicule Vehicule, Agence agence) throws SQLException {
        contratRepository = new ContratRepository();
        Contrat contrat = (Contrat) contratRepository.findById(idcontrat);
        contrat.setId(idcontrat);
        contrat.setDateDeRetrait(dateDeRetrait);
        contrat.setDateDeRetour(dateDeRetour);
        contrat.setKmRetrait(kmRetrait);
        contrat.setKmRetour(kmRetour);
        contrat.setClient(client);
        contrat.setVehicule(Vehicule);
        contrat.setAgence(agence);
        contratRepository.update(contrat);
        Collection<Entity> contrats = contratRepository.findAll();
        for (Entity entity : contrats) {
            Contrat c = (Contrat) entity;
            if(c.getDateDeRetrait() == dateDeRetrait) {
                System.out.println(c.getId() + " | " + c.getDateDeRetrait() + " | " + c.getDateDeRetour() + " | " + c.getKmRetrait()
                        + " | " +c.getKmRetour() + " | " + c.getClient().getId() + " | " + c.getVehicule().getImmatriculation()
                        + " | " + c.getAgence().getId() +  " : uptaded");
            } else {
                System.out.println(c.getId() + " | " + c.getDateDeRetrait() + " | " + c.getDateDeRetour() + " | " + c.getKmRetrait()
                        + " | " +c.getKmRetour() + " | " + c.getClient().getId() + " | " + c.getVehicule().getImmatriculation()
                        + " | " + c.getAgence().getId());
            }
        }
    }

    public void deleteContrat(int idcontrat) throws SQLException {
        contratRepository = new ContratRepository();
        Contrat contrat = (Contrat) contratRepository.findById(idcontrat);
        contratRepository.delete(contrat);
        findAllContrat();
        System.out.println(contrat.getId() + " | " + contrat.getDateDeRetrait() + " | " + contrat.getDateDeRetour() + " | " + contrat.getKmRetrait()
                + " | " + contrat.getKmRetour() + " | " + contrat.getClient().getId() + " | " + contrat.getVehicule().getImmatriculation()
                + " | " + contrat.getAgence().getId() + " : deleted");
    }

    public void findAllContrat() throws SQLException {
        contratRepository = new ContratRepository();
        Collection<Entity> contrats = contratRepository.findAll();
        for (Entity entity : contrats) {
            Contrat c = (Contrat) entity;
            System.out.println(c.getId() + " | " + c.getDateDeRetrait() + " | " + c.getDateDeRetour() + " | " + c.getKmRetrait()
                    + " | " +c.getKmRetour() + " | " + c.getClient().getId() + " | " + c.getVehicule().getImmatriculation()
                    + " | " + c.getAgence().getId());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE CONTRAT *****");
        dateDeRetrait = new Date(2020,8,25);
        dateDeRetour = new Date(2020,8,25);
        kmRetrait = 100;
        kmRetour = 100;
        client = new ClientRepository().findById(2);
        vehicule = new VehiculeRepository().findById("34-HT-67");
        agence = new AgenceRepository().findById(2);
        new ContratRequest().createContrat(dateDeRetrait,dateDeRetour,kmRetrait,kmRetour,
                (Client)client,(Vehicule)vehicule,(Agence)agence);
        System.out.println("");

        System.out.println("***** UPDATE CONTRAT *****");
        dateDeRetrait = new Date(2020,8,25);
        dateDeRetour = new Date(2020,8,25);
        kmRetrait = 150;
        kmRetour = 150;
        idcontrat = 6;
        client = new ClientRepository().findById(4);
        vehicule = new VehiculeRepository().findById("67-DC-40");
        agence = new AgenceRepository().findById(4);
        new ContratRequest().updateContrat(1,null,null,0,0,
                (Client)client,(Vehicule)vehicule,(Agence)agence);
        System.out.println("");

        System.out.println("***** DELETE CONTRAT *****");
        new ContratRequest().deleteContrat(idcontrat);
    }
}

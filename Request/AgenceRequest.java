package Request;

import Entity.Agence;
import Entity.Entity;
import Entity.Ville;
import Repository.AgenceRepository;
import Repository.VilleRepository;

import java.sql.SQLException;
import java.util.Collection;

public class AgenceRequest {

    public AgenceRepository agenceRepository;

    public static int id;
    public static int nbEmployes;
    public static Entity ville;

    public void createAgence(int nbEmployes, Ville ville) throws SQLException {
        agenceRepository = new AgenceRepository();
        Agence agence = new Agence(id,nbEmployes,ville);
        agenceRepository.create(agence);
        Collection<Entity> agences = agenceRepository.findAll();
        for (Entity entity : agences) {
            Agence a = (Agence) entity;
            if(a.getNbEmploye() == nbEmployes) {
                System.out.println(a.getId() + " | " + a.getNbEmploye() + " | " + a.getVille().getId() +  " : created");
            } else {
                System.out.println(a.getId() + " | " + a.getNbEmploye() + " | " + a.getVille().getId());
            }
        }
    }

    public void updateAgence(int id, int nbEmployes, Ville ville) throws SQLException {
        agenceRepository = new AgenceRepository();
        Agence agence = (Agence) agenceRepository.findById(id);
        agence.setNbEmploye(nbEmployes);
        agence.setVille(ville);
        agenceRepository.update(agence);
        Collection<Entity> agences = agenceRepository.findAll();
        for (Entity entity : agences) {
            Agence a = (Agence) entity;
            if(a.getId()==id) {
                System.out.println(a.getId() + " | " + a.getNbEmploye() + " | " + a.getVille().getId() +  " : uptaded");
            } else {
                System.out.println(a.getId() + " | " + a.getNbEmploye() + " | " + a.getVille().getId());
            }
        }
    }

    public void deleteAgence(int id) throws SQLException {
        agenceRepository = new AgenceRepository();
        Agence agence = (Agence) agenceRepository.findById(id);
        agenceRepository.delete(agence);
        findAllAgence();
        System.out.println(agence.getId() + " | " + agence.getNbEmploye() + " | " + agence.getVille().getId() +  " : deleted");
    }

    public void findAllAgence() throws SQLException {
        agenceRepository = new AgenceRepository();
        Collection<Entity> agences = agenceRepository.findAll();
        for (Entity entity : agences) {
            Agence a = (Agence) entity;
            System.out.println(a.getId() + " | " + a.getNbEmploye() + " | " + a.getVille().getId());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE AGENCE *****");
        nbEmployes = 3500;
        ville = new VilleRepository().findById(2);
        new AgenceRequest().createAgence(nbEmployes,(Ville)ville);
        System.out.println("");

        System.out.println("***** UPDATE AGENCE *****");
        nbEmployes = 9000;
        id = 6;
        ville = new VilleRepository().findById(2);
        new AgenceRequest().updateAgence(id, nbEmployes,(Ville)ville);
        System.out.println("");

        System.out.println("***** DELETE AGENCE *****");
        new AgenceRequest().deleteAgence(id);
    }
}

package Request;

import Entity.Entity;
import Entity.Ville;
import Repository.VilleRepository;

import java.sql.SQLException;
import java.util.Collection;

public class VilleRequest {

    public VilleRepository villeRepository;

    public static int id;
    public static String nom;
    public static int nbHabitant;

    public void createVille(String nom, int nbHabitant) throws SQLException {
        villeRepository = new VilleRepository();
        Ville ville = new Ville(nom,nbHabitant);
        villeRepository.create(ville);
        Collection<Entity> villes = villeRepository.findAll();
        for (Entity entity : villes) {
            Ville e = (Ville) entity;
            if(e.getNom().equals(nom)) {
                System.out.println(e.getId() + " | " + e.getNom() + " | " + e.getNbHabitant() + " : created");
            } else {
                System.out.println(e.getId() + " | " + e.getNom() + " | " + e.getNbHabitant());
            }
        }
    }

    public void updateVille(int id, String nom, int nbHabitant) throws SQLException {
        villeRepository = new VilleRepository();
        Ville ville = (Ville) villeRepository.findById(id);
        ville.setNom(nom);
        ville.setNbHabitant(nbHabitant);
        villeRepository.update(ville);
        Collection<Entity> villes = villeRepository.findAll();
        for (Entity entity : villes) {
            Ville e = (Ville) entity;
            if(e.getId()==id) {
                System.out.println(e.getId() + " | " + e.getNom() + " | " + e.getNbHabitant() + " : updated");
            } else {
                System.out.println(e.getId() + " | " + e.getNom() + " | " + e.getNbHabitant());
            }
        }
    }

    public void deleteVille(int id) throws SQLException {
        villeRepository = new VilleRepository();
        Ville ville = (Ville) villeRepository.findById(id);
        villeRepository.delete(ville);
        findAllVille();
        System.out.println(ville.getId() + " | " + ville.getNom() + " | " + ville.getNbHabitant() + " : deleted");
    }

    public void findAllVille() throws SQLException {
        villeRepository = new VilleRepository();
        Collection<Entity> villes = villeRepository.findAll();
        for (Entity entity : villes) {
            Ville e = (Ville) entity;
            System.out.println(e.getId() + " | " + e.getNom() + " | " + e.getNbHabitant());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE VILLE *****");
        nom = "¨PARIS";
        nbHabitant = 300000;
        new VilleRequest().createVille(nom,nbHabitant);
        System.out.println("");

        System.out.println("***** UPDATE VILLE *****");
        nom = "Strasbourg";
        id = 6;
        nbHabitant = 900000;
        new VilleRequest().updateVille(id, nom,nbHabitant);
        System.out.println("");

        System.out.println("***** DELETE VILLE *****");
        new VilleRequest().deleteVille(id);
    }
}

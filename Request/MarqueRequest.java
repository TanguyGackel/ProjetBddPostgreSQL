package Request;

import Entity.Entity;
import Entity.Marque;
import Repository.MarqueRepository;

import java.sql.SQLException;
import java.util.Collection;

public class MarqueRequest {

    public MarqueRepository marqueRepository;

    public static int id;
    public static String nommarque;

    public void createMarque(String nommarque) throws SQLException {
        marqueRepository = new MarqueRepository();
        Marque marque = new Marque(id,nommarque);
        marqueRepository.create(marque);
        Collection<Entity> marques = marqueRepository.findAll();
        for (Entity entity : marques) {
            Marque e = (Marque) entity;
            if(e.getNom().equals(nommarque)) {
                System.out.println(e.getId() + " | " + e.getNom() + " : created");
            } else {
                System.out.println(e.getId() + " | " + e.getNom());
            }
        }
    }

    public void updateMarque(int id, String nommarque) throws SQLException {
        marqueRepository = new MarqueRepository();
        Marque marque = (Marque) marqueRepository.findById(id);
        marque.setNom(nommarque);
        marqueRepository.update(marque);
        Collection<Entity> marques = marqueRepository.findAll();
        for (Entity entity : marques) {
            Marque e = (Marque) entity;
            if(e.getId()==id) {
                System.out.println(e.getId() + " | " + e.getNom() + " : updated");
            } else {
                System.out.println(e.getId() + " | " + e.getNom());
            }
        }
    }

    public void deleteMarque(int id) throws SQLException {
        marqueRepository = new MarqueRepository();
        Marque marque = (Marque) marqueRepository.findById(id);
        marqueRepository.delete(marque);
        findAllMarque();
        System.out.println(marque.getId() + " | " + marque.getNom() + " : deleted");
    }

    public void findAllMarque() throws SQLException {
        marqueRepository = new MarqueRepository();
        Collection<Entity> marques = marqueRepository.findAll();
        for (Entity entity : marques) {
            Marque e = (Marque) entity;
            System.out.println(e.getId() + " | " + e.getNom());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE MARQUE *****");
        nommarque = "Mercedez";
        new MarqueRequest().createMarque(nommarque);
        System.out.println("");

        System.out.println("***** UPDATE MARQUE *****");
        nommarque = "Ferrari";
        id = 6;
        new MarqueRequest().updateMarque(id, nommarque);
        System.out.println("");

        System.out.println("***** DELETE MARQUE *****");
        new MarqueRequest().deleteMarque(id);
    }
}

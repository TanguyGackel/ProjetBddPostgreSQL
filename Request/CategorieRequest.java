package Request;

import Entity.Categorie;
import Entity.Entity;
import Repository.CategorieRepository;

import java.sql.SQLException;
import java.util.Collection;

public class CategorieRequest {

    public CategorieRepository categorieRepository;

    public static int id;
    public static String libellecategorie;

    public void createCategorie(String libellecategorie) throws SQLException {
        categorieRepository = new CategorieRepository();
        Categorie categorie = new Categorie(id,libellecategorie);
        categorieRepository.create(categorie);
        Collection<Entity> categories = categorieRepository.findAll();
        for (Entity entity : categories) {
            Categorie e = (Categorie) entity;
            if(e.getLibelle().equals(libellecategorie)) {
                System.out.println(e.getId() + " | " + e.getLibelle() + " : created");
            } else {
                System.out.println(e.getId() + " | " + e.getLibelle());
            }
        }
    }

    public void updateCategorie(int id, String libellecategorie) throws SQLException {
        categorieRepository = new CategorieRepository();
        Categorie categorie = (Categorie) categorieRepository.findById(id);
        categorie.setLibelle(libellecategorie);
        categorieRepository.update(categorie);
        Collection<Entity> categories = categorieRepository.findAll();
        for (Entity entity : categories) {
            Categorie e = (Categorie) entity;
            if(e.getId()==id) {
                System.out.println(e.getId() + " | " + e.getLibelle() + " : updated");
            } else {
                System.out.println(e.getId() + " | " + e.getLibelle());
            }
        }
    }

    public void deleteCategorie(int id) throws SQLException {
        categorieRepository = new CategorieRepository();
        Categorie categorie = (Categorie) categorieRepository.findById(id);
        categorieRepository.delete(categorie);
        findAllCategorie();
        System.out.println(categorie.getId() + " | " + categorie.getLibelle() + " : deleted");
    }

    public void findAllCategorie() throws SQLException {
        categorieRepository = new CategorieRepository();
        Collection<Entity> categories = categorieRepository.findAll();
        for (Entity entity : categories) {
            Categorie e = (Categorie) entity;
            System.out.println(e.getId() + " | " + e.getLibelle());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE CATEGORIE *****");
        libellecategorie = "Cabriolet";
        new CategorieRequest().createCategorie(libellecategorie);
        System.out.println("");

        System.out.println("***** UPDATE CATEGORIE *****");
        libellecategorie = "Berline";
        id = 6;
        new CategorieRequest().updateCategorie(id, libellecategorie);
        System.out.println("");

        System.out.println("***** DELETE CATEGORIE *****");
        new CategorieRequest().deleteCategorie(id);
    }
}

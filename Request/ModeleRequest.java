package Request;

import Entity.Entity;
import Entity.Modele;
import Repository.ModeleRepository;

import java.sql.SQLException;
import java.util.Collection;

public class ModeleRequest {

    public ModeleRepository modeleRepository;

    public static int id;
    public static String denomination;
    public static int PuissanceFiscale;

    public void createModele(String denomination, int PuissanceFiscale) throws SQLException {
        modeleRepository = new ModeleRepository();
        Modele modele = new Modele(id,denomination,PuissanceFiscale);
        modeleRepository.create(modele);
        Collection<Entity> modeles = modeleRepository.findAll();
        for (Entity entity : modeles) {
            Modele e = (Modele) entity;
            if(e.getDenomination().equals(denomination)) {
                System.out.println(e.getId() + " | " + e.getDenomination() + " | " + e.getPuissanceFiscale() + "CV : created");
            } else {
                System.out.println(e.getId() + " | " + e.getDenomination() + " | " + e.getPuissanceFiscale());
            }
        }
    }

    public void updateModele(int id, String denomination, int PuissanceFiscale) throws SQLException {
        modeleRepository = new ModeleRepository();
        Modele modele = (Modele) modeleRepository.findById(id);
        modele.setDenomination(denomination);
        modele.setPuissanceFiscale(PuissanceFiscale);
        modeleRepository.update(modele);
        Collection<Entity> modeles = modeleRepository.findAll();
        for (Entity entity : modeles) {
            Modele e = (Modele) entity;
            if(e.getId()==id) {
                System.out.println(e.getId() + " | " + e.getDenomination() + " | " + e.getPuissanceFiscale() + "CV : updated");
            } else {
                System.out.println(e.getId() + " | " + e.getDenomination() + " | " + e.getPuissanceFiscale());
            }
        }
    }

    public void deleteModele(int id) throws SQLException {
        modeleRepository = new ModeleRepository();
        Modele modele = (Modele) modeleRepository.findById(id);
        modeleRepository.delete(modele);
        findAllModele();
        System.out.println(modele.getId() + " | " + modele.getDenomination() + " | " + modele.getPuissanceFiscale() + "CV : deleted");
    }

    public void findAllModele() throws SQLException {
        modeleRepository = new ModeleRepository();
        Collection<Entity> modeles = modeleRepository.findAll();
        for (Entity entity : modeles) {
            Modele e = (Modele) entity;
            System.out.println(e.getId() + " | " + e.getDenomination() + " | " + e.getPuissanceFiscale());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE MODELE *****");
        denomination = "Megane";
        PuissanceFiscale = 120;
        new ModeleRequest().createModele(denomination,PuissanceFiscale);
        System.out.println("");

        System.out.println("***** UPDATE MODELE *****");
        denomination = "Kangoo";
        id = 6;
        PuissanceFiscale = 80;
        new ModeleRequest().updateModele(id, denomination,PuissanceFiscale);
        System.out.println("");

        System.out.println("***** DELETE MODELE *****");
        new ModeleRequest().deleteModele(id);
    }
}

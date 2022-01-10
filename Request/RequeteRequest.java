package Request;

import Connexion.Connexion;
import Entity.*;
import Repository.*;

import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class RequeteRequest {

    Scanner input = new Scanner(System.in);

    public Connexion c;
    public Connection connexion;
    public PreparedStatement statement;
    String requete;

    public RequeteRequest() {
        c = new Connexion();
        connexion = c.Connect();
    }

    public void request() throws SQLException {
        System.out.println("requête ?");
        switch (input.next()){
            case "2":
                Client client = new Client();
                Ville ville = new Ville();
                ClientRepository clientRepository = new ClientRepository();
                VilleRepository villeRepository = new VilleRepository();
                AgenceRepository agenceRepository = new AgenceRepository();
                VehiculeRepository vehiculeRepository = new VehiculeRepository();
                ContratRepository contratRepository = new ContratRepository();
                MarqueRepository marqueRepository = new MarqueRepository();
                ModeleRepository modeleRepository = new ModeleRepository();
                CategorieRepository categorieRepository = new CategorieRepository();
                TypeRepository typeRepository = new TypeRepository();

                System.out.println("Nouveau client ? y or n");
                String res = input.next();

                if (res.equals("y")) {
                    System.out.println("Votre nom :");
                    client.setNom(input.next());

                    input.nextLine();

                    System.out.println("Adresse :");
                    client.setAdresse(input.nextLine());

                    System.out.println("Code postal :");
                    client.setCodePostal(input.nextInt());

                    System.out.println("Nouvelle ville ? y or n");
                    res = input.next();

                    if (res.equals("y")) {

                        System.out.println("Nom de la ville :");
                        input.nextLine();
                        ville.setNom(input.nextLine());

                        System.out.println("Nombre d'habitants :");
                        ville.setNbHabitant(input.nextInt());

                        ville.setId(villeRepository.findAll().size()+1);
                        villeRepository.create(ville);
                        client.setVille(ville);
                    } else {
                        System.out.println("id de votre ville :");
                        client.setVille((Ville)villeRepository.findById(input.nextInt()));
                    }
                    client.setId(clientRepository.findAll().size()+1);
                    clientRepository.create(client);
                } else {
                    System.out.println("id client :");
                    client = (Client) clientRepository.findById(input.nextInt());
                }
                System.out.println("********** CLIENT **********");
                System.out.println(client.getId() + " " + client.getNom() + " " + client.getAdresse() + " " + client.getCodePostal() + " " + client.getVille().getNom());

                Agence agenceDepart = new Agence(agenceRepository.findAll().size()+1,15,(Ville) villeRepository.findById(2));
                System.out.println("********** New Agence **********");
                System.out.println(agenceDepart.getId() + " " + agenceDepart.getNbEmploye());
                agenceRepository.create(agenceDepart);

                Agence agenceRetour = new Agence(agenceRepository.findAll().size()+1,50,(Ville) villeRepository.findById(3));
                System.out.println("********** New Agence 2 **********");
                System.out.println(agenceRetour.getId() + " " + agenceRetour.getNbEmploye());
                agenceRepository.create(agenceRetour);

                System.out.println("********** New Vehicule **********");
                System.out.println("Immatriculation ?");
                input.nextLine();
                Vehicule vehicule = new Vehicule(input.nextLine(), new Date(2012-10-10), "bon état", 100000, 20,(Marque)marqueRepository.findById(2),(Modele)modeleRepository.findById(2),(Categorie)categorieRepository.findById(2),(Type)typeRepository.findById(2),agenceDepart);
                System.out.println(vehicule.getImmatriculation() + " " + vehicule.getDateMiseEnCirculation() + " " + vehicule.getEtat() + " " + vehicule.getNbKilometres() + " " + vehicule.getPrixParJourDeLocation() + " " + agenceDepart.getId());
                vehiculeRepository.create(vehicule);

                System.out.println("********** New Contrat **********");
                System.out.println("Combien de temps de location ? (indiquez la date au format dd/mm/aaaa)");
                String date = input.nextLine();
                String d = date.split("/")[0];
                String m = date.split("/")[1];
                String y = date.split("/")[2];
                Calendar today = Calendar.getInstance();
                Date debutContrat = new Date(today.get(Calendar.YEAR)-1900,today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH));
                Date finContrat = new Date(Integer.parseInt(y)-1900,Integer.parseInt(m)-1,Integer.parseInt(d));
                Contrat contrat = new Contrat(contratRepository.findAll().size()+1,debutContrat, finContrat,100000,0,client,vehicule,agenceRetour);
                contratRepository.create(contrat);
                System.out.println(contrat.getId() + " " + contrat.getDateDeRetrait() + " " + contrat.getDateDeRetour() + " " + contrat.getKmRetrait() + " " + contrat.getKmRetour() + " " + contrat.getClient().getNom() + " " + contrat.getVehicule().getImmatriculation() + " " + contrat.getAgence().getId());
            case "3":
                System.out.println("id de votre véhicule :");
                VehiculeRepository vehiculeRepository1 = new VehiculeRepository();
                vehicule = (Vehicule)vehiculeRepository1.findById(input.next());
                System.out.println("kilomètres totaux ? Avant :" + vehicule.getNbKilometres());
                vehicule.setNbKilometres(input.nextInt());
                System.out.println("********** Agence avant **********");
                System.out.println(vehicule.getAgence().getId());
                System.out.println("id de l'agence de retour :");
                AgenceRepository agenceRepository1 = new AgenceRepository();
                vehicule.setAgence((Agence)agenceRepository1.findById(input.nextInt()));
                vehiculeRepository1.update(vehicule);
                System.out.println("********** Agence après **********");
                System.out.println(vehicule.getAgence().getId());
            case "4" :
                System.out.println("id de votre contrat :");
                ContratRepository contratRepository1 = new ContratRepository();
                contrat = (Contrat) contratRepository1.findById(input.nextInt());
                contrat.setKmRetour(contrat.getVehicule().getNbKilometres());
                contratRepository1.update(contrat);
                System.out.println("********** New Facture **********");
                Facture facture = new Facture();
                FactureRepository factureRepository = new FactureRepository();
                facture.setId(factureRepository.findAll().size()+1);
                facture.setContrat(contrat);
                facture.setMontant((int)contrat.getVehicule().getPrixParJourDeLocation()*50);
                factureRepository.create(facture);
                System.out.println(facture.getId() + " " + facture.getContrat().getId());
                break;
            case "5":
                requete = "select a.idagence, sum(facture.montant)\n" +
                        "from facture join contrat c on c.idcontrat = facture.idcontrat\n" +
                        "         \t join agence a on a.idagence = c.idagencederetour\n" +
                        "where extract(months from c.datederetour) = ?\n" +
                        "and a.idagence = ?\n" +
                        "group by a.idagence;";
                statement = connexion.prepareStatement(requete);


                System.out.println("Quelle mois ? (en chiffre)");
                statement.setInt(1, Integer.parseInt(input.next()));
                System.out.println("Quelle agence ? (id)");
                statement.setInt(2, Integer.parseInt(input.next()));

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getInt(1) + " | " + keys.getFloat(2) + " |");
                    }
                }
                break;
            case "6":
                requete = "select m.nommarque, count(v.immatriculation) as nbVehicule\n" +
                        "from vehicule v join marque m on v.idmarque = m.idmarque\n" +
                        "group by m.nommarque;";
                statement = connexion.prepareStatement(requete);

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getString(1) + " | " + keys.getInt(2) + " |");
                    }
                }
                break;
            case "7":
                requete = "select client.nomClient, count(contrat.idContrat) as nbLocations\n" +
                        "from contrat\n" +
                        "inner join client on client.idclient = contrat.idclient\n" +
                        "where contrat.idagencederetour = ? " +
                        "and extract(year from contrat.datederetour)=?\n" +
                        "group by client.nomcLient\n" +
                        "order by count(contrat.idcontrat) desc;";
                statement = connexion.prepareStatement(requete);

                System.out.println("Quelle agence ? (id)");
                statement.setInt(1, Integer.parseInt(input.next()));
                System.out.println("Quelle année ? ");
                statement.setInt(2, Integer.parseInt(input.next()));

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getString(1) + " | " + keys.getInt(2) + " |");
                    }
                }
                break;
            case "8":
                requete = "select c.libellecategorie, sum(f.montant) as montant\n" +
                        "from categorie c join vehicule v on c.idcategorie = v.idcategorie\n" +
                        "join agence a on v.idagence = a.idagence\n" +
                        "join contrat c2 on a.idagence = c2.idagencederetour\n" +
                        "join facture f on c2.idcontrat = f.idcontrat\n" +
                        "group by c.libellecategorie;";
                statement = connexion.prepareStatement(requete);

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getString(1) + " | " + keys.getFloat(2) + " |");
                    }
                }
                break;
            case "9":
                requete = "select t.libelletype, sum(f.montant) as montant\n" +
                        "from type t join vehicule v on t.idtype = v.idtype\n" +
                        "            join agence a on v.idagence = a.idagence\n" +
                        "            join contrat c2 on a.idagence = c2.idagencederetour\n" +
                        "            join facture f on c2.idcontrat = f.idcontrat\n" +
                        "group by t.libelletype;";
                statement = connexion.prepareStatement(requete);

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getString(1) + " | " + keys.getFloat(2) + " |");
                    }
                }
                break;
            case "10":
                requete = "select a.idagence, count(v.immatriculation) as nbVehicule\n" +
                        "from vehicule v join agence a on a.idagence = v.idagence\n" +
                        "where v.nbkilometres >= 150000\n" +
                        "and v.datemiseencirculation < date(now()-interval '2' year)\n" +
                        "group by a.idagence;";
                statement = connexion.prepareStatement(requete);

                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getInt(1) + " |");
                    }
                }
                break;
            case "11":
                requete = "SELECT a.idagence , sum(facture.montant)\n" +
                        "FROM facture\n" +
                        "         JOIN contrat c on c.idcontrat = facture.idcontrat\n" +
                        "         JOIN agence a on a.idagence = c.idagencederetour\n" +
                        "WHERE EXTRACT(year FROM c.datederetour) = ?\n" +
                        "GROUP BY a.idagence;";
                statement = connexion.prepareStatement(requete);

                System.out.println("Quelle année ?");
                statement.setInt(1, Integer.parseInt(input.next()));
                try(ResultSet keys = statement.executeQuery()) {
                    while(keys.next()) {
                        System.out.println("| " + keys.getInt(1) + " | " + keys.getFloat(2) + " |");
                    }
                }
                break;
            default:
                System.out.println("Pas de requête ..");
        }

    }

    public static void main(String[] args) throws SQLException {
        new RequeteRequest().request();
    }
}

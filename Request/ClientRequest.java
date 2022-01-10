package Request;

import Entity.Client;
import Entity.Entity;
import Entity.Ville;
import Repository.ClientRepository;
import Repository.VilleRepository;

import java.sql.SQLException;
import java.util.Collection;

public class ClientRequest {

    public ClientRepository clientRepository;

    public static int id;
    public static String nomClient;
    public static String adresseClient;
    public static int codePostalClient;
    public static Entity ville;

    public void createClient(String nomClient, String adresseClient, int codePostalClient, Ville ville) throws SQLException {
        clientRepository = new ClientRepository();
        Client client = new Client(id,nomClient,adresseClient,codePostalClient,ville);
        clientRepository.create(client);
        Collection<Entity> clients = clientRepository.findAll();
        for (Entity entity : clients) {
            Client c = (Client) entity;
            if(c.getNom().equals(nomClient)) {
                System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getAdresse() + " | " + c.getCodePostal() + " | " + c.getVille().getId() +  " : created");
            } else {
                System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getAdresse() + " | " + c.getCodePostal() + " | " + c.getVille().getId());
            }
        }
    }

    public void updateClient(int id, String nomClient, String adresseClient, int codePostalClient, Ville ville) throws SQLException {
        clientRepository = new ClientRepository();
        Client client = (Client) clientRepository.findById(id);
        client.setNom(nomClient);
        client.setAdresse(adresseClient);
        client.setCodePostal(codePostalClient);
        client.setVille(ville);
        clientRepository.update(client);
        Collection<Entity> clients = clientRepository.findAll();
        for (Entity entity : clients) {
            Client c = (Client) entity;
            if(c.getId()==id) {
                System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getAdresse() + " | " + c.getCodePostal() + " | " + c.getVille().getId() +  " : uptaded");
            } else {
                System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getAdresse() + " | " + c.getCodePostal() + " | " + c.getVille().getId());
            }
        }
    }

    public void deleteClient(int id) throws SQLException {
        clientRepository = new ClientRepository();
        Client client = (Client) clientRepository.findById(id);
        clientRepository.delete(client);
        findAllClient();
        System.out.println(client.getId() + " | " + client.getNom() + " | " + client.getAdresse() + " | " + client.getCodePostal() + " | " + client.getVille().getId() +  " : deleted");
    }

    public void findAllClient() throws SQLException {
        clientRepository = new ClientRepository();
        Collection<Entity> clients = clientRepository.findAll();
        for (Entity entity : clients) {
            Client c = (Client) entity;
            System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getAdresse() + " | " + c.getCodePostal() + " | " + c.getVille().getId());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE CLIENT *****");
        nomClient = "Eren YAEGER";
        adresseClient = "Eldia";
        codePostalClient = 69;
        ville = new VilleRepository().findById(2);
        new ClientRequest().createClient(nomClient,adresseClient,codePostalClient,(Ville)ville);
        System.out.println("");

        System.out.println("***** UPDATE CLIENT *****");
        nomClient = "Mikasa ACKERMAN";
        adresseClient = "MAHR";
        codePostalClient = 420;
        id = 6;
        ville = new VilleRepository().findById(2);
        new ClientRequest().updateClient(id, nomClient,adresseClient,codePostalClient,(Ville)ville);
        System.out.println("");

        System.out.println("***** DELETE CLIENT *****");
        new ClientRequest().deleteClient(id);
    }
}

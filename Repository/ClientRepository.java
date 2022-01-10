package Repository;

import Connexion.Connexion;
import Entity.Client;
import Entity.Entity;
import Entity.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientRepository implements Interface {

    public Connection connexion;
    public Connexion c;

    public PreparedStatement statement = null;
    public String requete;

    public ClientRepository() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    @Override
    public Collection<Entity> findAll() {
        Collection<Entity> clients = new ArrayList<>();
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");

            while (resultSet.next()) {
                Client client = new Client();
                VilleRepository villeRepository = new VilleRepository();
                client.setId(resultSet.getInt("idclient"));
                client.setNom(resultSet.getString("nomclient"));
                client.setAdresse(resultSet.getString("adresseclient"));
                client.setCodePostal(resultSet.getInt("codepostalclient"));
                client.setVille((Ville)villeRepository.findById(resultSet.getInt("idville")));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Entity findById(int id) {
        Client client = new Client();

        try {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client WHERE idclient="+id);

            while (resultSet.next()) {
                VilleRepository villeRepository = new VilleRepository();
                client.setId(resultSet.getInt("idclient"));
                client.setNom(resultSet.getString("nomclient"));
                client.setAdresse(resultSet.getString("adresseclient"));
                client.setCodePostal(resultSet.getInt("codepostalclient"));
                client.setVille((Ville)villeRepository.findById(resultSet.getInt("idville")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }

        return client;
    }

    @Override
    public void create(Entity entity) {
        requete = "insert into client(nomclient,adresseclient,codepostalclient,idville) values (?,?,?,?)";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Client)entity).getNom());
            statement.setString(2, ((Client)entity).getAdresse());
            statement.setInt(3, ((Client)entity).getCodePostal());
            statement.setInt(4, ((Client)entity).getVille().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        requete = "update client set nomclient = ?, adresseclient = ?, codepostalclient = ?, idville = ? where idclient = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setString(1, ((Client)entity).getNom());
            statement.setString(2, ((Client)entity).getAdresse());
            statement.setInt(3, ((Client)entity).getCodePostal());
            statement.setInt(4, ((Client)entity).getVille().getId());
            statement.setInt(5,((Client)entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) {
        requete = "delete from client where idclient = ?";

        try {
            statement = connexion.prepareStatement(requete);
            statement.setInt(1,((Client) entity).getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
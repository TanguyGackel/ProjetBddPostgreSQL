package Entity;

import java.sql.Date;

public class Contrat extends Entity {

    private int id;
    private Date dateDeRetrait;
    private Date dateDeRetour;
    private int kmRetrait;
    private int kmRetour;
    private Client client;
    private Vehicule vehicule;
    private Agence agence;

    public Contrat() {
        this(0);
    }

    public Contrat(int id) {
        this(id, null);
    }

    public Contrat(int id, Date dateDeRetrait) {
        this(id,dateDeRetrait,null);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour) {
        this(id,dateDeRetrait,dateDeRetour,0);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour, int kmRetrait) {
        this(id,dateDeRetrait,dateDeRetour,kmRetrait,0);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour) {
        this(id,dateDeRetrait,dateDeRetour, kmRetrait, kmRetour, null);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour, Client client) {
        this(id,dateDeRetrait,dateDeRetour, kmRetrait, kmRetour, client,null);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour, Client client, Vehicule vehicule) {
        this(id,dateDeRetrait,dateDeRetour, kmRetrait, kmRetour, client,vehicule, null);
    }

    public Contrat(int id, Date dateDeRetrait, Date dateDeRetour, int kmRetrait, int kmRetour, Client client, Vehicule vehicule, Agence agence) {
        super();
        this.id = id;
        this.dateDeRetrait = dateDeRetrait;
        this.dateDeRetour = dateDeRetour;
        this.kmRetrait = kmRetrait;
        this.kmRetour = kmRetour;
        this.client = client;
        this.vehicule = vehicule;
        this.agence = agence;
    }

    public int getId() {
        return id;
    }

    public Agence getAgence() {
        return agence;
    }

    public Date getDateDeRetour() {
        return dateDeRetour;
    }

    public Date getDateDeRetrait() {
        return dateDeRetrait;
    }

    public Client getClient() {
        return client;
    }

    public int getKmRetour() {
        return kmRetour;
    }

    public int getKmRetrait() {
        return kmRetrait;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDateDeRetour(Date dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public void setDateDeRetrait(Date dateDeRetrait) {
        this.dateDeRetrait = dateDeRetrait;
    }

    public void setKmRetour(int kmRetour) {
        this.kmRetour = kmRetour;
    }

    public void setKmRetrait(int kmRetrait) {
        this.kmRetrait = kmRetrait;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
}

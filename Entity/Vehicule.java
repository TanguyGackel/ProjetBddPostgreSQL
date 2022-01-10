package Entity;

import java.sql.Date;

public class Vehicule extends Entity {

    private String immatriculation;
    private Date dateMiseEnCirculation;
    private String etat;
    private int nbKilometres;
    private float prixParJourDeLocation;
    private Marque marque;
    private Modele modele;
    private Categorie categorie;
    private Type type;
    private Agence agence;

    public Vehicule() {
        this(null);
    }

    public Vehicule(String immatriculation) {
        this(immatriculation, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation) {
        this(immatriculation, dateMiseEnCirculation, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat) {
        this(immatriculation,dateMiseEnCirculation,etat, 0);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, 0);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation, Marque marque) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, marque, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation, Marque marque, Modele modele) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, marque, modele, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation, Marque marque, Modele modele, Categorie categorie) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, marque, modele, categorie, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation, Marque marque, Modele modele, Categorie categorie, Type type) {
        this(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, marque, modele, categorie, type, null);
    }

    public Vehicule(String immatriculation, Date dateMiseEnCirculation, String etat, int nbKilometres, float prixParJourDeLocation, Marque marque, Modele modele, Categorie categorie, Type type, Agence agence) {
        super();
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.etat = etat;
        this.nbKilometres = nbKilometres;
        this.prixParJourDeLocation = prixParJourDeLocation;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        this.type = type;
        this.agence = agence;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Date getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(Date dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public float getPrixParJourDeLocation() {
        return prixParJourDeLocation;
    }

    public Agence getAgence() {
        return agence;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public int getNbKilometres() {
        return nbKilometres;
    }

    public Marque getMarque() {
        return marque;
    }

    public Modele getModele() {
        return modele;
    }

    public String getEtat() {
        return etat;
    }

    public Type getType() {
        return type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public void setNbKilometres(int nbKilometres) {
        this.nbKilometres = nbKilometres;
    }

    public void setPrixParJourDeLocation(float prixParJourDeLocation) {
        this.prixParJourDeLocation = prixParJourDeLocation;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

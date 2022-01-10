package Entity;

public class Agence extends Entity {

    private int id;
    private int nbEmploye;
    private Ville ville;

    public Agence() {
        this(0);
    }

    public Agence(int id) {
        this(id, 0);
    }

    public Agence(int id, int nbEmploye) {
        this(id,nbEmploye,null);
    }

    public Agence(int id, int nbEmploye, Ville ville) {
        super();
        this.id = id;
        this.nbEmploye = nbEmploye;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbEmploye() {
        return nbEmploye;
    }

    public void setNbEmploye(int nbEmploye) {
        this.nbEmploye = nbEmploye;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}

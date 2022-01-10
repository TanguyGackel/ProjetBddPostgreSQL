package Entity;

public class Client extends Entity {
    
    private int id;
    private String nom;
    private String adresse;
    private int codePostal;
    private Ville ville;
    
    public Client() {
        this(0);
    }
    
    public Client(int id) {
        this(id, null);
    }
    
    public Client(int id, String nom) {
        this(id,nom,null);
    }
    
    public Client(int id, String nom, String adresse) {
        this(id,nom,adresse,0);
    }
    
    public Client(int id, String nom, String adresse, int codePostal) {
        this(id,nom,adresse,codePostal,null);
    }
    
    public Client(int id, String nom, String adresse, int codePostal, Ville ville) {
        super();
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public Ville getVille() {
        return ville;
    }

    public String getNom() {
        return nom;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}

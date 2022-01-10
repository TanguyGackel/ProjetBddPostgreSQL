package Entity;

public class Facture extends Entity {
    
    private int id;
    private int montant;
    private Contrat contrat;
    
    public Facture() {
        this(0);
    }
    
    public Facture(int id) {
        this(id, 0);
    }
    
    public Facture(int id, int montant) {
        this(id, montant, null);
    }
    
    public Facture(int id, int montant, Contrat contrat) {
        super();
        this.id = id;
        this.montant = montant;
        this.contrat = contrat;
    }

    public int getId() {
        return id;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public int getMontant() {
        return montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}

package Entity;

public class Modele extends Entity{

    private int id;
    private String denomination;
    private int puissanceFiscale;

    public Modele() {
        this(0);
    }

    public Modele(int id) {
        this(id, null);
    }

    public Modele(int id, String denomination) {
        this(id,denomination,0);
    }

    public Modele(int id, String denomination, int puissanceFiscale) {
        super();
        this.id = id;
        this.denomination = denomination;
        this.puissanceFiscale = puissanceFiscale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissanceFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }
}

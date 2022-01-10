package Entity;

public class LoadEntity {

    String requete;

    public String createTables() {
        requete = createVille() + createMarque() + createType() + createCategorie() + createModele() + createAgence() + createClient() + createVehicule() + createContrat() + createFacture();
        return requete;
    }

    public String destroyTables() {
        requete = "DROP TABLE IF EXISTS facture, contrat, vehicule, client, agence, modele, categorie, type, marque, ville CASCADE;\n";
        return requete;
    }

    public String insertTables() {
        requete = insertVille() + insertMarque() + insertType() + insertCategorie() + insertModele() + insertAgence() + insertClient() + insertVehicule() + insertContrat() + insertFacture();
        return requete;
    }

    public String createVille() {
        requete = "CREATE TABLE ville(\n" +
                "            idVille SERIAL,\n" +
                "            nomVille VARCHAR(255),\n" +
                "    nombreHabitants INT,\n" +
                "    PRIMARY KEY (idVille)\n" +
                ");";
        return requete;
    }

    public String insertVille() {
        requete = "insert into ville values(default, 'Metropolis', 3000000);\n" +
                "insert into ville values(default, 'Springfield', 15000000);\n" +
                "insert into ville values(default, 'Gotham City', 500000);\n" +
                "insert into ville values(default, 'New York', 250000);\n" +
                "insert into ville values(default, 'Orléan', 5000);\n";
        return requete;
    }

    public String createMarque() {
        requete = "CREATE TABLE marque(\n" +
                "            idMarque SERIAL,\n" +
                "            nomMarque VARCHAR(255),\n" +
                "    PRIMARY KEY (idMarque)\n" +
                ");";
        return requete;
    }

    public String insertMarque() {
        requete = "insert into marque values(default, 'Peugeot');\n" +
                "insert into marque values(default, 'BMW');\n"+
                "insert into marque values(default, 'Audi');\n"+
                "insert into marque values(default, 'Citroën');\n"+
                "insert into marque values(default, 'Fiat');\n";
        return requete;
    }

    public String createType() {
        requete = "CREATE TABLE type(\n" +
                "            idType SERIAL,\n" +
                "            libelleType VARCHAR(255),\n" +
                "    PRIMARY KEY (idType)\n" +
                ");";
        return requete;
    }

    public String insertType() {
        requete = "insert into type values(default, 'SP95');\n" +
                "insert into type values(default, 'SP98');\n"+
                "insert into type values(default, 'SP95-E10');\n"+
                "insert into type values(default, 'Gazole');\n"+
                "insert into type values(default, 'Diesel');\n";
        return requete;
    }

    public String createCategorie() {
        requete = "CREATE TABLE categorie(\n" +
                "            idCategorie SERIAL,\n" +
                "            libelleCategorie VARCHAR(255),\n" +
                "    PRIMARY KEY (idCategorie)\n" +
                ");";
        return requete;
    }

    public String insertCategorie() {
        requete = "insert into categorie values(default, 'Supersportive');\n" +
                "insert into categorie values(default, '4x4');\n"+
                "insert into categorie values(default, 'Rally');\n"+
                "insert into categorie values(default, 'Buggy');\n"+
                "insert into categorie values(default, 'Classique');\n";
        return requete;
    }

    public String createModele() {
        requete = "CREATE TABLE modele(\n" +
                "            idModele SERIAL,\n" +
                "            denomination VARCHAR(255),\n" +
                "    puissanceFiscale INT,\n" +
                "    PRIMARY KEY (idModele)\n" +
                ");";
        return requete;
    }

    public String insertModele() {
        requete = "insert into modele values(default, 'Megane', 95);\n" +
                "insert into modele values(default, 'Panda', 70);\n"+
                "insert into modele values(default, 'GTX', 110);\n"+
                "insert into modele values(default, 'Sport', 115);\n"+
                "insert into modele values(default, 'A5', 130);\n";
        return requete;
    }

    public String createAgence() {
        requete = "CREATE TABLE agence(\n" +
                "            idAgence SERIAL,\n" +
                "            nbEmployes INT,\n" +
                "            idVille INT,\n" +
                "            PRIMARY KEY (idAgence),\n" +
                "    CONSTRAINT fkAgenceVille FOREIGN KEY (idVille) REFERENCES ville(idVille)\n" +
                "            );";
        return requete;
    }

    public String insertAgence() {
        requete = "insert into agence values(default, 5, 1);\n" +
                "insert into agence values(default, 10, 2);\n"+
                "insert into agence values(default, 15, 3);\n"+
                "insert into agence values(default, 20, 4);\n"+
                "insert into agence values(default, 25, 5);\n";
        return requete;
    }

    public String createClient() {
        requete = "CREATE TABLE client(\n" +
                "            idClient SERIAL,\n" +
                "            nomClient VARCHAR(255),\n" +
                "    adresseClient VARCHAR(255),\n" +
                "    codePostalCLient INT,\n" +
                "    idVille INT,\n" +
                "    PRIMARY KEY (idClient),\n" +
                "    CONSTRAINT fkClientVille FOREIGN KEY (idVille) REFERENCES ville(idVille)\n" +
                "            );";
        return requete;
    }

    public String insertClient() {
        requete = "insert into client values(default, 'Armin Arlert', '45 rue des orchidees', 51655, 1);\n" +
                "insert into client values(default, 'Sacha Braus', '18 rue des champs Elysee', 51250, 2);\n" +
                "insert into client values(default, 'Reiner Braun', '37 rue pierre sellier', 37895, 3);\n" +
                "insert into client values(default, 'Annie Leondhart', '4 avenue marechal juin', 96547, 4);\n" +
                "insert into client values(default, 'Pieck Finger', '19 rue audincourt', 25546, 5);\n";
        return requete;
    }

    public String createVehicule() {
        requete = "CREATE TABLE vehicule(\n" +
                "            immatriculation VARCHAR(255),\n" +
                "            dateMiseEnCirculation DATE,\n" +
                "            etat VARCHAR (255),\n" +
                "    nbKilometres INT,\n" +
                "    prixParJourDeLocation FLOAT,\n" +
                "    idMarque INT,\n" +
                "    idModele INT,\n" +
                "    idCategorie INT,\n" +
                "    idType INT,\n" +
                "    idAgence INT,\n" +
                "    PRIMARY KEY (immatriculation),\n" +
                "    CONSTRAINT fkVehiculeMarque FOREIGN KEY (idMarque) REFERENCES marque(idMarque),\n" +
                "    CONSTRAINT fkVehiculeModele FOREIGN KEY (idModele) REFERENCES modele(idModele),\n" +
                "    CONSTRAINT fkVehiculeCategorie FOREIGN KEY (idCategorie) REFERENCES categorie(idCategorie),\n" +
                "    CONSTRAINT fkVehiculeType FOREIGN KEY (idType) REFERENCES type(idType),\n" +
                "    CONSTRAINT fkVehiculeAgence FOREIGN KEY (idAgence) REFERENCES  agence(idAgence)\n" +
                "            );";
        return requete;
    }

    public String insertVehicule() {
        requete = "insert into vehicule values('18-AG-12', '2004-12-25', 'bon état', 150000, '60.5', 1, 1, 1, 1, 1);\n" +
                "insert into vehicule values('24-FD-78', '1995-06-17', 'neuve', 157000, '144.2', 2, 2, 2, 2, 2);\n" +
                "insert into vehicule values('86-GT-98', '2009-08-03', 'occasion', 15000, '230.3', 3, 3, 3, 3, 3);\n" +
                "insert into vehicule values('67-DC-40', '2015-07-07', 'usée', 232000, '587.9', 4, 4, 4, 4, 4);\n" +
                "insert into vehicule values('34-HT-67', '2017-02-14', 'peu servie', 5000, '316.25', 5, 5, 5, 5, 5);\n";
        return requete;
    }

    public String createContrat() {
        requete = "CREATE TABLE contrat(\n" +
                "            idContrat SERIAL,\n" +
                "            dateDeRetrait DATE,\n" +
                "            dateDeRetour DATE,\n" +
                "            kmRetrait INT,\n" +
                "            kmRetour INT,\n" +
                "            idClient INT,\n" +
                "            immatriculation VARCHAR(255),\n" +
                "            idAgenceDeRetour INT,\n" +
                "            PRIMARY KEY (idContrat),\n" +
                "    CONSTRAINT fkContratClient FOREIGN KEY (idClient) REFERENCES client(idClient),\n" +
                "    CONSTRAINT fkContratVehicule FOREIGN KEY (immatriculation) REFERENCES vehicule(immatriculation),\n" +
                "    CONSTRAINT fkContratAgence FOREIGN KEY (idAgenceDeRetour) REFERENCES agence(idAgence)\n" +
                "            );";
        return requete;
    }

    public String insertContrat() {
        requete = "insert into contrat values(default, '2004-12-25', '2009-12-25', 62548, 95748, 3, '18-AG-12', 3);\n" +
                "insert into contrat values(default, '1995-06-17', '2004-06-17', 4586, 14585, 3, '24-FD-78', 5);\n" +
                "insert into contrat values(default, '2009-08-03', '2010-08-03', 58745, 62458, 4, '86-GT-98', 4);\n" +
                "insert into contrat values(default, '2015-07-07', '2017-07-07', 84575, 95877, 5, '67-DC-40', 3);\n" +
                "insert into contrat values(default, '2017-02-14', '2020-02-14', 32579, 49473, 5, '34-HT-67', 5);\n" +
                "insert into contrat values(default, '2017-02-14', '2020-02-14', 32579, 49473, 5, '34-HT-67', 5);\n" +
                "insert into contrat values(default, '2017-02-14', '2020-02-14', 32579, 49473, 5, '34-HT-67', 5);\n" +
                "insert into contrat values(default, '2017-02-14', '2020-02-14', 32579, 49473, 5, '34-HT-67', 5);\n" +
                "insert into contrat values(default, '2017-02-14', '2020-02-14', 32579, 49473, 5, '34-HT-67', 5);\n";
        return requete;
    }

    public String createFacture() {
        requete = "CREATE TABLE facture(\n" +
                "            idFacture SERIAL,\n" +
                "            montant INT,\n" +
                "            idContrat INT,\n" +
                "            PRIMARY KEY (idFacture),\n" +
                "    CONSTRAINT fkFactureContrat FOREIGN KEY (idContrat) REFERENCES contrat(idContrat)\n" +
                "            );";
        return requete;
    }

    public String insertFacture() {
        requete = "insert into facture values(default, 5000, 1);\n" +
                "insert into facture values(default, 7500, 2);\n"+
                "insert into facture values(default, 12000, 3);\n"+
                "insert into facture values(default, 8500, 4);\n"+
                "insert into facture values(default, 3200, 5);\n";
        return requete;
    }
}

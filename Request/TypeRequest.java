package Request;

import Connexion.Connexion;
import Entity.Entity;
import Entity.Type;
import Repository.TypeRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class TypeRequest {

    public TypeRepository typeRepository;

    public static int id;
    public static String libelletype;

    public Connexion c;
    public Connection connexion;

    public TypeRequest() throws SQLException {
        c = new Connexion();
        connexion = c.Connect();
    }

    public void createType(String libelletype) throws SQLException {
        typeRepository = new TypeRepository();
        Type type = new Type(id,libelletype);
        typeRepository.create(type);
        Collection<Entity> types = typeRepository.findAll();
        for (Entity entity : types) {
            Type e = (Type) entity;
            if(e.getLibelle().equals(libelletype)) {
                System.out.println(e.getId() + " | " + e.getLibelle() + " : created");
            } else {
                System.out.println(e.getId() + " | " + e.getLibelle());
            }
        }
    }

    public void updateType(int id, String libelletype) throws SQLException {
        typeRepository = new TypeRepository();
        Type type = (Type) typeRepository.findById(id);
        type.setLibelle(libelletype);
        typeRepository.update(type);
        Collection<Entity> types = typeRepository.findAll();
        for (Entity entity : types) {
            Type e = (Type) entity;
            if(e.getId()==id) {
                System.out.println(e.getId() + " | " + e.getLibelle() + " : updated");
            } else {
                System.out.println(e.getId() + " | " + e.getLibelle());
            }
        }
    }

    public void deleteType(int id) throws SQLException {
        typeRepository = new TypeRepository();
        Type type = (Type) typeRepository.findById(id);
        typeRepository.delete(type);
        findAllType();
        System.out.println(type.getId() + " | " + type.getLibelle() + " : deleted");
    }

    public void findAllType() throws SQLException {
        typeRepository = new TypeRepository();
        Collection<Entity> types = typeRepository.findAll();
        for (Entity entity : types) {
            Type e = (Type) entity;
            System.out.println(e.getId() + " | " + e.getLibelle());
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("***** CREATE TYPE *****");
        libelletype = "Essence";
        new TypeRequest().createType(libelletype);
        System.out.println("");

        System.out.println("***** UPDATE TYPE *****");
        libelletype = "Diesel";
        id = 6;
        new TypeRequest().updateType(id, libelletype);
        System.out.println("");

        System.out.println("***** DELETE TYPE *****");
        new TypeRequest().deleteType(id);
    }
}

package Repository;

import Entity.Entity;

import java.sql.SQLException;
import java.util.Collection;

public interface Interface2 {
    Collection<Entity> findAll() throws SQLException;
    Entity findById(String id) throws SQLException;
    void create(Entity entity) throws SQLException;
    void update(Entity entity) throws SQLException;
    void delete(Entity entity) throws SQLException;
}

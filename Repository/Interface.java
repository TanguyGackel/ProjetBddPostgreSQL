package Repository;

import Entity.Entity;

import java.sql.SQLException;
import java.util.Collection;

public interface Interface {
    Collection<Entity> findAll() throws SQLException;
    Entity findById(int id) throws SQLException;
    void create(Entity entity) throws SQLException;
    void update(Entity entity) throws SQLException;
    void delete(Entity entity) throws SQLException;
}

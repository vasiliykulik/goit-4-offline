package ua.goit.offline4.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

import ua.goit.offline4.entity.Component;

/**
 * ComponentDao.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
public interface ComponentDao {

    Component get(long id)
        throws SQLException;

    Collection<Component> getAll()
        throws SQLException;

    Collection<Component> gtByPrize(BigDecimal prize)
        throws SQLException;

    Component add(String name, BigDecimal prize)
        throws SQLException;

    boolean update(Component component)
        throws SQLException;

    boolean delete(long id)
        throws SQLException;
}

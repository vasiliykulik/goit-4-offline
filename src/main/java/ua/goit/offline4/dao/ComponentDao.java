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

    Component get(long id);

    Collection<Component> getAll();

    Collection<Component> gtByPrize(BigDecimal prize);

    Component add(String name, BigDecimal prize);

    boolean update(Component component);

    boolean delete(long id);
}

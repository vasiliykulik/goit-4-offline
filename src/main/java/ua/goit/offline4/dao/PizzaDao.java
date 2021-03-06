package ua.goit.offline4.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import ua.goit.offline4.entity.PizzaComponents;
import ua.goit.offline4.entity.Pizza;

/**
 * PizzaDao.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
public interface PizzaDao {

    Pizza get(long id)
        throws SQLException;

    Collection<Pizza> getAll()
        throws SQLException;

    Pizza add(String name, BigDecimal prize, List<PizzaComponents> components)
        throws SQLException;

    void update(Pizza pizza)
        throws SQLException;

    void delete(Pizza pizza)
        throws SQLException;

    void addComponent(long id, PizzaComponents component)
        throws SQLException;

    void removeComponent(long id, PizzaComponents component)
        throws SQLException;
}

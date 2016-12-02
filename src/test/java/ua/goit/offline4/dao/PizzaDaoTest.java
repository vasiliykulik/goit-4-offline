package ua.goit.offline4.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

import ua.goit.offline4.dao.jdbc.ComponentDaoJdbc;
import ua.goit.offline4.dao.jdbc.PizzaDaoJbdc;
import ua.goit.offline4.entity.Component;
import ua.goit.offline4.entity.ComponentAmount;
import ua.goit.offline4.entity.Pizza;

/**
 * PizzaDaoTest.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class PizzaDaoTest {

    private static final String URL = "jdbc:postgresql://localhost/pizzeria";

    public static void main(String[] args)
        throws SQLException {
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(2);

        PizzaDao dao = new PizzaDaoJbdc(dataSource);
        System.out.println(dao.getAll());
        System.out.println(dao.get(1));

        ComponentDao cDao = new ComponentDaoJdbc(dataSource);
        List<Component> allComponents = new ArrayList<>(cDao.getAll());
        Collections.shuffle(allComponents);
        List<ComponentAmount> amounts = new ArrayList<>();
        for (Component c : allComponents.subList(0, 3)) {
            ComponentAmount componentAmount = new ComponentAmount();
            componentAmount.setComponent(c);
            componentAmount.setAmount(BigDecimal.valueOf(0.2));
            amounts.add(componentAmount);
        }

        Pizza pizza = dao.add("New one", null, amounts);
        System.out.println(pizza);
        System.out.println(dao.get(pizza.getId()));
    }
}

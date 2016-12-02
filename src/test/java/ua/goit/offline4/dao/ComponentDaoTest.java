package ua.goit.offline4.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import ua.goit.offline4.dao.jdbc.ComponentDaoJdbc;
import ua.goit.offline4.dao.jdbc.ComponentDaoJdbcSC;
import ua.goit.offline4.entity.Component;

/**
 * ComponentDaoTest.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class ComponentDaoTest {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getCause());
        }
    }
    private static final String URL = "jdbc:postgresql://localhost/pizzeria";

    public static void main(String[] args)
        throws SQLException {
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        ComponentDaoJdbc dao = new ComponentDaoJdbcSC(URL, username, password);
        System.out.println(dao.getAll());
        System.out.println(dao.get(1));
        System.out.println(dao.gtByPrize(BigDecimal.valueOf(50)));
        Component component = dao.add("test", BigDecimal.ONE);
        System.out.println(component);
        component = new Component(component.getId(), "new-name", component.getPrize());
        System.out.println(dao.update(component));
        System.out.println(dao.get(component.getId()));
        System.out.println(dao.delete(component.getId()));
        System.out.println(dao.get(component.getId()));

        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("org.postgresql.Driver");
        dataSource1.setUrl(URL);
        dataSource1.setUsername(username);
        dataSource1.setPassword(password);
        dataSource1.setMaxActive(10);
        dataSource1.setMaxIdle(2);
        dao = new ComponentDaoJdbc(dataSource1);
        System.out.println(dao.getAll());
        System.out.println(dao.get(1));
    }
}

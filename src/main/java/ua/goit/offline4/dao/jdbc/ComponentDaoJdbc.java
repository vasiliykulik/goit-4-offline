package ua.goit.offline4.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import ua.goit.offline4.dao.ComponentDao;
import ua.goit.offline4.entity.Component;

/**
 * ComponentDaoJdbc.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
public class ComponentDaoJdbc
    implements ComponentDao {

    private static final String GET_BY_ID = "select id, name, prize from pizzeria.components where id = ?";
    private static final String GET_ALL = "select id, name, prize from pizzeria.components";
    private static final String GET_GT_PRIZE = "select id, name, prize from pizzeria.components where prize > ?";
    private static final String INSERT_NEW = "insert into pizzeria.components(name, prize) values (?, ?) returning id";
    // private static final String GET_LAST_INSERTED = "select LAST_INSERT_ID()";
    private static final String UPDATE_ROW = "update pizzeria.components set name = ?, prize = ? where id = ?";
    private static final String DELETE_ROW = "delete from pizzeria.components where id = ?";
    private static final String DELETE_PC_ROW = "delete from pizzeria.pizza_components where component_id = ?";

    private DataSource dataSource;

    public ComponentDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection()
        throws SQLException {
        return dataSource.getConnection();
    }

    public Component get(long id) {
        try {
            try (Connection connection = getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
                    ps.setLong(1, id);
                    try (ResultSet resultSet = ps.executeQuery()) {
                        if (!resultSet.next()) {
                            return null;
                        }
                        Component component = new Component();
                        component.setId(resultSet.getLong(1));
                        component.setName(resultSet.getString(2));
                        component.setPrize(resultSet.getBigDecimal(3));
                        return component;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Collection<Component> getAll() {
        try {
            try (Connection connection = getConnection()) {
                try (Statement ps = connection.createStatement()) {
                    Collection<Component> out = new ArrayList<>();
                    try (ResultSet resultSet = ps.executeQuery(GET_ALL)) {
                        while (resultSet.next()) {
                            Component component = new Component();
                            component.setId(resultSet.getLong(1));
                            component.setName(resultSet.getString(2));
                            component.setPrize(resultSet.getBigDecimal(3));
                            out.add(component);
                        }
                    }
                    return out;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Collection<Component> gtByPrize(BigDecimal prize) {
        try {
            try (Connection connection = getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(GET_GT_PRIZE)) {
                    ps.setBigDecimal(1, prize);
                    Collection<Component> out = new ArrayList<>();
                    try (ResultSet resultSet = ps.executeQuery()) {
                        while (resultSet.next()) {
                            Component component = new Component();
                            component.setId(resultSet.getLong(1));
                            component.setName(resultSet.getString(2));
                            component.setPrize(resultSet.getBigDecimal(3));
                            out.add(component);
                        }
                    }
                    return out;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Component add(String name, BigDecimal prize) {
        try {
            try (Connection connection = getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW)) {
                    ps.setString(1, name);
                    ps.setBigDecimal(2, prize);
                    if (!ps.execute()) {
                        return null;
                    }
                    try (ResultSet resultSet = ps.getResultSet()) {
                        if (!resultSet.next()) {
                            return null;
                        }
                        Component component = new Component();
                        component.setId(resultSet.getLong(1));
                        component.setName(name);
                        component.setPrize(prize);
                        return component;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Component component) {
        try {
            try (Connection connection = getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROW)) {
                    ps.setString(1, component.getName());
                    ps.setBigDecimal(2, component.getPrize());
                    ps.setLong(3, component.getId());
                    return ps.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            try (Connection connection = getConnection()) {
                try {
                    connection.setAutoCommit(false);
                    try (PreparedStatement ps = connection.prepareStatement(DELETE_PC_ROW)) {
                        ps.setLong(1, id);
                        ps.executeUpdate();
                    }
                    boolean removed;
                    try (PreparedStatement ps = connection.prepareStatement(DELETE_ROW)) {
                        ps.setLong(1, id);
                        removed = ps.executeUpdate() > 0;
                    }
                    connection.commit();
                    return removed;
                } catch (Exception e) {
                    connection.rollback();
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

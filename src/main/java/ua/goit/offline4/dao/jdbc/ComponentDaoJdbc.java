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

    private static final String GET_BY_ID = "select id, name, prize from components where id = ?";
    private static final String GET_ALL = "select id, name, prize from components";
    private static final String GET_GT_PRIZE = "select id, name, prize from components where prize > ?";
    private static final String INSERT_NEW = "insert into components(name, prize) values (?, ?)";
    private static final String GET_LAST_INSERTED = "select LAST_INSERT_ID()";
    private static final String UPDATE_ROW = "update components set name = ?, prize = ? where id = ?";
    private static final String DELETE_ROW = "delete from components where id = ?";

    private DataSource dataSource;

    public ComponentDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection()
        throws SQLException {
        return dataSource.getConnection();
    }

    public Component get(long id)
        throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
                ps.setLong(1, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (!resultSet.next()) {
                        return null;
                    }
                    return new Component(resultSet.getLong(1), resultSet.getString(2), resultSet.getBigDecimal(3));
                }
            }
        }
    }

    public Collection<Component> getAll()
        throws SQLException {
        try (Connection connection = getConnection()) {
            try (Statement ps = connection.createStatement()) {
                Collection<Component> out = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery(GET_ALL)) {
                    while (resultSet.next()) {
                        out.add(new Component(resultSet.getLong(1), resultSet.getString(2), resultSet.getBigDecimal(3)));
                    }
                }
                return out;
            }
        }
    }

    @Override
    public Collection<Component> gtByPrize(BigDecimal prize)
        throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(GET_GT_PRIZE)) {
                ps.setBigDecimal(1, prize);
                Collection<Component> out = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        out.add(new Component(resultSet.getLong(1), resultSet.getString(2), resultSet.getBigDecimal(3)));
                    }
                }
                return out;
            }
        }
    }

    @Override
    public Component add(String name, BigDecimal prize)
        throws SQLException {
        try (Connection connection = getConnection()) {
            int res;
            try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW)) {
                ps.setString(1, name);
                ps.setBigDecimal(2, prize);
                res = ps.executeUpdate();
            }
            if (res > 0) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED)) {
                        if (!resultSet.next()) {
                            return null;
                        }
                        return new Component(resultSet.getLong(1), name, prize);
                    }
                }
            }
            return null;
        }
    }

    @Override
    public boolean update(Component component)
        throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROW)) {
                ps.setString(1, component.getName());
                ps.setBigDecimal(2, component.getPrize());
                ps.setLong(3, component.getId());
                return ps.executeUpdate() > 0;
            }
        }
    }

    @Override
    public boolean delete(long id)
        throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(DELETE_ROW)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        }
    }
}

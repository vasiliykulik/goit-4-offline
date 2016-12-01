package ua.goit.offline4.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ComponentDaoJdbcSC.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class ComponentDaoJdbcSC
    extends ComponentDaoJdbc {

    private String connectionURL;
    private String username;
    private String password;

    public ComponentDaoJdbcSC(String connectionURL, String username, String password) {
        super(null);
        this.connectionURL = connectionURL;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, username, password);
    }
}

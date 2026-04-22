package bank.management.system;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Connn {
    private static final String CONFIG_FILE = "db.properties";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public Connn() {
        try {
            Properties props = loadDatabaseProperties();
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | IOException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Properties loadDatabaseProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                // Fallback to default values if config file not found
                props.setProperty("db.url", "jdbc:mysql://localhost:3306/bankSystem");
                props.setProperty("db.username", "root");
                props.setProperty("db.password", "AyushVish");
                System.out.println("Warning: " + CONFIG_FILE + " not found. Using default database configuration.");
            } else {
                props.load(input);
            }
        }
        return props;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection is not established.");
        }
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection is not established.");
        }
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int executeUpdate(String sql) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection is not established.");
        }
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }

    public void close() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }

    // Auto-closeable implementation for try-with-resources
    public void closeResources() {
        close();
    }
}

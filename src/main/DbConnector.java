package main;

import java.sql.*;
import java.util.Properties;

public class DbConnector {
    Connection connection;
    Statement statement;
    static String url, database, username, password, port, driver, hostName;

    public DbConnector(Properties properties, String pass) {

        database = properties.getProperty("Database");
        username = properties.getProperty("User_Name");
        password = pass;
        hostName = properties.getProperty("Host_Name");
        port = properties.getProperty("Port");
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://" + hostName + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC";
    }

    public boolean open() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection(url, username, password);
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet executeQuery(String s) throws SQLException {
        return this.statement.executeQuery(s);
    }

    public int executeUpdate(String s) throws SQLException {
        return statement.executeUpdate(s);
    }
}

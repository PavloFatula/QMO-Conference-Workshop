package pages.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseConnection.class);

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        String username = "lora";
        String password = "Lora_111";
        String URL = "jdbc:mysql://192.168.1.37:3306/lv298";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        connection = DriverManager.getConnection(URL, username, password);
        if (connection != null) {
            logger.info("Success connection");
        } else {
            logger.error("Connection error");
            System.exit(1);
        }

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM temp");

        int columnCount = results.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(results.getMetaData().getColumnName(i) + "\t");
        }
        System.out.println("");

        while (results.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(results.getString(i) + "\t");
            }
            System.out.println("");
        }

        if (results != null) {
            results.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

    }
}

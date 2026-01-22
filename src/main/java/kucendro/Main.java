package kucendro;

import java.sql.Connection;

import static kucendro.db.connector.closeConnection;
import static kucendro.db.connector.getConnection;

public class Main {

    public static void main(String[] args) {

        try {
            Connection connection = getConnection();
            var resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
            
            resultSet.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
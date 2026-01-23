package kucendro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import kucendro.logs.errorLogger;

import static kucendro.db.connector.closeConnection;
import static kucendro.db.connector.getConnection;

import static kucendro.db.autoDeploy.deployContainer;
import static kucendro.db.autoDeploy.shutdownContainer;

public class Main {

    public static void main(String[] args) throws Exception, InterruptedException {

        errorLogger logger = errorLogger.getInstance();
        // TODO: Deploy the container after GUI init on another thread
        deployContainer();

        try {
            Connection connection = getConnection();
            ResultSet resultSet = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString(1) + " NAME: " + resultSet.getString(2) + " EMAIL: "
                        + resultSet.getString(3));
            }

            String name = new String(UUID.randomUUID().toString().substring(0, 16));
            String email = name + "@example.com";

            PreparedStatement query = connection
                    .prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)");
            query.setString(1, name);
            query.setString(2, email);
            query.executeUpdate();

            resultSet.close();
            closeConnection();
        } catch (Exception e) {
            logger.log("Error during database operation", e);
        }

        // TODO: Shutdown the container on GUI exit
        // shutdownContainer();
    }
}
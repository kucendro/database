package kucendro;

import java.sql.Connection;

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
            var resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }

            // connection.prepareStatement(
            // "INSERT INTO users (id, name, email) VALUES ('epsteinid', 'Epstein',
            // 'epstein@example.com')")
            // .executeUpdate();

            resultSet.close();
            closeConnection();
        } catch (Exception e) {
            logger.log("Error during database operation", e);
        }

        // TODO: Shutdown the container on GUI exit
        // shutdownContainer();
    }
}
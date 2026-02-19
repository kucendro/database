package kucendro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import static kucendro.db.autoDeploy.deployContainer;
import static kucendro.db.autoDeploy.shutdownContainer;
import static kucendro.db.connector.closeConnection;
import static kucendro.db.connector.getConnection;
import kucendro.logs.errorLogger;

/* tips. preparedStatement.RETURN_GENERATED_KEYS
        connection.setAutoCommit(false) zabrání autocommitu a můžu rollbacknout
        používat rollbace, mohu udělat savePointy abych pak nemusel pouštět celý dotaz znovu
 */
public class Main {

    public static void main(String[] args) throws Exception, InterruptedException {

        errorLogger logger = errorLogger.getInstance();
        deployContainer();
        // here bude GUI instance

        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);

            try {
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
                        .prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                query.setString(1, name);
                query.setString(2, email);
                query.executeUpdate();

                connection.commit();
                closeConnection();

            } catch (Exception e) {

                connection.setSavepoint("insertion_error");
                connection.rollback();
                closeConnection();
                logger.log("Error during database operation: " + e.getMessage());
            }

        } catch (Exception e) {

            logger.log("Error during database operation: " + e.getMessage());
        }

        // TODO: Shutdown the container on GUI exit
        shutdownContainer();
    }
}
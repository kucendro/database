package kucendro;

import java.sql.Connection;
import static kucendro.db.connector.closeConnection;
import static kucendro.db.connector.getConnection;

import static kucendro.db.autoDeploy.deploy;
import static kucendro.db.autoDeploy.undeploy;

public class Main {

    public static void main(String[] args) throws Exception, InterruptedException {
        deploy();

        try {
            Connection connection = getConnection();
            var resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }

            // connection.prepareStatement("INSERT INTO users (id, name, email) VALUES
            // ('epsteinid', 'Epstein', 'epstein@example.com')")
            // .executeUpdate();

            resultSet.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        undeploy();
    }
}
package kucendro.db;

import static kucendro.db.connector.pingDatabase;
import static kucendro.global.consoleOutput.printHeadline;

public class autoDeploy {

    private static ProcessBuilder processBuilder = new ProcessBuilder();

    public static void deploy() throws Exception, InterruptedException {

        if (pingDatabase() == true) {
            printHeadline("Database container running.");
            return;
        } else {
            try {
                printHeadline("Starting database container...");
                processBuilder.inheritIO().command("docker", "compose", "up", "-d").start().waitFor();
                printHeadline("Seeding the database... cca 5s");
                Thread.sleep(5000);
            } catch (Exception e) {
                printHeadline("Container not found!");
            }
        }
    }

    public static void undeploy() throws Exception, InterruptedException {
        if (pingDatabase() == false) {
            printHeadline("Database container not running.");
            return;
        } else {
            try {
                printHeadline("Stopping database container...");
                processBuilder.inheritIO().command("docker", "compose", "down").start().waitFor();
                printHeadline("Database container stopped.");
            } catch (Exception e) {
                printHeadline("Container not found!");
            }
        }
    }

}

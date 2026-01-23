package kucendro.db;

import static kucendro.db.connector.pingDatabase;
import static kucendro.global.consoleOutput.printHeadline;
import static kucendro.global.consoleOutput.printLine;
import static kucendro.global.consoleOutput.printLiveOutput;

public class autoDeploy implements kucendro.global.interfaces.containerManagement {

    public static void deployContainer() throws Exception, InterruptedException {

        if (pingDatabase() == true) {
            printHeadline("Container alive.");
            return;

        } else {

            try {
                printHeadline("Starting container...");
                processBuilder.inheritIO().command("docker", "compose", "up", "-d").start().waitFor(); // ? Command
                printLine();

                long startTime = System.currentTimeMillis();

                while (pingDatabase() == false) {
                    float elapsedTime = System.currentTimeMillis() - startTime;
                    Thread.sleep(100);
                    printLiveOutput("\u001B[31mTesting connection...\u001B[0m {}s",
                            String.valueOf(elapsedTime / 1000));
                }
                System.out.println();

                printLine();

            } catch (Exception e) {
                printHeadline("Container not found!");
                logger.log("Container not found!", e);
            }
        }
    }

    public static void shutdownContainer() throws Exception, InterruptedException {
        if (pingDatabase() == false) {
            printHeadline("Container not running.");
            return;
        } else {
            try {
                printHeadline("Stopping  container...");
                processBuilder.inheritIO().command("docker", "compose", "down").start().waitFor(); // ? Command
                printHeadline("Container stopped.");
            } catch (Exception e) {
                printHeadline("Container not found!");
                logger.log("Container not found!", e);
            }
        }
    }

    public static void destroyContainer() throws Exception, InterruptedException {

        if (pingDatabase() == false) {
            printHeadline("Container not running.");
            return;
        } else {
            try {
                printHeadline("Destroying container...");
                processBuilder.inheritIO().command("docker", "compose", "down", "-v").start().waitFor(); // ? Command
                printHeadline("Container destroyed.");
            } catch (Exception e) {
                printHeadline("Container not found!");
                logger.log("Container not found!", e);
            }
        }
    }

}

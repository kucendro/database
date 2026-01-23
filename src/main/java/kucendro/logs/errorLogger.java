package kucendro.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class errorLogger {

    private static errorLogger instance;

    private errorLogger() {
        File logFile = new File(System.getenv("ERROR_LOG_PATH"));
        logFile.getParentFile().mkdirs();
    }

    public static errorLogger getInstance() {
        if (instance == null) {
            instance = new errorLogger();
        }
        return instance;
    }

    public void log(String message, Exception e) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getenv("ERROR_LOG_PATH"), true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            writer.write(formatter.format(new Date()) + "\n");
            writer.write(message + "\n");

            if (e != null) {
                writer.write(e.getClass().getName() + "\n");
                writer.write(e.getMessage() + "\n");
                e.printStackTrace(new PrintWriter(writer));
            }
            writer.write("\n");

        } catch (Exception ex) {
            System.err.println("Failed to log error " + ex.getMessage());
        }
    }

    public void log(String message) {
        log(message, null);
    }

}

package kucendro.logs;

import java.io.File;

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

    // TODO: Implement this function
    public void log(String message, Exception e) {

    }

    public void log(String message) {
        log(message, null);
    }

}

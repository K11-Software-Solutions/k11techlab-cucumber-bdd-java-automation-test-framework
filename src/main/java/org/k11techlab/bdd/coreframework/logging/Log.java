package org.k11techlab.bdd.coreframework.logging;

import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Reporter;

public class Log {

    public static final Logger LOGGER = Logger.getLogger("logging");
    private static FileAppender appender;
    private static ConsoleAppender consoleAppender;
    private static PatternLayout layout = new PatternLayout("%d{dd MMM yyyy HH:mm:ss} %5p %c{1} - %m%n");

    static {
        try
        {
            consoleAppender = new ConsoleAppender(layout, "System.out");
            appender= new FileAppender(layout,"LogFile.txt",true);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }        // Initialize the LOGGER
    }



    /**
     * method to display information in logs
     * @param message -message to be displayed
     */
    public static void info(String message){
        consoleAppender.setName("Console");
        LOGGER.addAppender(consoleAppender);
        LOGGER.addAppender(appender);
        LOGGER.setLevel((Level) Level.INFO);
        LOGGER.info(message);
    }

    public static void info(String message, boolean logToReport) {
        LOGGER.info(message);
        if (logToReport) {
            Reporter.log(message);
        }
    }
    /**
     * method to display errors in log.
     * @param className name of class in which error occurred.
     * @param methodName name of method in which error occurred.
     * @param exception stack trace of exception
     */
    public static void logError (String className,String methodName,String exception)
    {
        LOGGER.addAppender(appender);
        LOGGER.setLevel((Level) Level.INFO);
        LOGGER.info("ClassName :"+className);
        LOGGER.info("MethodName :"+methodName );
        LOGGER.info("Exception :" +exception);
        LOGGER.info("-----------------------------------------------------------------------------------");
    }


    /**
     * Log an error message.
     *
     * @param message the error message to log
    
     */
    public static void error(String message) {
        LOGGER.error(message);
    }

    /**
     * Log an error message with an exception.
     *
     * @param message the error message to log
     * @param e       the exception to log with the error message
    
     */
    public static void error(String message, Exception e) {
        LOGGER.error(message, e);
    }

    /**
     * Log a debug message.
     *
     * @param message the debug message to log
    
     */
    public static void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * Log a warning message.
     *
     * @param message the warning message to log
    
     */
    public static void warn(String message) {
        LOGGER.warn(message);
    }


}
package org.k11techlab.bdd.coreframework;

import org.k11techlab.bdd.coreframework.logging.Log;

import java.time.Duration;

/**
 * General Utilities.
 * <p>
 * Arbitrary waits are discouraged, but in some cases they are needed.
 */
public class WaitUtil {

    public static void waitForDuration(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Log.error("Error while applying wait...", e);
        }
        Log.info(duration.toSeconds() + " seconds of wait completed...");
    }

    public static void waitMillis(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Don't care.
        }
    }

    /**
     * Wait without throwing the interrupt
     * @param seconds 
     */
    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // Don't care.
        }
    }

    public static void waitOneSecond() {
        waitSeconds(1);
    }

    public static void waitFiveSeconds() {
        waitSeconds(5);
    }

}

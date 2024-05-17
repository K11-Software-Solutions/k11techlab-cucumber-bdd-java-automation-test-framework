package org.k11techlab.bdd.webuitestframework.commonUtil;

/**
 * General Utilities.
 * <p>
 * Arbitrary waits are discouraged, but in some cases they are needed.
 */
public class WaitUtil {

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

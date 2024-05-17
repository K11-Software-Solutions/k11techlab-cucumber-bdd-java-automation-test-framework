package org.k11techlab.bdd.webuitestframework.enums;

/**
 * Enum class for available browsers.
 */
public enum Browsers {

    CHROME,

    FIREFOX,

    IE,

    INTERNETEXPLORER;

    /**
     * Gets the browser.
     *
     * @param browser the browser value to get
     * @return the browser enum value
     * @throws IllegalArgumentException Throws exception if browser is not available
     */
    public static Browsers browserForName(String browser) {
        for (Browsers b : values()) {
            if (b.toString().equalsIgnoreCase(browser)) {
                return b;
            }
        }
        throw browserNotFound(browser);
    }

    /**
     * Throws new exception if browser is not found.
     *
     * @param outcome the outcome.
     * @return Return new IllegalArgumentException
     */
    private static IllegalArgumentException browserNotFound(String outcome) {
        return new IllegalArgumentException(("Invalid browser [" + outcome + "]"));
    }
}

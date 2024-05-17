package org.k11techlab.bdd.webuitestframework.exceptions;

import org.testng.SkipException;

/**
 * To indicate automation error. Not an AUT failure so that the test case can be
 * in skip state instead of fail
 */
public class AutomationError extends SkipException {

    private static final long serialVersionUID = 2820870863950734300L;

    /**
     * Constructs a new runtime exception.
     *
     * @param msg The specified detail message
     */
    public AutomationError(String msg) {
        super(msg);
    }

    /**
     * Constructs a new runtime exception.
     *
     * @param msg The specified detail message
     * @param cause The cause
     */
    public AutomationError(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new runtime exception.
     *
     * @param cause The cause
     */
    public AutomationError(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public final boolean isSkip() {
        return true;
    }
}

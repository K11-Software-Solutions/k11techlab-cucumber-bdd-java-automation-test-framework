package org.k11techlab.bdd.apitestframework;

public class Validator {


    public static void validateStatusCode(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected status code " + expected + " but got " + actual);
        }
    }


    public static void validateResponseBodyContains(String expectedContent, String actualBody) {
        if (!actualBody.contains(expectedContent)) {
            throw new AssertionError("Expected body to contain: " + expectedContent);
        }
    }
}

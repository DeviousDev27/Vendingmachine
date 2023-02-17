package com.techelevator.util;

/**
 * SalesReportException extends RuntimeException and reports SalesReport-specific exceptions.
 */

public class SalesReportException extends RuntimeException {

    public SalesReportException(String message) {
        super(message);
    }
}

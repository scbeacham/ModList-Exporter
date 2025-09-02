package com.yourname.modlistexporter.config;

/**
 * Logger interface for configuration operations.
 * This allows the common module to log without depending on specific logging frameworks.
 */
public interface ConfigLogger {
    
    /**
     * Logs an info message.
     *
     * @param message The message to log
     * @param args Optional arguments for message formatting
     */
    void info(String message, Object... args);
    
    /**
     * Logs an error message.
     *
     * @param message The message to log
     * @param throwable The throwable to log
     */
    void error(String message, Throwable throwable);
}

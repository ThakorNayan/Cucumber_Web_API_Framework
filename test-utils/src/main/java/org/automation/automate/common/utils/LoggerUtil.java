package org.automation.automate.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();

    private LoggerUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Logger getLogger(Class<?> clazz) {
        if (getLoggerTL() == null) {
            setLoggerTL(LoggerFactory.getLogger(clazz));
        }
        return getLoggerTL();
    }

    private static Logger getLoggerTL() {
        return loggerThreadLocal.get();
    }

    private static void setLoggerTL(Logger logger) {
        loggerThreadLocal.set(logger);
    }

    private static void removeLoggerTL() {
        loggerThreadLocal.remove();
    }

    public static void logInfo(String message) {
        getLoggerTL().info(message);
    }

    public static void logError(String message) {
        getLoggerTL().error(message);
    }

    public static void logError(String message, Throwable throwable) {
        getLoggerTL().error(message, throwable);
    }

    public static void logDebug(String message) {
        getLoggerTL().debug(message);
    }

    public static void logWarn(String message) {
        getLoggerTL().warn(message);
    }
}

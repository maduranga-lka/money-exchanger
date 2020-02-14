package com.genting.moneyexchanger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
    public static final String EXCHANGER_APPLICATION = "Exchanger application";
    public static final String TRACE = "TRACE";
    public static final String DEBUG = "DEBUG";
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROR";
    public static final String LOGS_URI = "/logs";
    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping(LOGS_URI)
    public String index() {
        logger.trace(TRACE);
        logger.debug(DEBUG);
        logger.info(INFO);
        logger.warn(WARN);
        logger.error(ERROR);
        return EXCHANGER_APPLICATION;
    }
}
